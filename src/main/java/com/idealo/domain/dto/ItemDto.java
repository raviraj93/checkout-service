package com.idealo.domain.dto;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class ItemDto {
    @NonNull
    private final String name;
    @NonNull
    private final Integer quantity;
    @NonNull
    private final Double price;
    @Nullable
    private final Integer offerQty;
    @Nullable
    private final Double offerPrice;
    @Nullable
    private final SpecialPrice specialPrice;
}
