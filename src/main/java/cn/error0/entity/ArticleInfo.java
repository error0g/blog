package cn.error0.entity;

import lombok.Data;

@Data
public class ArticleInfo {
    private Long id;
    private String title;
    private int traffic;
    private boolean top;
}
