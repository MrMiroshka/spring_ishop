package ru.miroshka.hw3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private String phone;
    private int totalPrice;
    private String address;
    private LocalDateTime createdAt;

}
