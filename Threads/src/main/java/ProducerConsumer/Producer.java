package ProducerConsumer;

public class Producer extends Thread {
    WareHouse wareHouse;

    public Producer(WareHouse wareHouse) {
        this.wareHouse = wareHouse;
    }

    @Override
    public void run() {
        while (true) {
            wareHouse.produce();
        }
    }
}
