package ru.dreamjteam.beans;

import binds.Car;
import binds.Logs;
import binds.CarType;
import binds.CarTypes;
import binds.Cars;
import binds.Order;
import binds.Orders;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import ru.dreamjteam.entity.*;

import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import session.XMLSessionBeanLocal;


public class TaxiBeanEmulator {
    
        //--------------------------- Cars --------------------------
    
	private static boolean checkCar(String govNumber) throws NamingException {
		final CarEntityBeanLocalHome home = BeanProvider.getCarHome();
		try {
			final CarEntityBeanLocal carEntity = home.findByGovNumber(govNumber);
			return false;
		} catch (FinderException e) {
			return true;
		}
	}
        
        private static boolean checkCar2(CarVO car) throws NamingException {
		final CarEntityBeanLocalHome home = BeanProvider.getCarHome();
		try {
			final CarEntityBeanLocal carEntity = home.findByPrimaryKey(car.getId());
			return false;
		} catch (FinderException e) {
			return true;
		}
	}

  

        private static void checkCar4(Cars cars) throws NamingException,CreateException,FinderException{
            CarTypeEntityBeanLocal ctl;
            CarTypeEntityBeanLocalHome cthome;
            for (Car car: cars.getCar()) {
            cthome = BeanProvider.getCarTypeHome();
            try {
                ctl = cthome.findByName(car.getCarType().getName());
              
            } catch (FinderException e) {
                
                   cthome.createCarType(car.getCarType().getName(), car.getCarType().getCostPerKm(), car.getCarType().getCapacity());          
                   

            }
            }
         
        }

//        public static void checkCarAll(Cars cars) throws DuplicateKeyException,NamingException,CreateException,FinderException {
//             for (Car car: cars.getCar()) {
//                 if (!checkCar(car.getGovNumber()))
//                     throw new DuplicateKeyException("Car01");
//             }
//             if (!checkCar3(cars))
//                            throw new DuplicateKeyException("Car03");
//             if (!checkCar4(cars))
//                            throw new DuplicateKeyException("Car04");
//        }
                
	public static CarVO getCar(Integer Id) throws FinderException, NamingException {
		final CarEntityBeanLocalHome home = BeanProvider.getCarHome();
		final CarEntityBeanLocal carEntity = home.findByPrimaryKey(Id);
		return carEntity.getCarVO(true,false);
	}
        
	public static List<CarVO> getCars(String field,String type) throws NamingException, FinderException {
		List<CarVO> cars = new LinkedList<CarVO>();
		final CarEntityBeanLocalHome home = BeanProvider.getCarHome();
		final Collection carEntities;
                if (field == null) {
                    carEntities = home.findAll();
                } else {
                    carEntities = home.findSort(field,type);
                }
		for (Object carEntity : carEntities)
			cars.add(((CarEntityBeanLocal) carEntity).getCarVO(true,false));
		return cars;
	}
        
	public static void createCar(CarVO car) throws CreateException, NamingException {
		if (!checkCar(car.getGovNumber()))
			throw new DuplicateKeyException("Car01");
		final CarEntityBeanLocalHome home = BeanProvider.getCarHome();
		home.createCar(car.getGovNumber(), car.getColor(), car.getModel(), car.getCarTypeId());
	}
        
        public static void createCars(List<CarVO> cars) throws CreateException, NamingException, FinderException {
                final CarEntityBeanLocalHome home = BeanProvider.getCarHome();
                CarTypeEntityBeanLocalHome cthome = BeanProvider.getCarTypeHome();
                CarTypeEntityBeanLocal ctl;
                //checkCarAll(convertCarVOListToCars(cars,true));
                for (CarVO car : cars)
                {
                    if (!checkCar(car.getGovNumber()))
                        throw new DuplicateKeyException("Car01");
                   
                    ctl = cthome.findByName(car.getCarTypeVO().getName());
                    home.createCar(car.getGovNumber(), car.getColor(), car.getModel(), ctl.getId());

                }
	}
        
