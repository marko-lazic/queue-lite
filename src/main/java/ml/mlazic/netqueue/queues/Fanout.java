package ml.mlazic.netqueue.queues;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Fanout extends AbstractQueue implements QueueImpl {
    private String exchangeName;

    protected Fanout(Connection connection, String exchangeName) {
        super(connection);
        this.exchangeName = exchangeName;
    }

    public Object send(Object in) {
        try {
            channel.exchangeDeclare(exchangeName, "fanout");
            channel.basicPublish(exchangeName, "", null, in.toString().getBytes());
            return in;

        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public Object receive() {

        try {
            channel.exchangeDeclare(exchangeName, "fanout");
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, exchangeName, "");

            Consumer consumer = new DefaultConsumer((channel)) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body) throws IOException {
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
