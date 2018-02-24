import com.web.crawler.PageSnapshotCreator;
import com.web.crawler.downlaod.PageDownloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DownloadPageApplication {

    public static void main(String[] args) throws IOException {

        PageDownloader pageDownloader = null; //TODO: change null to your implementation

        Path tempDirectory = createOutputDirectory();

        PageSnapshotCreator pageSnapshotCreator = new PageSnapshotCreator(
                null, // WebCrawler implementation instead of null
                null //PageExtractor implementation instead of null
        );

        //this operation should download page
        pageDownloader.downloadPage(
                pageSnapshotCreator.createPageNode("http://example.com/", 1),
                tempDirectory.toFile());
    }

    private static Path createOutputDirectory() throws IOException {
        Path tempDirectory = Files.createTempDirectory("savedPage");
        System.out.println("Page will be saved: " + tempDirectory.toFile().getAbsolutePath());
        return tempDirectory;
    }
}
