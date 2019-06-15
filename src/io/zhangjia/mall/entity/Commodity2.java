package io.zhangjia.mall.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Commodity2 {
    private Integer commodityId;
    private String commodityName;
    private Double commodityOriginalPrice;
    private Double commodityPresentPrice;
    private Integer commodityInventor;
    private Integer commoditySales;
    private Date commodityCreateTime;
    private Integer commodityViews;
    private Integer firstMenuId;
    private Integer secMenuId;
    private Integer commodityIsDel;

//    ----------------------------------------

    private List<Map<String, Object>> commodityImg;
    private List<Map<String, Object>> commodityDetails;
    private String firstMenuChineseName;
    private String secMenuChineseName;


    public String getFirstMenuChineseName() {
        return firstMenuChineseName;
    }

    public void setFirstMenuChineseName(String firsMenuChineseName) {
        this.firstMenuChineseName = firsMenuChineseName;
    }

    public String getSecMenuChineseName() {
        return secMenuChineseName;
    }

    public void setSecMenuChineseName(String secMenuChineseName) {
        this.secMenuChineseName = secMenuChineseName;
    }

    public Commodity2() {
    }

    public Commodity2(String commodityName, Double commodityOriginalPrice, Double commodityPresentPrice, Integer commodityInventor, Integer commoditySales, Date commodityCreateTime, Integer commodityViews, Integer firstMenuId, Integer secMenuId, Integer commodityIsDel) {
        this.commodityName = commodityName;
        this.commodityOriginalPrice = commodityOriginalPrice;
        this.commodityPresentPrice = commodityPresentPrice;
        this.commodityInventor = commodityInventor;
        this.commoditySales = commoditySales;
        this.commodityCreateTime = commodityCreateTime;
        this.commodityViews = commodityViews;
        this.firstMenuId = firstMenuId;
        this.secMenuId = secMenuId;
        this.commodityIsDel = commodityIsDel;
    }

    public Commodity2(Integer commodityId, String commodityName, Double commodityOriginalPrice, Double commodityPresentPrice, Integer commodityInventor, Integer commoditySales, Date commodityCreateTime, Integer commodityViews, Integer firstMenuId, Integer secMenuId, Integer commodityIsDel) {
        this.commodityId = commodityId;
        this.commodityName = commodityName;
        this.commodityOriginalPrice = commodityOriginalPrice;
        this.commodityPresentPrice = commodityPresentPrice;
        this.commodityInventor = commodityInventor;
        this.commoditySales = commoditySales;
        this.commodityCreateTime = commodityCreateTime;
        this.commodityViews = commodityViews;
        this.firstMenuId = firstMenuId;
        this.secMenuId = secMenuId;
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

    public void setFirstMenuId(Integer firstMenuId) {
        this.firstMenuId = firstMenuId;
    }

    public void setSecMenuId(Integer secMenuId) {
        this.secMenuId = secMenuId;
    }
    public Integer getCommodityId() {
        return commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public Double getCommodityOriginalPrice() {
        return commodityOriginalPrice;
    }

    public Double getCommodityPresentPrice() {
        return commodityPresentPrice;
    }

    public Integer getCommodityInventor() {
        return commodityInventor;
    }

    public Integer getCommoditySales() {
        return commoditySales;
    }

    public Date getCommodityCreateTime() {
        return commodityCreateTime;
    }

    public Integer getCommodityViews() {
        return commodityViews;
    }



    public Integer getCommodityIsDel() {
        return commodityIsDel;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public void setCommodityOriginalPrice(Double commodityOriginalPrice) {
        this.commodityOriginalPrice = commodityOriginalPrice;
    }

    public void setCommodityPresentPrice(Double commodityPresentPrice) {
        this.commodityPresentPrice = commodityPresentPrice;
    }

    public void setCommodityInventor(Integer commodityInventor) {
        this.commodityInventor = commodityInventor;
    }

    public void setCommoditySales(Integer commoditySales) {
        this.commoditySales = commoditySales;
    }

    public void setCommodityCreateTime(Date commodityCreateTime) {
        this.commodityCreateTime = commodityCreateTime;
    }

    public void setCommodityViews(Integer commodityViews) {
        this.commodityViews = commodityViews;
    }

    public Integer getFirstMenuId() {
        return firstMenuId;
    }

    public Integer getSecMenuId() {
        return secMenuId;
    }

    public void setCommodityIsDel(Integer commodityIsDel) {
        this.commodityIsDel = commodityIsDel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commodity2 commodity = (Commodity2) o;
        return Objects.equals(commodityId, commodity.commodityId) &&
                Objects.equals(commodityName, commodity.commodityName) &&
                Objects.equals(commodityOriginalPrice, commodity.commodityOriginalPrice) &&
                Objects.equals(commodityPresentPrice, commodity.commodityPresentPrice) &&
                Objects.equals(commodityInventor, commodity.commodityInventor) &&
                Objects.equals(commoditySales, commodity.commoditySales) &&
                Objects.equals(commodityCreateTime, commodity.commodityCreateTime) &&
                Objects.equals(commodityViews, commodity.commodityViews) &&
                Objects.equals(firstMenuId, commodity.firstMenuId) &&
                Objects.equals(secMenuId, commodity.secMenuId) &&
                Objects.equals(commodityIsDel, commodity.commodityIsDel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commodityId, commodityName, commodityOriginalPrice, commodityPresentPrice, commodityInventor, commoditySales, commodityCreateTime, commodityViews, firstMenuId, secMenuId, commodityIsDel);
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "commodityId=" + commodityId +
                ", commodityName='" + commodityName + '\'' +
                ", commodityOriginalPrice=" + commodityOriginalPrice +
                ", commodityPresentPrice=" + commodityPresentPrice +
                ", commodityInventor=" + commodityInventor +
                ", commoditySales=" + commoditySales +
                ", commodityCreateTime=" + commodityCreateTime +
                ", commodityViews=" + commodityViews +
                ", firstMenuId=" + firstMenuId +
                ", secMenuId=" + secMenuId +
                ", commodityIsDel=" + commodityIsDel +
                ", commodityImg=" + commodityImg +
                ", commodityDetails=" + commodityDetails +
                ", firsMenuChineseName='" + firstMenuChineseName + '\'' +
                ", secMenuChineseName='" + secMenuChineseName + '\'' +
                '}';
    }
}
