package ru.dreamjteam.entity;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.*;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import javax.jms.ObjectMessage;
import change.Change;


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

        private boolean isModified;

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
        
        public Collection ejbFindByCarAndStatus(Integer carId, String status) throws FinderException {
		
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
        
        public Collection ejbFindByStatus(String status) throws FinderException {
		
		PreparedStatement st = null;
		try {
			openConnection();
			st = conn.prepareStatement("SELECT ID FROM ORDERS WHERE STATUS = ?");
			st.setString(1, status);
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

         public Collection ejbFindSort(String field,String type) throws FinderException {

		PreparedStatement st = null;
		try {
			openConnection();
                        String sql;
                        if ((!field.equals("NAME")) && (!field.equals("CAPACITY")))
                            sql = "SELECT ID FROM ORDERS ORDER BY "+field+" "+type;
                        else
                            sql = "SELECT CARS.ID FROM CARS JOIN TYPES ON CARS.TYPE_ID=TYPES.ID ORDER BY "+field+" "+type;
			st = conn.prepareStatement(sql);
                        //st.setString(1, field);
                        //st.setString(1, type);
			ResultSet resultSet = st.executeQuery();
			Collection<Integer> result = new ArrayList<Integer>();
			while (resultSet.next())
				result.add(resultSet.getInt("ID"));
			return result;
		} catch (SQLException e) {
			throw new EJBException(e);
		} finally {
			closeConnection(st);
		}
	}

        public Collection ejbFindByOrderVO(OrderVO orderVO) throws FinderException {

		PreparedStatement st = null;
		try {
			openConnection();
                        String sql = "SELECT ID FROM ORDERS WHERE ID=ID" + (orderVO.getPhone() == null ? "" : " AND PHONE='"+orderVO.getPhone()+"'")
                                                                         + (orderVO.getStatus() == null ? "" : " AND STATUS='"+orderVO.getStatus()+"'")
                                                                         + (orderVO.getCarId() == null ? "" : " AND CARID='"+orderVO.getCarId()+"'")
                                                                         + (orderVO.getCost() == null ? "" : " AND COST="+orderVO.getCost())
                                                                         + (orderVO.getDistance() == null ? "" : " AND DISTANCE="+orderVO.getDistance())
                                                                         + (orderVO.getPassengers() == null ? "" : " AND PASSENGERS="+orderVO.getPassengers())
                                                                         + (orderVO.getStartPoint() == null ? "" : " AND STARTPOINT="+orderVO.getStartPoint());

			st = conn.prepareStatement(sql);
			ResultSet resultSet = st.executeQuery();
			Collection<Integer> result = new ArrayList<Integer>();
			while (resultSet.next())
				result.add(resultSet.getInt("ID"));
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
			dataSource = (DataSource) ic.lookup("java:comp/env/taxi");
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
		java.util.Date now = new java.util.Date();
		this.timeOrd = new Timestamp(now.getTime());
		
		PreparedStatement st = null;
		try {
			openConnection();
			st = conn.prepareStatement("INSERT INTO ORDERS (ID, TIME_ORD, START_POINT, PASSENGERS, PHONE, STATUS)" +
					" VALUES (orders_seq.nextval, sysdate, ?, ?, ?, ?)");
			st.setInt(1, startPoint);
			st.setInt(2, passengers);
			st.setString(3, phone);
			st.setString(4, status);
			if (st.executeUpdate() != 1)
				throw new CreateException();
			st = conn.prepareStatement("select orders_seq.currval from dual");
			final ResultSet rs = st.executeQuery();
			if (!rs.next())
				throw new CreateException();
                        now = new java.util.Date();
                        sendJMSMessageToTaxiQueue(new Change(0,"Order",new Timestamp(now.getTime()),1,"Created new order - "+timeOrd));
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new EJBException(e);
		} catch (JMSException e) {
                        throw new EJBException(e);
                } catch (NamingException e) {
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
                        java.util.Date now = new java.util.Date();
                        sendJMSMessageToTaxiQueue(new Change(0,"Order",new Timestamp(now.getTime()),3,"Order "+timeOrd+" deleted"));
		} catch (SQLException e) {
			throw new EJBException(e);
		} catch (JMSException e) {
                        throw new EJBException(e);
                } catch (NamingException e) {
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
                        isModified = false;
		} catch (SQLException e) {
			throw new EJBException(e);
		} finally {
			closeConnection(st);
		}
	}

	public void ejbStore() throws EJBException {
                if (!isModified) return;
		id = (Integer) context.getPrimaryKey();
		
		PreparedStatement st = null;
		try {
			openConnection();
                        
                        String query1 = "UPDATE ORDERS SET TIME_ORD = ?, TIME_DONE = ?, START_POINT = ?, PASSENGERS = ?, DISTANCE = ?, COST = ?, PHONE = ?, STATUS = ?, CAR_ID = ? WHERE ID = ?";
                        String query2 = "UPDATE ORDERS SET TIME_ORD = ?, TIME_DONE = ?, START_POINT = ?, PASSENGERS = ?, DISTANCE = ?, COST = ?, PHONE = ?, STATUS = ? WHERE ID = ?";
                        
                            if (carId == 0)
                            {
                                st = conn.prepareStatement(query2);
                                st.setInt(9, id);
                            }
                                else
                                {
                                    st = conn.prepareStatement(query1);
                                    st.setInt(9, carId);
                                    st.setInt(10, id);
                                }
                        
			st.setTimestamp(1, timeOrd);
			st.setTimestamp(2, timeDone);
			st.setInt(3, startPoint);
			st.setInt(4, passengers);
                        st.setInt(5, distance);
                        st.setInt(6, cost);
			st.setString(7, phone);
                        st.setString(8, status);
			
			if (st.executeUpdate() < 1)
				throw new NoSuchEntityException("Row for id " + id + " not found in database");
                        java.util.Date now = new java.util.Date();
                        sendJMSMessageToTaxiQueue(new Change(0,"Order",new Timestamp(now.getTime()),2,"Order "+timeOrd+" was updated"));
		} catch (SQLException e) {
			throw new EJBException(e);
		} catch (JMSException e) {
                        throw new EJBException(e);
                } catch (NamingException e) {
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
                isModified = true;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
                isModified = true;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
                isModified = true;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
                isModified = true;
	}

	public Integer getPassengers() {
		return passengers;
	}

	public void setPassengers(Integer passengers) {
		this.passengers = passengers;
                isModified = true;
	}

	public Timestamp getTimeDone() {
		return timeDone;
	}

	public void setTimeDone(Timestamp timeDone) {
		this.timeDone = timeDone;
                isModified = true;
	}

	public Timestamp getTimeOrd() {
		return timeOrd;
	}

	public void setTimeOrd(Timestamp timeOrd) {
		this.timeOrd = timeOrd;
                isModified = true;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
                isModified = true;
	}

	public Integer getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Integer startPoint) {
		this.startPoint = startPoint;
                isModified = true;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
                isModified = true;
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
		if (!withDependences) return orderVO;
                if (carId!=0)
                {
                    final CarEntityBeanLocalHome carHome = lookupCarEntityBeanLocal();
                    final CarEntityBeanLocal carEntity = carHome.findByPrimaryKey(carId);
                    orderVO.setCar(carEntity.getCarVO(false));
                }
                final PointEntityBeanLocalHome pointHome = lookupPointEntityBeanLocal();
                final PointEntityBeanLocal pointEntity = pointHome.findByPrimaryKey(startPoint);
                orderVO.setPoint(pointEntity.getPointVO());
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

        private CarEntityBeanLocalHome lookupCarEntityBeanLocal() {
            try {
                Context c = new InitialContext();
                CarEntityBeanLocalHome rv = (CarEntityBeanLocalHome) c.lookup("java:comp/env/CarEntityBean");
                return rv;
            } catch (NamingException ne) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
                throw new RuntimeException(ne);
            }
        }

        private PointEntityBeanLocalHome lookupPointEntityBeanLocal() {
            try {
                Context c = new InitialContext();
                PointEntityBeanLocalHome rv = (PointEntityBeanLocalHome) c.lookup("java:comp/env/PointEntityBean");
                return rv;
            } catch (NamingException ne) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
                throw new RuntimeException(ne);
            }
        }

    private Message createJMSMessageForjmsTaxiQueue(Session session, Change messageData) throws JMSException {
        // TODO create and populate message to send
        ObjectMessage tm = session.createObjectMessage();
        tm.setObject(messageData);
        tm.setJMSPriority(messageData.getPriority());
        return tm;
    }

    private void sendJMSMessageToTaxiQueue(Change messageData) throws NamingException, JMSException {
                Properties properties = new Properties();

 properties.setProperty("java.naming.factory.initial",
                        "org.jnp.interfaces.NamingContextFactory");
 properties.setProperty("java.naming.provider.url", "jnp://localhost:1099");
 properties.setProperty("java.naming.factory.url.pkgs", "org.jnp.interfaces.NamingContextFactory");
        Context c = new InitialContext(properties);
        //ConnectionFactory cf = (ConnectionFactory) c.lookup("java:comp/env/jms/taxiQueueFactory
        ConnectionFactory cf = (ConnectionFactory) c.lookup("ConnectionFactory");
        javax.jms.Connection conn = null;
        Session s = null;
        try {
            conn = cf.createConnection();
            s = conn.createSession(false, s.CLIENT_ACKNOWLEDGE);
            //Destination destination = (Destination) c.lookup("java:comp/env/jms/taxiQueue");
            Destination destination = (Destination) c.lookup("queue/SampleServerQueue");
            MessageProducer mp = s.createProducer(destination);
            mp.send(createJMSMessageForjmsTaxiQueue(s, messageData));
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

}
