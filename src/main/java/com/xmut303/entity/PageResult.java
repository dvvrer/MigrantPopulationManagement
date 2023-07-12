package com.xmut303.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author JokkiePan
 * @vision 1.0
 * @createDate 2023/4/21 15:32
 */

@Data
public class PageResult implements Serializable {

    public PageResult(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    private Long total;
    private List rows;
}
