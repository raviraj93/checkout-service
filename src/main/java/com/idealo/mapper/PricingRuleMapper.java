package com.idealo.mapper;

import com.idealo.domain.entity.PricingRuleEntity;
import com.idealo.pricing.RuleConfiguration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PricingRuleMapper {

    public List<RuleConfiguration> convertToEntityList(List<PricingRuleEntity> pricingRuleEntities) {
        return pricingRuleEntities.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    private RuleConfiguration convertToEntity(PricingRuleEntity pricingRuleEntity) {
        return new RuleConfiguration(pricingRuleEntity.getName(),
                pricingRuleEntity.getItemName(),
                pricingRuleEntity.getUnitPrice(),
                pricingRuleEntity.getQuantity(),
                pricingRuleEntity.getSpecialQuantity(),
                pricingRuleEntity.getSpecialPrice(),
                pricingRuleEntity.getDiscountPercentage(),
                pricingRuleEntity.getSpecialDates());
    }
}
