package ml.mlazic.netqueue.old;

public class AmqpClient {

    private static QueueLite queueLite;

    public static void main(String[] args) {

        queueLite = new QueueLite("localhost", 5672, "guest", "guest");

        queueLite.send("hello world");
        queueLite.send("hello world2");
        queueLite.send("hello world3");

        queueLite.send("hello Fans1", "fanout");
        queueLite.send("hello Fans2", "fanout");
        queueLite.send("hello Fans3", "fanout");


    }
}
