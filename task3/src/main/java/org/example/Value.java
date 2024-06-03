package org.example;

import java.math.BigDecimal;
import java.util.List;

public class Value {
    private BigDecimal id;
    private String value;

    @Override
    public String toString(){
        return "id: " + id + " value: " + value;
    }
    public Value(BigDecimal id, String value){
        this.id = id;
        this.value = value;
    }

    public Value(){

    }

    public Value(BigDecimal id, String value, List<Value> values){
        this.id = id;
        this.value = value;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
