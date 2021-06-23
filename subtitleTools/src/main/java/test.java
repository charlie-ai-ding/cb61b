import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

    private static String filterForWord(String s){

        String result="(English (auto-generated))What makes a good life_ Lessons from the longest study on happiness _ Robert Waldinger_video_id_8KkKuTCFvzI";
       // String regex = "[a-zA-Z]+";
        String regex= "\\(.*\\)";
        //Creating a pattern object
        Pattern pattern = Pattern.compile(regex);
        //Creating a Matcher object
        Matcher matcher = pattern.matcher(s);

        while(matcher.find()){
            result= matcher.group();
        }

        return result.replaceAll(regex,"");

    }
    private static String filterForWord2(String s){

        String result="";
        String regex = "[a-zA-Z]+";
        //Creating a pattern object
        Pattern pattern = Pattern.compile(regex);
        //Creating a Matcher object
        Matcher matcher = pattern.matcher(s);

        while(matcher.find()){
            result += matcher.group();
        }
        return result;

    }

    public static void main(String[] args) {

        String test ="?a,b'ou-t...";
        System.out.println(filterForWord2(test));

        //{"the":16267}

        String test2 ="{\"the\":16267}";
       String t= test2.replace("\"","").replace("{","").replace("}","").split(":")[0];
        System.out.println(t);
    }
}
