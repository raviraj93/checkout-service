#!/bin/bash

# Your logic to insert pricing rules
mongo mongodb://localhost:27017/idealo --eval 'db.pricing_rules.insertMany([
                                                   // Quantity Discount Rules
                                                   { name: "QuantityDiscountRule", itemName: "A", unitPrice: 40.0, specialQuantity: 3, specialPrice: 100.0 },
                                                   { name: "QuantityDiscountRule", itemName: "B", unitPrice: 50.0, specialQuantity: 2, specialPrice: 80.0 },
                                                   { name: "QuantityDiscountRule", itemName: "C", unitPrice: 25.0, specialQuantity: 3, specialPrice: 100.0 },
                                                   { name: "QuantityDiscountRule", itemName: "D", unitPrice: 20.0, specialQuantity: 3, specialPrice: 100.0 },
                                                   { name: "QuantityDiscountRule", itemName: "E", unitPrice: 100.0, specialQuantity: 3, specialPrice: 100.0 },

                                                   // Special Date Pricing Rules
                                                   { name: "SpecialDatePricingRule", itemName: "E", unitPrice: 100.0, specialQuantity: 1, discountPercentage: 0, specialDates: ["2024-01-20"] },

                                                   // Unit Pricing Rules
                                                   { name: "UnitPricingRule", itemName: "A", unitPrice: 40.0 },
                                                   { name: "UnitPricingRule", itemName: "B", unitPrice: 50.0 },
                                                   { name: "UnitPricingRule", itemName: "C", unitPrice: 25.0 },
                                                   { name: "UnitPricingRule", itemName: "D", unitPrice: 20.0 },
                                                   { name: "UnitPricingRule", itemName: "E", unitPrice: 100.0 }
                                               ]);'
