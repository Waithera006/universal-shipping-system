The Universal Shipping System
This system demonstrates how Object-Oriented Programming II concepts work together in a real-world scenario.
 Specifically, it integrates:
Abstract classes


Interfaces as data types


Marker interfaces


Polymorphism


Comparable interface


Runtime type checking


The scenario models a logistics company that ships Electronics and Documents.
 Problem Overview
The system has the following requirements:
All shipping items must have:


A tracking number


A weight


Some items are insured, meaning they have a declared value.


Items must be sortable by weight.


The system must process items using interface and abstract class types, not concrete classes.


Some behaviors must be detected at runtime.


 Interface: Insurable
interface Insurable {
    double getDeclaredValue();
}

 Explanation
This is a capability interface


It represents the ability to be insured


Any class that implements this interface must provide a declared value


It allows the system to treat different items uniformly if they are insurable



 Abstract Class: ShippingItem
abstract class ShippingItem implements Comparable<ShippingItem>

 Purpose
Acts as a base template for all items in the system


Prevents direct instantiation


Forces all subclasses to share common properties


 Fields
String trackingNumber;
double weight;

Every shipping item must have these fields.
Comparable Implementation
@Override
public int compareTo(ShippingItem other) {
    return Double.compare(this.weight, other.weight);
}

Why This Matters
Implements the Comparable interface


Allows shipping items to be sorted by weight


Enables the use of:


Collections.sort(list);

This is important for loading and logistics optimization.

 Concrete Class: Electronic
class Electronic extends ShippingItem
implements Insurable, Serializable

 Key Concepts Demonstrated
Inheritance (extends ShippingItem)


Multiple interface implementation


Marker interface usage


 Why Electronic Implements:
Insurable → electronics are valuable


Serializable → electronics can be transmitted across a network


 Declared Value
public double getDeclaredValue() {
    return value;
}

This method satisfies the Insurable contract.
 Concrete Class: Document
class Document extends ShippingItem

 Explanation
Documents share the identity of shipping items


They are not insured


They are not serializable


This demonstrates selective behavior inheritance


 Class: ShippingProcessor
public void process(ShippingItem item)

 Core Design Principle
The processor uses:
Abstract class as a data type


No dependency on concrete classes


This is a strong example of:
Programming to an interface, not an implementation

 Runtime Capability Checking
if (item instanceof Insurable)

Checks whether the item supports insurance


Uses dynamic binding


Applies only when the capability exists


if (item instanceof Serializable)

Detects marker interface


Enables logging behavior


Demonstrates runtime polymorphism


 Main Method (System Execution)
List<ShippingItem> items = new ArrayList<>();

 Why ShippingItem as the type?
Enables storage of different object types


Demonstrates polymorphism



 Sorting
Collections.sort(items);

Works because ShippingItem implements Comparable


Items are sorted by weight automatically


 Processing
for (ShippingItem item : items) {
    processor.process(item);
}

Each object behaves according to its actual type


No need to know whether it is Electronic or Document.


 
