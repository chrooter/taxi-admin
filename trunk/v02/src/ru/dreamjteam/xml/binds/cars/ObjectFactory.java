package ru.dreamjteam.xml.binds.cars;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cars package. 
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
    private final static QName _Cars_QNAME = new QName("", "cars");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cars
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
     * Create an instance of {@link Car }
     * 
     */
    public Car createCar() {
        return new Car();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Cars }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "cars")
    public JAXBElement<Cars> createCars(Cars value) {
        return new JAXBElement<Cars>(_Cars_QNAME, Cars.class, null, value);
    }

}
