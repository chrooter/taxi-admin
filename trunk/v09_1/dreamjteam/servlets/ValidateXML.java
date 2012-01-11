/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.dreamjteam.servlets;
import java.io.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import org.xml.sax.SAXException;
/**
 *
 * @author диман
 */
public class ValidateXML {
    public static void CheckXML(String XML,String schemaPath) throws SAXException,IOException {
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        File schemaLocation = new File(schemaPath);
        Schema schema = factory.newSchema(schemaLocation);
        Validator validator = schema.newValidator();
        CharArrayReader r = new CharArrayReader(XML.toCharArray());
        Source source = new StreamSource(r);
        validator.validate(source);   
    }
}
