package ml.mlazic.netqueue.old;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class QueueLite {

/*
QueueLite acts as Bridge. It is server and client
open (the connection)
begin (the session)
attach (the link)
transfer
flow
disposition
detach (the link)
end (the session)
close (the connection
 */

    private final static String QUEUE_NAME = "hello";
    ConnectionFactory factory;
    Connection connection;

    public QueueLite(String hostname, int port, String username, String password) {
        factory = new ConnectionFactory();
        factory.setHost(hostname);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(username);
    }

    public QueueLite() {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
    }

    private QueueLite openConnection() {
        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return this;
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String message) {
        openConnection();
        Sender sender = new Sender(connection);
        sender.send(message);
    }

    public void send(String message, String type) {
        openConnection();
        Sender sender = new Sender(connection);
        if (type == "fanout")
            sender.sendFanout(message);
        else
            sender.send(message);
    }

    public void receive() {
        openConnection();
        Receiver receiver = new Receiver(connection);
        receiver.recieve();
    }

    public void receive(String type) {
        openConnection();
        Receiver receiver = new Receiver(connection);
        if (type == "fanout")
            receiver.recieveFanout();
        else
            receiver.recieve();
    }

    public void close() {
        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
