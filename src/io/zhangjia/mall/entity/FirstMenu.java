package io.zhangjia.mall.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FirstMenu {
    private BigDecimal firstMenuId;
    private String firstMenuChineseName;
    private String firstMenuEnglishName;
    private String firstMenuAbout;
    private Date firstMenuCreateTime;
    private Integer firstMenuIsDel;

//------------------------------------//
    //存二级菜单
    private List<Map<String,Object>> secMenu;

    public List<Map<String, Object>> getSecMenu() {
        return secMenu;
    }

    public void setSecMenu(List<Map<String, Object>> secMenu) {
        this.secMenu = secMenu;
    }

    //------------------------------------//
    public FirstMenu() {
    }

    public FirstMenu(String firstMenuChineseName, String firstMenuEnglishName, String firstMenuAbout, Date firstMenuCreateTime, Integer firstMenuIsDel) {
        this.firstMenuChineseName = firstMenuChineseName;
        this.firstMenuEnglishName = firstMenuEnglishName;
        this.firstMenuAbout = firstMenuAbout;
        this.firstMenuCreateTime = firstMenuCreateTime;
        this.firstMenuIsDel = firstMenuIsDel;
    }

    public FirstMenu(BigDecimal firstMenuId, String firstMenuChineseName, String firstMenuEnglishName, String firstMenuAbout, Date firstMenuCreateTime, Integer firstMenuIsDel) {
        this.firstMenuId = firstMenuId;
        this.firstMenuChineseName = firstMenuChineseName;
        this.firstMenuEnglishName = firstMenuEnglishName;
        this.firstMenuAbout = firstMenuAbout;
        this.firstMenuCreateTime = firstMenuCreateTime;
        this.firstMenuIsDel = firstMenuIsDel;
    }

    public BigDecimal getFirstMenuId() {
        return firstMenuId;
    }

    public void setFirstMenuId(BigDecimal firstMenuId) {
        this.firstMenuId = firstMenuId;
    }

    public String getFirstMenuChineseName() {
        return firstMenuChineseName;
    }

    public void setFirstMenuChineseName(String firstMenuChineseName) {
        this.firstMenuChineseName = firstMenuChineseName;
    }

    public String getFirstMenuEnglishName() {
        return firstMenuEnglishName;
    }

    public void setFirstMenuEnglishName(String firstMenuEnglishName) {
        this.firstMenuEnglishName = firstMenuEnglishName;
    }

    public String getFirstMenuAbout() {
        return firstMenuAbout;
    }

    public void setFirstMenuAbout(String firstMenuAbout) {
        this.firstMenuAbout = firstMenuAbout;
    }

    public Date getFirstMenuCreateTime() {
        return firstMenuCreateTime;
    }

    public void setFirstMenuCreateTime(Date firstMenuCreateTime) {
        this.firstMenuCreateTime = firstMenuCreateTime;
    }

    public Integer getFirstMenuIsDel() {
        return firstMenuIsDel;
    }

    public void setFirstMenuIsDel(Integer firstMenuIsDel) {
        this.firstMenuIsDel = firstMenuIsDel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstMenu firstMenu = (FirstMenu) o;
        return Objects.equals(firstMenuId, firstMenu.firstMenuId) &&
                Objects.equals(firstMenuChineseName, firstMenu.firstMenuChineseName) &&
                Objects.equals(firstMenuEnglishName, firstMenu.firstMenuEnglishName) &&
                Objects.equals(firstMenuAbout, firstMenu.firstMenuAbout) &&
                Objects.equals(firstMenuCreateTime, firstMenu.firstMenuCreateTime) &&
                Objects.equals(firstMenuIsDel, firstMenu.firstMenuIsDel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstMenuId, firstMenuChineseName, firstMenuEnglishName, firstMenuAbout, firstMenuCreateTime, firstMenuIsDel);
    }

    @Override
    public String toString() {
        return "FirstMenuDao{" +
                "firstMenuId=" + firstMenuId +
                ", firstMenuChineseName='" + firstMenuChineseName + '\'' +
                ", firstMenuEnglishName='" + firstMenuEnglishName + '\'' +
                ", firstMenuAbout='" + firstMenuAbout + '\'' +
                ", firstMenuCreateTime=" + firstMenuCreateTime +
                ", firstMenuIsDel=" + firstMenuIsDel +
                '}';
    }
}
