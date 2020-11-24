package org.example.demo.other.web.article.service;

import com.ag777.util.lang.StringUtils;
import org.example.demo.other.web.article.mapper.ArticleMapper;
import org.example.demo.other.web.article.dao.ArticleJdbcDao;
import org.example.demo.other.web.article.model.Article;
import org.example.demo.other.web.article.service.interf.ArticleServiceInterf;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@ComponentScan(value = {"com.example.demo"})
public class ArticleJdbcSerice implements ArticleServiceInterf {

    public static final String CACHE_OBJECT = "article";  //缓存名称
    public static final String CACHE_LIST_KEY  = "\"list\"";

    @Resource
    ArticleJdbcDao articleJdbcDao;

    @Resource
    ArticleMapper articleDao;

    @CacheEvict(value = CACHE_OBJECT,key = CACHE_LIST_KEY)   //删除List集合缓存
    public Article save(Article article) {
//        articleJdbcDao.save(article);
        //int a = 2/0；  //人为制造一个异常，用于测试事务
        articleDao.add(article.setArticleId(StringUtils.uuid()));
        return article;
    }

    @Caching(evict = {
            @CacheEvict(value = CACHE_OBJECT,key = CACHE_LIST_KEY),   //删除List集合缓存
            @CacheEvict(value = CACHE_OBJECT,key = "#id")  //删除单条记录缓存
    })
    public void deleteById(String id) {
        articleDao.deleteById(id);
//        articleJdbcDao.deleteById(id);
    }

    public Article findById(String id){
        return articleJdbcDao.findById(id);
    }

    @CachePut(value = CACHE_OBJECT,key = "#article.getArticleId()")
    @Caching(evict = {
            @CacheEvict(value = CACHE_OBJECT,key = CACHE_LIST_KEY),   //删除List集合缓存
//            @CacheEvict(value = CACHE_OBJECT,key = "#article.getArticleId()")  //删除单条记录缓存
    })
    public Article update(Article article) {
        articleDao.update(article);
        return article;
    }

    @Cacheable(value = CACHE_OBJECT, key = CACHE_LIST_KEY)   //这里的value和key参考下面的redis数据库截图理解
    public List<Article> findAll() {
        return articleDao.queryList(null);
//        return articleDAO.findAll();
    }
}
