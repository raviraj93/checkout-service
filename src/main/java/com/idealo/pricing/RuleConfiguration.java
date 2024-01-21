package com.idealo.pricing;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RuleConfiguration {
    private String name;
    private char itemName;
    private int specialQuantity;
    private double specialPrice;
    private int discountPercentage;
    private List<String> specialDates;
}

