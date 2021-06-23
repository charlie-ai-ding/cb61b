package com.geccocrawler.gecco.pipeline;

import com.alibaba.fastjson.JSON;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.spider.SpiderBean;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


//@PipelineName("consolePipeline")
public class ConsolePipelineToMysql implements Pipeline<SpiderBean> {

	private List<String> all2500= new ArrayList<>();

	private Connection conn = null;

	public ConsolePipelineToMysql(){
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/english?" +
					"user=root&password=dzg19791110");
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}


	private void insertToMysql(String json){
		PreparedStatement stmt = null;
		ResultSet rs = null;

		//json="hello";
		String content="";
		String SQL_INSERT = "INSERT INTO words_big_table(json)VALUES(?)";
		try {
			stmt = conn.prepareStatement(SQL_INSERT);



			stmt.setString(1,json);
			int row =stmt.executeUpdate();

			System.out.println("insert row count is:"+ row);
			System.out.println("insert row end at" + System.currentTimeMillis());




		}
		catch (SQLException ex){
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {

		}
	}

	@Override
	public void process(SpiderBean bean) {
		String result = JSON.toJSONString(bean);


		this.insertToMysql(result);


		// System.out.println("end at" + System.currentTimeMillis());
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
