import model.Consumer;
import model.Storage;

import java.util.concurrent.CyclicBarrier;

public class MainApp {
    public static void main(String[] args) {
        if (checkArgs(args)) {
            int consumerCount = Integer.parseInt(args[0]);
            Storage storage = new Storage();
            CyclicBarrier barrier = new CyclicBarrier(consumerCount);
            for (int i = 0; i < consumerCount; i++) {
                new Consumer(storage, barrier, i + 1).start();
            }
        }
    }

    private static boolean checkArgs(String[] args) {
        if (args.length != 1) {
            System.out.println("Должен быть указан только один параметр - кол - во покупателей!");
            return false;
        }
        try {
            Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Параметр должен быть числом!");
            return false;
        }
        return true;
    }
}
