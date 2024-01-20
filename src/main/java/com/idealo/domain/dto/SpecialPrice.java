package com.idealo.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class SpecialPrice {

    @NotNull
    private final int qty;

    @NotNull
    private final double price;
}




