/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author диман
 */
@XmlRootElement
public class Car {
    public int car_id;
    public int ref_type;
    public String gov_number;
    public int running;

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public String getGov_number() {
        return gov_number;
    }

    public void setGov_number(String gov_number) {
        this.gov_number = gov_number;
    }

    public int getRef_type() {
        return ref_type;
    }

    public void setRef_type(int ref_type) {
        this.ref_type = ref_type;
    }

    public int getRunning() {
        return running;
    }

    public void setRunning(int running) {
        this.running = running;
    }
}
