package ru.dreamjteam.xml;

import java.io.*;
import javax.xml.bind.*;

/**
 * @author Артём
 */
public class XMLParser {
	/**
	 * Parses XML string with JAXB into object of specified class.
	 * @param xml string with serialized object
	 * @param classOfT class of serialized object
	 * @return object
	 */
	public static <T> T parseXML(final String xml, Class<T> classOfT) {
		try {
			final String packageName = classOfT.getPackage().getName();
			JAXBContext jaxbCtx = JAXBContext.newInstance(packageName);
			Unmarshaller um = jaxbCtx.createUnmarshaller();
			StringReader reader = new StringReader(xml);
			JAXBElement<T> ot = (JAXBElement<T>) um.unmarshal(reader);
			return ot.getValue();
		}
		catch (JAXBException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
