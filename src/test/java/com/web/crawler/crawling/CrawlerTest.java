package com.web.crawler.crawling;

import com.web.crawler.extract.PageExtractor;
import com.web.crawler.model.Page;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CrawlerTest {

    private RegexCrawler regexCrawler;
    private PageExtractor pageExtractor;
    private Crawler crawler;

    @Before
    public void setUp() throws Exception {
        regexCrawler = Mockito.mock(RegexCrawler.class);
        pageExtractor = Mockito.mock(PageExtractor.class);
        crawler = new Crawler(regexCrawler, pageExtractor);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void crawl() {

        //Given
        Page page = new Page("", "");
        when(regexCrawler.find(page.getAddress(), page.getBody())).thenReturn(Arrays.asList("www.example.com"));
        when(pageExtractor.extractPage("www.example.com")).thenReturn(new Page("expected adress", "expectedBody"));

        Collection<Page> expected = new ArrayList<>();
        expected.add(new Page("expected adress", "expectedBody"));

        //When
        Collection<Page> pages = crawler.crawl(page);

        //Then
        assertEquals(expected, pages);
    }
}
