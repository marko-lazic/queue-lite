package ml.mlazic.netqueue.examples;

import ml.mlazic.netqueue.NetQueue;
import ml.mlazic.netqueue.builders.QueueBuilder;
import ml.mlazic.netqueue.queues.QueueType;

public class WorkSender {

    public static void main(String[] args) {

        QueueBuilder builder = new QueueBuilder();
        builder.setQueueName("hello.work")
                .setType(QueueType.WORK);

        NetQueue queue = new NetQueue(builder);

        queue.send(new String("{Work: '1''}"));
        queue.send(new String("{Wor: '2''}"));
        queue.send(new String("{Wo: '3''}"));
        queue.send(new String("{W: '4''}"));

        queue.close();
    }

}
