package ml.mlazic.netqueue.queues;

/**
 * QueueType.java
 * Purpose: Used as type selector in QueueBuilder
 * the child classes.
 *
 * @author Marko Lazic
 * @version 1.0 4/21/18
 */
public enum QueueType {
    /** Posman is one to one queue.*/
    POSTMAN,
    /** Work is where data is sent to multiple worker units. */
    WORK,
    /** Fanout method queue sends message to everyone.*/
    FANOUT
}
