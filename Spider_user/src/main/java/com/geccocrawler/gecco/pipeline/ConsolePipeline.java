package com.geccocrawler.gecco.pipeline;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONReader;





import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.spider.SpiderBean;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@PipelineName("consolePipeline")
public class ConsolePipeline implements Pipeline<SpiderBean> {



	public ConsolePipeline(){

	}

	@Override
	public void process(SpiderBean bean)  {


		String result = JSON.toJSONString(bean);



		 String s1 = result.split("subscript")[1];



		String fileName = getFileName(result);

		// fileName ="bigBang";


		  writeToFile_new(s1,fileName);

//		    DHCrawler.all2500.add(s1);
//
//		    if(this.all2500.size()==151){
//				writeToFile_new_2(this.all2500,"Total");
//			}



		// String filename = result.split("档名：")[1].substring(0,10);

		// String filename = result.split(" ")[0].substring(9);

	//	String filename = result.split(" ")[0].substring(10);
		// String filename="hyj_jw_1";

		// writeToFile_3(all2500,filename);

	}



private static String getFileName(String result){
	org.json.simple.parser.JSONParser parser = new JSONParser();

	JSONObject jsonObject = null;
	String url ="";

	try {
		jsonObject = (JSONObject) parser.parse(result);
		String temp = jsonObject.get("request").toString();
		JSONObject jsonObject2 = (JSONObject) parser.parse(temp);
		 url = jsonObject2.get("url").toString();

		int i = url.indexOf("/season");

		url= url.substring(i+1).replace("/","-");

	} catch (ParseException e) {
		e.printStackTrace();
	}

	return url;
}


	private static void writeToFile_new(String s,String filename){

	//	List<String> list = stringToMultiline(s);

		List<String> list = new ArrayList<>();
		list.add(s);

		Path p = Paths.get("/Users/dzgygmdhx/work/java/github/Spider_user/src/main/java/dh/"+filename+".txt");

		System.out.println(p.getFileName());

		// list.add(s);

		try {
			Files.write(p,
					list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private static void writeToFile_new_2(List<String> list,String filename){

		//	List<String> list = stringToMultiline(s);



		Path p = Paths.get("/Users/dzgygmdhx/work/java/github/Spider_user/src/main/java/dh/"+filename+".txt");

		try {
			Files.write(p,
					list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static List<String>  stringToMultiline(String s){
		List<String> result = new ArrayList<>();

		String[] source = s.split(" ");
		String tmp ="";

		for (int i=0; i<source.length;i++){

			if( i % 12 == 0){
				tmp= tmp+" "+source[i]+"\n";
				result.add(tmp);
				tmp="";
			}else{
				tmp = tmp +" "+source[i];
			}
		}

		return result;
	}

	private static void writeToFile(List<String> list,String filename){
		System.out.println(list.size());
		try {
			Files.write(Paths.get("/Users/dzgygmdhx/work/java/spider/gecco/src/main/java/hyj/"+filename+".txt"),
							list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void writeToFile_2(List<String> list,String filename){
		System.out.println(list.size());
		String url="/Users/dzgygmdhx/work/java/github/Spider_user/src/main/java/dnpj/";
		try {
			Files.write(Paths.get(url+filename+".txt"),
					list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void writeToFile_3(List<String> list,String filename){
		System.out.println(list.size());
		String url="/Users/dzgygmdhx/work/java/github/Spider_user/src/main/java/hyjqs/";
		try {
			Files.write(Paths.get(url+filename+".txt"),
					list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
