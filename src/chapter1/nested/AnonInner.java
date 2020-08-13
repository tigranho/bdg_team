package chapter1.nested;

public class AnonInner {
     class SaleTodayOnly {
         int dollarsOff() {return 0;}
    }

    public int admission(int basePrice) {
        SaleTodayOnly sale = new SaleTodayOnly() {
            int dollarsOff() {
                return 3;
            }
        };
        return basePrice - sale.dollarsOff();
    }
}
