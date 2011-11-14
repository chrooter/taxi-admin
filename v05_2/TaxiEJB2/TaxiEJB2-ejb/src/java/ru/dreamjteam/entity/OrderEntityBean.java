package ru.dreamjteam.entity;


import javax.ejb.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;


public class OrderEntityBean implements EntityBean {
	private Integer id;
        private Timestamp timeOrd;
        private Timestamp timeDone;
        private Integer startPoint;
        private Integer passengers;
        private Integer distance;
	private Integer cost;
	private String phone;
        private String status;
	private Integer carId;
	
	private EntityContext context;
	private DataSource dataSource;
        private Connection conn;


	public Integer ejbFindByPrimaryKey(Integer key) throws FinderException {
		
		PreparedStatement st = null;
		try {
			openConnection();
			st = conn.prepareStatement("SELECT ID FROM ORDERS WHERE ID = ?");
			st.setInt(1, key);
			ResultSet rs = st.executeQuery();
			if (!rs.next())
				throw new ObjectNotFoundException();
			return key;
		} catch (SQLException e) {
			throw new EJBException(e);
		} finally {
			closeConnection(st);
		}
	}
        
        public Collection ejbFindByCarAndCompleted(Integer carId, String status) throws FinderException {
		
		PreparedStatement st = null;
		try {
			openConnection();
			st = conn.prepareStatement("SELECT ID FROM ORDERS WHERE CAR_ID = ? AND STATUS = ?");
			st.setInt(1, carId);
			st.setString(2, status);
			ResultSet rs = st.executeQuery();
			Collection<Integer> result = new ArrayList<Integer>();
			while (rs.next())
				result.add(rs.getInt("ID"));
			return result;
		} catch (SQLException e) {
			throw new EJBException(e);
		} finally {
			closeConnection(st);
		}
	}

	public Collection ejbFindByCar(Integer carId) throws FinderException {
		
		PreparedStatement st = null;
		try {
			openConnection();
			st = conn.prepareStatement("SELECT ID FROM ORDERS WHERE CAR_ID = ?");
			st.setInt(1, carId);
			ResultSet rs = st.executeQuery();
			Collection<Integer> result = new ArrayList<Integer>();
			while (rs.next())
				result.add(rs.getInt("ID"));
			return result;
		} catch (SQLException e) {
			throw new EJBException(e);
		} finally {
			closeConnection(st);
		}
	}

	public Collection ejbFindAll() throws FinderException {
		
		PreparedStatement st = null;
		try {
			openConnection();
			st = conn.prepareStatement("SELECT ID FROM ORDERS");
			ResultSet rs = st.executeQuery();
			Collection<Integer> result = new ArrayList<Integer>();
			while (rs.next())
				result.add(rs.getInt("ID"));
			return result;
		} catch (SQLException e) {
			throw new EJBException(e);
		} finally {
			closeConnection(st);
		}
	}


