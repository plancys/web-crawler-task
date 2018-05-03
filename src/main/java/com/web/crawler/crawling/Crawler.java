package com.web.crawler.crawling;

import com.web.crawler.extract.PageExtractor;
import com.web.crawler.model.Page;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Crawler implements WebCrawler {

    private RegexLinkCrawler regexLinkCrawler;
    private PageExtractor pageExtractor;

    public Crawler(RegexLinkCrawler regexLinkCrawler, PageExtractor pageExtractor) {
        this.regexLinkCrawler = regexLinkCrawler;
        this.pageExtractor = pageExtractor;
    }

    @Override
    public Collection<Page> crawl(Page page) {

        List<String> links = regexLinkCrawler.find(page);

        return links.stream()
                .map(link -> pageExtractor.extractPage(link))
                .collect(Collectors.toList());
    }
}
