package com.xmut303.entity;

import lombok.Data;

/**
 * 响应前台json数据的结果集
 * @author JokkiePan
 * @vision 1.0
 * @createDate 2023/4/23 11:09
 */

@Data
public class Result<T> {

    private Boolean success;
    private String message;
    private T data;

    public Result(Boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Result(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
