package ml.mlazic.netqueue.queues;

import ml.mlazic.netqueue.builders.QueueBuilder;

public class Queue {
    protected QueueImpl imp;

    public Queue(QueueBuilder builder) {
        imp = builder.buildQueue();

    }

    public Queue() {
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
