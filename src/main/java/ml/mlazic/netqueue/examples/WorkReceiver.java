package ml.mlazic.netqueue.examples;

import ml.mlazic.netqueue.queues.NetQueue;
import ml.mlazic.netqueue.queues.QueueBuilder;
import ml.mlazic.netqueue.queues.QueueType;

public class WorkReceiver {

    public static void main(String[] args) {

        QueueBuilder builder = new QueueBuilder();
        builder
                .setQueueName("hello")
                .setType(QueueType.WORK);

        NetQueue queue = new NetQueue(builder);

        queue.receive();

        //queue.close();

    }

}
