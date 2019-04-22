package cn.yfz.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCPUtils {
	private static DataSource datasource;
	static{
		try {
			InputStream in=DBCPUtils.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
			Properties prop=new Properties();
			prop.load(in);
			datasource=BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}
	public static DataSource getDataSource(){
		return datasource;
	}
	public static Connection getConnection(){
		try {
			return datasource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
}
