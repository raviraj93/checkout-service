package com.idealo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
public class ItemDto {
    private Character name;
    private Integer quantity;
    private Double price;
}
