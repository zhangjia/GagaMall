package io.zhangjia.mall.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Commodity {
    private Integer commodityId;
    private String commodityName;
    private String commodityAttributes;
    private Date commodityCreateTime;
    private Date commodityUpdateTIme;
    private Integer commodityViews;
    private Integer firstMenuId;
    private Integer secMenuId;
    private Integer commodityIsDel;




//    ----------------------------------------

    private List<Map<String, Object>> commodityImg;
    private List<Map<String, Object>> commodityDetails;
    private String firstMenuChineseName;
    private String secMenuChineseName;
    private Integer commoditySales;
    private Integer commodityInventor;
    private Double commodityMinOriginalPrice;
    private Double commodityMaxOriginalPrice;
    private Double commodityMinPresentPrice;
    private Double commodityMaxPresentPrice;
    private Double commodityMaxMarkDown;//降价

    public Commodity() {
    }

    public Commodity(String commodityName, String commodityAttributes, Date commodityCreateTime, Date commodityUpdateTIme, Integer commodityViews, Integer firstMenuId, Integer secMenuId, Integer commodityIsDel) {
        this.commodityName = commodityName;
        this.commodityAttributes = commodityAttributes;
        this.commodityCreateTime = commodityCreateTime;
        this.commodityUpdateTIme = commodityUpdateTIme;
        this.commodityViews = commodityViews;
        this.firstMenuId = firstMenuId;
        this.secMenuId = secMenuId;
        this.commodityIsDel = commodityIsDel;
    }

    public Commodity(Integer commodityId, String commodityName, String commodityAttributes, Date commodityCreateTime, Date commodityUpdateTIme, Integer commodityViews, Integer firstMenuId, Integer secMenuId, Integer commodityIsDel) {
        this.commodityId = commodityId;
        this.commodityName = commodityName;
        this.commodityAttributes = commodityAttributes;
        this.commodityCreateTime = commodityCreateTime;
        this.commodityUpdateTIme = commodityUpdateTIme;
        this.commodityViews = commodityViews;
        this.firstMenuId = firstMenuId;
        this.secMenuId = secMenuId;
        this.commodityIsDel = commodityIsDel;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityAttributes() {
        return commodityAttributes;
    }

    public void setCommodityAttributes(String commodityAttributes) {
        this.commodityAttributes = commodityAttributes;
    }

    public Date getCommodityCreateTime() {
        return commodityCreateTime;
    }

    public void setCommodityCreateTime(Date commodityCreateTime) {
        this.commodityCreateTime = commodityCreateTime;
    }

    public Date getCommodityUpdateTIme() {
        return commodityUpdateTIme;
    }

    public void setCommodityUpdateTIme(Date commodityUpdateTIme) {
        this.commodityUpdateTIme = commodityUpdateTIme;
    }

    public Integer getCommodityViews() {
        return commodityViews;
    }

    public void setCommodityViews(Integer commodityViews) {
        this.commodityViews = commodityViews;
    }

    public Integer getFirstMenuId() {
        return firstMenuId;
    }

    public void setFirstMenuId(Integer firstMenuId) {
        this.firstMenuId = firstMenuId;
    }

    public Integer getSecMenuId() {
        return secMenuId;
    }

    public void setSecMenuId(Integer secMenuId) {
        this.secMenuId = secMenuId;
    }

    public Integer getCommodityIsDel() {
        return commodityIsDel;
    }

    public void setCommodityIsDel(Integer commodityIsDel) {
        this.commodityIsDel = commodityIsDel;
    }

    public List<Map<String, Object>> getCommodityImg() {
        return commodityImg;
    }

    public void setCommodityImg(List<Map<String, Object>> commodityImg) {
        this.commodityImg = commodityImg;
    }

    public List<Map<String, Object>> getCommodityDetails() {
        return commodityDetails;
    }

    public void setCommodityDetails(List<Map<String, Object>> commodityDetails) {
        this.commodityDetails = commodityDetails;
    }

    public String getFirstMenuChineseName() {
        return firstMenuChineseName;
    }

    public void setFirstMenuChineseName(String firstMenuChineseName) {
        this.firstMenuChineseName = firstMenuChineseName;
    }

    public String getSecMenuChineseName() {
        return secMenuChineseName;
    }

    public void setSecMenuChineseName(String secMenuChineseName) {
        this.secMenuChineseName = secMenuChineseName;
    }

    public Integer getCommoditySales() {
        return commoditySales;
    }

    public void setCommoditySales(Integer commoditySales) {
        this.commoditySales = commoditySales;
    }

    public Integer getCommodityInventor() {
        return commodityInventor;
    }

    public void setCommodityInventor(Integer commodityInventor) {
        this.commodityInventor = commodityInventor;
    }

    public Double getCommodityMinOriginalPrice() {
        return commodityMinOriginalPrice;
    }

    public void setCommodityMinOriginalPrice(Double commodityMinOriginalPrice) {
        this.commodityMinOriginalPrice = commodityMinOriginalPrice;
    }

    public Double getCommodityMaxOriginalPrice() {
        return commodityMaxOriginalPrice;
    }

    public void setCommodityMaxOriginalPrice(Double commodityMaxOriginalPrice) {
        this.commodityMaxOriginalPrice = commodityMaxOriginalPrice;
    }

    public Double getCommodityMinPresentPrice() {
        return commodityMinPresentPrice;
    }

    public void setCommodityMinPresentPrice(Double commodityMinPresentPrice) {
        this.commodityMinPresentPrice = commodityMinPresentPrice;
    }

    public Double getCommodityMaxPresentPrice() {
        return commodityMaxPresentPrice;
    }

    public void setCommodityMaxPresentPrice(Double commodityMaxPresentPrice) {
        this.commodityMaxPresentPrice = commodityMaxPresentPrice;
    }

    public Double getCommodityMaxMarkDown() {
        return commodityMaxMarkDown;
    }

    public void setCommodityMaxMarkDown(Double commodityMaxMarkDown) {
        this.commodityMaxMarkDown = commodityMaxMarkDown;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commodity commodity = (Commodity) o;
        return Objects.equals(commodityId, commodity.commodityId) &&
                Objects.equals(commodityName, commodity.commodityName) &&
                Objects.equals(commodityAttributes, commodity.commodityAttributes) &&
                Objects.equals(commodityCreateTime, commodity.commodityCreateTime) &&
                Objects.equals(commodityUpdateTIme, commodity.commodityUpdateTIme) &&
                Objects.equals(commodityViews, commodity.commodityViews) &&
                Objects.equals(firstMenuId, commodity.firstMenuId) &&
                Objects.equals(secMenuId, commodity.secMenuId) &&
                Objects.equals(commodityIsDel, commodity.commodityIsDel) &&
                Objects.equals(commodityImg, commodity.commodityImg) &&
                Objects.equals(commodityDetails, commodity.commodityDetails) &&
                Objects.equals(firstMenuChineseName, commodity.firstMenuChineseName) &&
                Objects.equals(secMenuChineseName, commodity.secMenuChineseName) &&
                Objects.equals(commoditySales, commodity.commoditySales) &&
                Objects.equals(commodityInventor, commodity.commodityInventor) &&
                Objects.equals(commodityMinOriginalPrice, commodity.commodityMinOriginalPrice) &&
                Objects.equals(commodityMaxOriginalPrice, commodity.commodityMaxOriginalPrice) &&
                Objects.equals(commodityMinPresentPrice, commodity.commodityMinPresentPrice) &&
                Objects.equals(commodityMaxPresentPrice, commodity.commodityMaxPresentPrice) &&
                Objects.equals(commodityMaxMarkDown, commodity.commodityMaxMarkDown);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commodityId, commodityName, commodityAttributes, commodityCreateTime, commodityUpdateTIme, commodityViews, firstMenuId, secMenuId, commodityIsDel, commodityImg, commodityDetails, firstMenuChineseName, secMenuChineseName, commoditySales, commodityInventor, commodityMinOriginalPrice, commodityMaxOriginalPrice, commodityMinPresentPrice, commodityMaxPresentPrice, commodityMaxMarkDown);
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "commodityId=" + commodityId +
                ", commodityName='" + commodityName + '\'' +
                ", commodityAttributes='" + commodityAttributes + '\'' +
                ", commodityCreateTime=" + commodityCreateTime +
                ", commodityUpdateTIme=" + commodityUpdateTIme +
                ", commodityViews=" + commodityViews +
                ", firstMenuId=" + firstMenuId +
                ", secMenuId=" + secMenuId +
                ", commodityIsDel=" + commodityIsDel +
                ", commodityImg=" + commodityImg +
                ", commodityDetails=" + commodityDetails +
                ", firstMenuChineseName='" + firstMenuChineseName + '\'' +
                ", secMenuChineseName='" + secMenuChineseName + '\'' +
                ", commoditySales=" + commoditySales +
                ", commodityInventor=" + commodityInventor +
                ", commodityMinOriginalPrice=" + commodityMinOriginalPrice +
                ", commodityMaxOriginalPrice=" + commodityMaxOriginalPrice +
                ", commodityMinPresentPrice=" + commodityMinPresentPrice +
                ", commodityMaxPresentPrice=" + commodityMaxPresentPrice +
                ", commodityMaxMarkDown=" + commodityMaxMarkDown +
                '}';
    }
}
