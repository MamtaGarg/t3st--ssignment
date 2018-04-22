package org.mamta.garg.assignment.task;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * The Class JsIdentifierTask.
 */
public class IdentifyJsLibTask implements Callable<List<String>> {

	/**
	 * The item.
	 */
	private JsonNode item;

	/**
	 * Instantiates a new javascript naming task.
	 *
	 * @param item
	 */
	public IdentifyJsLibTask(JsonNode item) {
		this.item = item;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public List<String> call() throws IOException {
		List<String> list = Collections.EMPTY_LIST;
		try {
			Connection.Response res = Jsoup.connect(item.get("link").asText()).timeout(100000).execute();
			if (res.statusCode() == 200) {
				Document doc = res.parse();
				Elements scriptTags = doc.select("script");
				list = scriptTags.stream()
						.filter(scriptTag -> scriptTag.hasAttr("src"))
						.map(scriptTag -> scriptTag.attr("src"))
						.filter(scriptName -> !StringUtil.isBlank(scriptName))
						.collect(toList());
			}
		} catch (ConnectException | HttpStatusException e) {
			System.out.println(e.getMessage() + " for " + item.get("link").asText());
		}
		return list;
	}
}
