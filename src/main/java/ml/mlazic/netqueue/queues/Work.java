package ml.mlazic.netqueue.queues;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Work extends Queue implements QueueImpl {
    private String queueName;

    protected Work(Connection connection, String queueName) {
        super(connection);
        this.queueName = queueName;
    }

    public Object send(Object in) {
        try {
            channel.basicPublish("", queueName, null, in.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return in;
    }

    public Object receive() {
        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");

                System.out.println(" [x] Received '" + message + "'");
                try {
                    doWork(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(" [x] Done");
                }
            }
        };
        boolean autoAck = true; // acknowledgment is covered below
        try {
            return channel.basicConsume(queueName, autoAck, consumer);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return -1;
    }


    private static void doWork(String tast) throws InterruptedException {
        for (char c : tast.toCharArray())
            Thread.sleep(1000);
    }
}
