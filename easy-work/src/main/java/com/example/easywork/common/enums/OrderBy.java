package com.example.easywork.common.enums;

public enum OrderBy {
    ID("id"), WORK_NAME("workName");
    private String OrderByCode;

    private OrderBy(String orderBy) {
        this.OrderByCode = orderBy;
    }

    public String getOrderByCode() {
        return this.OrderByCode;
    }
}
