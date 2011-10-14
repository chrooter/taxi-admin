package ru.dreamjteam.xml.binds.carReports;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the carReports package. 
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

    private final static QName _CarReports_QNAME = new QName("", "carReports");
    private final static QName _CarReport_QNAME = new QName("", "carReport");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: carReports
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CarReport }
     * 
     */
    public CarReport createCarReport() {
        return new CarReport();
    }

    /**
     * Create an instance of {@link CarReports }
     * 
     */
    public CarReports createCarReports() {
        return new CarReports();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CarReports }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "carReports")
    public JAXBElement<CarReports> createCarReports(CarReports value) {
        return new JAXBElement<CarReports>(_CarReports_QNAME, CarReports.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CarReport }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "carReport")
    public JAXBElement<CarReport> createCarReport(CarReport value) {
        return new JAXBElement<CarReport>(_CarReport_QNAME, CarReport.class, null, value);
    }

}