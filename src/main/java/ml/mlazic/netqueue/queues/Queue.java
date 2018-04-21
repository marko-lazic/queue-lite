package ml.mlazic.netqueue.queues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

abstract class Queue {
    protected Channel channel;
    protected final Connection connection;

    protected Queue(Connection connection) {
        this.connection = connection;
        open();
    }

    public void open() {
        try {
            this.channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
}
