package com.geccocrawler.gecco.demo.des.script;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public  class MyUnit {

    public static List<String>  stringToMultiline(String s){
        List<String> result = new ArrayList<>();

        String[] source = s.split(" ");
        for (int i=0; i<source.length;i++){
            if( i%12 ==0){
                result.add(source[i]+"\n");
            }else{
                result.add(source[i]);
            }
        }

        return result;
    }

    public static void main(String[] args) {
         int a =  36 ;
        System.out.println(a % 12);

      //  loadDicFromFile();

        String  s ="https://subslikescript.com/series/Desperate_Housewives-410975/season-1/episode-1-Pilot";

        int i = s.indexOf("/season");
        System.out.println(s.substring(i+1).replace("/","-"));
    }

    public static List<String>  loadDicFromFile(String urls ){

       List<String> result = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("/Users/dzgygmdhx/work/java/github/Spider_user/src/main/java/dh/"+urls+".txt")) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray msg = (JSONArray) jsonObject.get("urls");


            for(int i=0;i< msg.size();i++){
               String s = msg.get(i).toString();

               // System.out.println(s);

                JSONObject jsonObject1 =(JSONObject)  parser.parse(s);
                JSONArray msg2 = (JSONArray) jsonObject1.get("urls");

                 for (int j=0;j<msg2.size();j++){
                     String ss = msg2.get(j).toString();
                  //   System.out.println(ss);
                     result.add(ss);
                 }
            }



        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;

    }
}
