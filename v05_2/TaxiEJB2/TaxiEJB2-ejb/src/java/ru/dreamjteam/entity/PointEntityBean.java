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

        private EntityContext context;
        private DataSource dataSource;
        private Connection conn;

        private boolean isModified;

        public PointEntityBean() {
        }

        public Integer ejbFindByPrimaryKey(Integer key) throws FinderException {

            PreparedStatement st = null;
                try {
                    openConnection();
                    st = conn.prepareStatement("SELECT ID FROM POINTS WHERE ID = ?");
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

        public Collection ejbFindChain (Integer Id) throws FinderException {

            PreparedStatement st = null;
            try {
                openConnection();
                st = conn.prepareStatement("SELECT ID FROM POINTS CONNECT BY PRIOR POINT_REF = ID START WITH ID = ?");
                st.setInt(1, Id);
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
            this.context = null;
        }

        public void ejbActivate() throws EJBException {
        }

        public void ejbPassivate() throws EJBException {
        }

        public Integer ejbCreatePoint(String addr, Integer nextId) throws CreateException {
            this.addr = addr;
            this.nextId = nextId;

            PreparedStatement st = null;
            try {
                openConnection();
                st = conn.prepareStatement("INSERT INTO POINTS (ID, POINT_REF, ADDR)" +
                                    " VALUES (POINTS_SEQ.nextval, ?, ?)");
                if (nextId != null) st.setInt(1, nextId);
                else st.setNull(1, java.sql.Types.NUMERIC);
                st.setString(2, addr);
                if (st.executeUpdate() != 1)
                    throw new CreateException();
                st = conn.prepareStatement("select POINTS_SEQ.currval from dual");
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

        public void ejbPostCreatePoint(String addr, Integer nextId) throws CreateException {
        }

        public void ejbRemove() throws RemoveException, EJBException {

            PreparedStatement st = null;
            try {
                openConnection();
                st = conn.prepareStatement("DELETE FROM POINTS WHERE ID = ?");
                st.setInt(1, id);
                if (st.executeUpdate() < 1)
                throw new RemoveException();
            } catch (SQLException e) {
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
                st = conn.prepareStatement("SELECT ADDR, POINT_REF FROM POINTS WHERE ID = ?");
                st.setInt(1, id);
                ResultSet rs = st.executeQuery();
                if (!rs.next())
                    throw new NoSuchEntityException("Row for id " + id + " not found in database");
                addr = rs.getString("ADDR");
                nextId = rs.getInt("POINT_REF");
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
                st = conn.prepareStatement("UPDATE POINTS SET ADDR = ?, POINT_REF = ? WHERE ID = ?");
                st.setString(1, addr);
                st.setInt(2, nextId);
                st.setInt(3, id);
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
            isModified = true;
        }

        public Integer getNextId() {
            return nextId;
        }

        public void setNextId(Integer nextId) {
            this.nextId = nextId;
            isModified = true;
        }

        public String getAddress() {
            return addr;
        }

        public void setAddress(String addr) {
            this.addr = addr;
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

        
        /*public void createChain (PointVO chain) throws CreateException{

            int size = chain.getPointVOs().size();
            int prevId;
            PointVO[] ch = (PointVO[]) chain.getPointVOs().toArray();

            prevId = ejbCreatePoint(ch[size - 1].getAddress(), null);
            for (int i = size - 1; i > 0; i--) {
                prevId = ejbCreatePoint(ch[i - 1].getAddress(), prevId);
            }
        }*/

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
            final PointVO pointVO = new PointVO(id, addr, nextId);
            return pointVO;
        }
        
        public void setPointVO(PointVO value) {
		setId(value.getId());
                setAddress(value.getAddress());
                setNextId(value.getNextId());
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
