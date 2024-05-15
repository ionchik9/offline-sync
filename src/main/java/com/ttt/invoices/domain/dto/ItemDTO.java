package com.ttt.invoices.domain.dto;

import lombok.*;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO  {
    private long updatedAt;
    private String name;
    private long price;
    private String description;
    private Boolean archived;
    private boolean isTaxable;
}
