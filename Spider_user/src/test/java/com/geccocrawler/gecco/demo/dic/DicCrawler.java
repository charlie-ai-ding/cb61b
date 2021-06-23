package com.geccocrawler.gecco.demo.dic;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.annotation.Text;

import com.geccocrawler.gecco.demo.dic.DicLink.IdoecEntry;
import com.geccocrawler.gecco.demo.dic.Thes.ThesBox;
import com.geccocrawler.gecco.demo.dic.tools.PreWords;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// @Gecco(pipelines="consolePipeline")
public class DicCrawler implements HtmlBean {

	private static final long serialVersionUID = -8870668223740844338L;

	@Request
	private HttpRequest request;

	@Text
	@HtmlField(cssPath = "span.ldoceEntry.Entry")
	private IdoecEntry idoecEntry;


	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}


	public IdoecEntry getIdoecEntry() {
		return idoecEntry;
	}

	public void setIdoecEntry(IdoecEntry idoecEntry) {
		this.idoecEntry = idoecEntry;
	}

	private static List<String> getUrls_longman_3000() {
		List langman = PreWords.get1368();
		List<String> urls = new ArrayList<>();
		String url = "https://www.ldoceonline.com/dictionary/";
		for (int i = 0; i < langman.size(); i++) {
			urls.add(url + langman.get(i).toString());
		}

		return urls;

	}

	private static List<String> test_threads() {
		List<String> prebs = Arrays.asList("on","off","at","to","of","out","get","have","take","make","be");
		List<String> urls = new ArrayList<>();
		String url = "https://www.ldoceonline.com/dictionary/";
		for (int i = 0; i < prebs.size(); i++) {
			urls.add(url + prebs.get(i).toString());
		}

		return urls;

	}

	public static void main(String[] args) {
		List<String> urls =  test_threads();
		//urls.add("https://www.ldoceonline.com/dictionary/have");
		System.out.println("for begin at" + System.currentTimeMillis());
		for (int i = 0; i < urls.size(); i++) {
			// System.out.println("begin at" + System.currentTimeMillis());
			GeccoEngine.create()
					.classpath("com.geccocrawler.gecco.demo.dic")
					.start(urls.get(i))
					.interval(50)
					.start();

//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}


		}
		System.out.println("for end at" + System.currentTimeMillis());
	}

}
