package com.company.springcloud.enitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account {
    private Long id;
    private Long userId;
    private Integer money;
}
