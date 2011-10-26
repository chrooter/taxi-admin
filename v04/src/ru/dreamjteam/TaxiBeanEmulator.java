/*
	Copyright 2011 Open Code Ltd.
    http://www.o-code.ru
 
    $Id: $
*/
package ru.dreamjteam;

import ru.dreamjteam.entity.*;

import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author abolmasov (21.10.2011 10:37:33)
 * @version $Revision: $
 */
public class TaxiBeanEmulator {
	private static boolean checkCar(CarVO car) throws NamingException {
		final LocalCarEntityHome home = BeanProvider.getCarHome();
		try {
			final LocalCarEntity carEntity = home.findByGovNumber(car.getGovNumber());
			return carEntity.getId().equals(car.getId());
		} catch (FinderException e) {
			return true;
		}
	}
	private static boolean checkCarType(CarTypeVO carType) throws NamingException {
		final LocalCarTypeEntityHome home = BeanProvider.getCarTypeHome();
		try {
			final LocalCarTypeEntity carTypeEntity = home.findByName(carType.getName());
			return carTypeEntity.getId().equals(carType.getId());
		} catch (FinderException e) {
			return true;
		}
	}
	public static CarVO getCar(Integer Id) throws FinderException, NamingException {
		final LocalCarEntityHome home = BeanProvider.getCarHome();
		final LocalCarEntity carEntity = home.findByPrimaryKey(Id);
		return carEntity.getCarVO(true);
	}
	public static List<CarVO> getCars() throws NamingException, FinderException {
		List<CarVO> cars = new LinkedList<CarVO>();
		final LocalCarEntityHome home = BeanProvider.getCarHome();
		final Collection carEntities = home.findAll();
		for (Object carEntity : carEntities)
			cars.add(((LocalCarEntity) carEntity).getCarVO(true));
		return cars;
	}
	public static void createCar(CarVO car) throws CreateException, NamingException {
		if (!checkCar(car))
			throw new DuplicateKeyException("Автомобиль с таким гос. номером уже существует");
		final LocalCarEntityHome home = BeanProvider.getCarHome();
		home.createCar(car.getGovNumber(), car.getRunning(), car.getModel(), car.getCarTypeId());
	}
	public static void updateCar(CarVO car) throws FinderException, NamingException, DuplicateKeyException {
		if (!checkCar(car))
			throw new DuplicateKeyException("Автомобиль с таким гос. номером уже существует");
		final LocalCarEntityHome home = BeanProvider.getCarHome();
		final LocalCarEntity carEntity = home.findByPrimaryKey(car.getId());
		carEntity.setCarVO(car);
	}
	public static void deleteCar(Integer Id) throws FinderException, NamingException, RemoveException {
		final LocalCarEntityHome home = BeanProvider.getCarHome();
		final LocalCarEntity carEntity = home.findByPrimaryKey(Id);
		carEntity.remove();
	}
	public static CarTypeVO getCarType(Integer Id) throws FinderException, NamingException {
		final LocalCarTypeEntityHome home = BeanProvider.getCarTypeHome();
		final LocalCarTypeEntity carTypeEntity = home.findByPrimaryKey(Id);
		return carTypeEntity.getCarTypeVO(true);
	}
	public static List<CarTypeVO> getCarTypes() throws NamingException, FinderException {
		List<CarTypeVO> carTypes = new LinkedList<CarTypeVO>();
		final LocalCarTypeEntityHome home = BeanProvider.getCarTypeHome();
		final Collection carTypeEntities = home.findAll();
		for (Object carTypeEntity : carTypeEntities)
			carTypes.add(((LocalCarTypeEntity) carTypeEntity).getCarTypeVO(true));
		return carTypes;
	}
	public static void createCarType(CarTypeVO carType) throws CreateException, NamingException {
		if (!checkCarType(carType))
			throw new DuplicateKeyException("Тип с таким названием уже существует");
		final LocalCarTypeEntityHome home = BeanProvider.getCarTypeHome();
		home.createCarType(carType.getName(), carType.getCostPerKm(), carType.getSeatCapacity());
	}
	public static void updateCarType(CarTypeVO carType) throws FinderException, NamingException, DuplicateKeyException {
		if (!checkCarType(carType))
			throw new DuplicateKeyException("Тип с таким названием уже существует");
		final LocalCarTypeEntityHome home = BeanProvider.getCarTypeHome();
		final LocalCarTypeEntity carEntity = home.findByPrimaryKey(carType.getId());
		carEntity.setCarTypeVO(carType);
	}
	public static void deleteCarType(Integer Id) throws FinderException, NamingException, RemoveException {
		final LocalCarTypeEntityHome home = BeanProvider.getCarTypeHome();
		final LocalCarTypeEntity carTypeEntity = home.findByPrimaryKey(Id);
		carTypeEntity.remove();
	}
	public static OrderVO getOrder(Integer Id) throws FinderException, NamingException {
		final LocalOrderEntityHome home = BeanProvider.getOrderHome();
		final LocalOrderEntity orderEntity = home.findByPrimaryKey(Id);
		return orderEntity.getOrderVO(true);
	}
	public static void createOrder(OrderVO order) throws CreateException, NamingException {
		final LocalOrderEntityHome home = BeanProvider.getOrderHome();
		home.createOrder(order.getAddrDep(), order.getAddrDest(), order.getDistance(), order.getPassengers(), order.getPhone(), order.getCarId());
	}
	public static void updateOrder(OrderVO order) throws FinderException, NamingException {
		final LocalOrderEntityHome home = BeanProvider.getOrderHome();
		final LocalOrderEntity orderEntity = home.findByPrimaryKey(order.getId());
		orderEntity.setOrderVO(order);
	}
	public static void deleteOrder(Integer Id) throws RemoveException, NamingException, FinderException {
		final LocalOrderEntityHome home = BeanProvider.getOrderHome();
		final LocalOrderEntity orderEntity = home.findByPrimaryKey(Id);
		orderEntity.remove();
	}
	public static List<OrderVO> getOrders() throws NamingException, FinderException {
		List<OrderVO> orders = new LinkedList<OrderVO>();
		final LocalOrderEntityHome home = BeanProvider.getOrderHome();
		final Collection carTypeEntities = home.findAll();
		for (Object orderEntity : carTypeEntities)
			orders.add(((LocalOrderEntity) orderEntity).getOrderVO(true));
		return orders;
	}
}
