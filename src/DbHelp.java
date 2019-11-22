import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHelp {

    private static DbHelp db= null;
    private static Connection conn= null;
    private static String url = "jdbc:mysql://127.0.0.1:3306/yanxue?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static String user = "root";
    private static String password = "123456";
    private static Statement stmt = null;

    private DbHelp(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
        }catch (ClassNotFoundException var13) {
            var13.printStackTrace();
        }catch (SQLException var14) {
            var14.printStackTrace();
        }
    }

    public static DbHelp getDbHelp(){

            db = new DbHelp();
            return db;

    }

    public static Connection getConn() throws Exception{
        if(conn==null){
            conn = DriverManager.getConnection(url, user, password);
        }
        return conn;
    }


    public ResultSet executeQuery(String sql){
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        }catch (SQLException var14) {
            var14.printStackTrace();
        }
        return rs;
    }

    public int executeUpdate(String sql){
        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        }catch (SQLException var14) {
            var14.printStackTrace();
        }
        return result;
    }


    //自己做反射
    public static List populate(ResultSet rs , Class clazz) throws SQLException, InstantiationException, IllegalAccessException{
        //结果集的元素对象
        ResultSetMetaData rsmd = rs.getMetaData();
        //获取结果集的元素个数
        int colCount = rsmd.getColumnCount();
//         System.out.println("#");
//         for(int i = 1;i<=colCount;i++){
//             System.out.println(rsmd.getColumnName(i));
//             System.out.println(rsmd.getColumnClassName(i));
//             System.out.println("#");
//         }
        //返回结果的列表集合
        List list = new ArrayList();
        //业务对象的属性数组
        Field[] fields = clazz.getDeclaredFields();
        while(rs.next()){//对每一条记录进行操作
            Object obj = clazz.newInstance();//构造业务对象实体
            //将每一个字段取出进行赋值
            for(int i = 1;i<=colCount;i++){
                Object value = rs.getObject(i);
                //寻找该列对应的对象属性
                for(int j=0;j<fields.length;j++){
                    Field f = fields[j];
                    //如果匹配进行赋值
                    if(f.getName().equalsIgnoreCase(rsmd.getColumnName(i))){
                        boolean flag = f.isAccessible();
                        f.setAccessible(true);
                        f.set(obj, value);
                        f.setAccessible(flag);
                    }
                }
            }
            list.add(obj);
        }
        return list;
    }
}
