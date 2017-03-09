package win.wellcoding.seimicrawler.oscblog.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import win.wellcoding.seimicrawler.oscblog.model.Article;

/**
 * Created by liuxuegang on 2017/3/9.
 */
public interface ArticleDao {
    @Insert("insert into article (title,content,update_time) values (#{article.title},#{article.content},now())")
    @Options(useGeneratedKeys = true, keyProperty = "article.id")
    int save(@Param("article") Article article);
}
