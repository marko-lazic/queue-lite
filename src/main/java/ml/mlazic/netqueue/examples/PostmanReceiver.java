package ml.mlazic.netqueue.examples;

import ml.mlazic.netqueue.queues.NetQueue;
import ml.mlazic.netqueue.queues.QueueBuilder;
import ml.mlazic.netqueue.queues.QueueType;

public class PostmanReceiver {

    public static void main(String[] args) {

        QueueBuilder builder = new QueueBuilder();
        builder.setHost("localhost")
                .setPort(5672)
                .setUsername("guest")
                .setPassword("guest")
                .setQueueName("postman.queue")
                .setType(QueueType.POSTMAN);

        NetQueue queue = new NetQueue(builder);

        queue.receive();

        queue.close();

    }

}