        public static void createOrUpdateCars(List<CarVO> cars, boolean update) throws CreateException, NamingException, FinderException {
                final CarEntityBeanLocalHome home = BeanProvider.getCarHome();
                for (CarVO car : cars)
                {
                    if (update==true)
                        if (!checkCar(car.getGovNumber()))
                        {
                            final CarEntityBeanLocal carEntity = home.findByGovNumber(car.getGovNumber());
                            carEntity.setCarVO(car);
                        }
                        else if (!checkCar2(car))
                        {
                            final CarEntityBeanLocal carEntity = home.findByPrimaryKey(car.getId());
                            carEntity.setCarVO(car);
                        }
                        else
                        {
                            final CarEntityBeanLocal carEntity = home.createCar(car.getGovNumber(), car.getColor(), car.getModel(), car.getCarTypeId());
                            carEntity.setId(car.getId());
                        }
                    if (update==false)
                        if (checkCar2(car) && checkCar(car.getGovNumber()))
                        {
                            final CarEntityBeanLocal carEntity = home.createCar(car.getGovNumber(), car.getColor(), car.getModel(), car.getCarTypeId());
                            carEntity.setId(car.getId());
                        }
                }
	}
        
	public static void updateCar(CarVO car) throws FinderException, NamingException, DuplicateKeyException {
		if (!checkCar(car.getGovNumber()))
			throw new DuplicateKeyException("Автомобиль с гос. номером "+car.getGovNumber()+" уже существует");
		final CarEntityBeanLocalHome home = BeanProvider.getCarHome();
		final CarEntityBeanLocal carEntity = home.findByPrimaryKey(car.getId());
		carEntity.setCarVO(car);
	}
        
