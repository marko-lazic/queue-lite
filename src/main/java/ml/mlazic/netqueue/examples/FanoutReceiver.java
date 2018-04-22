package ml.mlazic.netqueue.examples;

import ml.mlazic.netqueue.builders.QueueBuilder;
import ml.mlazic.netqueue.NetQueue;
import ml.mlazic.netqueue.queues.QueueType;

public class FanoutReceiver {

    public static void main(String[] args) {

        QueueBuilder builder = new QueueBuilder();
        builder.setExchaneName("fanout")
                .setType(QueueType.FANOUT)
                .handeMessage((message) -> { System.out.println(" [x] Received '" + message + "'"); });

        NetQueue queue = new NetQueue(builder);

        queue.receive();

        //queue.close();
    }
}
