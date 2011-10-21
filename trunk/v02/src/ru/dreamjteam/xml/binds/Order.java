
package ru.dreamjteam.xml.binds;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for order complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="order">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}car"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="carId" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="timeOrd" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="timeDest" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="addrDep" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="addrDest" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="passengers" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="status">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="new"/>
 *             &lt;enumeration value="executing"/>
 *             &lt;enumeration value="done"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="distAppr" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="distInfact" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="cost" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "order", propOrder = {
    "car"
})
@XmlRootElement
public class Order {

    @XmlElement(required = true)
    protected Car car;
    @XmlAttribute
    protected Integer id;
    @XmlAttribute
    protected Integer carId;
    @XmlAttribute
    protected String timeOrd;
    @XmlAttribute
    protected String timeDest;
    @XmlAttribute
    protected String addrDep;
    @XmlAttribute
    protected String addrDest;
    @XmlAttribute
    protected Integer passengers;
    @XmlAttribute
    protected String status;
    @XmlAttribute
    protected Integer distAppr;
    @XmlAttribute
    protected Integer distInfact;
    @XmlAttribute
    protected Integer cost;

    /**
     * Gets the value of the car property.
     * 
     * @return
     *     possible object is
     *     {@link Car }
     *     
     */
    public Car getCar() {
        return car;
    }

    /**
     * Sets the value of the car property.
     * 
     * @param value
     *     allowed object is
     *     {@link Car }
     *     
     */
    public void setCar(Car value) {
        this.car = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Gets the value of the carId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCarId() {
        return carId;
    }

    /**
     * Sets the value of the carId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCarId(Integer value) {
        this.carId = value;
    }

    /**
     * Gets the value of the timeOrd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeOrd() {
        return timeOrd;
    }

    /**
     * Sets the value of the timeOrd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeOrd(String value) {
        this.timeOrd = value;
    }

    /**
     * Gets the value of the timeDest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeDest() {
        return timeDest;
    }

    /**
     * Sets the value of the timeDest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeDest(String value) {
        this.timeDest = value;
    }

    /**
     * Gets the value of the addrDep property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddrDep() {
        return addrDep;
    }

    /**
     * Sets the value of the addrDep property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddrDep(String value) {
        this.addrDep = value;
    }

    /**
     * Gets the value of the addrDest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddrDest() {
        return addrDest;
    }

    /**
     * Sets the value of the addrDest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddrDest(String value) {
        this.addrDest = value;
    }

    /**
     * Gets the value of the passengers property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPassengers() {
        return passengers;
    }

    /**
     * Sets the value of the passengers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPassengers(Integer value) {
        this.passengers = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the distAppr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDistAppr() {
        return distAppr;
    }

    /**
     * Sets the value of the distAppr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDistAppr(Integer value) {
        this.distAppr = value;
    }

    /**
     * Gets the value of the distInfact property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDistInfact() {
        return distInfact;
    }

    /**
     * Sets the value of the distInfact property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDistInfact(Integer value) {
        this.distInfact = value;
    }

    /**
     * Gets the value of the cost property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCost() {
        return cost;
    }

    /**
     * Sets the value of the cost property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCost(Integer value) {
        this.cost = value;
    }

}
