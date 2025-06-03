import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Message implements Runnable, Delayed {
    private String message;
    private long delay;
    private long targetTime;

    public Message(String message, long delay) {
        this.delay = delay;
        this.message = message;
        this.targetTime = System.currentTimeMillis() + delay;
    }

    public int compareTo(Delayed other) {
        Message m = (Message) other;
        return targetTime > ((Message) other).targetTime ? 1: -1;
    }

    public long getDelay(TimeUnit unit) {
        return targetTime - System.currentTimeMillis();
    }

    @Override
    public void run() {
            System.out.println("message: " + message + ", delay: " + delay);
    }
}
