import java.math.BigDecimal;
import java.util.List;

public class Product {
    private int productId;
    private int day;
    private String description;
    private String title;
    private String coverUrl;
    private String prief;
    private String name;
    private String type;
    private BigDecimal price;
    private String city;
    private String quyu;
    private String theme;
    private String tag;
    private List<Tag> tags;


    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getQuyu() {
        return quyu;
    }

    public void setQuyu(String quyu) {
        this.quyu = quyu;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getPrief() {
        return prief;
    }

    public void setPrief(String prief) {
        this.prief = prief;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", day=" + day +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", prief='" + prief + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", city='" + city + '\'' +
                ", quyu='" + quyu + '\'' +
                ", theme='" + theme + '\'' +
                '}';
    }
}
