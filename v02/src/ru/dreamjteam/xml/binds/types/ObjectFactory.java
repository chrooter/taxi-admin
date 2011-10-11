
package ru.dreamjteam.xml.binds.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.dreamjteam.xml.binds.types package. 
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

    private final static QName _CarType_QNAME = new QName("", "carType");
    private final static QName _CarTypes_QNAME = new QName("", "carTypes");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.dreamjteam.xml.binds.types
     * 
     */
    public ObjectFactory() {
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
     * Create an instance of {@link JAXBElement }{@code <}{@link CarType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "carType")
    public JAXBElement<CarType> createCarType(CarType value) {
        return new JAXBElement<CarType>(_CarType_QNAME, CarType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CarTypes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "carTypes")
    public JAXBElement<CarTypes> createCarTypes(CarTypes value) {
        return new JAXBElement<CarTypes>(_CarTypes_QNAME, CarTypes.class, null, value);
    }

}
