package ru.dreamjteam.xml.binds.orders;

import javax.xml.bind.annotation.*;


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
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="timeOrd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="timeDest" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="addrDep" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="addrDest" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="passengers" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="status">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="new"/>
 *               &lt;enumeration value="executing"/>
 *               &lt;enumeration value="done"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="distAppr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="distInfact" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cost" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "order", propOrder = {
    "id",
    "timeOrd",
    "timeDest",
    "addrDep",
    "addrDest",
    "passengers",
    "status",
    "distAppr",
    "distInfact",
    "cost"
})
@XmlRootElement
public class Order {

    protected int id;
    @XmlElement(required = true)
    protected String timeOrd;
    @XmlElement(required = true)
    protected String timeDest;
    @XmlElement(required = true)
    protected String addrDep;
    @XmlElement(required = true)
    protected String addrDest;
    protected int passengers;
    @XmlElement(required = true)
    protected String status;
    protected int distAppr;
    protected int distInfact;
    protected int cost;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
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
     */
    public int getPassengers() {
        return passengers;
    }

    /**
     * Sets the value of the passengers property.
     * 
     */
    public void setPassengers(int value) {
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
     */
    public int getDistAppr() {
        return distAppr;
    }

    /**
     * Sets the value of the distAppr property.
     * 
     */
    public void setDistAppr(int value) {
        this.distAppr = value;
    }

    /**
     * Gets the value of the distInfact property.
     * 
     */
    public int getDistInfact() {
        return distInfact;
    }

    /**
     * Sets the value of the distInfact property.
     * 
     */
    public void setDistInfact(int value) {
        this.distInfact = value;
    }

    /**
     * Gets the value of the cost property.
     * 
     */
    public int getCost() {
        return cost;
    }

    /**
     * Sets the value of the cost property.
     * 
     */
    public void setCost(int value) {
        this.cost = value;
    }

}
