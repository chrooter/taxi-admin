/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dreamjteam.mdb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.CreateException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ru.dreamjteam.session.PutToLogLocal;
import ru.dreamjteam.session.PutToLogLocalHome;
import change.Change;
/**
 *
 * @author диман
 */
public class Receiver implements MessageDrivenBean, MessageListener {
    
    private MessageDrivenContext context;
    
    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click on the + sign on the left to edit the code.">
    
    /**
     * @see javax.ejb.MessageDrivenBean#setMessageDrivenContext(javax.ejb.MessageDrivenContext)
     */
    public void setMessageDrivenContext(MessageDrivenContext aContext) {
        context = aContext;
    }
    
    /**
     * See section 15.4.4 of the EJB 2.0 specification
     * See section 15.7.3 of the EJB 2.1 specification
     */
    public void ejbCreate() {
        // TODO Add code to acquire and use other enterprise resources (DataSource, JMS, enterprise bean, Web services)
    }
    
    /**
     * @see javax.ejb.MessageDrivenBean#ejbRemove()
     */
    public void ejbRemove() {
        // TODO release any resource acquired in ejbCreate.
        // The code here should handle the possibility of not getting invoked
        // See section 15.7.3 of the EJB 2.1 specification
    }
    
    // </editor-fold>
    
    public void onMessage(Message message) {
        ObjectMessage msg = null;
        Change newchange;
        try {
            if (message instanceof ObjectMessage) {
                msg = (ObjectMessage) message;
                msg.acknowledge();
                newchange = (Change)msg.getObject();
                PutToLogLocal ptl = lookupPutToLogLocal();
                ptl.Put(newchange);
            } else {
               System.out.println("Message of wrong type: " +
                        message.getClass().getName());

            }
        } catch (JMSException e) {
            e.printStackTrace();
            context.setRollbackOnly();
        } catch (Throwable te) {
            te.printStackTrace();
        }
    }

    private PutToLogLocal lookupPutToLogLocal() {
        try {
            Context c = new InitialContext();
            PutToLogLocalHome rv = (PutToLogLocalHome) c.lookup("java:comp/env/PutToLog");
            return rv.create();
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        } catch (CreateException ce) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ce);
            throw new RuntimeException(ce);
        }
    }

    
}
