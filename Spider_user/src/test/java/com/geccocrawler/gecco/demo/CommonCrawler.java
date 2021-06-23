package com.geccocrawler.gecco.demo;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.ArrayList;
import java.util.List;

//@Gecco(pipelines="consolePipeline")

public class CommonCrawler implements HtmlBean {

	private static final long serialVersionUID = -8870768223740844229L;

	@Request
	private HttpRequest request;
	
	@Text(own=false)
	@HtmlField(cssPath="span.ColloBox")
	private String body;

	@Text(own=false)
	@HtmlField(cssPath="span.Collocate span.collo COLLOC")
	private String collocation;


	@Text(own=false)
	@HtmlField(cssPath="span.Collocate span.EXAMPLE")
	private String collocation_example;

	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getCollocation() {
		return collocation;
	}

	public void setCollocation(String collocation) {
		this.collocation = collocation;
	}

	public String getCollocation_example() {
		return collocation_example;
	}

	public void setCollocation_example(String collocation_example) {
		this.collocation_example = collocation_example;
	}






	private static String fourBitStr_1(String s){
		String result ="";
		if(s.length()==1){
			result="000"+s;
		}else if(s.length()==2){
			result= "00"+s;
		}else if(s.length()==3){
			result="0"+s;
		}else {
			result =s;
		}
		String url ="http://www.fosss.org/sutra/Qianshi/hyjJingKong/Pages/"+result+".html";
		return url;
	}
	private static String fourBitStr(String s){
		String result ="";

//		if(s.length()==1){
//			result="0"+s;
//		}else {
//			result =s;
//		}
		result="0A";
		//http://www.fosss.org/Sutra/HuaYanJing/hyj41.html
		//http://www.fosss.org/Sutra/NiePanJing/dbnpj00.html
		//String url ="http://www.fosss.org/Sutra/HuaYanJing/hyj"+result+".html";
		// String url ="http://www.fosss.org/Sutra/NiePanJing/dbnpj"+result+".html";

		String url ="http://www.fosss.org/Sutra/Qianshi/hyjQianShi/hyjqs_"+result+".html";

		return url;
	}

	private static List<String> getUrls() {
		List<String> urls = new ArrayList<>();
		for(int i=1;i<2122;i++){
			urls.add(fourBitStr(String.valueOf(i)));
		}
		// urls.forEach(System.out::println);
		return urls;

	}

	private static List<String> getUrls_2() {
		List<String> urls = new ArrayList<>();
		for(int i=23;i<24;i++){
			urls.add(fourBitStr(String.valueOf(i)));
		}
		// urls.forEach(System.out::println);
		return urls;

	}

	private static List<String> getUrls_3() {
		List<String> urls = new ArrayList<>();
//		for(int i=23;i<24;i++){
//			urls.add(fourBitStr(String.valueOf(i)));
//		}
		// urls.forEach(System.out::println);
		urls.add("https://www.ldoceonline.com/dictionary/factor");
		return urls;

	}
	
	public static void main(String[] args) {
			List<String> urls = getUrls_3();

		for(int i=0;i<urls.size();i++){
			GeccoEngine.create()
					.classpath("com.geccocrawler.gecco.demo")
					.start(urls.get(i))
					.interval(1000)
					.start();

			try {
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}



	}
}
