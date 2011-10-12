package taxixml;


import java.io.*;
import javax.xml.bind.*;
/**
 *
 * @author Артём
 */
public class XMLParser {
 
    
    public Object parseXML(String s, String object_type)
    {
        FileInputStream fis = null;
        
        try
        {
            Object ot = Class.forName(object_type).newInstance();
            //Создаём контекст
            JAXBContext jaxbCtx = JAXBContext.newInstance(ot.getClass().getPackage().getName());
            //Создаём, используя контекст, демаршаллизатор
            Unmarshaller um = jaxbCtx.createUnmarshaller();
            //Демаршаллизируем поток символов в объект
            StringReader sr = new StringReader(s);
            ot = um.unmarshal(sr);
            return ot;
        }
        catch (InstantiationException ex)
        {
            ex.printStackTrace();
        }
        catch (IllegalAccessException ex)
        {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (JAXBException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    
}
