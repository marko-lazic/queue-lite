package ml.mlazic.netqueue.examples;

import ml.mlazic.netqueue.queues.Queue;
import ml.mlazic.netqueue.builders.QueueBuilder;
import ml.mlazic.netqueue.queues.QueueType;

public class PostmanSender {

    public static void main(String[] args) {

        QueueBuilder builder = new QueueBuilder();
        builder.setHost("localhost")
                .setPort(5672)
                .setUsername("guest")
                .setPassword("guest")
                .setQueueName("postman.queue")
                .setType(QueueType.POSTMAN);

        Queue queue = new Queue(builder);

        queue.send(new String("hello world"));

        queue.close();
    }

}
