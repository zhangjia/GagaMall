package io.zhangjia.mall.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Commodity {
    private Integer commodityId;
    private String commodityName;
    private Double commodityOriginalPrice;
    private Double commodityPresentPrice;
    private Integer commodityInventor;
    private Integer commoditySales;
    private Date commodityCreateTime;
    private Integer commodityViews;
    private Integer commodityFirstMenuId;
    private Integer commoditySecMenuId;
    private Integer commodityIsDel;

//    ----------------------------------------

    private List<Map<String, Object>> commodityImg;
    private List<Map<String, Object>> commodityDetails;


    public Commodity() {
    }

    public Commodity(String commodityName, Double commodityOriginalPrice, Double commodityPresentPrice, Integer commodityInventor, Integer commoditySales, Date commodityCreateTime, Integer commodityViews, Integer commodityFirstMenuId, Integer commoditySecMenuId, Integer commodityIsDel) {
        this.commodityName = commodityName;
        this.commodityOriginalPrice = commodityOriginalPrice;
        this.commodityPresentPrice = commodityPresentPrice;
        this.commodityInventor = commodityInventor;
        this.commoditySales = commoditySales;
        this.commodityCreateTime = commodityCreateTime;
        this.commodityViews = commodityViews;
        this.commodityFirstMenuId = commodityFirstMenuId;
        this.commoditySecMenuId = commoditySecMenuId;
        this.commodityIsDel = commodityIsDel;
    }

    public Commodity(Integer commodityId, String commodityName, Double commodityOriginalPrice, Double commodityPresentPrice, Integer commodityInventor, Integer commoditySales, Date commodityCreateTime, Integer commodityViews, Integer commodityFirstMenuId, Integer commoditySecMenuId, Integer commodityIsDel) {
        this.commodityId = commodityId;
        this.commodityName = commodityName;
        this.commodityOriginalPrice = commodityOriginalPrice;
        this.commodityPresentPrice = commodityPresentPrice;
        this.commodityInventor = commodityInventor;
        this.commoditySales = commoditySales;
        this.commodityCreateTime = commodityCreateTime;
        this.commodityViews = commodityViews;
        this.commodityFirstMenuId = commodityFirstMenuId;
        this.commoditySecMenuId = commoditySecMenuId;
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

    public Integer getCommodityFirstMenuId() {
        return commodityFirstMenuId;
    }

    public Integer getCommoditySecMenuId() {
        return commoditySecMenuId;
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

    public void setCommodityFirstMenuId(Integer commodityFirstMenuId) {
        this.commodityFirstMenuId = commodityFirstMenuId;
    }

    public void setCommoditySecMenuId(Integer commoditySecMenuId) {
        this.commoditySecMenuId = commoditySecMenuId;
    }

    public void setCommodityIsDel(Integer commodityIsDel) {
        this.commodityIsDel = commodityIsDel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commodity commodity = (Commodity) o;
        return Objects.equals(commodityId, commodity.commodityId) &&
                Objects.equals(commodityName, commodity.commodityName) &&
                Objects.equals(commodityOriginalPrice, commodity.commodityOriginalPrice) &&
                Objects.equals(commodityPresentPrice, commodity.commodityPresentPrice) &&
                Objects.equals(commodityInventor, commodity.commodityInventor) &&
                Objects.equals(commoditySales, commodity.commoditySales) &&
                Objects.equals(commodityCreateTime, commodity.commodityCreateTime) &&
                Objects.equals(commodityViews, commodity.commodityViews) &&
                Objects.equals(commodityFirstMenuId, commodity.commodityFirstMenuId) &&
                Objects.equals(commoditySecMenuId, commodity.commoditySecMenuId) &&
                Objects.equals(commodityIsDel, commodity.commodityIsDel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commodityId, commodityName, commodityOriginalPrice, commodityPresentPrice, commodityInventor, commoditySales, commodityCreateTime, commodityViews, commodityFirstMenuId, commoditySecMenuId, commodityIsDel);
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
                ", commodityFirstMenuId=" + commodityFirstMenuId +
                ", commoditySecMenuId=" + commoditySecMenuId +
                ", commodityIsDel=" + commodityIsDel +
                ", commodityImg=" + commodityImg +
                ", commodityDetails=" + commodityDetails +
                '}';
    }
}
