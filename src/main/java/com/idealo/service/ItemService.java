package com.idealo.service;

import com.idealo.domain.dto.ItemDto;

import java.util.List;

public interface ItemService {
    List<ItemDto> getItems(List<String> itemsName);
}
