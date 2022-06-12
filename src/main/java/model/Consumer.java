package model;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Consumer extends Thread {

    private int purchasedGoodsCount;
    private int purchasesCount;
    private final Storage storage;
    private final CyclicBarrier barrier;
    private final int consumerIdentifier;

    public Consumer(Storage storage, CyclicBarrier barrier, int consumerIdentifier) {
        this.storage = storage;
        this.barrier = barrier;
        this.consumerIdentifier = consumerIdentifier;
    }

    @Override
    public void run() {
        buyProduct();
    }

    public void buyProduct() {
        while (storage.getQuantityGoods() > 0) {
            purchasedGoodsCount += storage.buyFromStorage(new Random().nextInt(1, 11));
            purchasesCount++;
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                break;
            }
        }
        barrier.reset();
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Покупатель " + consumerIdentifier + " совершил " + purchasesCount + " покупок. Количество купленного товара: " + purchasedGoodsCount;
    }
}
