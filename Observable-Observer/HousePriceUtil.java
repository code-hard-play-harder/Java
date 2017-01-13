import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by BPeng on 1/13/2017.
 */
public class HousePriceUtil {
    public static void main(String[] args) {
        HousePrice hp = new HousePrice();
        HousePriceObserver hpo = new HousePriceObserver();

        // Add observers to observable object
        hp.addObserver(hpo);

        System.out.println("House price is growing fast...");
        System.out.println();
        hp.generatePrice();

    }
}

class HousePrice extends Observable {
    // Just use double to represent price, it is not appropriate though
    // 4.99 actually is 199.99k
    private final double BASE_PRICE = 199.99;
    private double price;
    private int count = 0;
    public double getPrice() {
        return this.price;
    }

    public void generatePrice() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                double currentPrice = BASE_PRICE + count;
                if(price!=currentPrice) {
                    price = currentPrice;
                    count++;

                    // Notify house observers
                    setChanged();
                    notifyObservers();
                }
            }
        }, 1000, 1000);
    }
}

class HousePriceObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof HousePrice) {
            System.out.println("The current price is " + ((HousePrice) o).getPrice());
        }
    }
}
