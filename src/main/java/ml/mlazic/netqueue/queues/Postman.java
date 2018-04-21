package ml.mlazic.netqueue.queues;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Postman extends AbstractQueue implements QueueImpl {
    private String queueName;

    public Postman(Connection connection, String queueName) {
        super(connection);
        this.queueName = queueName;
    }

    public Object send(Object in) {
        try {
            channel.queueDeclare(queueName, false, false, false, null);
            channel.basicPublish("", queueName, null, in.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return in;
    }

    public Object receive() {
        try {
            channel.queueDeclare(queueName, false, false, false, null);

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


}
