package ml.mlazic.netqueue.examples;

import ml.mlazic.netqueue.builders.QueueBuilder;
import ml.mlazic.netqueue.NetQueue;
import ml.mlazic.netqueue.queues.QueueType;

/**
 * FanoutSender.java
 * Purpose: Example of fanout sender
 *
 * @author Marko Lazic
 * @version 1.0 4/21/18
 */
public class FanoutSender {
    public static void main(String[] args) {
        QueueBuilder builder = new QueueBuilder();
        builder.setExchaneName("fanout")
                .setType(QueueType.FANOUT);

        NetQueue queue = new NetQueue(builder);

        queue.send(new String("Hello Fanout!"));


        queue.close();

    }
}
