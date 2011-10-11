//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.10.10 at 08:22:17 PM MSD 
//


package generated3;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated3 package. 
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

    private final static QName _SingleType_QNAME = new QName("", "single_type");
    private final static QName _Types_QNAME = new QName("", "types");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated3
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SingleTypeType }
     * 
     */
    public SingleTypeType createSingleTypeType() {
        return new SingleTypeType();
    }

    /**
     * Create an instance of {@link TypesType }
     * 
     */
    public TypesType createTypesType() {
        return new TypesType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SingleTypeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "single_type")
    public JAXBElement<SingleTypeType> createSingleType(SingleTypeType value) {
        return new JAXBElement<SingleTypeType>(_SingleType_QNAME, SingleTypeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TypesType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "types")
    public JAXBElement<TypesType> createTypes(TypesType value) {
        return new JAXBElement<TypesType>(_Types_QNAME, TypesType.class, null, value);
    }

}
