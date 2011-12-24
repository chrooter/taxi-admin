/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package change;

import java.sql.Timestamp;

/**
 *
 * @author диман
 */
public class Change implements  java.io.Serializable{
    private int id;
    private String entity;
    private Timestamp dateOfChange;
    private int priority;
    private String change;

    public Change (int id,String entity,Timestamp dateOfChange,int priority,String change) {
        this.id = id;
        this.entity = entity;
        this.dateOfChange = dateOfChange;
        this.priority = priority;
        this.change = change;
    }
    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public Timestamp getDateOfChange() {
        return dateOfChange;
    }

    public void setDateOfChange(Timestamp dateOfChange) {
        this.dateOfChange = dateOfChange;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

}
