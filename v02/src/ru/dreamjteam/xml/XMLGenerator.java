package taxixml;


import orders.*;
import cars.*;
import types.*;
import java.io.*;
import javax.xml.bind.*;
/**
 *
 * @author Артём
 */
public class XMLGenerator {
    
    
    public String generateXMLOrders(Orders ot)
    {
        StringWriter result = null;
        try
        {
                //Создаём контекст
                JAXBContext jaxbCtx = JAXBContext.newInstance(ot.getClass().getPackage().getName());
                //Создаеём, используя контекст, объект для маршаллизации
                Marshaller marshaller = jaxbCtx.createMarshaller();
                //Задаём свойства кодировки и устанавливаем флаг форматированного вывода
                marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                
                //Маршаллизируем экземпляр класса OrdersType в поток символов
                result = new StringWriter();
                marshaller.marshal(ot, result);
                String result2=result.toString();
                return result2;
        }
        catch (JAXBException e2)
        {
            e2.printStackTrace();
        }
        return null;
    }
    
    public String generateXMLCars(Cars ct)
    {
        StringWriter result = null;
        try
        {
                JAXBContext jaxbCtx = JAXBContext.newInstance(ct.getClass().getPackage().getName());
                Marshaller marshaller = jaxbCtx.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                
                result = new StringWriter();
                marshaller.marshal(ct, result);
                String result2=result.toString();
                return result2;
        }
        catch (JAXBException e2)
        {
            e2.printStackTrace();
        }
        return null;
    }

    
    public String generateXMLTypes(CarTypes tt)
    {
        StringWriter result = null;
        try
        {
                JAXBContext jaxbCtx = JAXBContext.newInstance(tt.getClass().getPackage().getName());
                Marshaller marshaller = jaxbCtx.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                
                result = new StringWriter();
                marshaller.marshal(tt, result);
                String result2=result.toString();
                return result2;
        }
        catch (JAXBException e2)
        {
            e2.printStackTrace();
        }
        return null;
    }
}
