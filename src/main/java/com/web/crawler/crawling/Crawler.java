package com.web.crawler.crawling;

import com.web.crawler.extract.HttpPageExtractor;
import com.web.crawler.extract.PageExtractor;
import com.web.crawler.model.Page;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Crawler implements WebCrawler {

    RegexCrawler regexCrawler = new RegexCrawler();
    PageExtractor pageExtractor = new HttpPageExtractor();

    @Override
    public Collection<Page> crawl(Page page) {

        List<String> links = regexCrawler.find(page.getBody());

        return links.stream()
                .map(link -> pageExtractor.extractPage(link))
                .collect(Collectors.toList());
    }
}
