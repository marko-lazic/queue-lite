package ml.mlazic.queuelite;

public class AmqpClient {

    private static QueueLite queueLite;

    public static void main(String[] args) {

        queueLite = new QueueLite("localhost", 5672, "guest", "guest");

        queueLite.send("hello world");
        queueLite.send("hello world2");
        queueLite.send("hello world3");

        queueLite.receive();
    }
}
