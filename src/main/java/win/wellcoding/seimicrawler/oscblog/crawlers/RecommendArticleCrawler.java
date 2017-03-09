package win.wellcoding.seimicrawler.oscblog.crawlers;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import cn.wanghaomiao.xpath.model.JXDocument;
import org.springframework.beans.factory.annotation.Autowired;
import win.wellcoding.seimicrawler.oscblog.dao.ArticleDao;
import win.wellcoding.seimicrawler.oscblog.model.Article;

import java.util.ArrayList;
import java.util.List;


/**
 * 抓取OSChina博客最新推荐下的文章
 */
@Crawler(name = "recommendArticleCrawler")
public class RecommendArticleCrawler extends BaseSeimiCrawler {

    /**
     * 总页数
     */
    private static final Integer PAGE_TOTAL = 50;
    /**
     * 推荐文章列表URL
     */
    private static final String URL = "http://www.oschina.net/action/ajax/get_more_recommend_blog?classification=0&p=";

    @Autowired
    private ArticleDao articleDao;

    @Override
    public String[] startUrls() {
        final List<String> urls = new ArrayList<>();
        for (int i = 1; i <= PAGE_TOTAL; i++) {
            urls.add(URL + i);
        }
        return urls.toArray(new String[PAGE_TOTAL]);
    }

    @Override
    public void start(final Response response) {
        final JXDocument doc = response.document();
        try {
            final List<Object> urls = doc.sel("//a[@class='sc overh blog-title-link']/@href");
            this.logger.info("{}", urls.size());
            for (final Object s : urls) {
                push(Request.build(s.toString(), "renderArticle"));
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 提取文章并保存
     *
     * @param response
     */
    public void renderArticle(final Response response) {
        final JXDocument doc = response.document();
        try {
            final Article article = response.render(Article.class);
            this.logger.info("bean resolve res={},url={}", article, response.getUrl());
            // 使用神器paoding-jade存储到DB
            final int changeNum = this.articleDao.save(article);
            final Long blogId = article.getId();
            this.logger.info("store success,blogId = {},changeNum={}", blogId, changeNum);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
