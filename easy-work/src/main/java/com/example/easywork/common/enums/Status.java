package com.example.easywork.common.enums;

public enum Status {
    PLAINING(1), DOING(2), COMPLETE(3);

    private int statusValue;

    private Status (int statusValue) {
            this.statusValue = statusValue;
    }

    public int getStatusValue() {
        return statusValue;
    }
}
