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
        
	public static void deleteCar(Integer Id) throws FinderException, NamingException, RemoveException, SQLIntegrityConstraintViolationException {
		final CarEntityBeanLocalHome home = BeanProvider.getCarHome();
		final CarEntityBeanLocal carEntity = home.findByPrimaryKey(Id);
                CarVO car = carEntity.getCarVO(true);
                if (!car.getOrderVOs().isEmpty())
			throw new SQLIntegrityConstraintViolationException("Удаление невозможно. Удалите все заявки, связанные с автомобилем.");
                if (!car.getCurrentOrderVOs().isEmpty())
			throw new SQLIntegrityConstraintViolationException("Удаление невозможно. Данный автомобиль выполняет заявку.");
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
			orders.add(((OrderEntityBeanLocal) orderEntity).getOrderVO(false));
		return orders;
	}
        
        //--------------------------- Points --------------------------
        
        public static int createChain(List<PointVO> chain) throws CreateException, NamingException {
		final PointEntityBeanLocalHome home = BeanProvider.getPointHome();
		PointEntityBeanLocal endPoint = home.createPoint(chain.get(chain.size()-1).getAddress(), null);
                int nextId = endPoint.getId();
                
                for (int i=chain.size()-2; i>=0; i--)
                    nextId = home.createPoint(chain.get(i).getAddress(), nextId).getId();
                return nextId;
	}
        
        public static void updateChain(Integer id, List<PointVO> chain) throws FinderException, NamingException {
		final PointEntityBeanLocalHome home = BeanProvider.getPointHome();
                Integer nextId = id;
                for (PointVO pointVO : chain)
                {
                    PointEntityBeanLocal pointEntity = home.findByPrimaryKey(nextId);
                    nextId = pointEntity.getNextId();
                    pointEntity.setPointVO(new PointVO(pointEntity.getId(), pointVO.getAddress(), nextId));
                }
	}
        
        public static List<PointVO> getChain(Integer Id) throws FinderException, NamingException {
                List<PointVO> chain = new LinkedList<PointVO>();
		final PointEntityBeanLocalHome home = BeanProvider.getPointHome();
		final Collection pointEntities = home.findChain(Id);
                for (Object pointEntity : pointEntities)
			chain.add(((PointEntityBeanLocal) pointEntity).getPointVO());
		return chain;
	}
        
        public static void deleteChain(Integer Id) throws RemoveException, NamingException, FinderException {
		final PointEntityBeanLocalHome home = BeanProvider.getPointHome();
                final Collection pointEntities = home.findChain(Id);
                for (Object pointEntity : pointEntities)
                        ((PointEntityBeanLocal)pointEntity).remove();
	}
}
