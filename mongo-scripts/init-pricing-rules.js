db.pricing_rules.insertMany([
    {
        name: "QuantityDiscountRule",
        itemName: "A",
        specialQuantity: 3,
        specialPrice: 100.0
    },
    {
        name: "QuantityDiscountRule",
        itemName: "B",
        specialQuantity: 2,
        specialPrice: 80.0
    },
    {
        name: "SpecialDatePricingRule",
        itemName: "E",
        discountPercentage: 10,
        specialDates: ["2024-01-20", "2024-02-14"]
    },

    {
        name: "UnitPricingRule",
        itemName: "C",
        basePrice: 25.0
    },
    {
        name: "UnitPricingRule",
        itemName: "D",
        basePrice: 20.0
    },
]);
