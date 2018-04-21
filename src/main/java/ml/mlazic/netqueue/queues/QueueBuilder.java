package ml.mlazic.netqueue.queues;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class QueueBuilder {
    private ConnectionFactory factory = new ConnectionFactory();
    private QueueType type;
    private String queueName, exchangeName;
    private Connection connection;

    private void builderDefaults() {
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        type = QueueType.POSTMAN;
        queueName = "hello";
        exchangeName = "world";
    }

    public QueueBuilder() {
        builderDefaults();
    }

    public QueueBuilder setType(QueueType type) {
        this.type = type;
        return this;
    }

    public QueueBuilder setQueueName(String queueName) {
        this.queueName = queueName;
        return this;
    }

    public QueueBuilder setExchaneName(String exchaneName) {
        this.exchangeName = exchaneName;
        return this;
    }

    public QueueBuilder setHost(String hostname) {
        factory.setHost(hostname);
        return this;
    }

    public QueueBuilder setPort(int port) {
        factory.setPort(port);
        return this;
    }

    public QueueBuilder setUsername(String username) {
        factory.setUsername(username);
        return this;
    }

    public QueueBuilder setPassword(String password) {
        factory.setPassword(password);
        return this;
    }

    public QueueType getType() {
        return type;
    }

    public QueueImpl buildQueue() {
        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        switch (type) {
            case POSTMAN:
                return new Postman(connection, queueName);
            case FANOUT:
                return new Fanout(connection, exchangeName);
            case WORK:
                return new Work(connection, queueName);
                default:
                    return new Postman(connection, queueName);
        }
    }
}