	public void setEntityContext(EntityContext context) throws EJBException {
		this.context = context;
		try {
			final InitialContext ic = new InitialContext();
			dataSource = (DataSource) ic.lookup("java:comp/env/taxiref");
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}

	public void unsetEntityContext() throws EJBException {
            context = null;
	}

        public Integer ejbCreateOrder(Integer startPoint, Integer passengers, String phone) throws CreateException {
		this.startPoint = startPoint;
		this.passengers = passengers;
		this.phone = phone;
		this.status = "new";
		//final java.util.Date now = new java.util.Date();
		//this.timeOrd = new Timestamp(now.getTime());
		
		PreparedStatement st = null;
		try {
			openConnection();
			st = conn.prepareStatement("INSERT INTO ORDERS (ID, TIME_ORD, START_POINT, PASSENGERS, PHONE, STATUS)" +
					" VALUES (orders_seq.nextval, sysdate, ?, ?, ?, ?)");
			st.setInt(1, startPoint);
			st.setInt(2, passengers);
			st.setString(3, phone);
			st.setString(9, status);
			if (st.executeUpdate() != 1)
				throw new CreateException();
			st = conn.prepareStatement("select orders_seq.currval from dual");
			final ResultSet rs = st.executeQuery();
			if (!rs.next())
				throw new CreateException();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new EJBException(e);
		} finally {
			closeConnection(st);
		}
	}

	public void ejbPostCreateOrder(Integer startPoint, Integer passengers, String phone) throws CreateException {
	}
        
	public void ejbRemove() throws RemoveException, EJBException {
		
		PreparedStatement st = null;
		try {
			openConnection();
			st = conn.prepareStatement("DELETE FROM ORDERS WHERE ID = ?");
			st.setInt(1, id);
			if (st.executeUpdate() < 1)
				throw new RemoveException();
		} catch (SQLException e) {
			throw new EJBException(e);
		} finally {
			closeConnection(st);
		}
	}

	public void ejbActivate() throws EJBException {
	}

	public void ejbPassivate() throws EJBException {
	}

	public void ejbLoad() throws EJBException {
		id = ((Integer) context.getPrimaryKey());
		
		PreparedStatement st = null;
		try {
			openConnection();
			st = conn.prepareStatement("SELECT TIME_ORD, TIME_DONE, START_POINT, PASSENGERS, DISTANCE, COST, PHONE, STATUS, CAR_ID FROM ORDERS WHERE ID = ?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (!rs.next())
				throw new NoSuchEntityException("Row for id " + id + " not found in database");
			timeOrd = rs.getTimestamp("TIME_ORD");
			timeDone = rs.getTimestamp("TIME_DONE");
			startPoint = rs.getInt("START_POINT");
			passengers = rs.getInt("PASSENGERS");
			distance = rs.getInt("DISTANCE");
			cost = rs.getInt("COST");
			phone = rs.getString("PHONE");
                        status = rs.getString("STATUS");
			carId = rs.getInt("CAR_ID");
		} catch (SQLException e) {
			throw new EJBException(e);
		} finally {
			closeConnection(st);
		}
	}

	public void ejbStore() throws EJBException {
		id = (Integer) context.getPrimaryKey();
		
		PreparedStatement st = null;
		try {
			openConnection();
			st = conn.prepareStatement("UPDATE ORDERS SET TIME_ORD = ?, TIME_DONE = ?, START_POINT = ?, PASSENGERS = ?, DISTANCE = ?, COST = ?, PHONE = ?, STATUS = ?, CAR_ID = ? WHERE ID = ?");
			st.setTimestamp(1, timeOrd);
			st.setTimestamp(2, timeDone);
			st.setInt(3, startPoint);
			st.setInt(4, passengers);
			st.setInt(5, distance);
			st.setInt(6, cost);
			st.setString(7, phone);
                        st.setString(8, status);
			st.setInt(9, carId);
			st.setInt(10, id);
			if (st.executeUpdate() < 1)
				throw new NoSuchEntityException("Row for id " + id + " not found in database");
		} catch (SQLException e) {
			throw new EJBException(e);
		} finally {
			closeConnection(st);
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

	public Timestamp getTimeDone() {
		return timeDone;
	}

	public void setTimeDone(Timestamp timeDone) {
		this.timeDone = timeDone;
	}

	public Timestamp getTimeOrd() {
		return timeOrd;
	}

	public void setTimeOrd(Timestamp timeOrd) {
		this.timeOrd = timeOrd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Integer startPoint) {
		this.startPoint = startPoint;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

        private void openConnection(){
                try{
                    Locale.setDefault(Locale.ENGLISH);
                    conn = dataSource.getConnection();
                }catch(Exception ex){
                    throw new EJBException("Unable to connect to database. " + ex.getMessage());
                }
            }
        
	private void closeConnection(Statement st) {
		try {
			if (st != null)
				st.close();
		} catch (SQLException ex) {
			throw new EJBException("Unable to connect to database. " + ex.getMessage());
		}
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException ex) {
			throw new EJBException("Unable to connect to database. " + ex.getMessage());
		}
	}

	public OrderVO getOrderVO(Boolean withDependences) throws FinderException, NamingException {
		final OrderVO orderVO = new OrderVO(id, cost, phone, distance, passengers, timeDone, timeOrd, startPoint, carId, status);
		/*if (!withDependences) return orderVO;
		final LocalCarEntityHome carHome = BeanProvider.getCarHome();
		final LocalCarEntity carEntity = carHome.findByPrimaryKey(carId);
		orderVO.setCar(carEntity.getCarVO(false));*/
		return orderVO;
	}

	public void setOrderVO(OrderVO value) {
		setId(value.getId());
		setCost(value.getCost());
		setPhone(value.getPhone());
		setDistance(value.getDistance());
		setPassengers(value.getPassengers());
		setTimeDone(value.getTimeDone());
		setTimeOrd(value.getTimeOrd());
		setStartPoint(value.getStartPoint());
		setCarId(value.getCarId());
		setStatus(value.getStatus());
	}

}
