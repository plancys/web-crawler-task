package com.web.crawler.crawling;

import com.web.crawler.extract.PageExtractor;
import com.web.crawler.model.Page;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Crawler implements WebCrawler {

    private RegexCrawler regexCrawler;
    private PageExtractor pageExtractor;

    public Crawler(RegexCrawler regexCrawler, PageExtractor pageExtractor) {
        this.regexCrawler = regexCrawler;
        this.pageExtractor = pageExtractor;
    }

    @Override
    public Collection<Page> crawl(Page page) {

        List<String> links = regexCrawler.find(page.getAddress(), page.getBody());

        return links.stream()
                .map(link -> pageExtractor.extractPage(link))
                .collect(Collectors.toList());
    }
}
