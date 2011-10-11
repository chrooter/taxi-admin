
package ru.dreamjteam.xml.binds.orders;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.dreamjteam.xml.binds.orders package. 
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

    private final static QName _Order_QNAME = new QName("", "order");
    private final static QName _Orders_QNAME = new QName("", "orders");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.dreamjteam.xml.binds.orders
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Orders }
     * 
     */
    public Orders createOrders() {
        return new Orders();
    }

    /**
     * Create an instance of {@link Order }
     * 
     */
    public Order createOrder() {
        return new Order();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Orders }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "orders")
    public JAXBElement<Orders> createOrders(Orders value) {
        return new JAXBElement<Orders>(_Orders_QNAME, Orders.class, null, value);
    }

}
