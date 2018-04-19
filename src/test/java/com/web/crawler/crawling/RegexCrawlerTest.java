package com.web.crawler.crawling;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegexCrawlerTest {

    @Test
    public void shouldExtractLinks() {

        //Given
        RegexCrawler regexCrawler = new RegexCrawler();
        String url = "http://example.com/";
        String websiteSource = "<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Example Domain</title>\n" +
                "\n" +
                "    <meta charset=\"utf-8\" />\n" +
                "    <meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n" +
                "    <style type=\"text/css\">\n" +
                "    body {\n" +
                "        background-color: #f0f0f2;\n" +
                "        margin: 0;\n" +
                "        padding: 0;\n" +
                "        font-family: \"Open Sans\", \"Helvetica Neue\", Helvetica, Arial, sans-serif;\n" +
                "        \n" +
                "    }\n" +
                "    div {\n" +
                "        width: 600px;\n" +
                "        margin: 5em auto;\n" +
                "        padding: 50px;\n" +
                "        background-color: #fff;\n" +
                "        border-radius: 1em;\n" +
                "    }\n" +
                "    a:link, a:visited {\n" +
                "        color: #38488f;\n" +
                "        text-decoration: none;\n" +
                "    }\n" +
                "    @media (max-width: 700px) {\n" +
                "        body {\n" +
                "            background-color: #fff;\n" +
                "        }\n" +
                "        div {\n" +
                "            width: auto;\n" +
                "            margin: 0 auto;\n" +
                "            border-radius: 0;\n" +
                "            padding: 1em;\n" +
                "        }\n" +
                "    }\n" +
                "    </style>    \n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<div>\n" +
                "    <h1>Example Domain</h1>\n" +
                "    <p>This domain is established to be used for illustrative examples in documents. You may use this\n" +
                "    domain in examples without prior coordination or asking for permission.</p>\n" +
                "    <p><a href=\"http://www.iana.org/domains/example\">More information...</a></p>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>\n";

        //When
        List<String> result = regexCrawler.find(url, websiteSource);

        //Then
        Assert.assertEquals(Arrays.asList("http://www.iana.org/domains/example"), result);

    }

    @Test
    public void shouldBuildFullLinks() {

        //Given
        RegexCrawler regexCrawler = new RegexCrawler();
        String url = "http://example.com/domains/reserved";
        String givenBody = "<link rel=\"stylesheet\" media=\"screen\" href=\"/_css/2015.1/screen.css\"/>" +
                "\n<script type=\"text/javascript\" src=\"/_js/2013.1/jquery.js\"></script>" +
                "\n<div class=\"navigation\">\n" +
                "\t\t\t\t<ul>\n" +
                "\t\t\t\t\t<li><a href=\"/domains\">Domains</a></li>";

        List<String> expected = new ArrayList<>();
        expected.add("http://example.com/_css/2015.1/screen.css");
        //expected.add("http://example.com/_js/2013.1/jquery.js");  TODO
        expected.add("http://example.com/domains");


        //When
        List<String> result = regexCrawler.find(url, givenBody);
        System.out.println(result);
        //Then
        Assert.assertEquals(expected, result);
    }
}