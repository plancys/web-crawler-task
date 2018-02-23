package com.web.crawler;

import com.web.crawler.crawling.WebCrawler;
import com.web.crawler.extract.PageExtractor;
import com.web.crawler.model.Page;
import com.web.crawler.model.PageNode;

import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toSet;

public class PageNodeBuilder {

    private final WebCrawler webCrawler;
    private final PageExtractor pageExtractor;
    private final Set<String> visitedPage;

    public PageNodeBuilder(
            WebCrawler webCrawler,
            PageExtractor pageExtractor) {

        this.webCrawler = webCrawler;
        this.pageExtractor = pageExtractor;
        this.visitedPage = new HashSet<>();
    }

    public PageNode createPageNode(String url, int depth) {
        return getPage(pageExtractor.extractPage(url), depth);
    }

    private PageNode getPage(Page page, int depth) {
        if (depth == 0) {
            return new PageNode(page, emptyList());
        }

        return new PageNode(page, getLinks(page, depth));
    }

    private Set<PageNode> getLinks(Page root, int depth) {
        return webCrawler.crawl(root)
                .stream()
                .filter(p -> !visitedPage.contains(p.getAddress()))
                .peek(p -> visitedPage.add(p.getAddress()))
                .map(p -> getPage(p, depth - 1))
                .collect(toSet());
    }
}
