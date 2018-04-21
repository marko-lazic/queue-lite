package ml.mlazic.netqueue.queues;

public class NetQueue {
    protected QueueImpl imp;

    public NetQueue(QueueBuilder builder) {
        imp = builder.buildQueue();
    }

    public NetQueue() {
        this(new QueueBuilder());
    }

    public Object send(Object in) {
        return imp.send(in);
    }

    public Object receive() {
        return imp.receive();
    }

    public void open() {
        imp.open();
    }

    public void close() {
        imp.close();
    }

}
