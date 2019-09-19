package com.example.easywork.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    PLAINING(1), DOING(2), COMPLETE(3);

    private int statusValue;

    private Status (int statusValue) {
            this.statusValue = statusValue;
    }

    public int getStatusValue() {
        return statusValue;
    }
    @JsonValue
    public int toValue() {
         return getStatusValue();
    }
    public static Status forCode(int code) {
        for (Status element : values()) {
           if (element.statusValue == code) {
              return element;
           }
       }
       return null; //or throw exception if you like...
    }

    @JsonCreator
    public static Status forValue(String v) {
        return Status.forCode(Integer.parseInt(v));
    } 
}
