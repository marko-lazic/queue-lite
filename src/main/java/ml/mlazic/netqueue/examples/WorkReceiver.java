package ml.mlazic.netqueue.examples;

import ml.mlazic.netqueue.NetQueue;
import ml.mlazic.netqueue.builders.QueueBuilder;
import ml.mlazic.netqueue.queues.QueueType;

/**
 * WorkReceiver.java
 * Purpose: Example of work receiver
 *
 * @author Marko Lazic
 * @version 1.0 4/21/18
 */
public class WorkReceiver {

    public static void main(String[] args) {
        QueueBuilder builder = new QueueBuilder();
        builder.setQueueName("hello.work")
                .setType(QueueType.WORK)
                .handeMessage((message) -> { doWork(message.toString()); });

        NetQueue queue = new NetQueue(builder);

        queue.receive();
    }

    /**
     * We generate fake work so that WorkReceiver is occupied with doing something.
     * @param task is received message.
     */
    private static void doWork(String task) {
        System.out.println(" [x] Received '" + task + "'");
        for (char c : task.toCharArray()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(" [x] Done");
    }
}
