package ru.dreamjteam.entity;

import ru.dreamjteam.BeanProvider;

import javax.ejb.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * @author abolmasov (18.10.2011 18:27:09)
 * @version $Revision: $
 */
public class OrderEntityBean implements EntityBean {
	private Integer id;
	private Integer cost;
	private Integer distance;
	private Integer carId;
	private Integer passengers;
	private Timestamp timeDest;
	private Timestamp timeOrd;
	private Boolean completed;
	private String addrDep;
	private String addrDest;
	private String phone;
	private EntityContext entityContext;
	private DataSource dataSource;

	public OrderEntityBean() {
	}

	public Integer ejbFindByPrimaryKey(Integer key) throws FinderException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("SELECT ID FROM ORDR WHERE ID = ?");
			statement.setInt(1, key);
			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next())
				throw new ObjectNotFoundException();
			return key;
		} catch (SQLException e) {
			throw new EJBException(e);
		} finally {
			closeConnection(connection, statement);
		}
	}

	public void setEntityContext(EntityContext entityContext) throws EJBException {
		this.entityContext = entityContext;
		try {
			final InitialContext ic = new InitialContext();
			dataSource = (DataSource) ic.lookup("java:TaxiDS");
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}

	public void unsetEntityContext() throws EJBException {
	}

	public void ejbRemove() throws RemoveException, EJBException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("DELETE FROM ORDR WHERE ID = ?");
			statement.setInt(1, id);
			if (statement.executeUpdate() < 1)
				throw new RemoveException();
		} catch (SQLException e) {
			throw new EJBException(e);
		} finally {
			closeConnection(connection, statement);
		}
	}

	public void ejbActivate() throws EJBException {
	}

	public void ejbPassivate() throws EJBException {
	}

	public void ejbLoad() throws EJBException {
		id = ((Integer) entityContext.getPrimaryKey());
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("SELECT TIME_ORD, TIME_DONE, ADDR_DEP, ADDR_DEST, PASSENGERS, COMPLETED, DISTANCE, COST, PHONE, CARID FROM ORDR WHERE ID = ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next())
				throw new NoSuchEntityException();
			timeOrd = resultSet.getTimestamp("TIME_ORD");
			timeDest = resultSet.getTimestamp("TIME_DONE");
			addrDep = resultSet.getString("ADDR_DEP");
			addrDest = resultSet.getString("ADDR_DEST");
			passengers = resultSet.getInt("PASSENGERS");
			completed = resultSet.getBoolean("COMPLETED");
			distance = resultSet.getInt("DISTANCE");
			cost = resultSet.getInt("COST");
			phone = resultSet.getString("PHONE");
			carId = resultSet.getInt("CARID");
		} catch (SQLException e) {
			throw new EJBException(e);
		} finally {
			closeConnection(connection, statement);
		}
	}

	public void ejbStore() throws EJBException {
		id = (Integer) entityContext.getPrimaryKey();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("UPDATE ORDR SET TIME_ORD = ?, TIME_DONE = ?, ADDR_DEP = ?, ADDR_DEST = ?, PASSENGERS = ?, COMPLETED = ?, DISTANCE = ?, COST = ?, PHONE = ?, CARID = ? WHERE ID = ?");
			statement.setTimestamp(1, timeOrd);
			statement.setTimestamp(2, timeDest);
			statement.setString(3, addrDep);
			statement.setString(4, addrDest);
			statement.setInt(5, passengers);
			statement.setBoolean(6, completed);
			statement.setInt(7, distance);
			statement.setInt(8, cost);
			statement.setString(9, phone);
			statement.setInt(10, carId);
			statement.setInt(11, id);
			if (statement.executeUpdate() < 1)
				throw new NoSuchEntityException();
		} catch (SQLException e) {
			throw new EJBException(e);
		} finally {
			closeConnection(connection, statement);
		}
	}

	public Collection ejbFindByCarAndCompleted(Integer carId, Boolean completed) throws FinderException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("SELECT ID FROM ORDR WHERE CARID = ? AND COMPLETED = ?");
			statement.setInt(1, carId);
			statement.setBoolean(2, completed);
			ResultSet resultSet = statement.executeQuery();
			Collection<Integer> result = new ArrayList<Integer>();
			while (resultSet.next())
				result.add(resultSet.getInt("ID"));
			return result;
		} catch (SQLException e) {
			throw new EJBException(e);
		} finally {
			closeConnection(connection, statement);
		}
	}

	public Collection ejbFindByCar(Integer carId) throws FinderException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("SELECT ID FROM ORDR WHERE CARID = ?");
			statement.setInt(1, carId);
			ResultSet resultSet = statement.executeQuery();
			Collection<Integer> result = new ArrayList<Integer>();
			while (resultSet.next())
				result.add(resultSet.getInt("ID"));
			return result;
		} catch (SQLException e) {
			throw new EJBException(e);
		} finally {
			closeConnection(connection, statement);
		}
	}

	public Collection ejbFindAll() throws FinderException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("SELECT ID FROM ORDR");
			ResultSet resultSet = statement.executeQuery();
			Collection<Integer> result = new ArrayList<Integer>();
			while (resultSet.next())
				result.add(resultSet.getInt("ID"));
			return result;
		} catch (SQLException e) {
			throw new EJBException(e);
		} finally {
			closeConnection(connection, statement);
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Integer getPassengers() {
		return passengers;
	}

	public void setPassengers(Integer passengers) {
		this.passengers = passengers;
	}

	public Timestamp getTimeDest() {
		return timeDest;
	}

	public void setTimeDest(Timestamp timeDest) {
		this.timeDest = timeDest;
	}

	public Timestamp getTimeOrd() {
		return timeOrd;
	}

	public void setTimeOrd(Timestamp timeOrd) {
		this.timeOrd = timeOrd;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public String getAddrDep() {
		return addrDep;
	}

	public void setAddrDep(String addrDep) {
		this.addrDep = addrDep;
	}

	public String getAddrDest() {
		return addrDest;
	}

	public void setAddrDest(String addrDest) {
		this.addrDest = addrDest;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}


	private void closeConnection(Connection connection, Statement statement) {
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer ejbCreateOrder(String addrDep, String addrDest, Integer distAppr, Integer passengers, String phone, Integer carId) throws CreateException {
		this.addrDep = addrDep;
		this.addrDest = addrDest;
		this.passengers = passengers;
		this.phone = phone;
		this.completed = false;
		final java.util.Date now = new java.util.Date();
		this.timeOrd = new Timestamp(now.getTime());
		this.carId = carId;
		distance = 0;
		cost = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("INSERT INTO ORDR (ID, TIME_ORD, TIME_DONE, ADDR_DEP, ADDR_DEST, PASSENGERS, COMPLETED, DISTANCE, COST, PHONE, CARID)" +
					" VALUES (taxiseq.nextval, sysdate, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setTimestamp(1, timeDest);
			statement.setString(2, addrDep);
			statement.setString(3, addrDest);
			statement.setInt(4, passengers);
			statement.setBoolean(5, completed);
			statement.setInt(6, distance);
			statement.setInt(7, cost);
			statement.setString(8, phone);
			statement.setInt(9, carId);
			if (statement.executeUpdate() != 1)
				throw new CreateException();
			statement = connection.prepareStatement("select taxiseq.currval from dual");
			final ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next())
				throw new CreateException();
			return resultSet.getInt(1);
		} catch (SQLException e) {
			throw new EJBException(e);
		} finally {
			closeConnection(connection, statement);
		}
	}

	public void ejbPostCreateOrder(String addrDep, String addrDest, Integer distAppr, Integer passengers, String phone, Integer carId) throws CreateException {
	}

	public OrderVO getOrderVO(Boolean withDependences) throws FinderException, NamingException {
		final OrderVO orderVO = new OrderVO(id, cost, phone, distance, passengers, timeDest, timeOrd, addrDep, addrDest, carId, completed);
		if (!withDependences) return orderVO;
		final LocalCarEntityHome carHome = BeanProvider.getCarHome();
		final LocalCarEntity carEntity = carHome.findByPrimaryKey(carId);
		orderVO.setCar(carEntity.getCarVO(false));
		return orderVO;
	}

	public void setOrderVO(OrderVO value) {
		setId(value.getId());
		setCost(value.getCost());
		setPhone(value.getPhone());
		setDistance(value.getDistance());
		setPassengers(value.getPassengers());
		setTimeDest(value.getTimeDest());
		setTimeOrd(value.getTimeOrd());
		setAddrDep(value.getAddrDep());
		setAddrDest(value.getAddrDest());
		setCarId(value.getCarId());
		setCompleted(value.getCompleted());
	}
}
