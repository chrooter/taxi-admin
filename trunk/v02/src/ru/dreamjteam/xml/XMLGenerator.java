package ru.dreamjteam.xml;



import ru.dreamjteam.xml.binds.*;

import java.io.*;
import javax.xml.bind.*;

/**
 * @author Артём
 */
public class XMLGenerator {
	/**
	 * Sserializes object to xml string with JAXB if xsd schema exists.
	 * @param ot object
	 * @return string with XML
	 */
	public static String toXML(Object ot) {
		try {
			JAXBContext jaxbCtx = JAXBContext.newInstance(ot.getClass().getPackage().getName());
			Marshaller marshaller = jaxbCtx.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
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
