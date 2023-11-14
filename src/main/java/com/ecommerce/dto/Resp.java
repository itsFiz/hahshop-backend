package com.ecommerce.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resp {
    private Integer status = 1; // 1 - ok, 4 - not found
    private Object body;
    private Map<String, Object> pagination;

    public Resp(Integer status, Object body) {
        this.status = status;
        this.body = body;
    }
}
