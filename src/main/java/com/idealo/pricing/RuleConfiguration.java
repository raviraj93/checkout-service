package com.idealo.pricing;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RuleConfiguration {
    private String name;
    private Character itemName;
    private Double unitPrice ;
    private Integer quantity;
    private Integer specialQuantity ;
    private Double specialPrice ;
    private Integer discountPercentage ;
    private List<String> specialDates;
}

