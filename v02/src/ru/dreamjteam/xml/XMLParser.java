package ru.dreamjteam.xml;

import java.io.*;
import javax.xml.bind.*;

/**
 * @author Артём
 */
public class XMLParser {
	public <T> T parseXML(String s, Class<T> classOfT) {
		try {
			JAXBContext jaxbCtx = JAXBContext.newInstance(classOfT.getPackage().getName());
			Unmarshaller um = jaxbCtx.createUnmarshaller();
			StringReader sr = new StringReader(s);
			Object ot = um.unmarshal(sr);
			return classOfT.cast(ot);
		}
		catch (JAXBException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
