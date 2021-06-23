package com.geccocrawler.gecco.pipeline;

public class Test {

    private static String changeString(String input){

        String result=	input.replace("\"", "\\\"");
        result =result.replace("\'","\\'");
        return result;
    }

    public static void main(String[] args) {
        String ss= "{\"nihao\",and what\'s your name?}";

        String ts="{\"key1\": \"value\", \"key2\": 2019, \"time\": \"2015-07-29 12:18:29.000000\"}";

        System.out.println(changeString(ss));
    }
}
