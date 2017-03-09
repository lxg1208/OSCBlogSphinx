package win.wellcoding.seimicrawler.oscblog.model;

import cn.wanghaomiao.seimi.annotation.Xpath;

/**
 * Created by liuxuegang on 2017/3/9.
 */
public class Article {
    private Long id;

    @Xpath("//div[@class='title']/text()")
    private String title;

    @Xpath("//div[@id='blogBody']/html()")
    private String content;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(final String content) {
        this.content = content;
    }
}
