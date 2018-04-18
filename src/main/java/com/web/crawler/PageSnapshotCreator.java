package com.web.crawler;

import com.web.crawler.crawling.WebCrawler;
import com.web.crawler.extract.PageExtractor;
import com.web.crawler.model.Page;
import com.web.crawler.model.PageSnapshot;

import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toSet;

public class PageSnapshotCreator {

    private final WebCrawler webCrawler;
    private final PageExtractor pageExtractor;
    private final Set<String> visitedPage;

    public PageSnapshotCreator(
            WebCrawler webCrawler,
            PageExtractor pageExtractor) {

        this.webCrawler = webCrawler;
        this.pageExtractor = pageExtractor;
        this.visitedPage = new HashSet<>();
    }

    public PageSnapshot createPageNode(String url, int depth) {
        return getPage(pageExtractor.extractPage(url), depth);
    }

    private PageSnapshot getPage(Page page, int depth) {
        if (depth == 0) {
            return new PageSnapshot(page, emptyList());
        }

        return new PageSnapshot(page, getLinks(page, depth));
    }

    private Set<PageSnapshot> getLinks(Page root, int depth) {
        return webCrawler.crawl(root)
                .stream()
                .filter(p -> !visitedPage.contains(p.getAddress()))
                .peek(p -> visitedPage.add(p.getAddress()))
                .map(p -> getPage(p, depth - 1))
                .collect(toSet());
    }
}