import java.io.Serializable;
import java.util.*;

//  Interface
interface Insurable {
    double getDeclaredValue();
}

// Abstract Class
abstract class ShippingItem implements Comparable<ShippingItem> {
    String trackingNumber;
    double weight;

    public ShippingItem(String trackingNumber, double weight) {
        this.trackingNumber = trackingNumber;
        this.weight = weight;
    }

    @Override
    public int compareTo(ShippingItem other) {
        return Double.compare(this.weight, other.weight);
    }
}

//  Concrete Classes
class Electronic extends ShippingItem implements Insurable, Serializable {
    double value;

    public Electronic(String tn, double w, double v) {
        super(tn, w);
        this.value = v;
    }

    public double getDeclaredValue() {
        return value;
    }
}

class Document extends ShippingItem {
    public Document(String tn, double w) {
        super(tn, w);
    }
}

//  Processor
class ShippingProcessor {
    public void process(ShippingItem item) {
        System.out.println("Processing " + item.trackingNumber +
                " (" + item.weight + "kg)");

        if (item instanceof Insurable) {
            Insurable insurableItem = (Insurable) item;
            System.out.println("ALERT: Insure for $" +
                    insurableItem.getDeclaredValue());
        }

        if (item instanceof Serializable) {
            System.out.println("LOG: Item is serializable.");
        }
    }
}

//  MAIN CLASS 
public class UniversalShippingSystem {
        public static void main(String[] args) {

        List<ShippingItem> items = new ArrayList<>();
        items.add(new Electronic("E001", 2.5, 1200));
        items.add(new Document("D001", 0.5));
        items.add(new Electronic("E002", 1.2, 800));

        Collections.sort(items);

        ShippingProcessor processor = new ShippingProcessor();
        for (ShippingItem item : items) {
            processor.process(item);
        }
    }
}
