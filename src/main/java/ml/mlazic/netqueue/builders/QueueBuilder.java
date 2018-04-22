package ml.mlazic.netqueue.builders;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ml.mlazic.netqueue.queues.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * QueueBuilder.java
 * Purpose: Builds the connection, type of NetQueue implementation,
 * queue name and exchange names.
 *
 * @author Marko Lazic
 * @version 1.0 4/21/18
 */
public class QueueBuilder {
    /** Factory from RabbitMQ wrapped in this builder. */
    private ConnectionFactory factory = new ConnectionFactory();

    /** Class type selector for building appropriate class. */
    private QueueType type;

    /** AMQP defines a self-describing encoding scheme allowing interoperable
     * representation of a wide range of commonly used types. It also allows
     * typed data to be annotated with additional meaning. */
    private String queueName, exchangeName;

    /**  A connection between two peers can have multiple channels multiplexed
     * over it, each logically independent. */
    private Connection connection;

    /** Handle for procesing messaes. */
    private Message messageHandler;

    /** Init's default values. guest:guest localhost:5672 postman with queue hello
     * and exhange world. */
    private void builderDefaults() {
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        type = QueueType.POSTMAN;
        queueName = "hello";
        exchangeName = "world";
    }

    /** Default constructor. */
    public QueueBuilder() {
        builderDefaults();
    }

    /**
     * @param type sets default type.
     * @return this QueueBuilder object.
     */
    public QueueBuilder setType(QueueType type) {
        this.type = type;
        return this;
    }

    /**
     * @param queueName the default queue name.
     * @return this QueueBuilder object.
     */
    public QueueBuilder setQueueName(String queueName) {
        this.queueName = queueName;
        return this;
    }

    /**
     * @param exchaneName the default exchange name.
     * @return this QueueBuilder object.
     */
    public QueueBuilder setExchaneName(String exchaneName) {
        this.exchangeName = exchaneName;
        return this;
    }

    /**
     * @param hostname the default host to use for connections
     * @return this QueueBuilder object.
     */
    public QueueBuilder setHost(String hostname) {
        factory.setHost(hostname);
        return this;
    }

    /**
     * Set the target port.
     * @param port the default port to use for connections.
     * @return this QueueBuilder object.
     */
    public QueueBuilder setPort(int port) {
        factory.setPort(port);
        return this;
    }

    /**
     * Set the user name.
     * @param username the AMQP user name to use when connecting to the broker.
     * @return this QueueBuilder object.
     */
    public QueueBuilder setUsername(String username) {
        factory.setUsername(username);
        return this;
    }

    /**
     * @param password the password to use when connecting to the broker.
     * @return this QueueBuilder object.
     */
    public QueueBuilder setPassword(String password) {
        factory.setPassword(password);
        return this;
    }

    /**
     * @param messageHandler the default messageHandler for messageHandler queue.
     * @return this QueueBuilder object.
     */
    public QueueBuilder handeMessage(Message messageHandler) {
        this.messageHandler = messageHandler;
        return this;
    }

    /**
     * Builds Queue with appropriate queue type, name or exchange name.
     * @return concrete queue.
     */
    public Queue buildQueue() {
        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        switch (type) {
            case POSTMAN:
                return new Postman(connection, messageHandler, queueName);
            case FANOUT:
                return new Fanout(connection, messageHandler, exchangeName);
            case WORK:
                return new Work(connection, messageHandler, queueName);
                default:
                    return new Postman(connection, messageHandler, queueName);
        }
    }
}
