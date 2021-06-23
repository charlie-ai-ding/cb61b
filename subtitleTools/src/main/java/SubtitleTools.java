
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Crunchify.com
 * How to write JSON object to File in Java?
 */

public class SubtitleTools {
    private static FileWriter file;


    public static void mapToJson(Map<String,Integer> sortMap,String fileName){
        JSONObject obj = new JSONObject();
        JSONArray words = new JSONArray();

        sortMap.entrySet().stream().forEach(e->{
            JSONObject temp = new JSONObject();
            temp.put(e.getKey(),e.getValue());
            words.add(temp);
        } );

        obj.put("words",words);
        try {

            // Constructs a FileWriter given a file name, using the platform's default charset
            FileWriter  file = new FileWriter("/Users/dzgygmdhx/work/java/github/subtitleTools/src/main/json/"+fileName+".txt");
            file.write(obj.toJSONString());


            CrunchifyLog("Successfully Copied JSON Object to File...");
            CrunchifyLog("\nJSON Object: " + obj);

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        // JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
        JSONObject obj = new JSONObject();
        obj.put("Name", "Crunchify.com");
        obj.put("Author", "App Shah");

        JSONArray company = new JSONArray();
        company.add("Company: Facebook");
        company.add("Company: PayPal");
        company.add("Company: Google");
        obj.put("Company List", company);
        try {

            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("/Users/dzgygmdhx/work/java/github/subtitleTools/src/main/json/crunchify.txt");
            file.write(obj.toJSONString());
            CrunchifyLog("Successfully Copied JSON Object to File...");
            CrunchifyLog("\nJSON Object: " + obj);

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    static public void CrunchifyLog(String str) {
        System.out.println("str");
    }

}