package cn.error0.util;

import lombok.Data;

@Data
public class Result<T>{

    private Integer code; //返回状态
    private String msg;   //返回信息
    private T data;       //返回数据

}
