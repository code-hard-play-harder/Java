/**
 * Created by BPeng on 9/16/2016.
 */
import java.util.Observable;
import java.util.Observer;

public class Test {
    public static void main(String[] args){
        System.out.println("---Start the Observable/Observer---");
        ObservableValue obsv = new ObservableValue(0);
        ObserverValue obs = new ObserverValue();
        obsv.addObserver(obs);
        obsv.setValue(3);
        System.out.println("---End the Observable/Observer---");
    }
}

// Observable object
class ObservableValue extends Observable {
    private int number = 0;
    
    public ObservableValue(int number) {
        this.number = number;
    }

    public void setValue(int number) {
        this.number = number;
        setChanged();
        notifyObservers();
    }

    public int getValue() {
        return this.number;
    }

}

// Observer object
class ObserverValue implements Observer {
    private ObservableValue obsv = null;

    @Override
    public void update(Observable o, Object arg) {
        obsv = (ObservableValue)o;
        System.out.println("This is the changed value " + obsv.getValue());

    }
}
