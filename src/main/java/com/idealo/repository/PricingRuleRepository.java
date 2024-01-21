package com.idealo.repository;

import com.idealo.domain.entity.PricingRuleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PricingRuleRepository extends MongoRepository<PricingRuleEntity, String> {
}
