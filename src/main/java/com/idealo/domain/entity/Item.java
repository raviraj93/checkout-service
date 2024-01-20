package com.idealo.domain.entity;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public final class Item {
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
}
