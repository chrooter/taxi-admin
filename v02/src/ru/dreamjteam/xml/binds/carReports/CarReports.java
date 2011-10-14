package ru.dreamjteam.xml.binds.carReports;


import javax.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for carReports complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="carReports">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}carReport" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "carReports", propOrder = {
    "carReport"
})
public class CarReports {

    protected List<CarReport> carReport;

    /**
     * Gets the value of the carReport property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the carReport property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCarReport().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CarReport }
     * 
     * 
     */
    public List<CarReport> getCarReport() {
        if (carReport == null) {
            carReport = new ArrayList<CarReport>();
        }
        return this.carReport;
    }

}
