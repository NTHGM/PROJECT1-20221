package sql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class ConnectSQL {
	private Connection conn;
	
	public ConnectSQL() {
		var sn = "DESKTOP-TDSL96M\\SQLEXPRESS";//server name
		var user = "sa";//user name
		var pass = "123Q";//password
		var dn = "ThuVien";//database name
		var port=1433;//port number
		
		SQLServerDataSource ds = new SQLServerDataSource();
		ds.setUser(user);
		ds.setPassword(pass);
		ds.setDatabaseName(dn);
		ds.setPortNumber(port);
		ds.setServerName(sn);
		ds.setTrustServerCertificate(true);
		
		try {
			conn =ds.getConnection();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public Connection getConnect() {
		return conn;
	}
	
	public void releaseResource(ResultSet rs, Statement stmt, PreparedStatement pstmt, CallableStatement cstmt, Connection conn) throws SQLException {
        if(rs != null) {
            rs.close();
        }
        if(stmt != null) {
            stmt.close();
        }
        if(pstmt != null) {
            pstmt.close();
        }
        if(cstmt != null) {
            cstmt.close();
        }
        if(conn != null) {
            conn.close();
        }
    }
}
