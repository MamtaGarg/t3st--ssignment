package org.mamta.garg.assignment;

import org.junit.Test;
import org.mamta.garg.assignment.service.GoogleSearchResultsService;
import org.mamta.garg.assignment.service.LibCountServiceImpl;
import org.mamta.garg.assignment.service.WebCrawlerManager;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static junit.framework.TestCase.assertTrue;

/**
 * The Class WebCrawlerIntegrationTest.
 */
public class WebCrawlerIntegrationTest {

    private final WebCrawlerManager manager = new WebCrawlerManager(new LibCountServiceImpl(), new GoogleSearchResultsService());

    @Test
    public void getJsLibsCount() throws InterruptedException, ExecutionException, IOException {
        String searchQuery = "facebook";
        assertTrue(manager.getJsLibsCount(searchQuery).size() > 0);
    }
}
