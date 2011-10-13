package ru.dreamjteam.xml;


import ru.dreamjteam.xml.binds.cars.Cars;
import ru.dreamjteam.xml.binds.orders.Orders;
import ru.dreamjteam.xml.binds.types.CarTypes;

import java.io.*;
import javax.xml.bind.*;

/**
 * @author Артём
 */
public class XMLGenerator {
	public static String toXML(Object ot) {
		try {
			JAXBContext jaxbCtx = JAXBContext.newInstance(ot.getClass().getPackage().getName());
			Marshaller marshaller = jaxbCtx.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			StringWriter result = new StringWriter();
			marshaller.marshal(ot, result);
			return result.toString();
		}
		catch (JAXBException e2) {
			e2.printStackTrace();
		}
		return "";
	}
}
