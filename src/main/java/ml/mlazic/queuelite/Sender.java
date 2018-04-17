package ml.mlazic.queuelite;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class Sender {
    private Channel     channel;
    private String      queueName;


    public Sender(Connection connection, String queueName) {
        try {
            this.channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.queueName = queueName;
    }

    public void send(String message) {
        try {
            channel.queueDeclare(queueName, false, false, false, null);
            channel.basicPublish("", queueName, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
