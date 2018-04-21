package ml.mlazic.netqueue.queues;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Postman implements QueueImpl {
    private Channel channel;
    private String  queueName;
    private final Connection connection;

    public Postman(Connection connection, String queueName) {
        this.queueName = queueName;
        this.connection = connection;
        open();
    }

    public Object send(Object in) {
        try {
            channel.queueDeclare(queueName, false, false, false, null);
            channel.basicPublish("", queueName, null, in.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        //return " [x] Sent '" + in.toString() + "'";
        return in;
    }

    public Object receive() {
        try {
            channel.queueDeclare(queueName, false, false, false, null);

            //System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [x] Received '" + message + "'");

                }
            };
            return channel.basicConsume(queueName, true, consumer);

        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
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
