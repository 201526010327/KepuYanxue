package team.OR.backend;

import java.sql.*;

public class SQLHandle {
	// MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    // static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    // static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB";
 
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
	static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static String DB_URL = "jdbc:mysql://localhost:3306/Demo?useSSL=false&serverTimezone=UTC";
    // 数据库的用户名与密码，需要根据自己的设置
    static String USER = "root";
    static String PASS = "..9919or";
    static Connection conn = null;
	static Statement stmt = null;
    
	public SQLHandle(String DRIVER, String URL) {
		JDBC_DRIVER = DRIVER;
		DB_URL = URL;
	}
 
	public SQLHandle(String URL) {
		JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		DB_URL = URL;
	}
	
	public SQLHandle() {
	}
    
	public void SetUSER(String username, String Password) {
		USER = username;
		Password = PASS;
	}
    
	public void connection() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(this.DB_URL, this.USER, this.PASS);
		}catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
	}
	
	public void createHandle(String sql) {
		try {
			stmt = conn.createStatement();
			if (0 == stmt.executeLargeUpdate(sql)) {
				System.out.println("create table successfully!");
			}
			else{
				System.out.println("create table failed!");
			}
		}catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally {
        	try{
                if(stmt!=null) 
                	stmt.close();
            }catch(SQLException se2){
            }
        }
	}
	
	public boolean deleteHandle(String sql) {
		boolean flag = false;
		try {
			stmt = conn.createStatement();
			long num = stmt.executeLargeUpdate(sql);
			flag = true;
			System.out.println(num + " rows were been affected.");
		}catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally {
        	try{
                if(stmt!=null) 
                	stmt.close();
            }catch(SQLException se2){
            }
        }
		return flag;
	}
	
	public boolean insertHandle(String sql) {
		boolean flag = false;
		try {
			stmt = conn.createStatement();
			long num = stmt.executeLargeUpdate(sql);
			flag = true;
			System.out.println(num + " rows were been affected.");
		}catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally {
        	try{
                if(stmt!=null) {
                	stmt.close();
                }
            }catch(SQLException se2){
            }
        	
        }
		return flag;
	}
	
	public boolean updateHandle(String sql) {
		boolean flag = false;
		try {
			stmt = conn.createStatement();
			long num = stmt.executeLargeUpdate(sql);
			flag = true;
			System.out.println(num + " rows were been affected.");
		}catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally {
        	try{
                if(stmt!=null) 
                	stmt.close();
            }catch(SQLException se2){
            }
        }
		return flag;
	}
	
	public ProductKP selectHandle(String sql) {
		ProductKP product = new ProductKP();
		ResultSet result = null;
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(sql);
			while(result.next()) {
				String sID = result.getString("id");
				String sname = result.getString("name");
				String ssummary = result.getString("summary");
				String slabel = result.getString("label");
				String svideo_path = result.getString("video");
				String scontent = result.getString("content");
				String simg_path = result.getString("image");
				double sprice = result.getDouble("price");
				double sdiscount = result.getDouble("discount");
				product.newProduct(sID, sname, ssummary, slabel, svideo_path, scontent, simg_path, sprice, sdiscount);
			}
		}catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally {
        	try{
                if(stmt!=null) 
                	stmt.close();
            }catch(SQLException se2){
            }
        }
		return product;
	}
	
	public void close() {
		try{
            if(stmt!=null) 
            	stmt.close();
        }catch(SQLException se2){
        }
		
		try{
            if(conn!=null) 
            	conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
	}
	
//    public static void main(String[] args) {
//            SQLHandle mysql = new SQLHandle();
//            mysql.connection();
//            String sql;
//            // sql = "SELECT id, name, url FROM websites";
//            sql = "create table test2("
//            		+ "num varchar(5),"
//            		+ "name varchar(5)"
//            		+ ");";
//            //mysql.createHandle(sql);
//            sql = "insert into test(num, name) values('1', 'num1');";
//            //mysql.insertHandle(sql);
//            sql = "update test set name = 'num2' where num = '1';";
//            //mysql.updateHandle(sql);
//            sql = "delete from test where num = '1';";
//            //mysql.deleteHandle(sql);
//            sql = "select * from test;";
//            
//            
//            mysql.close();
//           
//    }
}