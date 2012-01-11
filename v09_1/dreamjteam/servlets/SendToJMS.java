/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dreamjteam.servlets;

import change.Change;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author диман
 */
   public class SendToJMS {
     public static Message createJMSMessageForjmsTaxiQueue(Session session, Change messageData) throws JMSException {
        // TODO create and populate message to send
        ObjectMessage tm = session.createObjectMessage();
        tm.setObject(messageData);
        tm.setJMSPriority(messageData.getPriority());
        return tm;
    }

    public static void sendJMSMessageToTaxiQueue(Change messageData) throws NamingException, JMSException {
                Properties properties = new Properties();

 properties.setProperty("java.naming.factory.initial",
                        "org.jnp.interfaces.NamingContextFactory");
 properties.setProperty("java.naming.provider.url", "jnp://localhost:1099");
 properties.setProperty("java.naming.factory.url.pkgs", "org.jnp.interfaces.NamingContextFactory");
        Context c = new InitialContext(properties);
        //ConnectionFactory cf = (ConnectionFactory) c.lookup("java:comp/env/jms/taxiQueueFactory
        ConnectionFactory cf = (ConnectionFactory) c.lookup("ConnectionFactory");
        javax.jms.Connection conn = null;
        Session s = null;
        try {
            conn = cf.createConnection();
            s = conn.createSession(false, s.CLIENT_ACKNOWLEDGE);
            //Destination destination = (Destination) c.lookup("java:comp/env/jms/taxiQueue");
            Destination destination = (Destination) c.lookup("queue/SampleServerQueue");
            MessageProducer mp = s.createProducer(destination);
            mp.send(createJMSMessageForjmsTaxiQueue(s, messageData));
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (JMSException e) {
                    
                }
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
