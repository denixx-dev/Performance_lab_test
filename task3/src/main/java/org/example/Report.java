package org.example;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class Report {
    private BigDecimal id;
    private String title;
    private String value;
    private List<Report> values;

    @Override
    public String toString(){
        return "id: " + id +  ", title: " + title + ", value: " + value + ", values: " + values;
    }
    public String toJson() {
        return "{\"id\": " + id + ", \"title\": \"" + title + "\", \"value\": \"" + value + "\", \"values\": " + (values != null ? "[" + String.join(", ", values.stream().map(Report::toJson).collect(Collectors.toList())) + "]" : "null") + "}";
    }

    public Report(){

    }

    public Report(BigDecimal id, String title, String value) {
        this.id = id;
        this.title = title;
        this.value = value;
    }

    public Report(BigDecimal id, String title, String value, List<Report> values){
        this.id = id;
        this.title = title;
        this.value = value;
        this.values = values;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Report> getValues() {
        return values;
    }

    public void setValues(List<Report> values) {
        this.values = values;
    }
}
