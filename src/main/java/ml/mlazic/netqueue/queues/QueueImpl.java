package ml.mlazic.netqueue.queues;

public interface QueueImpl {

    Object send(Object in);
    Object receive();
    void open();
    void close();
}