	public static void deleteCar(Integer Id) throws FinderException, NamingException, RemoveException, SQLIntegrityConstraintViolationException {
		final CarEntityBeanLocalHome home = BeanProvider.getCarHome();
		final CarEntityBeanLocal carEntity = home.findByPrimaryKey(Id);
                CarVO car = carEntity.getCarVO(true,false);
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
        
        private static boolean checkCarType2(Integer carTypeId) throws NamingException {
		final CarTypeEntityBeanLocalHome home = BeanProvider.getCarTypeHome();
		try {
			final CarTypeEntityBeanLocal carTypeEntity = home.findByPrimaryKey(carTypeId);
			return false;
		} catch (FinderException e) {
			return true;
		}
	}
        
        public static int getCostPerKm(Integer Id) throws NamingException, FinderException
        {
            final CarTypeEntityBeanLocalHome home = BeanProvider.getCarTypeHome();
            final CarTypeEntityBeanLocal carTypeEntity = home.findByPrimaryKey(Id);
            return carTypeEntity.getCarTypeVO(false,false).getCostPerKm();
        }
        
	public static CarTypeVO getCarType(Integer Id) throws FinderException, NamingException {
		final CarTypeEntityBeanLocalHome home = BeanProvider.getCarTypeHome();
		final CarTypeEntityBeanLocal carTypeEntity = home.findByPrimaryKey(Id);
		return carTypeEntity.getCarTypeVO(true,false);
	}
        
	public static List<CarTypeVO> getCarTypes(String field,String type) throws NamingException, FinderException {
		List<CarTypeVO> carTypes = new LinkedList<CarTypeVO>();
		final CarTypeEntityBeanLocalHome home = BeanProvider.getCarTypeHome();
                final Collection carTypeEntities;
                if (field == null) {
                    carTypeEntities = home.findAll();
                } else {
                    carTypeEntities = home.findSort(field,type);
                }
		for (Object carTypeEntity : carTypeEntities)
			carTypes.add(((CarTypeEntityBeanLocal) carTypeEntity).getCarTypeVO(true,false));
		return carTypes;
	}
        
	public static void createCarType(CarTypeVO carType) throws CreateException, NamingException {
		if (!checkCarType(carType))
			throw new DuplicateKeyException("Тип с названием "+carType.getName()+" уже существует");
		final CarTypeEntityBeanLocalHome home = BeanProvider.getCarTypeHome();
		home.createCarType(carType.getName(), carType.getCostPerKm(), carType.getCapacity());
	}
        
        public static void createCarTypes(List<CarTypeVO> carTypes) throws CreateException, NamingException {
                final CarTypeEntityBeanLocalHome home = BeanProvider.getCarTypeHome();
                for (CarTypeVO carType : carTypes)
                {
                    if (!checkCarType(carType))
                            throw new DuplicateKeyException("CarType01");
//                    if (!checkCarType2(carType))
//                            throw new DuplicateKeyException("Тип с id "+carType.getId()+" уже существует");
                    home.createCarType(carType.getName(), carType.getCostPerKm(), carType.getCapacity());
                }
	}
        
        public static void createOrUpdateCarTypes(List<CarTypeVO> carTypes, boolean update) throws CreateException, NamingException, FinderException {
                final CarTypeEntityBeanLocalHome home = BeanProvider.getCarTypeHome();
                for (CarTypeVO carType : carTypes)
                {
                    if (update==true)
                        if (!checkCarType(carType))
                        {
                            final CarTypeEntityBeanLocal carTypeEntity = home.findByName(carType.getName());
                            carTypeEntity.setCarTypeVO(carType);
                        }
                        else if (!checkCarType2(carType.getId()))
                        {
                            final CarTypeEntityBeanLocal carTypeEntity = home.findByPrimaryKey(carType.getId());
                            carTypeEntity.setCarTypeVO(carType);
                        }
                        else
                        {
                            final CarTypeEntityBeanLocal carTypeEntity = home.createCarType(carType.getName(), carType.getCostPerKm(), carType.getCapacity());
                            carTypeEntity.setId(carType.getId());
                        }
                    if (update==false)
                        if (checkCarType(carType) && checkCarType2(carType.getId()))
                        {
                            final CarTypeEntityBeanLocal carTypeEntity = home.createCarType(carType.getName(), carType.getCostPerKm(), carType.getCapacity());
                            carTypeEntity.setId(carType.getId());
                        }
                }
	}
        
	public static void updateCarType(CarTypeVO carType) throws FinderException, NamingException, DuplicateKeyException {
		if (!checkCarType(carType))
			throw new DuplicateKeyException("Тип с названием "+carType.getName()+" уже существует");
		final CarTypeEntityBeanLocalHome home = BeanProvider.getCarTypeHome();
		final CarTypeEntityBeanLocal carTypeEntity = home.findByPrimaryKey(carType.getId());
		carTypeEntity.setCarTypeVO(carType);
	}
        
	public static void deleteCarType(Integer Id) throws FinderException, NamingException, RemoveException, SQLIntegrityConstraintViolationException {
		final CarTypeEntityBeanLocalHome home = BeanProvider.getCarTypeHome();
		final CarTypeEntityBeanLocal carTypeEntity = home.findByPrimaryKey(Id);
                if (!carTypeEntity.getCarTypeVO(true,false).getCarVOs().isEmpty())
			throw new SQLIntegrityConstraintViolationException("Удаление невозможно. Существуют автомобили данного типа.");
		carTypeEntity.remove();
	}
        
        //--------------------------- Orders --------------------------
        
	public static OrderVO getOrder(Integer Id) throws FinderException, NamingException {
		final OrderEntityBeanLocalHome home = BeanProvider.getOrderHome();
		final OrderEntityBeanLocal orderEntity = home.findByPrimaryKey(Id);
		return orderEntity.getOrderVO(true,false);
	}
        
	public static void createOrder(OrderVO order) throws CreateException, NamingException {
		final OrderEntityBeanLocalHome home = BeanProvider.getOrderHome();
		home.createOrder(order.getStartPoint(), order.getPassengers(), order.getPhone());
	}

        public static void createOrders(List<OrderVO> orders) throws CreateException, NamingException, FinderException {
                final OrderEntityBeanLocalHome home = BeanProvider.getOrderHome();
                OrderEntityBeanLocal ol;
                CarEntityBeanLocalHome chome = BeanProvider.getCarHome();
                CarEntityBeanLocal cl;
                for (OrderVO order : orders)
                {
//                    if (!checkOrder(order))
//                            throw new DuplicateKeyException("Заявка с id "+order.getId()+" уже существует");
                   home.createOrder(order.getStartPoint(), order.getPassengers(), order.getPhone());
                   ol = home.findByLast();
                   cl = chome.findByGovNumber(order.getCar().getGovNumber());
                   ol.setCarId(cl.getId());
                }
	}

        private static boolean checkOrder(OrderVO order) throws NamingException {
		final CarTypeEntityBeanLocalHome home = BeanProvider.getCarTypeHome();
		try {
			final CarTypeEntityBeanLocal carTypeEntity = home.findByPrimaryKey(order.getId());
			return false;
		} catch (FinderException e) {
			return true;
		}
	}

        private static boolean checkOrder1(Order order) throws NamingException {
		final PointEntityBeanLocalHome home = BeanProvider.getPointHome();
		try {
			final PointEntityBeanLocal pointEntity = home.findByPrimaryKey(order.getStartPoint());
			return false;
		} catch (FinderException e) {
			return true;
		}
	}

        private static boolean checkOrder2(Order order) throws NamingException {
		final CarEntityBeanLocalHome home = BeanProvider.getCarHome();
		try {
			final CarEntityBeanLocal carEntity = home.findByGovNumber(order.getCar().getGovNumber());
			return false;
		} catch (FinderException e) {
			return true;
		}
	}


        public static void createOrUpdateOrders(List<OrderVO> orders, boolean update) throws CreateException, NamingException, FinderException {
                final OrderEntityBeanLocalHome home = BeanProvider.getOrderHome();
                for (OrderVO order : orders)
                {
                    if (update==true)
                        if (!checkOrder(order))
                        {
                            final OrderEntityBeanLocal orderEntity = home.findByPrimaryKey(order.getId());
                            orderEntity.setOrderVO(order);
                        }
                        else
                        {
                            final OrderEntityBeanLocal orderEntity = home.createOrder(order.getStartPoint(), order.getPassengers(), order.getPhone());
                            orderEntity.setId(order.getId());
                        }
                    if (update==false)
                        if (checkOrder(order))
                        {
                            final OrderEntityBeanLocal carTypeEntity = home.createOrder(order.getStartPoint(), order.getPassengers(), order.getPhone());
                            carTypeEntity.setId(order.getId());
                        }
                }
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
        
	public static List<OrderVO> getOrders(String field,String type, Boolean isExport) throws NamingException, FinderException {
		List<OrderVO> orders = new LinkedList<OrderVO>();
		final OrderEntityBeanLocalHome home = BeanProvider.getOrderHome();
		final Collection orderEntities;
                if (field == null) {
                    orderEntities = home.findAll();
                } else {
                    orderEntities = home.findSort(field,type);
                }
		for (Object orderEntity : orderEntities)
			orders.add(((OrderEntityBeanLocal) orderEntity).getOrderVO(true,isExport));
		return orders;
	}
        
        public static List<OrderVO> getNewOrders() throws NamingException, FinderException {
		List<OrderVO> orders = new LinkedList<OrderVO>();
		final OrderEntityBeanLocalHome home = BeanProvider.getOrderHome();
		final Collection orderEntities = home.findByStatus("new");
		for (Object orderEntity : orderEntities)
			orders.add(((OrderEntityBeanLocal) orderEntity).getOrderVO(false,false));
		return orders;
	}
        
        //--------------------------- Points --------------------------
        
        public static int createChain(List<PointVO> chain) throws CreateException, NamingException {
		final PointEntityBeanLocalHome home = BeanProvider.getPointHome();
		PointEntityBeanLocal endPoint = home.createPoint(chain.get(chain.size()-1).getAddress(), null);
                int nextId = endPoint.getId();
                
                if (chain.size()-2>=0)
                for (int i=chain.size()-2; i>=0; i--)
                    nextId = home.createPoint(chain.get(i).getAddress(), nextId).getId();
                return nextId;
	}
        
        public static void updateChain(Integer id, LinkedList<PointVO> chain) throws FinderException, NamingException, CreateException, RemoveException {
		final PointEntityBeanLocalHome home = BeanProvider.getPointHome();
                boolean flag = false;
                int additionNextId = 0;
                
                final Collection pointEntities = home.findChain(id);
                if (pointEntities.size()<chain.size())
                {
                    List<PointVO> addition = new LinkedList<PointVO>();
                    for(int i=0; i<Math.abs(chain.size()-pointEntities.size()); i++)
                        addition.add(chain.get(pointEntities.size()+i));
                    additionNextId = createChain(addition);
                    flag = true;
                }
                if (pointEntities.size()>chain.size())
                {
                    List<PointVO> addition = new LinkedList<PointVO>();
                    for(int i=0; i<Math.abs(chain.size()-pointEntities.size()); i++)
                        addition.add(((PointEntityBeanLocal)((List<PointEntityBeanLocal>)pointEntities).get(chain.size()+i)).getPointVO()); //
                    deleteChain(addition.get(0).getId());
                    flag = true;
                }
                
                Integer nextId = id;
                try {
                for (int i=0; i<chain.size(); i++)
                {
                    PointEntityBeanLocal pointEntity = home.findByPrimaryKey(nextId);
                    nextId = pointEntity.getNextId();
                    if (flag==true && (nextId == null || nextId == 0))
                    {
                        pointEntity.setPointVO(new PointVO(pointEntity.getId(), chain.get(i).getAddress(), additionNextId));
                        break;
                    }
                    else pointEntity.setPointVO(new PointVO(pointEntity.getId(), chain.get(i).getAddress(), nextId));
                    if (pointEntities.size()>chain.size() && i==chain.size()-1)
                        pointEntity.setPointVO(new PointVO(pointEntity.getId(), chain.get(i).getAddress(), null));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
	}
        
        public static List<PointVO> getChain(Integer id) throws FinderException, NamingException {
                List<PointVO> chain = new LinkedList<PointVO>();
		final PointEntityBeanLocalHome home = BeanProvider.getPointHome();
		final Collection pointEntities = home.findChain(id);
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
        
        //--------------------------- XML --------------------------
        
        public static String getXML(List<CarTypeVO> carTypes) throws NamingException, CreateException {
		final XMLSessionBeanLocal xmlBean = BeanProvider.getXMLHome();
                CarTypes ct = convertCarTypeVOListToCarTypes(carTypes);
                
                return xmlBean.toXML(ct);
	}
        
        public static String getXMLCars(List<CarVO> cars) throws NamingException, CreateException {
		final XMLSessionBeanLocal xmlBean = BeanProvider.getXMLHome();
                Cars c = convertCarVOListToCars(cars, true);
                
                return xmlBean.toXML(c);
	}

        public static String getXMLLogs(Logs logs) throws NamingException, CreateException {
		final XMLSessionBeanLocal xmlBean = BeanProvider.getXMLHome();
                return xmlBean.toXML(logs);
	}
        
        public static String getXMLOrders(List<OrderVO> orders) throws NamingException, CreateException {
		final XMLSessionBeanLocal xmlBean = BeanProvider.getXMLHome();
                Orders o = convertOrderVOListToOrders(orders, true);
                
                return xmlBean.toXML(o);
	}
        
        public static List<CarTypeVO> parseXML(String carTypes) throws NamingException,FinderException, CreateException {
		final XMLSessionBeanLocal xmlBean = BeanProvider.getXMLHome();
                CarTypes ct;
                ct = xmlBean.parseXML(carTypes, CarTypes.class);

                List<CarTypeVO> carTypesList = null;
                if (ct != null) {
                carTypesList = convertCarTypesToCarTypeVOList(ct,true);
                }
                return carTypesList;
	}
        
        public static List<CarVO> parseXMLCars(String cars) throws NamingException,FinderException, CreateException {
		final XMLSessionBeanLocal xmlBean = BeanProvider.getXMLHome();
                boolean bool = true;
                Cars c = xmlBean.parseXML(cars, Cars.class);
                for (Car car:c.getCar()) {
                    if (!checkCar(car.getGovNumber())) bool = false;
                }
                if (bool) {
                    checkCar4(c);
                }
                
                List<CarVO> carsList = null;
                if (c != null) {
                carsList = convertCarsToCarVOList(c,true);
            }
                return carsList;
         
               
	}

        public static List<OrderVO> parseXMLOrders(String orders) throws NamingException,FinderException, CreateException {
		final XMLSessionBeanLocal xmlBean = BeanProvider.getXMLHome();
                Orders o = xmlBean.parseXML(orders, Orders.class);
                boolean bool = true;
                Cars cars = new Cars();
                for (Order order:o.getOrder()) {
                if (checkOrder1(order)) {
                    throw new DuplicateKeyException("Order02");
                }
                if (checkOrder2(order)) {

                    if (!checkCar(order.getCar().getGovNumber())) bool = false;
                    if (bool) {
                        cars.setCar(order.getCar());
                        checkCar4(cars);
                    }
                    createCars(convertCarsToCarVOList(cars,true));
                }
                }
                
                List<OrderVO> ordersList = null;
                if (o != null) {
                ordersList = convertOrdersToOrderVOList(o,true);
                }
                return ordersList;
	}
        
        private static CarTypes convertCarTypeVOListToCarTypes(List<CarTypeVO> carTypes)
        {
            CarTypes ct = new CarTypes();
            for (int i=0; i<carTypes.size(); i++)
                {
                    CarType singleCT = new CarType();
                    CarTypeVO ctv = carTypes.get(i);
                    singleCT.setId(ctv.getId());
                    singleCT.setName(ctv.getName());
                    singleCT.setCapacity(ctv.getCapacity());
                    singleCT.setCostPerKm(ctv.getCostPerKm());
                   
                    ct.getCarType().add(singleCT);
                }
            return ct;
        }
        
        private static Cars convertCarVOListToCars(List<CarVO> cars, boolean depend)
        {
            Cars c = new Cars();
            for (int i=0; i<cars.size(); i++)
                {
                    Car singleC = new Car();
                    CarVO cv = cars.get(i);
                    singleC.setId(cv.getId());
                    singleC.setModel(cv.getModel());
                    singleC.setGovNumber(cv.getGovNumber());
                    singleC.setColor(cv.getColor());
                    singleC.setCarTypeId(cv.getCarTypeId());
                    if (depend)
                    {
                        List<CarTypeVO> newlist = new ArrayList<CarTypeVO>();
                        newlist.add(cv.getCarTypeVO());
                        singleC.setCarType(convertCarTypeVOListToCarTypes(newlist).getCarType().get(0));
                    }
                    
                    c.getCar().add(singleC);
                        
                }
            return c;
        }
        
        private static Orders convertOrderVOListToOrders(List<OrderVO> orders, boolean depend)
        {
            Orders o = new Orders();
            for (int i=0; i<orders.size(); i++)
                {
                    Order singleO = new Order();
                    OrderVO ov = orders.get(i);
                    singleO.setId(ov.getId());
                    singleO.setTimeOrd(ov.getTimeDone() == null ? null : new SimpleDateFormat("dd.MM.yyyy HH:mm").format(ov.getTimeOrd()));
                    singleO.setTimeDone(ov.getTimeDone() == null ? null : new SimpleDateFormat("dd.MM.yyyy HH:mm").format(ov.getTimeDone()));
                    singleO.setStartPoint(ov.getStartPoint());
                    singleO.setPassengers(ov.getPassengers());
                    singleO.setDistance(ov.getDistance());
                    singleO.setCost(ov.getCost());
                    singleO.setPhone(ov.getPhone());
                    singleO.setCarId(ov.getCarId());
                    if (depend) {
                        List<CarVO> newlist = new ArrayList<CarVO>();
                        newlist.add(orders.get(i).getCar());
                        singleO.setCarId(ov.getCarId());
                        singleO.setCar(convertCarVOListToCars(newlist,true).getCar().get(0));
                    }
                    o.getOrder().add(singleO);
                }
            return o;
        }
        
        private static List<CarTypeVO> convertCarTypesToCarTypeVOList(CarTypes ct, boolean depend) throws FinderException,NamingException
        {
            List<CarTypeVO> carTypes = new LinkedList<CarTypeVO>();
            if (ct != null) {
            List<CarType> ctList = ct.getCarType();
            List<CarVO> cars = new LinkedList<CarVO>();
            final CarEntityBeanLocalHome home = BeanProvider.getCarHome();
            Collection carEntities;
            for (int i=0; i<ctList.size(); i++)
                {

                    CarTypeVO carType = new CarTypeVO(ctList.get(i).getId(), ctList.get(i).getCapacity(), ctList.get(i).getCostPerKm(), ctList.get(i).getName());
                    if (depend) {
                        carEntities = home.findByType(ctList.get(i).getId());
                        for (Object carEntity : carEntities)
			cars.add(((CarEntityBeanLocal) carEntity).getCarVO(true,false));
                        carType.setCarVOs(cars);
                    }
                    carTypes.add(carType);
                }
            }
            return carTypes;
        }
        
        private static List<CarVO> convertCarsToCarVOList(Cars c, boolean depend) throws FinderException,NamingException
        {
            List<CarVO> cars = new LinkedList<CarVO>();
            if (c != null) {
            List<Car> cList = c.getCar();
            List<OrderVO> orders = new LinkedList<OrderVO>();
            CarTypeEntityBeanLocalHome chome = BeanProvider.getCarTypeHome();
            CarTypeEntityBeanLocal cl;
            final OrderEntityBeanLocalHome home = BeanProvider.getOrderHome();
            Collection orderEntities;
            
            for (int i=0; i<cList.size(); i++)
            {
                CarVO car = new CarVO(cList.get(i).getId(), cList.get(i).getModel(), cList.get(i).getGovNumber(), cList.get(i).getColor(), cList.get(i).getCarTypeId());
                if (depend)
                {
                    orderEntities = home.findByCarAndStatus(cList.get(i).getId(), "STATUS");
                    for (Object orderEntity : orderEntities)
			orders.add(((OrderEntityBeanLocal) orderEntity).getOrderVO(true,false));
                    car.setOrderVOs(orders);
                    orders.clear();
                    orderEntities.clear();
                    orderEntities = home.findByCarAndStatus(cList.get(i).getId(), "EXECUTING");
                    for (Object orderEntity : orderEntities)
			orders.add(((OrderEntityBeanLocal) orderEntity).getOrderVO(true,false));
                    car.setCurrentOrderVOs(orders);
                    cl = chome.findByName(cList.get(i).getCarType().getName());
                    car.setCarTypeVO(cl.getCarTypeVO(false, false));
                }
                cars.add(car);
            }
            }
            return cars;
        }
        
        private static List<OrderVO> convertOrdersToOrderVOList(Orders o, boolean depend) throws FinderException,NamingException
        {
            List<OrderVO> orders = new LinkedList<OrderVO>();
            if (o != null) {
            List<Order> oList = o.getOrder();
            for (int i=0; i<oList.size(); i++)
                {
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");

                    final String strTimeOrd = oList.get(i).getTimeOrd();
                    Timestamp timeOrd = null;
                    try {
                            timeOrd = strTimeOrd == null || strTimeOrd.trim().isEmpty() ? null : new Timestamp(dateFormatter.parse(strTimeOrd).getTime());
                    } catch (ParseException e) {
                            throw new IllegalStateException(e);
                    }
                    
                    final String strTimeDone = oList.get(i).getTimeDone();
                    Timestamp timeDone = null;
                    try {
                            timeDone = strTimeDone == null || strTimeDone.trim().isEmpty() ? null : new Timestamp(dateFormatter.parse(strTimeDone).getTime());
                    } catch (ParseException e) {
                            throw new IllegalStateException(e);
                    }
                    Cars newcars = new Cars();
                    newcars.setCar(oList.get(i).getCar());
                    OrderVO order = new OrderVO(oList.get(i).getId(), oList.get(i).getCost(), oList.get(i).getPhone(), oList.get(i).getDistance(), oList.get(i).getPassengers(), timeDone, timeOrd, oList.get(i).getStartPoint(), oList.get(i).getCarId(), oList.get(i).getStatus(), convertCarsToCarVOList(newcars,true).get(0));
                    //if ((depend) && (oList.get(i).getCar() != null))
                    //order.setCar(new CarVO(oList.get(i).getCar().getId(), oList.get(i).getCar().getModel(), oList.get(i).getCar().getGovNumber(), oList.get(i).getCar().getColor(), oList.get(i).getCar().getCarTypeId()));
                    orders.add(order);
                }
            }
            return orders;
        }
}
