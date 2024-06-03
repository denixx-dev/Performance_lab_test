package org.example;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class task3 {

    private static final String tests = "tests";
    private static final String values = "values";
    private static List<Value> valueList;
    private static List<Report> testList;

    private static void getValues(String file) {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode;

        try {
            rootNode = mapper.readTree(new File(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        valueList = new ArrayList<>();
        for (JsonNode valueNode : rootNode.path(values)) {

            Value value = new Value();
            value.setId(new BigDecimal(valueNode.path("id").intValue()));
            value.setValue(valueNode.path("value").textValue());
            valueList.add(value);
        }
    }

    private static void getTests(String file) {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode;

        try {
            rootNode = mapper.readTree(new File(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        testList = new ArrayList<>();
        for (JsonNode testNode : rootNode.path(tests)) {
            testList.add(parseNode(testNode));
        }
    }

    private static Report parseNode(JsonNode testNode) {
        Report test = new Report();
        test.setId(new BigDecimal(testNode.path("id").intValue()));
        test.setTitle(testNode.path("title").textValue());
        test.setValue(testNode.path("value").textValue());
        if (testNode.has("values")) {
            List<Report> curTest = new ArrayList<>();
            for (JsonNode node : testNode.path("values")) {
                curTest.add(parseNode(node));
            }
            test.setValues(curTest);
        }
        return test;
    }

    private static void setValues(Report report) {
        for (Value curValue : valueList) {
            if (Objects.equals(report.getId(), curValue.getId())) {
                report.setValue(curValue.getValue());
                valueList.remove(curValue);
                break;
            }
        }
        if (report.getValues() != null) {
            for (Report nestedTest : report.getValues()) {
                setValues(nestedTest);
            }
        }
    }

    private static void writeReport(String file) {

        Map<String, Object> map = new HashMap<>();
        map.put(tests, testList);

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(new File(file), map);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        getValues(args[0]);
        getTests(args[1]);

        for (Report curReport : testList) setValues(curReport);

        writeReport(args[2]);
    }
}
