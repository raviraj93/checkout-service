package com.idealo.repository;

import com.idealo.domain.entity.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {

    Item findByName(Character name);
}
