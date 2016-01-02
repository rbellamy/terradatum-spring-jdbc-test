package com.terradatum.db.objects;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by rbellamy on 12/28/15.
 */
public class MlsAreasObject implements Serializable {
    private Integer mlsSid;
    private BigDecimal areaTypeId;
    private String viewName;
    private String hasKey;
    private BigDecimal sortOrder;
    public Integer getMlsSid() {
        return mlsSid;
    }

    public void setMlsSid(Integer mlsSid) {
        this.mlsSid = mlsSid;
    }

    public BigDecimal getAreaTypeId() {
        return areaTypeId;
    }

    public void setAreaTypeId(BigDecimal areaTypeId) {
        this.areaTypeId = areaTypeId;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getHasKey() {
        return hasKey;
    }

    public void setHasKey(String hasKey) {
        this.hasKey = hasKey;
    }

    public BigDecimal getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(BigDecimal sortOrder) {
        this.sortOrder = sortOrder;
    }
}
