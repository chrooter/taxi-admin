/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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


	public ByteArrayOutputStream generateXMLOrders(Orders ot) {
		FileOutputStream fos = null;
		ByteArrayOutputStream result = null;
		try {
			//Создаём контекст.
			JAXBContext jaxbCtx = JAXBContext.newInstance(ot.getClass().getPackage().getName());
			//Создаеём, используя контекст, объект для маршаллизации.
			Marshaller marshaller = jaxbCtx.createMarshaller();
			//Задаём свойства кодировки и устанавливаем флаг форматированного вывода.
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			//Создаём ссылку на файл.
			File localNewFile = new File("C:\\test1.xml");
			//Создаём ссылку на поток вывода.
			fos = new FileOutputStream(localNewFile);
			//Маршаллизируем экземпляр класса ConfigType
			//в поток байт и в файл.
			result = new ByteArrayOutputStream();
			marshaller.marshal(ot, result);
			marshaller.marshal(ot, fos);
			return result;
		}
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (JAXBException e2) {
			e2.printStackTrace();
		}
		finally {
			try {
				fos.close();
			}
			catch (IOException e3) {
				e3.printStackTrace();
			}
		}
		return null;
	}

	public ByteArrayOutputStream generateXMLCars(Cars ct) {
		FileOutputStream fos = null;
		ByteArrayOutputStream result = null;
		try {
			//Создаём контекст.
			JAXBContext jaxbCtx = JAXBContext.newInstance(ct.getClass().getPackage().getName());
			//Создаеём, используя контекст, объект для маршаллизации.
			Marshaller marshaller = jaxbCtx.createMarshaller();
			//Задаём свойства кодировки и устанавливаем флаг форматированного вывода.
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			//Создаём ссылку на файл.
			File localNewFile = new File("C:\\test2.xml");
			//Создаём ссылку на поток вывода.
			fos = new FileOutputStream(localNewFile);
			//Маршаллизируем экземпляр класса ConfigType
			//в поток байт и в файл.
			result = new ByteArrayOutputStream();
			marshaller.marshal(ct, result);
			marshaller.marshal(ct, fos);
			return result;
		}
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (JAXBException e2) {
			e2.printStackTrace();
		}
		finally {
			try {
				fos.close();
			}
			catch (IOException e3) {
				e3.printStackTrace();
			}
		}
		return null;
	}


	public ByteArrayOutputStream generateXMLTypes(CarTypes tt) {
		FileOutputStream fos = null;
		ByteArrayOutputStream result = null;
		try {
			//Создаём контекст.
			JAXBContext jaxbCtx = JAXBContext.newInstance(tt.getClass().getPackage().getName());
			//Создаеём, используя контекст, объект для маршаллизации.
			Marshaller marshaller = jaxbCtx.createMarshaller();
			//Задаём свойства кодировки и устанавливаем флаг форматированного вывода.
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			//Создаём ссылку на файл.
			File localNewFile = new File("C:\\test3.xml");
			//Создаём ссылку на поток вывода.
			fos = new FileOutputStream(localNewFile);
			//Маршаллизируем экземпляр класса ConfigType
			//в поток байт и в файл.
			result = new ByteArrayOutputStream();
			marshaller.marshal(tt, result);
			marshaller.marshal(tt, fos);
			return result;
		}
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (JAXBException e2) {
			e2.printStackTrace();
		}
		finally {
			try {
				fos.close();
			}
			catch (IOException e3) {
				e3.printStackTrace();
			}
		}
		return result;
	}
}
