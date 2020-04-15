package cn.error0;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class BlogApplicationTests {

    @Test
    void getAllArticleContent() {

      String str="";
      System.out.println(str.isEmpty());
      System.out.println(str.length());
        System.out.println(str==null);
        System.out.println(str.equals(""));

    }
}
