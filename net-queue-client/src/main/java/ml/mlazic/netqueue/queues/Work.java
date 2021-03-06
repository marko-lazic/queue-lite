package ml.mlazic.netqueue.queues;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Work.java
 * Purpose: The main idea behind Work Queues (aka: Task Queues) is to avoid doing a
 * resource-intensive messageHandler immediately and having to wait for it to complete. Instead
 * we schedule the messageHandler to be done later. We encapsulate a messageHandler as a message and send it
 * to a queue. A worker process running in the background will pop the tasks and eventually
 * execute the job.
 *
 * @author Marko Lazic
 * @version 1.0 4/21/18
 */
public class Work extends BasicQueue {
    /** The queue name. */
    private String queueName;

    /**
     * Postman constructor
     * @param connection the socket connection
     * @param queueName the name of the queue.
     */
    public Work(Connection connection, Message messageHandler, String queueName) {
        super(connection, messageHandler);
        this.queueName = queueName;
    }

    /**
     * @param in an object to be sent.
     * @return if successful returns sent object else if exception occurs returns -1
     */
    public Object send(Object in) {
        try {
            channel.queueDeclare(queueName, true, false, false, null);

            channel.basicPublish("", queueName, null, in.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return in;
    }

    /** @return consumerTag generated by the server else if exception occurs returns -1 */
    public Object receive() {
        try {
            channel.queueDeclare(queueName, true, false, false, null);
            channel.basicQos(1);
            final Consumer consumer = new DefaultConsumer(channel) {

                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");

                    try {
                        messageHandler.process(message);
                    } finally {
                        channel.basicAck(envelope.getDeliveryTag(), false);
                    }
                }
            };
            boolean autoAck = false;
            return channel.basicConsume(queueName, autoAck, consumer);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
