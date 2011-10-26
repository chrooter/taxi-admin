package ru.dreamjteam.entity;

import ru.dreamjteam.BeanProvider;

import javax.ejb.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author abolmasov (18.10.2011 18:23:58)
 * @version $Revision: $
 */
public class CarEntityBean implements EntityBean {
	private Integer id;
	private String govNumber;
	private Integer running;
	private String model;
	private Integer carTypeId;
	private EntityContext entityContext;
	private DataSource dataSource;

	public CarEntityBean() {
	}

	public Integer ejbFindByPrimaryKey(Integer key) throws FinderException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("SELECT ID FROM CAR WHERE ID = ?");
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

	public Integer ejbFindByGovNumber(String govNumber) throws FinderException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("SELECT ID FROM CAR WHERE GOV_NUMBER = ?");
			statement.setString(1, govNumber);
			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next())
				throw new ObjectNotFoundException();
			return resultSet.getInt(1);
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

	public void ejbRemove() throws RemoveException, EJBException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("DELETE FROM CAR WHERE ID = ?");
			statement.setInt(1, id);
			if (statement.executeUpdate() < 1)
				throw new RemoveException();
		} catch (SQLException e) {
			throw new EJBException(e);
		} finally {
			closeConnection(connection, statement);
		}
	}

	public void ejbLoad() throws EJBException {
		id = ((Integer) entityContext.getPrimaryKey());
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("SELECT GOV_NUMBER, RUNNING, MODEL, TYPEID FROM CAR WHERE ID = ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next())
				throw new NoSuchEntityException();
			govNumber = resultSet.getString("GOV_NUMBER");
			running = resultSet.getInt("RUNNING");
			model = resultSet.getString("MODEL");
			carTypeId = resultSet.getInt("TYPEID");
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
			statement = connection.prepareStatement("UPDATE CAR SET GOV_NUMBER = ?, RUNNING = ?, MODEL = ?, TYPEID = ? WHERE ID = ?");
			statement.setString(1, govNumber);
			statement.setInt(2, running);
			statement.setString(3, model);
			statement.setInt(4, carTypeId);
			statement.setInt(5, id);
			if (statement.executeUpdate() < 1)
				throw new NoSuchEntityException();
		} catch (SQLException e) {
			throw new EJBException(e);
		} finally {
			closeConnection(connection, statement);
		}
	}

	public String getGovNumber() {
		return govNumber;
	}

	public void setGovNumber(String govNumber) {
		this.govNumber = govNumber;
	}

	public Integer getRunning() {
		return running;
	}

	public void setRunning(Integer running) {
		this.running = running;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(Integer carTypeId) {
		this.carTypeId = carTypeId;
	}

	public Integer ejbCreateCar(String govNumber, Integer running, String model, Integer carTypeId) throws CreateException {
		this.govNumber = govNumber;
		this.running = running;
		this.carTypeId = carTypeId;
		this.model = model;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("INSERT INTO CAR (ID, GOV_NUMBER, RUNNING, TYPEID, MODEL) VALUES(taxiseq.nextval, ?, ?, ?, ?)");
			statement.setString(1, govNumber);
			statement.setInt(2, running);
			statement.setInt(3, carTypeId);
			statement.setString(4, model);
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

	public void ejbPostCreateCar(String govNumber, Integer running, String model, Integer cartypeId) throws CreateException {
	}

	public void unsetEntityContext() throws EJBException {
	}

	public void ejbActivate() throws EJBException {
	}

	public void ejbPassivate() throws EJBException {
	}

	public CarVO getCarVO(Boolean withDependences) throws NamingException, FinderException {
		final CarVO carVO = new CarVO(getGovNumber(), getRunning(), getId(), getModel(), getCarTypeId());
		if (!withDependences) return carVO;
		final LocalCarTypeEntityHome carTypeHome = BeanProvider.getCarTypeHome();
		final LocalCarTypeEntity carTypeEntity = carTypeHome.findByPrimaryKey(carTypeId);
		carVO.setCarTypeVO(carTypeEntity.getCarTypeVO(false));
		final LocalOrderEntityHome orderHome = BeanProvider.getOrderHome();
		Collection list = orderHome.findByCar(id);
		ArrayList<OrderVO> orders = new ArrayList<OrderVO>(list.size());
		for (Object o : list)
			orders.add(((LocalOrderEntity) o).getOrderVO(false));
		carVO.setOrderVOs(Collections.unmodifiableList(orders));
		orders.clear();
		list = orderHome.findByCarAndCompleted(id, false);
		orders = new ArrayList<OrderVO>(list.size()); 
		for (Object o : list)
			orders.add(((LocalOrderEntity) o).getOrderVO(false));
		carVO.setCurrentOrderVOs(Collections.unmodifiableList(orders));
		return carVO;
	}

	public void setCarVO(CarVO value) {
		setGovNumber(value.getGovNumber());
		setRunning(value.getRunning());
		setId(value.getId());
		setModel(value.getModel());
		setCarTypeId(value.getCarTypeId());
	}

	public Collection ejbFindAll() throws FinderException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("SELECT ID FROM CAR");
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

	public Collection ejbFindByType(Integer carTypeId) throws FinderException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("SELECT ID FROM CAR WHERE TYPEID = ?");
			statement.setInt(1, carTypeId);
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
}
