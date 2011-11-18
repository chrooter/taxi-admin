package ru.dreamjteam.beans;

import java.sql.SQLIntegrityConstraintViolationException;
import ru.dreamjteam.entity.*;

import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class TaxiBeanEmulator {
    
        //--------------------------- Cars --------------------------
    
	private static boolean checkCar(CarVO car) throws NamingException {
		final CarEntityBeanLocalHome home = BeanProvider.getCarHome();
		try {
			final CarEntityBeanLocal carEntity = home.findByGovNumber(car.getGovNumber());
			return carEntity.getId().equals(car.getId());
		} catch (FinderException e) {
			return true;
		}
	}
                
	public static CarVO getCar(Integer Id) throws FinderException, NamingException {
		final CarEntityBeanLocalHome home = BeanProvider.getCarHome();
		final CarEntityBeanLocal carEntity = home.findByPrimaryKey(Id);
		return carEntity.getCarVO(true);
	}
        
	public static List<CarVO> getCars() throws NamingException, FinderException {
		List<CarVO> cars = new LinkedList<CarVO>();
		final CarEntityBeanLocalHome home = BeanProvider.getCarHome();
		final Collection carEntities = home.findAll();
		for (Object carEntity : carEntities)
			cars.add(((CarEntityBeanLocal) carEntity).getCarVO(true));
		return cars;
	}
        
	public static void createCar(CarVO car) throws CreateException, NamingException {
		if (!checkCar(car))
			throw new DuplicateKeyException("Автомобиль с таким гос. номером уже существует");
		final CarEntityBeanLocalHome home = BeanProvider.getCarHome();
		home.createCar(car.getGovNumber(), car.getColor(), car.getModel(), car.getCarTypeId());
	}
        
	public static void updateCar(CarVO car) throws FinderException, NamingException, DuplicateKeyException {
		if (!checkCar(car))
			throw new DuplicateKeyException("Автомобиль с таким гос. номером уже существует");
		final CarEntityBeanLocalHome home = BeanProvider.getCarHome();
		final CarEntityBeanLocal carEntity = home.findByPrimaryKey(car.getId());
		carEntity.setCarVO(car);
	}
        
	public static void deleteCar(Integer Id) throws FinderException, NamingException, RemoveException {
		final CarEntityBeanLocalHome home = BeanProvider.getCarHome();
		final CarEntityBeanLocal carEntity = home.findByPrimaryKey(Id);
		carEntity.remove();
	}
        
        //--------------------------- Types --------------------------
        
        private static boolean checkCarType(CarTypeVO carType) throws NamingException {
		final CarTypeEntityBeanLocalHome home = BeanProvider.getCarTypeHome();
		try {
			final CarTypeEntityBeanLocal carTypeEntity = home.findByName(carType.getName());
			return carTypeEntity.getId().equals(carType.getId());
		} catch (FinderException e) {
			return true;
		}
	}
        
	public static CarTypeVO getCarType(Integer Id) throws FinderException, NamingException {
		final CarTypeEntityBeanLocalHome home = BeanProvider.getCarTypeHome();
		final CarTypeEntityBeanLocal carTypeEntity = home.findByPrimaryKey(Id);
		return carTypeEntity.getCarTypeVO(true);
	}
        
	public static List<CarTypeVO> getCarTypes() throws NamingException, FinderException {
		List<CarTypeVO> carTypes = new LinkedList<CarTypeVO>();
		final CarTypeEntityBeanLocalHome home = BeanProvider.getCarTypeHome();
		final Collection carTypeEntities = home.findAll();
		for (Object carTypeEntity : carTypeEntities)
			carTypes.add(((CarTypeEntityBeanLocal) carTypeEntity).getCarTypeVO(true));
		return carTypes;
	}
        
	public static void createCarType(CarTypeVO carType) throws CreateException, NamingException {
		if (!checkCarType(carType))
			throw new DuplicateKeyException("Тип с таким названием уже существует");
		final CarTypeEntityBeanLocalHome home = BeanProvider.getCarTypeHome();
		home.createCarType(carType.getName(), carType.getCostPerKm(), carType.getCapacity());
	}
        
	public static void updateCarType(CarTypeVO carType) throws FinderException, NamingException, DuplicateKeyException {
		if (!checkCarType(carType))
			throw new DuplicateKeyException("Тип с таким названием уже существует");
		final CarTypeEntityBeanLocalHome home = BeanProvider.getCarTypeHome();
		final CarTypeEntityBeanLocal carTypeEntity = home.findByPrimaryKey(carType.getId());
		carTypeEntity.setCarTypeVO(carType);
	}
        
	public static void deleteCarType(Integer Id) throws FinderException, NamingException, RemoveException, SQLIntegrityConstraintViolationException {
		final CarTypeEntityBeanLocalHome home = BeanProvider.getCarTypeHome();
		final CarTypeEntityBeanLocal carTypeEntity = home.findByPrimaryKey(Id);
                if (!carTypeEntity.getCarTypeVO(true).getCarVOs().isEmpty())
			throw new SQLIntegrityConstraintViolationException("Удаление невозможно. Существуют автомобили данного типа.");
		carTypeEntity.remove();
	}
        
        //--------------------------- Orders --------------------------
        
	public static OrderVO getOrder(Integer Id) throws FinderException, NamingException {
		final OrderEntityBeanLocalHome home = BeanProvider.getOrderHome();
		final OrderEntityBeanLocal orderEntity = home.findByPrimaryKey(Id);
		return orderEntity.getOrderVO(true);
	}
        
	public static void createOrder(OrderVO order) throws CreateException, NamingException {
		final OrderEntityBeanLocalHome home = BeanProvider.getOrderHome();
		home.createOrder(order.getStartPoint(), order.getPassengers(), order.getPhone());
	}
        
	public static void updateOrder(OrderVO order) throws FinderException, NamingException {
		final OrderEntityBeanLocalHome home = BeanProvider.getOrderHome();
		final OrderEntityBeanLocal orderEntity = home.findByPrimaryKey(order.getId());
		orderEntity.setOrderVO(order);
	}
        
	public static void deleteOrder(Integer Id) throws RemoveException, NamingException, FinderException {
		final OrderEntityBeanLocalHome home = BeanProvider.getOrderHome();
		final OrderEntityBeanLocal orderEntity = home.findByPrimaryKey(Id);
		orderEntity.remove();
	}
        
	public static List<OrderVO> getOrders() throws NamingException, FinderException {
		List<OrderVO> orders = new LinkedList<OrderVO>();
		final OrderEntityBeanLocalHome home = BeanProvider.getOrderHome();
		final Collection orderEntities = home.findAll();
		for (Object orderEntity : orderEntities)
			orders.add(((OrderEntityBeanLocal) orderEntity).getOrderVO(true));
		return orders;
	}
        
        public static List<OrderVO> getNewOrders() throws NamingException, FinderException {
		List<OrderVO> orders = new LinkedList<OrderVO>();
		final OrderEntityBeanLocalHome home = BeanProvider.getOrderHome();
		final Collection orderEntities = home.findByStatus("new");
		for (Object orderEntity : orderEntities)
			orders.add(((OrderEntityBeanLocal) orderEntity).getOrderVO(true));
		return orders;
	}
}
