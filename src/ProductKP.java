package team.OR.backend;

public class ProductKP {
	private String ID;
	private String name;
	private String summary;
	private String label;
	private String video_path;
	private String content;
	private String img_path;
	private double price;
	private double discount;
	
	// get
	public String getID() {
		return ID;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSummary() {
		return summary;
	}
	
	public String getLable() {
		return label;
	}
	
	public String getVideoPath() {
		return video_path;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getImgPath() {
		return img_path;
	}
	
	public double getPrice() {
		return price;
	}
	
	public double getDiscount() {
		return discount;
	}
	
	// set
	public void setID(String sID) {
		ID = sID;
	}
	
	public void setName(String sname) {
		name = sname;
	}
	
	public void setSummary(String ssummary) {
		summary = ssummary;
	}
	
	public void setLable(String slabel) {
		label = slabel;
	}
	
	public void setVideoPath(String svideo_path) {
		video_path = svideo_path;
	}
	
	public void setContent(String scontent) {
		content = scontent;
	}
	
	public void setImgPath(String simg_path) {
		img_path = simg_path;
	}
	
	public void setPrice(double sprice) {
		price = sprice;
	}
	
	public void setDiscount(double sdiscount) {
		discount = sdiscount;
	}
	
	
	
	public ProductKP() {
		ID = "KPxxxxxx";
		name = "";
		summary = "";
		label = "";
		video_path = "";
		content = "";
		img_path = "";
		price = 0;
		discount = 0;
	}
	
	public ProductKP(String sID,String sname, String ssummary, String slabel, String svideo_path, String scontent, String simg_path, double sprice, Double sdiscount) {
		ID = sID;
		name = sname;
		summary = ssummary;
		label = slabel;
		video_path = svideo_path;
		content = scontent;
		img_path = simg_path;
		price = sprice;
		discount = sdiscount;
	}
	
	
	public void newProduct(String sID,String sname, String ssummary, String slabel, String svideo_path, String scontent, String simg_path, double sprice, Double sdiscount){
		ID = sID;
		name = sname;
		summary = ssummary;
		label = slabel;
		video_path = svideo_path;
		content = scontent;
		img_path = simg_path;
		price = sprice;
		discount = sdiscount;
	}
	
	public ProductKP getProduct2SQL() {
		SQLHandle mysql = new SQLHandle();
		mysql.connection();
		String getsql = "select * from KEPU WHERE id = "+ ID + ";";
		ProductKP product = mysql.selectHandle(getsql);
		mysql.close();
		return product;
	}
	
	public boolean addProduct2SQL() {
		SQLHandle mysql = new SQLHandle();
		mysql.connection();
		String insertsql = "INSERT INTO KEPU(id, name, summary, label, video, image, price, discount)"
				+ "VALUES('"
				+ ID +"', '"
				+ name +"', '"
				+ summary +"', '"
				+ label +"', '"
				+ video_path +"', '"
				+ content +"', '"
				+ img_path +"', "
				+ price +", "
				+ discount +");";
		boolean flag = mysql.insertHandle(insertsql);
		mysql.close();
		return flag;		
	}
	
	public boolean deleteProduct2SQL() {
		SQLHandle mysql = new SQLHandle();
		mysql.connection();
		String deletesql = "DELETE FROM KEPU WHERE ID = '" + ID +"';";
		boolean flag = mysql.deleteHandle(deletesql);
		mysql.close();
		return flag;
	}
	
	public boolean updateProduct2SQL() {
		SQLHandle mysql = new SQLHandle();
		mysql.connection();
		String updatesql = "UPDATE KUPU SET (name, summary, label, video, image, price, discount) = "
				+ "('"
				+ name +"', '"
				+ label +"', '"
				+ summary +"', '"
				+ video_path +"', '"
				+ content +"', '"
				+ img_path +"', "
				+ price +", "
				+ discount +") WHERE id = "+ ID + ";";
		
		boolean flag = mysql.updateHandle(updatesql);
		mysql.close();
		return flag;
	}
}
