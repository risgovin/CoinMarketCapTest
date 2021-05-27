package com.rishabh.context;

import java.util.HashMap;
import java.util.Map;

public class TestContext {

    private Map<String, Object> scenarioContext;

    public TestContext() {
        scenarioContext = new HashMap<>();
    }

    public void put(String key, Object value) {
        scenarioContext.put(key.toString(), value);
    }

    public Object get(String key) {
        return scenarioContext.get(key.toString());
    }

    public Boolean isContains(String key) {
        return scenarioContext.containsKey(key.toString());
    }

}