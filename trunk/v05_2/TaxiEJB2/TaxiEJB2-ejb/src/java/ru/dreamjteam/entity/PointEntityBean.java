package ru.dreamjteam.entity;

import javax.ejb.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import javax.naming.Context;


public class PointEntityBean implements EntityBean {
    private Integer id;
    private String addr;
    private Integer nextId;
    private EntityContext entityContext;
    private DataSource dataSource;

    public PointEntityBean() {
    }

    public Integer ejbFindByPrimaryKey(Integer key) throws FinderException {
        Connection connection = null;
	PreparedStatement statement = null;
            try {
                connection = dataSource.getConnection();
		statement = connection.prepareStatement("SELECT ID FROM POINTS WHERE ID = ?");
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
            dataSource = (DataSource) ic.lookup("java:comp/env/taxiref");
	} catch (Exception e) {
            throw new EJBException(e);
        }
    }

    public void unsetEntityContext() throws EJBException {
        this.entityContext = null;
    }

    public void ejbRemove() throws RemoveException, EJBException {
        Connection connection = null;
        PreparedStatement statement = null;
	try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("DELETE FROM POINTS WHERE ID = ?");
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
            statement = connection.prepareStatement("SELECT ADDR, POINT_REF FROM POINTS WHERE ID = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next())
                throw new NoSuchEntityException();
            addr = resultSet.getString("ADDR");
            nextId = resultSet.getInt("POINT_REF");
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
            statement = connection.prepareStatement("UPDATE POINTS SET ADDR = ?, POINT_REF = ? WHERE ID = ?");
            statement.setString(1, addr);
            statement.setInt(2, nextId);
            statement.setInt(3, id);
            if (statement.executeUpdate() < 1)
                throw new NoSuchEntityException();
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNextId() {
        return nextId;
    }

    public void setNextId(Integer cost) {
        this.nextId = cost;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Collection ejbFindChain (Integer Id) throws FinderException {
        Connection connection = null;
	PreparedStatement statement = null;
	try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT ID FROM POINTS CONNECT BY PRIOR POINT_REF = ID START WITH ID = ?");
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

    public Integer ejbCreatePoint(String addr, Integer nextId) throws CreateException {
        this.addr = addr;
	this.nextId = nextId;
	Connection connection = null;
	PreparedStatement statement = null;
	try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO POINTS (ID, ADDR, POINT_REF)" +
				" VALUES (POINTS_SEQ.nextval, ?, ?)");
            statement.setString(1, addr);
            statement.setInt(2, nextId);
            if (statement.executeUpdate() != 1)
                throw new CreateException();
            statement = connection.prepareStatement("select POINTS_SEQ.currval from dual");
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

    public void ejbPostCreatePoint(String addr, Integer nextId) throws CreateException {
    }

    public void createChain (PointVO chain) throws CreateException{

        int size = chain.getPointVOs().size();
        int prevId;
        PointVO[] ch = (PointVO[]) chain.getPointVOs().toArray();

        prevId = ejbCreatePoint(ch[size - 1].getAddress(), null);
        for (int i = size - 1; i > 0; i--) {
            prevId = ejbCreatePoint(ch[i - 1].getAddress(), prevId);
        }
    }

    public PointVO getChain (Integer id) throws FinderException, NamingException{
        PointVO pointVO = new PointVO(this.id, addr, nextId);
        
        Context c = new InitialContext();
        PointEntityBeanLocalHome pointHome 
                = (PointEntityBeanLocalHome) c.lookup("java:comp/env/PointEntityBean");
	
        final Collection list = pointHome.findChain(id);
        ArrayList<PointVO> points = new ArrayList<PointVO>(list.size());
	
        for (Object o : list)
            points.add(((PointEntityBeanLocal) o).getPointVO());
	
        pointVO.setPointVOs(Collections.unmodifiableList(points));
        
        return pointVO;
    }

    public PointVO getPointVO() throws NamingException, FinderException {
        final PointVO pointVO = new PointVO(getId(), getAddr(), getNextId());
	return pointVO;
    }
    
    public void deteleChain (Integer id) throws FinderException, NamingException, RemoveException{
        Context c = new InitialContext();
        PointEntityBeanLocalHome pointHome 
                = (PointEntityBeanLocalHome) c.lookup("java:comp/env/PointEntityBean");
        final Collection list = pointHome.findChain(id);	
        for (Object o : list) 
            ((PointEntityBeanLocal) o).remove();       
    }
}
