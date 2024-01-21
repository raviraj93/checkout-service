package com.idealo.domain.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

@Data
@Builder
@Document(collection = "baskets")
public final class Basket {
    @Id
    private String id;
    @NotNull
    private List<Item> items;
    @NotNull
    public double total;
}
