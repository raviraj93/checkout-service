package com.idealo.mapper;

import com.idealo.domain.dto.ItemDto;
import com.idealo.domain.dto.SpecialPrice;
import com.idealo.domain.entity.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.counting;

@Service
public final class ItemMapper {
    private ItemMapper() {
        throw new UnsupportedOperationException();
    }

    public static ItemDto toDto(Item item, List<String> itemsRequest) {
        return ItemDto.builder()
                .name(item.getName())
                .price(item.getPrice())
                .quantity(countSimilarItemsInRequest(item, itemsRequest))
                .specialPrice(prepareSpecialPrice(item))
                .build();
    }

    private static int countSimilarItemsInRequest(Item item, List<String> itemsName) {
        return itemsName.stream().filter(name -> name.equals(item.getName())).collect(counting()).intValue();
    }

    private static SpecialPrice prepareSpecialPrice(Item item) {
        return Optional.ofNullable(item.getOfferPrice())
                .map(price -> SpecialPrice.builder()
                        .price(price)
                        .qty(Objects.requireNonNullElse(item.getOfferQty(), 0))
                        .build())
                .orElse(null);
    }

}
