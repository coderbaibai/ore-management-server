package edu.hitwh.oremanagementserver.controller;

import lombok.Data;

@Data

public class Result {
    private int code;
    private String msg;
    private Object data;

    public Result(int code, Object data, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Result(int code) {
        this.code = code;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
