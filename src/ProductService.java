import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductService {

    private static ProductService p = null;

    public static ProductService getProductService() {
        if(p==null){
            p = new ProductService();
        }
        return p;
    }

    public String getAllKepuProduct(){
        String result="";
        PreparedStatement statement = null;
        String sql = "select * from kepuProduct";
        JSONArray jsonArray = new JSONArray();
        try {
            statement = (PreparedStatement)DbHelp.getConn().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id",id);
                jsonObject.put("title",resultSet.getString("title"));
                jsonObject.put("text",resultSet.getString("text"));
                jsonArray.add(jsonObject);
            }
            result = jsonArray.toJSONString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public String searchByType(String type){
        String result="";
        PreparedStatement statement = null;
        String sql = "select * from kepuProduct where type = ?;";
        JSONArray jsonArray = new JSONArray();
        try {
            statement = (PreparedStatement)DbHelp.getConn().prepareStatement(sql);
            statement.setString(1,type);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name",resultSet.getString("name"));
                jsonObject.put("type",resultSet.getString("type"));
                jsonObject.put("price",resultSet.getInt("price"));
                jsonObject.put("province",resultSet.getString("province"));
                jsonObject.put("city",resultSet.getString("city"));
                jsonObject.put("sjsl",resultSet.getString("sjsl"));
                jsonObject.put("yxyy",resultSet.getString("yxyy"));
                jsonObject.put("imgsrc",resultSet.getString("imgsrc"));
                jsonObject.put("tel",resultSet.getString("tel"));
                jsonObject.put("day",resultSet.getString("day"));
                jsonArray.add(jsonObject);
            }
            result = jsonArray.toJSONString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public String searchByArea(String area){
        String result="";
        return result;
    }

    public String searchByPrice(int price1,int price2){
        String result="";
        return result;
    }

    public void createProduct(String productJson){
        DbHelp db = DbHelp.getDbHelp();
        db.executeUpdate("insert into product(type) values('红色景区')");
    }

//    public static void main(String[] argv){
//        DbHelp db = DbHelp.getDbHelp();
//        db.executeUpdate("insert into product(type) values('红色景区')");
//    }
}
