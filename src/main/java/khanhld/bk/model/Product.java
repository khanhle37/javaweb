package khanhld.bk.model;

import java.time.LocalDate;

public class Product {
    private LocalDate createTime;
    private int id;
    private String name;
    private String price;
    private String image;
    private String specification;
    private String introduction;
    private boolean soldOut;
    private int guarantee;
    private int bought;
    private boolean deleted;
    private String promotion;

    public Product(LocalDate createTime, int id, String name, String price, String image, String specification, String introduction, boolean soldOut, int guarantee, int bought, boolean deleted, String promotion) {
        this.createTime = createTime;
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.specification = specification;
        this.introduction = introduction;
        this.soldOut = soldOut;
        this.guarantee = guarantee;
        this.bought = bought;
        this.deleted = deleted;
        this.promotion = promotion;
    }

    public Product() {
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public boolean isSoldOut() {
        return soldOut;
    }

    public void setSoldOut(boolean soldOut) {
        this.soldOut = soldOut;
    }

    public int getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(int guarantee) {
        this.guarantee = guarantee;
    }

    public int getBought() {
        return bought;
    }

    public void setBought(int bought) {
        this.bought = bought;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }
}
