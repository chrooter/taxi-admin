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
 * @author abolmasov (18.10.2011 18:27:32)
 * @version $Revision: $
 */
public class CarTypeEntityBean implements EntityBean {
	private Integer id;
	private Integer seatCapacity;
	private Integer costPerKm;
	private String name;
	private EntityContext entityContext;
	private DataSource dataSource;

	public CarTypeEntityBean() {
	}

	public Integer ejbFindByName(String name) throws FinderException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("SELECT ID FROM CAR_TYPE WHERE CAR_TYPE.NAME = ?");
			statement.setString(1, name);
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

	public Integer ejbFindByPrimaryKey(Integer key) throws FinderException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("SELECT ID FROM CAR_TYPE WHERE ID = ?");
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

	public Collection ejbFindAll() throws FinderException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("SELECT ID FROM CAR_TYPE");
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
			statement = connection.prepareStatement("DELETE FROM CAR_TYPE WHERE ID = ?");
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
			statement = connection.prepareStatement("SELECT COST_PER_KM, SEAT_CAP, CAR_TYPE.NAME FROM CAR_TYPE WHERE ID = ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next())
				throw new NoSuchEntityException();
			costPerKm = resultSet.getInt("COST_PER_KM");
			seatCapacity = resultSet.getInt("SEAT_CAP");
			name = resultSet.getString("NAME");
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
			statement = connection.prepareStatement("UPDATE CAR_TYPE SET COST_PER_KM = ?, SEAT_CAP = ?, CAR_TYPE.NAME = ? WHERE ID = ?");
			statement.setInt(1, costPerKm);
			statement.setInt(2, seatCapacity);
			statement.setString(3, name);
			statement.setInt(4, id);
			if (statement.executeUpdate() < 1)
				throw new NoSuchEntityException();
		} catch (SQLException e) {
			throw new EJBException(e);
		} finally {
			closeConnection(connection, statement);
		}
	}
	public Integer ejbCreateCarType(String name, Integer costPerKm, Integer seatCapacity) throws CreateException {
		this.name = name;
		this.costPerKm = costPerKm;
		this.seatCapacity = seatCapacity;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("INSERT INTO CAR_TYPE (ID, COST_PER_KM, SEAT_CAP, CAR_TYPE.NAME) VALUES(taxiseq.nextval, ?, ?, ?)");
			statement.setInt(1, costPerKm);
			statement.setInt(2, seatCapacity);
			statement.setString(3, name);
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
	public void ejbPostCreateCarType(String name, Integer costPerKm, Integer seatCapacity) throws CreateException {
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

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSeatCapacity() {
		return seatCapacity;
	}
	public void setSeatCapacity(Integer seatCapacity) {
		this.seatCapacity = seatCapacity;
	}
	public Integer getCostPerKm() {
		return costPerKm;
	}
	public void setCostPerKm(Integer costPerKm) {
		this.costPerKm = costPerKm;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public CarTypeVO getCarTypeVO(Boolean withDependences) throws NamingException, FinderException {
		final CarTypeVO carTypeVO = new CarTypeVO(id, seatCapacity, costPerKm, name);
		if (!withDependences) return carTypeVO;
		final LocalCarEntityHome carHome = BeanProvider.getCarHome();
		final Collection list = carHome.findByType(id);
		ArrayList<CarVO> cars = new ArrayList<CarVO>(list.size());
		for (Object o : list)
			cars.add(((LocalCarEntity) o).getCarVO(false));
		carTypeVO.setCarVOs(Collections.unmodifiableList(cars));
		return carTypeVO;
	}

	public void setCarTypeVO(CarTypeVO value) {
		setId(value.getId());
		setSeatCapacity(value.getSeatCapacity());
		setCostPerKm(value.getCostPerKm());
		setName(value.getName());
	}
}
