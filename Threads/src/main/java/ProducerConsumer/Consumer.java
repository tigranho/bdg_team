package ProducerConsumer;

public class Consumer extends Thread {
    WareHouse wareHouse;

    public Consumer(WareHouse wareHouse) {
        this.wareHouse = wareHouse;
    }

    @Override
    public void run() {
        while (true) {
            wareHouse.consume();
        }
    }
}
