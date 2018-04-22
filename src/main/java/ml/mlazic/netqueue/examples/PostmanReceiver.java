package ml.mlazic.netqueue.examples;

import ml.mlazic.netqueue.NetQueue;
import ml.mlazic.netqueue.builders.QueueBuilder;
import ml.mlazic.netqueue.queues.QueueType;

/**
 * PostmanReceiver.java
 * Purpose: Example of postman receiver
 *
 * @author Marko Lazic
 * @version 1.0 4/21/18
 */
public class PostmanReceiver {

    public static void main(String[] args) {
        QueueBuilder builder = new QueueBuilder();
        builder.setHost("localhost")
                .setPort(5672)
                .setUsername("guest")
                .setPassword("guest")
                .setQueueName("postman.queue")
                .setType(QueueType.POSTMAN)
                .handeMessage((message) -> { System.out.println(" [x] Received '" + message + "'"); });

        NetQueue queue = new NetQueue(builder);

        queue.receive();

        queue.close();
    }

}
