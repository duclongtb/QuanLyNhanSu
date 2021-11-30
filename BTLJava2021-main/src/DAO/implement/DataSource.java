package DAO.implement;

import DAO.IDataSource;
import Mapper.IMapper;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author Iroha
 * @param <T>
 */
public class DataSource<T> implements IDataSource<T>{
    public Connection getConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhanSuHaui;";
            String username = "sa";
            String password = "iroha";
            return DriverManager.getConnection(connectionUrl, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
    
    @Override
    public <T> List<T> excute(String sql, IMapper<T> mapper, Object... parameters) {
        List<T> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            setParameters(ps,parameters);
            rs = ps.executeQuery();
            while (rs.next()){
                results.add(mapper.mapRowToObject(rs));
            }
            return results;
        }catch (SQLException e){
            return null;
        }finally {
            try{
                if(rs != null){
                    rs.close();
                }
                if(ps != null){
                    ps.close();
                }
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException ex){
                return null;
            }
        }
    }

    @Override
    public Boolean insert(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(sql);
            setParameters(ps,parameters);
            ps.executeUpdate();
            connection.commit();
            return true;
        }catch (SQLException e){
            try{
                connection.rollback();
            }catch (SQLException ex){
                e.printStackTrace();
            }
            return false;
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                return false;
            }
        }
    }

    @Override
    public Boolean update(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(sql);
            setParameters(ps,parameters);
            ps.executeUpdate();
            connection.commit();
            return true;
        }catch (SQLException e){
            try{
                connection.rollback();
            }catch (SQLException ex){
                e.printStackTrace();
            }
            return false;
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                return false;
            }
        }
    }
    
    private void setParameters(PreparedStatement statement, Object... parameters){
        try{
            for(int i = 0; i< parameters.length; i++){
                Object parameter = parameters[i];
                int j = i + 1;
                if(parameter instanceof Long){
                    statement.setLong(j,(Long)parameter);
                }
                if(parameter instanceof String){
                    statement.setString(j, (String)parameter);
                }
                if(parameter instanceof Timestamp){
                    statement.setTimestamp(j, (Timestamp) parameter);
                }
                if(parameter instanceof Integer){
                    statement.setInt(j, (Integer) parameter);
                }
                if(parameter == null){
                    statement.setNull(j, java.sql.Types.NULL);
                }
                if(parameter instanceof Boolean){
                    statement.setBoolean(j, (Boolean) parameter);
                }
                if(parameter instanceof Date){
                    statement.setDate(j,(Date) parameter);
                }
                if(parameter instanceof byte[]){
                    statement.setBytes(j,(byte[]) parameter);
                }
                if(parameter instanceof Double){
                    statement.setDouble(j, (Double) parameter);
                }
            }
        }catch (SQLException ex){
            
        }
    }
}
