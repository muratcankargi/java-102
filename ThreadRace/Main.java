package ThreadOdev;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        NumberThread numberThread = new NumberThread();
        ExecutorService pool = Executors.newFixedThreadPool(15);
        for (int i = 1; i <= 10000; i++) {
            pool.execute(numberThread);
        }

        try {
        Thread.sleep(2000);
        } catch ( InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
