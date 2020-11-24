package org.example.demo.other.web.article.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true) // 可以链式调用 setter
public class Article {

    /**
     *
     */
    private String articleId;
    /**
     *
     */
    private String author;
    /**
     *
     */
    private String title;
    /**
     *
     */
    private String content;
    /**
     *
     */
    private java.util.Date createTime;

}

