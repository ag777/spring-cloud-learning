package org.example.demo.other.web.article.mapper;

import org.example.demo.other.web.article.model.Article;

import java.util.List;
import java.util.Map;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @Date: 2020/11/20 14:54
 */
public interface ArticleMapper {


    List<Article> queryList(Map<String, Object> params);


    int add(Article article);


    void deleteById(String id);


    int update(Article article);
}
