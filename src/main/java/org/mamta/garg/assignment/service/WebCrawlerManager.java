package org.mamta.garg.assignment.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.mamta.garg.assignment.model.JsCount;

/**
 * The Class HtmlParserService.
 */
public class WebCrawlerManager {

	private final LibCountService libCountService;
	private final GoogleSearchResultsService googleSearchResultsService;

	public WebCrawlerManager(LibCountService libCountService, GoogleSearchResultsService googleSearchResultsService) {
		this.libCountService = libCountService;
		this.googleSearchResultsService = googleSearchResultsService;
	}

    /**
	 * Gets the final script list.
	 *
	 * @param searchKeyword
	 * @return the final script list
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public List<JsCount> getJsLibsCount(String searchKeyword) throws IOException, ExecutionException, InterruptedException {
		String googleResponseJson = googleSearchResultsService.getGoogleSearchJson(searchKeyword);
		return libCountService.getLibsCount(googleResponseJson);
	}

}
