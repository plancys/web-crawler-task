package com.web.crawler.crawling;

import com.web.crawler.model.Page;

import java.util.Collection;

public interface WebCrawler {

    /**
     * Returns all pages which are linked on given <code>Page</code>
     */
    Collection<Page> crawl(Page page);
}
