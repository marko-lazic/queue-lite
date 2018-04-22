package ml.mlazic.netqueue.examples;

import ml.mlazic.netqueue.builders.QueueBuilder;
import ml.mlazic.netqueue.NetQueue;
import ml.mlazic.netqueue.queues.QueueType;

/**
 * FanoutReceiver.java
 * Purpose: Example of fanout receiver
 *
 * @author Marko Lazic
 * @version 1.0 4/21/18
 */
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
