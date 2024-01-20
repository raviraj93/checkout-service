package com.idealo.service.impl;

import com.idealo.domain.dto.ItemDto;
import com.idealo.domain.entity.Item;
import com.idealo.mapper.ItemMapper;
import com.idealo.repository.ItemRepository;
import com.idealo.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;


    @Override
    public List<ItemDto> getItems(List<String> itemsName) {
        return itemRepository
                .findByNameIn(itemsName)
                .stream()
                .map(item -> ItemMapper.toDto(item, itemsName))
                .collect(toList());
    }
}
