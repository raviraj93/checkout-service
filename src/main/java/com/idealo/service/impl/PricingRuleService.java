package com.idealo.service.impl;

import com.idealo.domain.entity.PricingRuleEntity;
import com.idealo.mapper.PricingRuleMapper;
import com.idealo.pricing.RuleConfiguration;
import com.idealo.repository.PricingRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingRuleService {
    private final PricingRuleRepository repository;

    private final PricingRuleMapper pricingRuleMapper;

    @Autowired
    public PricingRuleService(PricingRuleRepository repository,
                              PricingRuleMapper mapper) {
        this.repository = repository;
        this.pricingRuleMapper = mapper;
    }

    public List<RuleConfiguration> getAllPricingRules() {
        return pricingRuleMapper.convertToEntityList(repository.findAll());
    }

}
