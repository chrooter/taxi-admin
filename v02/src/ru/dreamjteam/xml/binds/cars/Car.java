
package ru.dreamjteam.xml.binds.cars;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for car complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="car">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="carTypeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="govNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="running" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "car", propOrder = {
    "id",
    "carTypeId",
    "govNumber",
    "running"
})
@XmlRootElement
public class Car {

    protected int id;
    protected int carTypeId;
    @XmlElement(required = true)
    protected String govNumber;
    protected int running;

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
     * Gets the value of the carTypeId property.
     * 
     */
    public int getCarTypeId() {
        return carTypeId;
    }

    /**
     * Sets the value of the carTypeId property.
     * 
     */
    public void setCarTypeId(int value) {
        this.carTypeId = value;
    }

    /**
     * Gets the value of the govNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGovNumber() {
        return govNumber;
    }

    /**
     * Sets the value of the govNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGovNumber(String value) {
        this.govNumber = value;
    }

    /**
     * Gets the value of the running property.
     * 
     */
    public int getRunning() {
        return running;
    }

    /**
     * Sets the value of the running property.
     * 
     */
    public void setRunning(int value) {
        this.running = value;
    }

}
