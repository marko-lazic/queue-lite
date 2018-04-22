package ml.mlazic.netqueue.queues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * BasicQueue.java
 * Purpose: To take out some of the ground implementation from
 * the child classes.
 * @see ml.mlazic.netqueue.queues.Postman
 * @see ml.mlazic.netqueue.queues.Fanout
 * @see ml.mlazic.netqueue.queues.Work
 *
 * @author Marko Lazic
 * @version 1.0 4/21/18
 */
abstract class BasicQueue implements Queue {
    /**
     * The connection abstracts the socket connection, and takes care of
     * protocol version negotiation and authentication and so on for us. */
    protected final Connection connection;

    /** This is where the most of the API for getting things done resides.
     * A channel is a bidirectional, sequential conversation between two peers
     * that is initiated with a begin frame and terminated with an end frame. */
    protected Channel channel;

    /** handles the received message.  */
    protected Message messageHandler;

    /** Initializes the connection. */
    protected BasicQueue(Connection connection, Message messageHandler) {
        this.connection = connection;
        open();
        this.messageHandler = messageHandler;
    }

    /** Tries to open connection on given properties and bein the session. */
    public void open() {
        try {
            this.channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Tries to close the session and the connection */
    public void close() {
        try {
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    /** @return RabbitMQ connection. */
    @Override
    public Connection getConnection() {
        return this.connection;
    }
}
