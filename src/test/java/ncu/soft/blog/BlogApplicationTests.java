package ncu.soft.blog;

import ncu.soft.blog.entity.Article;
import ncu.soft.blog.entity.ArticleAndUserVo;
import ncu.soft.blog.service.impl.ArticlesServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BlogApplicationTests {

    private final static Logger log  = LoggerFactory.getLogger(BlogApplicationTests.class);

    @Autowired
    ValueOperations<String, Object> valueOperations;

    @Autowired
    ArticlesServiceImpl articlesService;

    @Test
    public void testRedis() {
        valueOperations.set("test", "测试一下redis客户端");
        String value = (String) valueOperations.get("test");
        log.info("缓存中的信息：【{}】", value);
    }

    @Test
    public void testGetPage(){
        List<ArticleAndUserVo> articles = articlesService.getArticlesByPage(1, 5);
        log.info("分页信息 【{}】", articles);
    }

    @Test
    public void testGetArticle(){
        ArticleAndUserVo article = articlesService.getArticle(50, "127.0.0.1");
        log.info("文章信息 【{}】", article);
    }
}
