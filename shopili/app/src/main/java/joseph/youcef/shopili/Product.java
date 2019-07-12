package joseph.youcef.shopili;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Product implements Serializable {
    private int photo;
    private double price_g;
    private double price_wg;
    private String description;
    private String delivery;
    private String guarantee;
    private String link;


    public Product(int photo, double price_g, double price_wg, String description, String delivery, String guarantee, String link) {
        this.photo = photo;
        this.price_g = price_g;
        this.price_wg = price_wg;
        this.description = description;
        this.delivery = delivery;
        this.guarantee = guarantee;
        this.link = link;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public double getPrice_g() {
        return price_g;
    }

    public void setPrice_g(double price_g) {
        this.price_g = price_g;
    }

    public double getPrice_wg() {
        return price_wg;
    }

    public void setPrice_wg(double price_wg) {
        this.price_wg = price_wg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
