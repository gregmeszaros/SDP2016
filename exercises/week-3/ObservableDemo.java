import java.util.Observable;
import java.util.Observer;

class ObservedObject extends Observable {
    private String watchedValue;

    public ObservedObject(String value) {
        watchedValue = value;
    }

    public void setValue(String value) {
        // if value has changed notify observers
        if(!watchedValue.equals(value)) {
            System.out.println("Value changed to new value: "+value);
            watchedValue = value;

            // mark as value changed
            setChanged();
            // trigger notification
            notifyObservers(value);
        }
    }
}
public class ObservableDemo implements Observer {
    public static void main(String[] args) {
        // create watched and watcher objects
        ObservedObject watched = new ObservedObject("Original Value");
        // watcher object listens to object change
        ObservableDemo watcher = new ObservableDemo();

        // trigger value change
        watched.setValue("New Value");

        // add observer to the watched object
        watched.addObserver(watcher);

        // trigger value change
        watched.setValue("Latest Value");
    }

    public void update(Observable obj, Object arg) {
        System.out.println("Update called with Arguments: "+arg);
    }
}