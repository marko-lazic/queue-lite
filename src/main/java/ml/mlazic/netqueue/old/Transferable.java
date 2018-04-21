package ml.mlazic.netqueue.old;

public interface Transferable {

    void send(String message);
    void receive();
    void close();

}
