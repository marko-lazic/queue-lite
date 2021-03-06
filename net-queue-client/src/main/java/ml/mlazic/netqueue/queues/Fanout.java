package ml.mlazic.netqueue.queues;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Fanout.java
 * Purpose: Send messages to an exchange. Delivers a message to
 * multiple consumers. Messages are going to be broadcast to all
 * the receivers. This pattern is known as "publish/subscribe".
 *
 * @author Marko Lazic
 * @version 1.0 4/21/18
 */
public class Fanout extends BasicQueue {
    /** The name of the exchange. */
    private String exchangeName;

    /**
     * Fanout constructor
     * @param connection the socket connection
     * @param exchangeName the name of the exchange.
     */
    public Fanout(Connection connection, Message messageHandler, String exchangeName) {
        super(connection, messageHandler);
        this.exchangeName = exchangeName;
    }

    /**
     * @param in an object to be sent.
     * @return if successful returns sent object else if exception occurs returns -1
     */
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

    /** @return consumerTag generated by the server else if exception occurs returns -1 */
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
