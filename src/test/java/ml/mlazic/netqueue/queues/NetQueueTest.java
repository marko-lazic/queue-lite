package ml.mlazic.netqueue.queues;

import ml.mlazic.netqueue.NetQueue;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NetQueueTest {
    private NetQueue queue = mock(NetQueue.class);
    private String message = "test";

    @Test
    public void send() {
        when(queue.send(message)).thenReturn(message);
        assertEquals(queue.send(message), message);
    }

    @Test
    public void receive() {
        when(queue.receive()).thenReturn(message);
        assertEquals(queue.receive(), message);
    }

}