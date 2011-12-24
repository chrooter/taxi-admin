//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.12.17 at 04:34:14 PM GMT+03:00 
//


package ru.dreamjteam.binds;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
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
    private final static QName _Logs_QNAME = new QName("", "logs");
    private final static QName _Cars_QNAME = new QName("", "cars");
    private final static QName _CarTypes_QNAME = new QName("", "carTypes");
    private final static QName _Orders_QNAME = new QName("", "orders");
    private final static QName _Log_QNAME = new QName("", "log");
    private final static QName _CurrentOrders_QNAME = new QName("", "currentOrders");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Log }
     * 
     */
    public Log createLog() {
        return new Log();
    }

    /**
     * Create an instance of {@link Logs }
     * 
     */
    public Logs createLogs() {
        return new Logs();
    }

    /**
     * Create an instance of {@link Order }
     * 
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Create an instance of {@link Orders }
     * 
     */
    public Orders createOrders() {
        return new Orders();
    }

    /**
     * Create an instance of {@link CarTypes }
     * 
     */
    public CarTypes createCarTypes() {
        return new CarTypes();
    }

    /**
     * Create an instance of {@link CarType }
     * 
     */
    public CarType createCarType() {
        return new CarType();
    }

    /**
     * Create an instance of {@link Car }
     * 
     */
    public Car createCar() {
        return new Car();
    }

    /**
     * Create an instance of {@link Cars }
     * 
     */
    public Cars createCars() {
        return new Cars();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Logs }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "logs")
    public JAXBElement<Logs> createLogs(Logs value) {
        return new JAXBElement<Logs>(_Logs_QNAME, Logs.class, null, value);
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

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Log }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "log")
    public JAXBElement<Log> createLog(Log value) {
        return new JAXBElement<Log>(_Log_QNAME, Log.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Orders }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "currentOrders")
    public JAXBElement<Orders> createCurrentOrders(Orders value) {
        return new JAXBElement<Orders>(_CurrentOrders_QNAME, Orders.class, null, value);
    }

}
