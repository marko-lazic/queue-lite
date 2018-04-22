package ml.mlazic.netqueue.examples;

import ml.mlazic.netqueue.NetQueue;
import ml.mlazic.netqueue.builders.QueueBuilder;
import ml.mlazic.netqueue.queues.QueueType;

/**
 * PostmanSender.java
 * Purpose: Example of postman sender
 *
 * @author Marko Lazic
 * @version 1.0 4/21/18
 */
public class PostmanSender {

    public static void main(String[] args) {
        QueueBuilder builder = new QueueBuilder();
        builder.setHost("localhost")
                .setPort(5672)
                .setUsername("guest")
                .setPassword("guest")
                .setQueueName("postman.queue")
                .setType(QueueType.POSTMAN);

        NetQueue queue = new NetQueue(builder);

        queue.send(new String("Hello Postman 1!"));
        queue.send(new String("Hello Postman 2!"));
        queue.send(new String("Hello Postman 3!"));

        queue.close();
    }

}
