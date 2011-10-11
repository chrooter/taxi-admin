package taxixml;


import generated.*;
import java.io.*;
import javax.xml.bind.*;


public class XMLParser {
 
    
    public Object parseXML(ByteArrayOutputStream b, String object_type)
    {
        FileInputStream fis = null;
        
        try
        {
            Object ot = Class.forName(object_type).newInstance();
            //Создаем ссылку на файл
//            File xmlFile = new File(filename);
            //Ссылка на поток ввода
//            fis = new FileInputStream(xmlFile);
            
            //Создаём контекст
            JAXBContext jaxbCtx = JAXBContext.newInstance(ot.getClass().getPackage().getName());
            //Создаём, используя контекст, демаршаллизатор
            Unmarshaller um = jaxbCtx.createUnmarshaller();
            //Демаршаллизируем файл в объект
            ot = um.unmarshal(new ByteArrayInputStream(b.toByteArray()));
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
