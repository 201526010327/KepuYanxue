import com.alibaba.fastjson.JSONObject;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YanxueService {
    public static YanxueService yanxueService = null;
    public YanxueService(){

    }

    public static YanxueService getYanxueService(){
        if(yanxueService==null){
            yanxueService = new YanxueService();
        }
        return yanxueService;
    }

    public String find(String requestPath) throws Exception{
        String result = "";
        String sql = "select * from yanxueproduct ";

        int index = 0;
        if(requestPath.length()>36) {
            index = requestPath.lastIndexOf("/");
            String params[] = requestPath.substring(index+1).split("-");

            if (params.length > 0) {
                if (params[0].startsWith("q")) {
                    sql += "where city = '" + params[0].substring(1) + "'";
                } else if (params[0].startsWith("t")) {
                    sql += "where type = '" + params[0] + "'";
                } else if(params[0].startsWith("p")) {
                    switch (params[0]){
                        case "p1":
                            sql+= "where price > 0 and price < 200 ";
                            break;
                        case "p2":
                            sql+= "where price >= 200 and price < 500 ";
                            break;
                        case "p3":
                            sql+= "where price >= 500 and price < 1000 ";
                            break;
                    }
                }else if(params[0].startsWith("d")){
                    int day = Integer.parseInt(params[0].substring(1));
                    if(day!=6)
                        sql+= "where day=" + day;
                    else
                        sql+= "where day>5";
                }
                else{
                    sql += "where 1 = 1";
                }
            }

            for (int i = 1; i < params.length; i++) {
                if (params[i].startsWith("q")) {
                    sql += " and city = 510101'" + params[i] + "'";
                } else if (params[i].startsWith("t")) {
                    sql += " and type = '" + params[i] + "'";
                }else if(params[i].startsWith("p")) {
                    switch (params[i]){
                        case "p1":
                            sql+= "and price > 0 and price < 200 ";
                            break;
                        case "p2":
                            sql+= "and price >= 200 and price < 500 ";
                            break;
                        case "p3":
                            sql+= "and price >= 500 and price < 1000 ";
                            break;
                    }
                }else if(params[i].startsWith("d")){
                    int day = Integer.parseInt(params[i].substring(1));
                    if(day!=6)
                        sql+= "and day=" + day;
                    else
                        sql+= "and day>5";
                }
            }
        }

        ResultSet rs = DbHelp.getConn().prepareStatement(sql).executeQuery();
        List list = DbHelp.populate(rs,Product.class);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list",list);
        result = jsonObject.toJSONString();
        return result;
    }
}
