public class ParamObject {
    int id;
    String value;
    String url;
    ParamObject(){}
    ParamObject(int id,String value,String url){
        this.id = id;
        this.value = value;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString(){
        return "{id:'"+id+"',name:'"+value+"',url:'"+url+"'}";
    }
}
