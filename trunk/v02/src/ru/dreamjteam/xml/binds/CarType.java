
package ru.dreamjteam.xml.binds;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for carType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="carType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}cars"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="seatCap" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="massCap" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="costPerKm" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "carType", propOrder = {
    "cars"
})
public class CarType {

    @XmlElement(required = true)
    protected Cars cars;
    @XmlAttribute
    protected Integer id;
    @XmlAttribute
    protected String name;
    @XmlAttribute
    protected Integer seatCap;
    @XmlAttribute
    protected Integer massCap;
    @XmlAttribute
    protected Integer costPerKm;

    /**
     * Gets the value of the cars property.
     * 
     * @return
     *     possible object is
     *     {@link Cars }
     *     
     */
    public Cars getCars() {
        return cars;
    }

    /**
     * Sets the value of the cars property.
     * 
     * @param value
     *     allowed object is
     *     {@link Cars }
     *     
     */
    public void setCars(Cars value) {
        this.cars = value;
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
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the seatCap property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSeatCap() {
        return seatCap;
    }

    /**
     * Sets the value of the seatCap property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSeatCap(Integer value) {
        this.seatCap = value;
    }

    /**
     * Gets the value of the massCap property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMassCap() {
        return massCap;
    }

    /**
     * Sets the value of the massCap property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMassCap(Integer value) {
        this.massCap = value;
    }

    /**
     * Gets the value of the costPerKm property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCostPerKm() {
        return costPerKm;
    }

    /**
     * Sets the value of the costPerKm property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCostPerKm(Integer value) {
        this.costPerKm = value;
    }

}
