package pointer.multithreading;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static Scanner scanner;

    public static void main(String[] args) {
        MyThread myThread = new MyThread();

        scanner = new Scanner(System.in);
        System.out.println("How many fibonacci numbers do you want to see in direct order?");
        myThread.setCount(getInt());


        System.out.println("How many fibonacci numbers do you want to see in reverse order?");
        int count = getInt();
        scanner.close();

        ExecutorService service = Executors.newSingleThreadExecutor();

        service.execute(myThread);
        service.execute(() -> {
            if (count <= 0) {
                System.out.println("0");
                return;
            }

            int[] fibonacci = new int[count];

            for (int i = 0; i < 2 && i < count; i++) {
                fibonacci[i] = 1;
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
        });

        service.shutdown();
    }

    private static int getInt() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Wrong number. Type a new one:");
            scanner.nextLine();
            return getInt();
        } catch (NullPointerException ex) {
            System.out.println("Scanner is null.");
            return 0;
        }
    }
}