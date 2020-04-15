package cn.error0.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SortInfo {
    private Long id;
    private String name;
    private int number;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
    private Date modifiedDate;
    private Boolean effective;
}
