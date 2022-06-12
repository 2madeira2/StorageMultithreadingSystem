package model;

public class Storage {

    private int quantityGoods = 1000;

    public synchronized int buyFromStorage(int quantityGoodsForPurchase) {
        int res = Math.min(quantityGoods, quantityGoodsForPurchase);
        quantityGoods -= res;
        return res;
    }

    public synchronized int getQuantityGoods() {
        return quantityGoods;
    }
}
