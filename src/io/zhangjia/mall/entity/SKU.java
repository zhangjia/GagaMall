package io.zhangjia.mall.entity;

import java.sql.Date;
import java.util.Objects;

public class SKU {
    private Integer skuId;
    private Integer commodityId;
    private String skuValue;
    private Double skuOriginalPrice;
    private Double skuLastPrice;
    private Double presentPrice;
    private Integer skuInventory;
    private Integer skuSales;
    private Date skuCreateTime;
    private Date skuUpdateTime;
    private Integer skuIsDel;

    public SKU() {
    }

    public SKU(Integer commodityId, String skuValue, Double skuOriginalPrice, Double skuLastPrice, Double presentPrice, Integer skuInventory, Integer skuSales, Date skuCreateTime, Date skuUpdateTime, Integer skuIsDel) {
        this.commodityId = commodityId;
        this.skuValue = skuValue;
        this.skuOriginalPrice = skuOriginalPrice;
        this.skuLastPrice = skuLastPrice;
        this.presentPrice = presentPrice;
        this.skuInventory = skuInventory;
        this.skuSales = skuSales;
        this.skuCreateTime = skuCreateTime;
        this.skuUpdateTime = skuUpdateTime;
        this.skuIsDel = skuIsDel;
    }

    public SKU(Integer skuId, Integer commodityId, String skuValue, Double skuOriginalPrice, Double skuLastPrice, Double presentPrice, Integer skuInventory, Integer skuSales, Date skuCreateTime, Date skuUpdateTime, Integer skuIsDel) {
        this.skuId = skuId;
        this.commodityId = commodityId;
        this.skuValue = skuValue;
        this.skuOriginalPrice = skuOriginalPrice;
        this.skuLastPrice = skuLastPrice;
        this.presentPrice = presentPrice;
        this.skuInventory = skuInventory;
        this.skuSales = skuSales;
        this.skuCreateTime = skuCreateTime;
        this.skuUpdateTime = skuUpdateTime;
        this.skuIsDel = skuIsDel;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getSkuValue() {
        return skuValue;
    }

    public void setSkuValue(String skuValue) {
        this.skuValue = skuValue;
    }

    public Double getSkuOriginalPrice() {
        return skuOriginalPrice;
    }

    public void setSkuOriginalPrice(Double skuOriginalPrice) {
        this.skuOriginalPrice = skuOriginalPrice;
    }

    public Double getSkuLastPrice() {
        return skuLastPrice;
    }

    public void setSkuLastPrice(Double skuLastPrice) {
        this.skuLastPrice = skuLastPrice;
    }

    public Double getPresentPrice() {
        return presentPrice;
    }

    public void setPresentPrice(Double presentPrice) {
        this.presentPrice = presentPrice;
    }

    public Integer getSkuInventory() {
        return skuInventory;
    }

    public void setSkuInventory(Integer skuInventory) {
        this.skuInventory = skuInventory;
    }

    public Integer getSkuSales() {
        return skuSales;
    }

    public void setSkuSales(Integer skuSales) {
        this.skuSales = skuSales;
    }

    public Date getSkuCreateTime() {
        return skuCreateTime;
    }

    public void setSkuCreateTime(Date skuCreateTime) {
        this.skuCreateTime = skuCreateTime;
    }

    public Date getSkuUpdateTime() {
        return skuUpdateTime;
    }

    public void setSkuUpdateTime(Date skuUpdateTime) {
        this.skuUpdateTime = skuUpdateTime;
    }

    public Integer getSkuIsDel() {
        return skuIsDel;
    }

    public void setSkuIsDel(Integer skuIsDel) {
        this.skuIsDel = skuIsDel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SKU)) return false;
        SKU sku = (SKU) o;
        return Objects.equals(getSkuId(), sku.getSkuId()) &&
                Objects.equals(getCommodityId(), sku.getCommodityId()) &&
                Objects.equals(getSkuValue(), sku.getSkuValue()) &&
                Objects.equals(getSkuOriginalPrice(), sku.getSkuOriginalPrice()) &&
                Objects.equals(getSkuLastPrice(), sku.getSkuLastPrice()) &&
                Objects.equals(getPresentPrice(), sku.getPresentPrice()) &&
                Objects.equals(getSkuInventory(), sku.getSkuInventory()) &&
                Objects.equals(getSkuSales(), sku.getSkuSales()) &&
                Objects.equals(getSkuCreateTime(), sku.getSkuCreateTime()) &&
                Objects.equals(getSkuUpdateTime(), sku.getSkuUpdateTime()) &&
                Objects.equals(getSkuIsDel(), sku.getSkuIsDel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSkuId(), getCommodityId(), getSkuValue(), getSkuOriginalPrice(), getSkuLastPrice(), getPresentPrice(), getSkuInventory(), getSkuSales(), getSkuCreateTime(), getSkuUpdateTime(), getSkuIsDel());
    }

    @Override
    public String toString() {
        return "SKU{" +
                "skuId=" + skuId +
                ", commodityId=" + commodityId +
                ", skuValue='" + skuValue + '\'' +
                ", skuOriginalPrice=" + skuOriginalPrice +
                ", skuLastPrice=" + skuLastPrice +
                ", presentPrice=" + presentPrice +
                ", skuInventory=" + skuInventory +
                ", skuSales=" + skuSales +
                ", skuCreateTime=" + skuCreateTime +
                ", skuUpdateTime=" + skuUpdateTime +
                ", skuIsDel=" + skuIsDel +
                '}';
    }
}
