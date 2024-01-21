package com.idealo.mapper;

import com.idealo.domain.dto.ItemDto;
import com.idealo.domain.entity.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public static ItemDto toDto(Item item) {
        return ItemDto.builder()
                .name(item.getName())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .build();
    }
}
