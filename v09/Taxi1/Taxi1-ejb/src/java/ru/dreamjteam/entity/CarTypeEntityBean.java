package ru.dreamjteam.entity;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.*;
import javax.jms.Destination;
import javax.jms.Message;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import javax.jms.ConnectionFactory;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import ru.dreamjteam.mdb.Change;

public class CarTypeEntityBean implements EntityBean {
	private Integer id;
	private Integer capacity;
	private Integer costPerKm;
	private String name;
        
	private EntityContext context;
	private DataSource dataSource;
        private java.sql.Connection conn;

        private boolean isModified;
        
        public Integer ejbFindByPrimaryKey(Integer key) throws FinderException {
		
		PreparedStatement st = null;
		try {
			openConnection();
			st = conn.prepareStatement("SELECT ID FROM TYPES WHERE ID = ?");
			st.setInt(1, key);
			ResultSet resultSet = st.executeQuery();
			if (!resultSet.next())
				throw new ObjectNotFoundException();
			return key;
		} catch (SQLException e) {
			throw new EJBException(e);
		} finally {
			closeConnection(st);
		}
	}
        
	public Integer ejbFindByName(String name) throws FinderException {
		
		PreparedStatement st = null;
		try {
			openConnection();
			st = conn.prepareStatement("SELECT ID FROM TYPES WHERE NAME = ?");
			st.setString(1, name);
			ResultSet resultSet = st.executeQuery();
			if (!resultSet.next())
				throw new ObjectNotFoundException();
			return resultSet.getInt(1);
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
			st = conn.prepareStatement("SELECT ID FROM TYPES");
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
        
        public Integer ejbCreateCarType(String name, Integer costPerKm, Integer capacity) throws CreateException {
		this.name = name;
		this.costPerKm = costPerKm;
		this.capacity = capacity;
		
		PreparedStatement st = null;
		try {
			openConnection();
			st = conn.prepareStatement("INSERT INTO TYPES (ID, COST_PER_KM, CAPACITY, NAME) VALUES(types_seq.nextval, ?, ?, ?)");
			st.setInt(1, costPerKm);
			st.setInt(2, capacity);
			st.setString(3, name);
			if (st.executeUpdate() != 1)
				throw new CreateException();
			st = conn.prepareStatement("select types_seq.currval from dual");
			final ResultSet resultSet = st.executeQuery();
			if (!resultSet.next())
				throw new CreateException();
                        java.util.Date now = new java.util.Date();
                        sendJMSMessageToTaxiQueue(new Change(0,"CarType",new Timestamp(now.getTime()),1,"Created new carType - "+name));
			return resultSet.getInt(1);
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
        
	public void ejbPostCreateCarType(String name, Integer costPerKm, Integer capacity) throws CreateException {
	}
        
	public void ejbRemove() throws RemoveException, EJBException {
		
		PreparedStatement st = null;
		try {
			openConnection();
			st = conn.prepareStatement("DELETE FROM TYPES WHERE ID = ?");
			st.setInt(1, id);
			if (st.executeUpdate() < 1)
				throw new RemoveException();
                        java.util.Date now = new java.util.Date();
                        sendJMSMessageToTaxiQueue(new Change(0,"CarType",new Timestamp(now.getTime()),3,"CarType "+name+" deleted"));
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
			st = conn.prepareStatement("SELECT COST_PER_KM, CAPACITY, NAME FROM TYPES WHERE ID = ?");
			st.setInt(1, id);
			ResultSet resultSet = st.executeQuery();
			if (!resultSet.next())
				throw new NoSuchEntityException("Row for id " + id + " not found in database");
			costPerKm = resultSet.getInt("COST_PER_KM");
			capacity = resultSet.getInt("CAPACITY");
			name = resultSet.getString("NAME");
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
			st = conn.prepareStatement("UPDATE TYPES SET COST_PER_KM = ?, CAPACITY = ?, NAME = ? WHERE ID = ?");
			st.setInt(1, costPerKm);
			st.setInt(2, capacity);
			st.setString(3, name);
			st.setInt(4, id);
			if (st.executeUpdate() < 1)
				throw new NoSuchEntityException("Row for id " + id + " not found in database");
                        java.util.Date now = new java.util.Date();
                        sendJMSMessageToTaxiQueue(new Change(0,"CarType",new Timestamp(now.getTime()),2,"CarType "+name+" was updated"));
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

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
                isModified = true;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
                isModified = true;
	}
	public Integer getCostPerKm() {
		return costPerKm;
	}
	public void setCostPerKm(Integer costPerKm) {
		this.costPerKm = costPerKm;
                isModified = true;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
                isModified = true;
	}

	public CarTypeVO getCarTypeVO(Boolean withDependences) throws NamingException, FinderException {
		final CarTypeVO carTypeVO = new CarTypeVO(id, capacity, costPerKm, name);
		if (!withDependences) return carTypeVO;
		final CarEntityBeanLocalHome carHome = lookupCarEntityBeanLocal();
		final Collection list = carHome.findByType(id);
		ArrayList<CarVO> cars = new ArrayList<CarVO>(list.size());
		for (Object o : list)
			cars.add(((CarEntityBeanLocal) o).getCarVO(false));
		carTypeVO.setCarVOs(Collections.unmodifiableList(cars));
		return carTypeVO;
	}

	public void setCarTypeVO(CarTypeVO value) {
		setId(value.getId());
		setCapacity(value.getCapacity());
		setCostPerKm(value.getCostPerKm());
		setName(value.getName());
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
        Connection con = null;
        Session s = null;
        try {
            con = cf.createConnection();
            s = con.createSession(false, s.CLIENT_ACKNOWLEDGE);
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
            if (con != null) {
                con.close();
            }
        }
    }
}
