package org.example.demo.other.web.article.dao;

import com.ag777.util.lang.StringUtils;
import org.example.demo.other.web.article.model.Article;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository  //持久层依赖注入注解
public class ArticleJdbcDao {

    @Resource
//    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    //保存文章
    public void save(Article article) {
        //jdbcTemplate.update适合于insert 、update和delete操作；
        jdbcTemplate.update("INSERT INTO article(article_id,author, title,content,create_time) values(?, ?, ?, ?, ?)",
                StringUtils.uuid(),
                article.getAuthor(),
                article.getTitle(),
                article.getContent(),
                article.getCreateTime());

    }

    //删除文章
    public void deleteById(String id) {
        //jdbcTemplate.update适合于insert 、update和delete操作；
        jdbcTemplate.update("DELETE FROM article WHERE article_id = ?",id);

    }

    //根据id查找文章
    public Article findById(String id) {
        //queryForObject用于查询单条记录返回结果
        return (Article) jdbcTemplate.queryForObject("SELECT * FROM article WHERE article_id=?",
                new Object[]{id},new BeanPropertyRowMapper<>(Article.class));
    }

    //查询所有
    public List<Article> findAll(){
        //query用于查询结果列表
        return jdbcTemplate.query("SELECT * FROM article ",  new BeanPropertyRowMapper<>(Article.class));
    }

}
