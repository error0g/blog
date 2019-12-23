package cn.error0.entity;

import lombok.Data;

@Data
public class ArticleSort {
    private Long id;
    private Long sortId;
    private Long artcleId;
    private Boolean effective;
}
