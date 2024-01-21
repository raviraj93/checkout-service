package com.idealo.service.impl;

import com.idealo.domain.dto.RuleConfiguration;
import com.idealo.domain.entity.PricingRuleEntity;
import com.idealo.mapper.PricingRuleMapper;
import com.idealo.repository.PricingRuleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PricingRuleServiceTest {

    @Mock
    private PricingRuleRepository repository;

    @Mock
    private PricingRuleMapper pricingRuleMapper;

    @InjectMocks
    private PricingRuleService pricingRuleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllPricingRules_ReturnsListOfRuleConfigurations() {

        PricingRuleEntity ruleConfig1 = new PricingRuleEntity();
        ruleConfig1.setId("12421");
        ruleConfig1.setName("UnitPricingRule");
        ruleConfig1.setItemName('A');
        ruleConfig1.setUnitPrice(50.0);
        ruleConfig1.setQuantity(1);

        PricingRuleEntity ruleConfig2 = new PricingRuleEntity();
        ruleConfig2.setId("124213");
        ruleConfig2.setName("UnitPricingRule");
        ruleConfig2.setItemName('B');
        ruleConfig2.setUnitPrice(40.0);
        ruleConfig2.setQuantity(1);

        when(repository.findAll()).thenReturn(List.of(ruleConfig2, ruleConfig1));


        List<RuleConfiguration> ruleConfigList = List.of(new RuleConfiguration("UnitPricingRule", 'A', 50.0, 1, 0, null, null, null),
                new RuleConfiguration("UnitPricingRule", 'B', 40.0, 1, 0, null, null, null));

        when(pricingRuleMapper.convertToEntityList(repository.findAll())).thenReturn(ruleConfigList);

        List<RuleConfiguration> result = pricingRuleService.getAllPricingRules();

        assertEquals(ruleConfigList, result);
    }

    @Test
    void getAllPricingRules_ReturnsEmptyListWhenNoRules() {
        when(repository.findAll()).thenReturn(Arrays.asList());
        when(pricingRuleMapper.convertToEntityList(repository.findAll())).thenReturn(Arrays.asList());

        List<RuleConfiguration> result = pricingRuleService.getAllPricingRules();

        assertEquals(0, result.size());
    }
}
