package cn.error0;

import cn.error0.dao.ArticleContentDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {
    @Autowired
    ArticleContentDao articleContentDao;
    @Test
    void getAllArticleContent() {
       System.out.println(articleContentDao.selectAll());
    }

}
