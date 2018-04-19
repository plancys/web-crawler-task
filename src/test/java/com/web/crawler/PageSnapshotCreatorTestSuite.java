package com.web.crawler;

import com.web.crawler.crawling.WebCrawler;
import com.web.crawler.extract.PageExtractor;
import com.web.crawler.model.Page;
import com.web.crawler.model.PageSnapshot;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PageSnapshotCreatorTestSuite {

    private WebCrawler webCrawler;
    private PageExtractor pageExtractor;
    private PageSnapshotCreator pageSnapshotCreator;

    @Before
    public void setUp() throws Exception {
        webCrawler = mock(WebCrawler.class);
        pageExtractor = mock(PageExtractor.class);
        pageSnapshotCreator = new PageSnapshotCreator(webCrawler, pageExtractor);

    }

    @Test
    public void shouldCreatePageSnapShot() {

        //Given
        List<Page> links = new ArrayList<>();
        links.add(new Page("xyz.com", "xyzBody"));

        when(pageExtractor.extractPage("example.com")).thenReturn(new Page("example.com", "exampleBody"));
        when(webCrawler.crawl(new Page("example.com", "exampleBody"))).thenReturn(links);

        Set<PageSnapshot> pages = new HashSet<>();
        pages.add(new PageSnapshot(new Page("xyz.com", "xyzBody"), new HashSet<>()));

        //When
        PageSnapshot node = pageSnapshotCreator.createPageNode("example.com", 2);

        //Then
        assertEquals("example.com", node.getPage().getAddress());
        assertEquals("exampleBody", node.getPage().getBody());
        assertEquals(pages.size(), node.getLinks().size());
        assertEquals(pages, node.getLinks());
    }
}