package com.idealo.domain.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public final class Basket {
    @NotNull
    private List<Item> items;

    @NotNull
    public double total;
}
