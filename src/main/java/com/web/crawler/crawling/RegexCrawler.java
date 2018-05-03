package com.web.crawler.crawling;

import com.web.crawler.model.Page;

import java.util.List;

public interface RegexCrawler {
    List<String> find(Page page);
}
