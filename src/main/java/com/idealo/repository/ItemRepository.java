package com.idealo.repository;

import com.idealo.domain.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemRepository extends CrudRepository<Item, String> {

    List<Item> findByNameIn(List<String> name);

}
