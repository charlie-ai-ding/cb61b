import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadToJson {

    public static Map<String,Integer> loadDicFromFile(String dicFileName){

        HashMap<String,Integer> hashMap = new HashMap<>();
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("/Users/dzgygmdhx/work/java/github/subtitleTools/src/main/json/"+dicFileName+".txt")) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray msg = (JSONArray) jsonObject.get("words");

            msg.forEach(s->{
                String temp = s.toString();
                temp=  temp.replace("{","")
                        .replace("}","")
                        .replace("\"","");
                String[] temp2= temp.split(":");
                String word = temp2[0].replaceAll("\\W","");
                Integer count = Integer.valueOf(temp2[1]);
                hashMap.put(word,count);
            });

            System.out.println(hashMap);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return hashMap;

    }


public static void view(){
    JSONParser parser = new JSONParser();

    try (Reader reader = new FileReader("/Users/dzgygmdhx/work/java/github/subtitleTools/src/main/json/dic/lyb.txt")) {

        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        System.out.println(jsonObject);

        JSONArray msg = (JSONArray) jsonObject.get("words");

        HashMap<String,Integer> hashMap = new HashMap<>();

        msg.forEach(s->{
            String temp = s.toString();
            temp=  temp.replace("{","")
                    .replace("}","")
                    .replace("\"","");
            String[] temp2= temp.split(":");
            String word = filterForWord(temp2[0]);
            Integer count = Integer.valueOf(temp2[1]);

            hashMap.put(word,count);


        });
        System.out.println(hashMap);

    } catch (IOException e) {
        e.printStackTrace();
    } catch (ParseException e) {
        e.printStackTrace();
    }
}

private static String filterForWord(String s){

        String result="";
    String regex = "[a-zA-Z]+";
    //Creating a pattern object
    Pattern pattern = Pattern.compile(regex);
    //Creating a Matcher object
    Matcher matcher = pattern.matcher(s);

   while(matcher.find()){
       result= matcher.group();
   }
   return result;

}

    public static void main(String[] args) {

//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter sample text: ");
//        String data = sc.nextLine();
//        String regex = "[a-zA-Z]+";
//        //Creating a pattern object
//        Pattern pattern = Pattern.compile(regex);
//        //Creating a Matcher object
//        Matcher matcher = pattern.matcher(data);
//        System.out.println("Words in the given String: ");
//        while(matcher.find()) {
//            System.out.println(matcher.group()+" ");
//        }
        System.out.println(filterForWord("together."));

        //view();
    }

    }


