package ml.mlazic.queuelite;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Receiver {
    private Channel     channel;
    private String      queueName;

    public Receiver(Connection connection, String queueName) {
        try {
            this.channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.queueName = queueName;
    }

    public void recieve() {
        try {
            channel.queueDeclare(queueName, false, false, false, null);

            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [x] Received '" + message + "'");
                }
            };
            channel.basicConsume(queueName, true, consumer);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
