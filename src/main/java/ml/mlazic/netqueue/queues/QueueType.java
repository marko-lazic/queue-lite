package ml.mlazic.netqueue.queues;

public enum QueueType {
    /** Posman is one to one queue.*/
    POSTMAN,
    /** Work is where you can have multiple workers.*/
    WORK,
    /** Fanout is Publish / Subscribe method queue.*/
    FANOUT
}
