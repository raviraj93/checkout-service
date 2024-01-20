package com.idealo.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BasketDto {
    @NotNull
    private List<ItemDto> items;

    @NotNull
    public double total;
}
