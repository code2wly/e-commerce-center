package com.company.springcloud.enitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private Long id;
    private Long userId;
    private Long productId;
    private Integer nums;
    private Integer money;
    private Integer status;

}

