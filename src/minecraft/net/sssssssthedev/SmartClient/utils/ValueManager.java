package net.sssssssthedev.SmartClient.utils;

import java.util.ArrayList;

public class ValueManager extends ListManager<Value> {
    public ValueManager() {
        super(new ArrayList<>());
    }

    public Value find(String name) {
        for (Value value : contents) {
            if (value.getName().equals(name)) {
                return value;
            }
        }
        return null;
    }
}
