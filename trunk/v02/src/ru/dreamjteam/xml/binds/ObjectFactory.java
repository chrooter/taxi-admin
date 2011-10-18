
package ru.dreamjteam.xml.binds;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.dreamjteam.xml.binds package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Car_QNAME = new QName("", "car");
    private final static QName _CarType_QNAME = new QName("", "carType");
    private final static QName _Order_QNAME = new QName("", "order");
    private final static QName _Cars_QNAME = new QName("", "cars");
    private final static QName _CarTypes_QNAME = new QName("", "carTypes");
    private final static QName _Orders_QNAME = new QName("", "orders");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.dreamjteam.xml.binds
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Cars }
     * 
     */
    public Cars createCars() {
        return new Cars();
    }

    /**
     * Create an instance of {@link Order }
     * 
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Create an instance of {@link CarType }
     * 
     */
    public CarType createCarType() {
        return new CarType();
    }

    /**
     * Create an instance of {@link CarTypes }
     * 
     */
    public CarTypes createCarTypes() {
        return new CarTypes();
    }

    /**
     * Create an instance of {@link Car }
     * 
     */
    public Car createCar() {
        return new Car();
    }

    /**
     * Create an instance of {@link Orders }
     * 
     */
    public Orders createOrders() {
        return new Orders();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Car }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "car")
    public JAXBElement<Car> createCar(Car value) {
        return new JAXBElement<Car>(_Car_QNAME, Car.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CarType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "carType")
    public JAXBElement<CarType> createCarType(CarType value) {
        return new JAXBElement<CarType>(_CarType_QNAME, CarType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Order }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "order")
    public JAXBElement<Order> createOrder(Order value) {
        return new JAXBElement<Order>(_Order_QNAME, Order.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Cars }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "cars")
    public JAXBElement<Cars> createCars(Cars value) {
        return new JAXBElement<Cars>(_Cars_QNAME, Cars.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CarTypes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "carTypes")
    public JAXBElement<CarTypes> createCarTypes(CarTypes value) {
        return new JAXBElement<CarTypes>(_CarTypes_QNAME, CarTypes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Orders }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "orders")
    public JAXBElement<Orders> createOrders(Orders value) {
        return new JAXBElement<Orders>(_Orders_QNAME, Orders.class, null, value);
    }

}
