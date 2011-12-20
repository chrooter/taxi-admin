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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import javax.jms.ObjectMessage;
import ru.dreamjteam.mdb.Change;


public class CarEntityBean implements EntityBean {
    
	private Integer id;
        private String model;
	private String govNumber;
	private String color;
	private Integer carTypeId;
        
	private EntityContext context;
	private DataSource dataSource;
        private Connection conn;

        private boolean isModified;

	public Integer ejbFindByPrimaryKey(Integer key) throws FinderException {
		PreparedStatement st = null;
		try {
			openConnection();
			st = conn.prepareStatement("SELECT ID FROM CARS WHERE ID = ?");
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

	public Integer ejbFindByGovNumber(String govNumber) throws FinderException {
		
		PreparedStatement st = null;
		try {
			openConnection();
			st = conn.prepareStatement("SELECT ID FROM CARS WHERE GOV_NUMBER = ?");
			st.setString(1, govNumber);
			ResultSet rs = st.executeQuery();
			if (!rs.next())
				throw new ObjectNotFoundException();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new EJBException(e);
		} finally {
			closeConnection(st);
		}
	}
        
        public Collection ejbFindByType(Integer carTypeId) throws FinderException {
		
		PreparedStatement st = null;
		try {
			openConnection();
			st = conn.prepareStatement("SELECT ID FROM CARS WHERE TYPE_ID = ?");
			st.setInt(1, carTypeId);
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
			st = conn.prepareStatement("SELECT ID FROM CARS WHERE DELETED!=1");
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

        public Collection ejbFindByCarVO(CarVO carVO) throws FinderException {

		PreparedStatement st = null;
		try {
			openConnection();
                        String sql = "SELECT ID FROM CARS WHERE ID=ID" + (carVO.getModel() == null ? "" : " AND MODEL='"+carVO.getModel()+"'")
                                                                       + (carVO.getGovNumber() == null ? "" : " AND GOVNUMBER='"+carVO.getGovNumber()+"'")
                                                                       + (carVO.getColor() == null ? "" : " AND COLOR='"+carVO.getColor()+"'")
                                                                       + (carVO.getCarTypeId() == null ? "" : " AND COLOR="+carVO.getCarTypeId());
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
            this.context = null;
	}

	public void ejbActivate() throws EJBException {
	}

	public void ejbPassivate() throws EJBException {
	}
        
        public Integer ejbCreateCar(String govNumber, String color, String model, Integer carTypeId) throws CreateException {
		this.govNumber = govNumber;
		this.color = color;
		this.carTypeId = carTypeId;
		this.model = model;
		
		PreparedStatement st = null;
		try {
			openConnection();
			st = conn.prepareStatement("INSERT INTO CARS (ID, MODEL, GOV_NUMBER, COLOR, TYPE_ID) VALUES(cars_seq.nextval, ?, ?, ?, ?)");
			st.setString(1, model);
                        st.setString(2, govNumber);
			st.setString(3, color);
			st.setInt(4, carTypeId);
			
			if (st.executeUpdate() != 1)
				throw new CreateException();
			st = conn.prepareStatement("select cars_seq.currval from dual");
			final ResultSet rs = st.executeQuery();
			if (!rs.next())
				throw new CreateException();
                        java.util.Date now = new java.util.Date();
                        sendJMSMessageToTaxiQueue(new Change(0,"Car",new Timestamp(now.getTime()),1,"Created new car - "+govNumber));
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
        
        public void ejbPostCreateCar(String govNumber, String color, String model, Integer cartypeId) throws CreateException {
	}
        
	public void ejbRemove() throws RemoveException, EJBException {
		
		PreparedStatement st = null;
		try {
			openConnection();
			st = conn.prepareStatement("DELETE FROM CARS WHERE ID = ?");
			st.setInt(1, id);
			if (st.executeUpdate() < 1)
				throw new RemoveException();
                        java.util.Date now = new java.util.Date();
                        sendJMSMessageToTaxiQueue(new Change(0,"Car",new Timestamp(now.getTime()),3,"Car "+govNumber+" deleted"));
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

	public void ejbLoad() throws EJBException {
		id = (Integer) context.getPrimaryKey();
		
		PreparedStatement st = null;
		try {
			openConnection();
			st = conn.prepareStatement("SELECT GOV_NUMBER, COLOR, MODEL, TYPE_ID FROM CARS WHERE ID = ?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (!rs.next())
				throw new NoSuchEntityException("Row for id " + id + " not found in database");
			govNumber = rs.getString("GOV_NUMBER");
			color = rs.getString("COLOR");
			model = rs.getString("MODEL");
			carTypeId = rs.getInt("TYPE_ID");
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
			st = conn.prepareStatement("UPDATE CARS SET GOV_NUMBER = ?, COLOR = ?, MODEL = ?, TYPE_ID = ? WHERE ID = ?");
			st.setString(1, govNumber);
			st.setString(2, color);
			st.setString(3, model);
			st.setInt(4, carTypeId);
			st.setInt(5, id);
			if (st.executeUpdate() < 1)
				throw new NoSuchEntityException("Row for id " + id + " not found in database");
                        java.util.Date now = new java.util.Date();
                        sendJMSMessageToTaxiQueue(new Change(0,"Car",new Timestamp(now.getTime()),2,"Car "+govNumber+" was updated"));
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

	public String getGovNumber() {
		return govNumber;
	}

	public void setGovNumber(String govNumber) {
		this.govNumber = govNumber;
                isModified = true;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
                isModified = true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
                isModified = true;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
                isModified = true;
	}

	public Integer getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(Integer carTypeId) {
		this.carTypeId = carTypeId;
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

	
	public CarVO getCarVO(Boolean withDependences) throws NamingException, FinderException {
		final CarVO carVO = new CarVO(id, model, govNumber, color, carTypeId);
		if (!withDependences) return carVO;
                
		final CarTypeEntityBeanLocalHome carTypeHome = lookupCarTypeEntityBeanLocal();
		final CarTypeEntityBeanLocal carTypeEntity = carTypeHome.findByPrimaryKey(carTypeId);
		carVO.setCarTypeVO(carTypeEntity.getCarTypeVO(false));
                
		final OrderEntityBeanLocalHome orderHome = lookupOrderEntityBeanLocal();
		Collection list = orderHome.findByCar(id);
		ArrayList<OrderVO> orders = new ArrayList<OrderVO>(list.size());
		for (Object o : list)
			orders.add(((OrderEntityBeanLocal) o).getOrderVO(false));
		carVO.setOrderVOs(Collections.unmodifiableList(orders));
		//orders.clear();
                
                final OrderEntityBeanLocalHome orderHome2 = lookupOrderEntityBeanLocal();
		Collection list2 = orderHome2.findByCarAndStatus(id, "executing");
		ArrayList<OrderVO> orders2 = new ArrayList<OrderVO>(list2.size()); 
		for (Object o : list2)
			orders2.add(((OrderEntityBeanLocal) o).getOrderVO(false));
		carVO.setCurrentOrderVOs(Collections.unmodifiableList(orders2));
		return carVO;
	}

	public void setCarVO(CarVO value) {
		setGovNumber(value.getGovNumber());
		setColor(value.getColor());
		setId(value.getId());
		setModel(value.getModel());
		setCarTypeId(value.getCarTypeId());
	}

        private CarTypeEntityBeanLocalHome lookupCarTypeEntityBeanLocal() {
            try {
                Context c = new InitialContext();
                CarTypeEntityBeanLocalHome rv = (CarTypeEntityBeanLocalHome) c.lookup("java:comp/env/CarTypeEntityBean");
                return rv;
            } catch (NamingException ne) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
                throw new RuntimeException(ne);
            }
        }

        private OrderEntityBeanLocalHome lookupOrderEntityBeanLocal() {
            try {
                Context c = new InitialContext();
                OrderEntityBeanLocalHome rv = (OrderEntityBeanLocalHome) c.lookup("java:comp/env/OrderEntityBean");
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
        Context c = new InitialContext();
        ConnectionFactory cf = (ConnectionFactory) c.lookup("java:comp/env/jms/taxiQueueFactory");
        javax.jms.Connection conn = null;
        Session s = null;
        try {
            conn = cf.createConnection();
            s = conn.createSession(false, s.CLIENT_ACKNOWLEDGE);
            Destination destination = (Destination) c.lookup("java:comp/env/jms/taxiQueue");
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
