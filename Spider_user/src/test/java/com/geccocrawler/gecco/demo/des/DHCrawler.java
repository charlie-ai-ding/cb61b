package com.geccocrawler.gecco.demo.des;



import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.annotation.Text;

import com.geccocrawler.gecco.demo.des.script.MyUnit;
import com.geccocrawler.gecco.demo.des.script.Subscript;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.ArrayList;
import java.util.List;

@Gecco(pipelines="consolePipeline")
public class DHCrawler implements HtmlBean {

    public static List<String> all2500= new ArrayList<>();

    private static final long serialVersionUID = -8870668223740844338L;

    @Request
    private HttpRequest request;

    @Text
    @HtmlField(cssPath = "div.full-script")
    private String subscript;

    //手机
   //  @HtmlField(cssPath=".category-items > div:nth-child(1) > div:nth-child(2) > div.mc > div.items > dl")

//    @Text
//    @HtmlField(cssPath = "div.season")
    private List<Subscript> urls;


    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }


    public String getSubscript() {
        return subscript;
    }

    public void setSubscript(String subscript) {
        this.subscript = subscript;
    }

//    public List<Subscript> getUrls() {
//        return urls;
//    }
//
//    public void setUrls(List<Subscript> urls) {
//        this.urls = urls;
//    }

    private static List<String> test_sub_1() {

        List<String> urls = new ArrayList<>();
        String url = "https://subslikescript.com/series/The_Big_Bang_Theory-898266";
          urls.add(url);

        return urls;

    }

    private static List<String> test_sub_2() {

        List<String> urls = MyUnit.loadDicFromFile("bigBang");


        return urls;

    }

    public static void main(String[] args) {

        List<String> urls =  test_sub_2();
        //urls.add("https://www.ldoceonline.com/dictionary/have");
        System.out.println("1 for begin at" + System.currentTimeMillis());
        for (int i = 0; i < urls.size(); i++) {
            // System.out.println("begin at" + System.currentTimeMillis());
            GeccoEngine.create()
                    .classpath("com.geccocrawler.gecco.demo.des")
                    .start(urls.get(i))
                    .interval(50)
                    .start();

//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}


        }
        System.out.println("2 for end at" + System.currentTimeMillis());
    }

}

