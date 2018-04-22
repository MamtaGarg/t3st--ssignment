package org.mamta.garg.assignment.service;

import org.junit.Test;
import org.mamta.garg.assignment.model.JsCount;
import org.mamta.garg.assignment.service.GoogleSearchResultsService;
import org.mamta.garg.assignment.service.LibCountService;
import org.mamta.garg.assignment.service.WebCrawlerManager;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;

/**
 * The Class HtmlParserService.
 */
public class WebCrawlerManagerTest {

    private final LibCountService libCountService = mock(LibCountService.class);
    private final GoogleSearchResultsService googleSearchResultsService = mock(GoogleSearchResultsService.class);
    private final WebCrawlerManager webCrawlerManager = new WebCrawlerManager(libCountService, googleSearchResultsService);

    @Test
    public void getJsLibsCount() throws InterruptedException, ExecutionException, IOException {

        List<JsCount> testList = Collections.singletonList(new JsCount(1,"Jquery"));
        given(libCountService.getLibsCount(anyString())).willReturn(testList);
        given(googleSearchResultsService.getGoogleSearchJson(anyString())).willReturn("Some response Json");
        assertEquals(testList, webCrawlerManager.getJsLibsCount("Some search keyword"));
    }
}
