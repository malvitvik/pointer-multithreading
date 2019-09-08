package pointer.multithreading;

public class MyThread extends Thread {
    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void run() {
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

        for (int i = 0; i < count; i++) {
            System.out.print(fibonacci[i] + " ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
