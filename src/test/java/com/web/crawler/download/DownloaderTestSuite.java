package com.web.crawler.download;

import com.web.crawler.model.Page;
import com.web.crawler.model.PageSnapshot;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class DownloaderTestSuite {
    private PageDownloader downloader;

    @Before
    public void setUp() throws Exception {
        downloader = new Downloader();
    }

    @Test
    public void shouldDownloadPage() throws IOException {

        //Given
        Page page = new Page("www.example.com", "example");
        Collection<PageSnapshot> links = new HashSet<>();
        links.add(new PageSnapshot(new Page("contact", "contactBody"), new HashSet<>()));

        PageSnapshot pageSnapshot = new PageSnapshot(page, links);

        File siteDirectory = Files.createTempDirectory("siteDirectory").toFile();
        siteDirectory.deleteOnExit();

        //When
        downloader.downloadPage(pageSnapshot, siteDirectory);

        //Then
        List<String> indexContent = Files.readAllLines(new File(siteDirectory, "wwwexamplecom.html").toPath());
        List<String> contactContent = Files.readAllLines(new File(siteDirectory, "contact.html").toPath());
        assertEquals(indexContent, Arrays.asList("example"));
        assertEquals(contactContent, Arrays.asList("contactBody"));

    }
}
