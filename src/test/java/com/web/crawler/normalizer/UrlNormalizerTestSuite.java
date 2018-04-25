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
    public void shouldValidateUrl() {

        //Given
        String testUrlOne = "http://www.example.com";
        String testUrlTwo = "http://example.com";
        String testUrlThree = "example.com";
        String testUrlFour = "example.com/";

        String expected = "http://www.example.com";

        //When
        String validatedUrlOne = normalizer.validate(testUrlOne);
        String validatedUrlTwo = normalizer.validate(testUrlTwo);
        String validatedUrlThree = normalizer.validate(testUrlThree);
        String validatedUrlFour = normalizer.validate(testUrlFour);

        //Then
        assertEquals(expected, validatedUrlOne);
        assertEquals(expected, validatedUrlTwo);
        assertEquals(expected, validatedUrlThree);
        assertEquals(expected, validatedUrlFour);
    }
}