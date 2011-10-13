
package ru.dreamjteam.xml.binds.types;

import javax.xml.bind.annotation.*;


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
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="seatCap" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="massCap" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="costPerKm" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "carType", propOrder = {
    "id",
    "name",
    "seatCap",
    "massCap",
    "costPerKm"
})
@XmlRootElement
public class CarType {

    protected int id;
    @XmlElement(required = true)
    protected String name;
    protected int seatCap;
    protected int massCap;
    protected int costPerKm;

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
     */
    public int getSeatCap() {
        return seatCap;
    }

    /**
     * Sets the value of the seatCap property.
     * 
     */
    public void setSeatCap(int value) {
        this.seatCap = value;
    }

    /**
     * Gets the value of the massCap property.
     * 
     */
    public int getMassCap() {
        return massCap;
    }

    /**
     * Sets the value of the massCap property.
     * 
     */
    public void setMassCap(int value) {
        this.massCap = value;
    }

    /**
     * Gets the value of the costPerKm property.
     * 
     */
    public int getCostPerKm() {
        return costPerKm;
    }

    /**
     * Sets the value of the costPerKm property.
     * 
     */
    public void setCostPerKm(int value) {
        this.costPerKm = value;
    }

}
