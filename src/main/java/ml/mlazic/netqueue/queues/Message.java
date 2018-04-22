package ml.mlazic.netqueue.queues;

/**
 * Message.java
 * Purpose: Contains a simple interface method to process given data for Work queue.
 *
 * @author Marko Lazic
 * @version 1.0 4/21/18
 */
public interface Message {

    /** @param in task to be processed. */
    void process(Object in);
}
