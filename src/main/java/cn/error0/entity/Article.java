package cn.error0.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import java.util.Date;

@Data
public class Article {

    private Long id;              // 主键
    private String title;         // 文章标题
    private String content;       // 文章内容
    private boolean top;          // 是否置顶
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createDate;      // 创建时间
    private Date modifiedDate;    // 修改时间
    private int traffic;          //文章点击数量
    private Long kindId;          //分类Id
    private String kindName;      //分类名称
    private String kindNumber;    //文章对应分类数量
}
