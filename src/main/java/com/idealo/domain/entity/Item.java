package com.idealo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "items")
public class Item {
    @Id
    private String id;
    @NonNull
    private Character name;
    @NonNull
    private Integer quantity;
    @NonNull
    private  Double price;
}
