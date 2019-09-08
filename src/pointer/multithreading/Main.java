package pointer.multithreading;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();

        Scanner scanner = new Scanner(System.in);
        System.out.println("How many fibonacci numbers do you want to see in direct order?");
        myThread.setCount(scanner.nextInt());

        System.out.println("How many fibonacci numbers do you want to see in reverse order?");
        int count = scanner.nextInt();
        scanner.close();

        Runnable fibonacciWork = () -> {
            int[] fibonacci = new int[count];

            if (count > 0) {
                fibonacci[0] = 1;
            }

            if (count > 1) {
                fibonacci[1] = 1;
            }

            for (int i = 2; i < count; i++) {
                fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
            }
            System.out.println();

            for (int i = count - 1; i >= 0; i--) {
                System.out.print(fibonacci[i] + " ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        ExecutorService service = Executors.newSingleThreadExecutor();

        service.execute(myThread);
        service.execute(fibonacciWork);

        service.shutdown();
    }
}
