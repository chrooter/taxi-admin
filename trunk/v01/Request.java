/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author диман
 */
@XmlRootElement
public class Request {
    public int order_id;
    public Date time_ord;
    public Date time_dest;

    public String getAddr_dep() {
        return addr_dep;
    }

    public void setAddr_dep(String addr_dep) {
        this.addr_dep = addr_dep;
    }

    public String getAddr_dest() {
        return addr_dest;
    }

    public void setAddr_dest(String addr_dest) {
        this.addr_dest = addr_dest;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getPassenger() {
        return passenger;
    }

    public void setPassenger(int passenger) {
        this.passenger = passenger;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getTime_dest() {
        return time_dest;
    }

    public void setTime_dest(Date time_dest) {
        this.time_dest = time_dest;
    }

    public Date getTime_ord() {
        return time_ord;
    }

    public void setTime_ord(Date time_ord) {
        this.time_ord = time_ord;
    }
    public String addr_dep;
    public String addr_dest;
    public int passenger;
    public int status;
    public int dist;
    public int cost;
}
