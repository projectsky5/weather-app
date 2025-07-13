package com.projectsky.weatherconsumer.constants;

public enum Condition {

    SUNNY("Солнечно"),
    CLOUDY("Облачно"),
    RAINY("Дождь");

    private final String value;

    Condition(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Condition fromValue(String value){
        for (Condition condition : Condition.values()) {
            if (condition.getValue().equalsIgnoreCase(value)) {
                return condition;
            }
        }
        throw new IllegalArgumentException("Unknown condition: " + value);
    }

}
