package com.geccocrawler.gecco.demo.dic.tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PreWords {

    private static boolean beginNumber(String s) {
        String pattern = "\\d+";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(s);
        return m.matches();
    }

    /**
     * about prep S1, W1
     * about adv S1, W1
     * above adv, prep S2, W1
     * above adj W3
     * abroad adv S2, W3
     */

    public static List<String> getLongMan3000(){
        List<String> allLines = null;
        List<String> words = new ArrayList<>();
        try {
            allLines = Files.readAllLines(Paths.get("/Users/dzgygmdhx/work/java/github/Spider_user/src/test/java/com/geccocrawler/gecco/demo/dic/tools/lm3000.txt"));
            Stream<String> lines = allLines.stream().filter(line -> !"".equals(line)).filter(line -> !beginNumber(line.substring(0, 1)));
            words = lines.map(x -> x.split(" ")[0]).collect(Collectors.toList());


        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    public static List<String> get1368(){
        List<String> allLines = null;
        List<String> words = new ArrayList<>();
        try {
            allLines = Files.readAllLines(Paths.get("/Users/dzgygmdhx/work/java/github/Spider_user/src/test/java/com/geccocrawler/gecco/demo/dic/tools/1368.txt"));
            Stream<String> lines = allLines.stream().filter(l->l.length()>0).filter(line -> beginNumber(line.substring(0, 1)));
             words =lines.map(x->x.replaceAll("&"+"nbsp;", " ").replaceAll(String.valueOf((char) 160), " ")).map(x -> x.split(" ")[1]).collect(Collectors.toList());

            System.out.println(words);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    public static void main(String[] args) {
       // getLongMan3000();
         get1368();
      //  System.out.println((529108-519155)/1000);
//        String strLineApp="1. time";
//
//        strLineApp = strLineApp.replaceAll("&"+"nbsp;", " ");
//        strLineApp = strLineApp.replaceAll(String.valueOf((char) 160), " ");
//
//        System.out.println(strLineApp.split(" ")[1]);
    }
}
