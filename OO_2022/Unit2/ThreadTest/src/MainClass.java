import java.util.Random;
import java.util.concurrent.DelayQueue;

public class MainClass {
    public static void main(String[] args) throws InterruptedException {
        Random r = new Random();
        DelayQueue<Message> messageDelayQueue = new DelayQueue<>();
        for (int i = 0; i < 5; i++) {
            Message m = new Message(String.valueOf(i), r.nextInt(10000));
            messageDelayQueue.put(m);
        }
        while (!messageDelayQueue.isEmpty()) {
            Runnable run = messageDelayQueue.take();
            Thread thr = new Thread(run);
            thr.start();
        }
    }
}
