package org.mamta.garg.assignment;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.mamta.garg.assignment.model.JsCount;
import org.mamta.garg.assignment.service.GoogleSearchResultsService;
import org.mamta.garg.assignment.service.LibCountServiceImpl;
import org.mamta.garg.assignment.service.WebCrawlerManager;

/**
 * The Class WebCrawler.
 */
public class WebCrawler {

	/** Parses the google search results JSON */
	private static final WebCrawlerManager manager = new WebCrawlerManager(new LibCountServiceImpl(), new GoogleSearchResultsService());

	public static void main(String... args) throws IOException, ExecutionException, InterruptedException {
		if(args.length> 0){
			displayTheWinners(args[0]);
		} else {
			throw new IllegalArgumentException("Required search query missing. Please provide a keyword to search for in Google.");
		}
	}

	public static void displayTheWinners(String searchKeyword) throws IOException, ExecutionException, InterruptedException {

		List<JsCount> finalScriptList = manager.getJsLibsCount(searchKeyword);

		System.out.println("**************************************************************************************");
		System.out.println("");
		System.out.println("Here is the order of javascript libraries being used :-");
		System.out.println("");

		for (int i = 0; i < finalScriptList.size(); i++) {
			System.out.println(
				String.format(
					"Number %s: With %s occurrences { %s }.",
					i + 1,
					finalScriptList.get(i).getCount(),
					finalScriptList.get(i).getJsLib()
				)
			);
		}

		System.out.println("");
		System.out.println("**************************************************************************************");
	}

}
