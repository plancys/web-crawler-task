import com.web.crawler.PageSnapshotCreator;
import com.web.crawler.crawling.Crawler;
import com.web.crawler.crawling.RegexCrawler;
import com.web.crawler.download.Downloader;
import com.web.crawler.download.PageDownloader;
import com.web.crawler.extract.HttpPageExtractor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DownloadPageApplication {

    public static void main(String[] args) throws IOException {

        PageDownloader pageDownloader = new Downloader();

        //Path tempDirectory = createOutputDirectory();
        String path = "C:\\Users\\Jaras\\Desktop\\Temporary\\temp";
        File tempDirectory = new File(path);

        PageSnapshotCreator pageSnapshotCreator = new PageSnapshotCreator(
                new Crawler(new RegexCrawler(), new HttpPageExtractor()),
                new HttpPageExtractor()
        );

        pageDownloader.downloadPage(
                pageSnapshotCreator.createPageNode("http://example.com/", 3),
                tempDirectory);
    }

    private static Path createOutputDirectory() throws IOException {
        Path tempDirectory = Files.createTempDirectory("savedPage");
        System.out.println("Page will be saved: " + tempDirectory.toFile().getAbsolutePath());
        return tempDirectory;
    }
}
