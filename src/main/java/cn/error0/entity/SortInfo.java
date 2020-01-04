package cn.error0.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SortInfo {
    private Long id;
    private String name;
    private int number;
    private Date createDate;
    private Date modifiedDate;
    private Boolean effective;
}
