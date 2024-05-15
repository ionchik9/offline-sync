package com.ttt.invoices.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmountFormat implements Serializable {
    private char decimal;
    private char group;
}
