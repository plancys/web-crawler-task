package com.web.crawler.normalizer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UrlNormalizerTestSuite {
    private Normalizer normalizer;

    @Before
    public void setUp() throws Exception {
        normalizer = new UrlNormalizer();
    }

    @Test
    public void shouldNormalizeUrl() {

        //Given
        String testUrlOne = "http://www.example.com/";
        String testUrlTwo = "http://example.com/";
        String testUrlThree = "example.com/";
        String testUrlFour = "example.com/";
        String testUrlFive = "https://www.iana.org/domains/reserved/";
        String testUrlSix = "http://iana.org/_js_xyz.js";

        String expectedCaseOne = "https://www.example.com";
        String expectedCaseTwo = "https://www.iana.org/domains/reserved";
        String expectedCaseThree = "https://www.iana.org/_js_xyz.js";

        //When
        String validatedUrlOne = normalizer.normalize(testUrlOne);
        String validatedUrlTwo = normalizer.normalize(testUrlTwo);
        String validatedUrlThree = normalizer.normalize(testUrlThree);
        String validatedUrlFour = normalizer.normalize(testUrlFour);

        String validatedUrlFive = normalizer.normalize(testUrlFive);
        String validatedUrlSix = normalizer.normalize(testUrlSix);

        //Then
        assertEquals(expectedCaseOne, validatedUrlOne);
        assertEquals(expectedCaseOne, validatedUrlTwo);
        assertEquals(expectedCaseOne, validatedUrlThree);
        assertEquals(expectedCaseOne, validatedUrlFour);

        assertEquals(expectedCaseTwo, validatedUrlFive);
        assertEquals(expectedCaseThree, validatedUrlSix);
    }
}