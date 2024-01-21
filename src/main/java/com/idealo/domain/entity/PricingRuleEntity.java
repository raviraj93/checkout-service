package com.idealo.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "pricing_rules")
@Data
public class PricingRuleEntity {
    @Id
    private String id;
    private String name;
    private Character itemName;
    private double unitPrice;
    private int quantity;
    private Integer specialQuantity;
    private Double specialPrice;
    private Integer discountPercentage;
    private List<String> specialDates;
}
