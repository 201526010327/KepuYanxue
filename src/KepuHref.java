import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.*;

@WebServlet("/kepuHref/*")
public class KepuHref extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String result = "";
        //用户点击了一个分类标签后，意味着其它的所有标签的url都围绕着这个标签进行变化
        String url= request.getRequestURL().toString();
        if (url.length() > 31)
            url = url.substring(url.lastIndexOf('/')+1);
        else {
            url = "";
        }

        Map<String,Object> map = null;
        try {
            map = getKepuTagMap();
        }catch (Exception e){
            e.printStackTrace();
        }
        JSONObject jsonObject = null;
        if(map!=null){
            jsonObject = new JSONObject(map);
        }

        if(jsonObject!=null)
            result = jsonObject.toJSONString();
        System.out.println(result);
        response.setCharacterEncoding("utf-8");
        response.getWriter().append(result);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    private Map<String,Object> getKepuTagMap() throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        DbHelp dbHelp = DbHelp.getDbHelp();
        String sql = "";
        sql = "select * from tag";
        ResultSet resultSet = dbHelp.executeQuery(sql);
        List<ParamObject> paramList = new ArrayList<ParamObject>();
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String url = resultSet.getString("url");
            ParamObject p = new ParamObject(id,name,url);
            paramList.add(p);
        }
        map.put("tag",paramList);
        return map;
    }

    /* 此函数作用是生成分类标签的url，再发送给前端动态生成每个标签的href值*/
    private String changeUrl(String self,String url){
        String newUrl = "";
        String start = self.substring(0,1);
        List<String> list1 = Arrays.asList(url.split("-"));
        List<String> list = new ArrayList(list1);
        int length = list.size();
        int i;
        if(self.length()>1){
            for(i = 0; i < length;i++){
                if(list.get(i).indexOf(start)!=-1){
                    list.set(i,self);
                    break;
                }
            }
            if(i==length){
                list.add(self);
            }
        }else{
            for(i = 0; i < length;i++){
                if(list.get(i).indexOf(start)!=-1){
                    list.remove(i);
                    break;
                }
            }
        }
        if(list.size()>0){
            newUrl+=list.get(0);
            for(int j = 1;j < list.size();j++){
                newUrl += "-"+list.get(j);
            }
        }
        System.out.println(newUrl);
        return newUrl;
    }

    private String getAllKepuProduct(){
        String result = "";
        result = ProductService.getProductService().getAllKepuProduct();
        System.out.println(result);
        return result;
    }
    private String searchByType(String type){
        String result = "";
        result = ProductService.getProductService().searchByType(type);
        System.out.println(result);
        return result;
    }

    private String searchByArea(String area){
        String result = "";
        result = new ProductService().searchByArea(area);
        return result;
    }
    private String searchByPrice(int price1,int price2){
        String result = "";
        result = new ProductService().searchByPrice(price1,price2);
        return result;
    }

    public void createProduct(String productJson){
        new ProductService().createProduct(productJson);
    }
}
