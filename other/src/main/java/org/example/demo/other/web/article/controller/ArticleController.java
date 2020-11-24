package org.example.demo.other.web.article.controller;

import com.ag777.util.lang.collection.MapUtils;
import org.example.demo.other.web.article.model.Article;
import org.example.demo.other.web.article.service.ArticleJdbcSerice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.example.demo.base.model.AjaxResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@Api(value = "测试SwaggerAPI Annotation", tags = "Swagger测试之用户信息管理API")
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    ArticleJdbcSerice articleSerice;

    @ApiOperation(value = "接口方法说明", notes = "接口的详情描述")
    @GetMapping("/list")
    public AjaxResponse list() {
        return AjaxResponse.success(articleSerice.findAll());
    }

    @GetMapping("/test")
    public Map<String, Object> test() {
        return MapUtils.of("data", "啊哈");
    }

    @GetMapping("/add")
    public AjaxResponse add(@RequestParam("content") String content) {
        articleSerice.save(new Article().setContent(content).setCreateTime(new Date()));
        return AjaxResponse.success();
    }

    @ApiOperation(value = "接口方法说明", notes = "接口的详情描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章编号",required = true, dataType = "String", paramType = "path")
    })
    @DeleteMapping("/delete/{id}")
    public AjaxResponse delete(@PathVariable(name = "id") String articleId) {
        articleSerice.deleteById(articleId);
        return AjaxResponse.success();
    }

    @ApiOperation(value = "接口方法说明", notes = "接口的详情描述")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "文章编号",required = true, dataType = "String", paramType = "path")
//    })
    @PutMapping("/update")
    public AjaxResponse update(@RequestBody Article article) {
        articleSerice.update(article);
        return AjaxResponse.success();
    }
}
