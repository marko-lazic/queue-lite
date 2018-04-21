package ml.mlazic.netqueue.old;

public class AmqpServer {

    public static void main(String[] args) {
        QueueLite queueLite = new QueueLite();
        queueLite.receive("fanout");
    }


}
