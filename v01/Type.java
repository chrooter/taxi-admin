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
public class Type {
    public int type_id;
    public String name;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCost_per_km() {
        return cost_per_km;
    }

    public void setCost_per_km(int cost_per_km) {
        this.cost_per_km = cost_per_km;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeating_capacity() {
        return seating_capacity;
    }

    public void setSeating_capacity(int seating_capacity) {
        this.seating_capacity = seating_capacity;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }
    public int seating_capacity;
    public int capacity;
    public int cost_per_km;
}
