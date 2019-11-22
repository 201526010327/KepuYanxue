import com.alibaba.fastjson.JSONObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KepuService {

    public static KepuService kepuService = null;
    public KepuService(){

    }

    public static KepuService getKepuService(){
        if(kepuService==null){
            kepuService = new KepuService();
        }
        return kepuService;
    }

    public String find(String requestPath) throws Exception{
        String result = "";
        String sql = "select * from kepuproduct";

        int index = 0;
        System.out.println(requestPath);
        if(requestPath.length()>34) {
            index = requestPath.lastIndexOf("/");
            String params[] = requestPath.substring(index+1).split("-");

            if (params.length > 0) {
                if (params[0].startsWith("t")) {
                    sql += " where tag like '%" + params[0] + "%'";
                }
            }
        }


        System.out.println(sql);
        ResultSet rs = DbHelp.getConn().prepareStatement(sql).executeQuery();
        List list = DbHelp.populate(rs,Product.class);
        for(Object p : list){
            Product product = (Product) p;
            //tags为科普产品的标签,如  t1 t2
            String[] tags = ((Product) p).getTag().split(";");
            List<Tag> tagObjectList = new ArrayList<Tag>();
            //从tag分类表中拿数据出来，之后将上面的符号t1 t2，转换成实际的标签名 科技 奇趣
            rs = DbHelp.getDbHelp().executeQuery("select * from tag");
            List tagList = DbHelp.populate(rs,Tag.class);

            if(tags!=null){
                Tag t = (Tag) tagList.get(0);
                if(tags[0].equals(t.getUrl())){
                    tagObjectList.add(t);
                }
            }
            for(int i = 1;i < tags.length;i ++){
                for(Object tagObject : tagList){
                    Tag t = (Tag) tagObject;
                    if(tags[i].equals(t.getUrl())){
                        tagObjectList.add(t);
                    }
                }
            }
            ((Product) p).setTags(tagObjectList);
            //tagsName = 科技;奇趣
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list",list);
        result = jsonObject.toJSONString();
        System.out.println(result);
        return result;
    }
}
