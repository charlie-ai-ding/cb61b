
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class ReadSub {


    private static boolean beginNumber(String s) {
        String pattern = "\\d+";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(s);
        return m.matches();
    }

    private static void writeToFile_new(String s,String filename){

        List<String> list = new ArrayList<>();
        list.add(s);

        Path p = Paths.get(filename);

        try {
            Files.write(p,
                    list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void jsonToTxt(String dicFileName, String outPath){
        JSONParser parser = new JSONParser();
         String  txtPath ="/Users/dzgygmdhx/work/java/github/subtitleTools/src/main/json/"+outPath+".txt";
        try {

            Reader  reader = new FileReader("/Users/dzgygmdhx/work/java/github/subtitleTools/src/main/json/"+dicFileName+".txt");
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray msg = (JSONArray) jsonObject.get("words");

            StringBuffer  sb =new StringBuffer();

            String temp="";
            int count = msg.size();
            for(int i=count-1; i>=0; i --){
                if(i%10==0){
                    temp = temp+" , "+getPlain( msg.get(i).toString()) +"\n";
                    sb.append(temp);
                    temp=i+ " ";
                }else{
                    temp= temp+ " , "+ getPlain( msg.get(i).toString());
                }
            }

            writeToFile_new(sb.toString(),txtPath);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static String getPlain(String s){

        String t= s.replace("\"","").replace("{","").replace("}","").split(":")[0];
        return t;
    }


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
                String word = filterForWord(temp2[0]);
                String countSting = filterForWord(temp2[1]);
                Integer count =0;
                if(!countSting.equals("")){
                    try {
                        count  = Integer.valueOf(countSting);
                    }catch(Exception e){

                    }

                }

                hashMap.put(word,count);
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return hashMap;

    }


    public static void mapToJson(Map<String,Integer> sortMap,String fileName){
        JSONObject obj = new JSONObject();
        JSONArray words = new JSONArray();

        sortMap =  sortMap.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)->e1-e2,LinkedHashMap::new));


        sortMap.entrySet().stream().forEach(e->{
            JSONObject temp = new JSONObject();
            temp.put(e.getKey(),e.getValue());
            words.add(temp);
        } );
        obj.put("count",sortMap.size());
        obj.put("words",words);
        FileWriter  file =null;
        FileWriter   fw = null;

        /**
         * there are risk , you may encounter this situation: write new file failure, but the old file was rewrite by empty content ,then you will get nothing.
         * So, you should make sure  successfully write the content from memory to file,and then you remove the old file and just left the new file ,other wise ,throw a exception.
         * Feb 15 2021
         *
         */
        try {

            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("/Users/dzgygmdhx/work/java/github/subtitleTools/src/main/json/"+fileName+".txt");
            file.write(obj.toJSONString());

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            fw = new FileWriter("/Users/dzgygmdhx/work/java/github/subtitleTools/src/main/json/log.txt");
            fw.write("["+ wordsCollection +"]"+ "the collection ended at: " + (new Date()).toString()+"words :"+ myWords.size());


        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                file.flush();
                file.close();
                fw.flush();
                fw.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void doShutDownWork() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {

                    mapToJson(myWords,wordsCollection);
                } catch (Exception ex) {
                }
            }
        });

    }

    private static  Map<String,Integer> myWords= new HashMap<>();
    private static String wordsCollection="en";// lyb // mind

    public ReadSub(){
        myWords = loadDicFromFile(wordsCollection);
        System.out.println("load finished..." + myWords.size());
        doShutDownWork();

    }

    private static int quzheng(int count, int length) {
        return (int) Math.ceil(Float.valueOf(count) / Float.valueOf(length));
    }

    private static String filterForWord(String s){

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

    private static  List<String>  countWords(List<String> words){

      //  words.forEach(w-> filterForWord(w));

        words.stream().forEach(w->{
            if(myWords.containsKey(w)){
                Integer value = myWords.get(w);
                myWords.remove(w);
                myWords.put(w,value.intValue()+1);
            }else{
                myWords.put(w,1);
            }
        });

        List<String> result = new ArrayList<>();
        LinkedHashMap<String,Integer> sortMap =  myWords.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)-> e2-e1,LinkedHashMap::new));

        sortMap.entrySet().stream().forEach(e-> result.add(e.getKey()+"  "));

       // SubtitleTools.mapToJson(sortMap,"lyb_1");

        return result;


    }
    private static List<String> wordToLine_count(List<String> words, int count, int length) {



        int lines = quzheng(count, length);
        List<String> newlines = new ArrayList<>();

        for (int i = 0; i < lines; i++) {
            String temp = "";
            for (int j = 0; j < length; j++) {
                if (i * length + j < count) {
                    temp += " " + words.get(i * length + j);
                } else {
                    break;
                }

            }
            if(i==0){
                temp ="Count: " + count+"\n"+ temp + "\n";
            }else{
                temp = temp+"\n";
            }

            newlines.add(temp);
        }
        return newlines;
    }


    private static List<String> wordToLine(List<String> words, int count, int length) {

        //  System.out.println(countWords(words));

        int lines = quzheng(count, length);
        List<String> newlines = new ArrayList<>();

        for (int i = 0; i < lines; i++) {
            String temp = "";
            for (int j = 0; j < length; j++) {
                if (i * length + j < count) {
                    temp += " " + words.get(i * length + j);
                } else {
                    break;
                }

            }
            if(i==0){
                temp ="Count: " + count+"\n"+ temp + "\n";
            }else{
                // temp = temp+"\n";
                temp = temp.toLowerCase();

            }

            newlines.add(temp);
        }
        return newlines;
    }
    //"(\\()(.*?)(\\))", "")

    private static void srtToTxt_batch(String target, int length) {
        File f = new File("/Users/dzgygmdhx/Downloads");
        Arrays.stream(f.listFiles())
                .filter(file -> file.isFile() && file.getPath().endsWith(".srt"))
                .forEach(ff -> {
                    String strPath = ff.getPath();
                    String txtPath = target + strPath.split("/")[4].split("-")[0] + ".txt";
                    txtPath = txtPath.substring(txtPath.indexOf(" "));
                    // txtPath= txtPath.replaceAll("(\\()(.*?)(\\))","");

                    String txt_countPath =target+"count/words_count.txt";
                    srtToTxt(strPath, txtPath,txt_countPath, length);
                });

    }

    private static void srtToTxtOnNewFile(String filename,String target) {

        String strPath = filename;

        String txtPath = target + strPath.split("/")[4].replace(".srt",".txt");
       
        String txt_countPath =target+"count/words_count.txt";
        srtToTxt(strPath, txtPath, txt_countPath,20);

    }

    private static void srtToTxt(String srtPath, String txtPath,String txt_countPath, int length) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(srtPath));
            Stream<String> lines = allLines.stream().filter(line -> !"".equals(line)).filter(line -> !beginNumber(line.substring(0, 1)));

            List<String> words = lines.flatMap(l -> Arrays.asList(l.split(" ")).stream()).collect(Collectors.toList());

            Files.write(Paths.get(txtPath), wordToLine(words, words.size(), length));

            List<String> words_count = countWords(words);
            System.out.println("count is:"+ words_count.size());

           // Files.write(Paths.get(txt_countPath),wordToLine_count(words_count,words_count.size(),10));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void TxtToCount (String strPath) {
        try {
            List<File> files = Files.list(Paths.get(strPath))
                    .map(Path::toFile)
                    .collect(Collectors.toList());

            files.forEach(file -> {
                List<String> allLines = null;
                try {
                    allLines = Files.readAllLines(Paths.get(file.getPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stream<String> lines = allLines.stream();

                List<String> words = lines.flatMap(l -> Arrays.asList(l.split(" ")).stream().map(x->filterForWord(x))).collect(Collectors.toList());
                List<String> words_count = countWords(words);
               // System.out.println("count is:"+ words_count.size());

            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void listenAndAuto(String target){
        final String dir ="/Users/dzgygmdhx/Downloads/";
        final Path path = Paths.get(dir);

        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            //给path路径加上文件观察服务
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE);

            while (true) {
                final WatchKey key = watchService.take();

                for (WatchEvent<?> watchEvent : key.pollEvents()) {

                    final WatchEvent.Kind<?> kind = watchEvent.kind();

                    if (kind == StandardWatchEventKinds.OVERFLOW) {
                        continue;
                    }
                    //创建事件
                    if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                        System.out.println("[新建]");
                        Thread.sleep(500);

                        final WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
                        final Path filename = watchEventPath.context();
                        System.out.println(kind + " -> " + filename);
                        srtToTxtOnNewFile(dir+ filename.toString(),target);
                    }
                    //修改事件
                    if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                        System.out.println("修改]");
                    }
                    //删除事件
                    if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                        System.out.println("[删除]");
                    }
                    // get the filename for the event
                    final WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
                    final Path filename = watchEventPath.context();
                    // print it out
                    //  System.out.println(kind + " -> " + filename);

                }
                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }

        } catch (IOException | InterruptedException ex) {
            System.err.println(ex);
        }

    }


    public static void main(String[] args) {
        String target="/Users/dzgygmdhx/Desktop/srtToTxt/";
        String targetState ="/Users/dzgygmdhx/Desktop/srtToTxt/count/";

          ReadSub  rs= new ReadSub();

        while(true){
            listenAndAuto(target);
        }

        //String path="/Users/dzgygmdhx/work/java/github/Spider_user/src/main/java/dh/bigBand";
       //  TxtToCount(path);

      //  jsonToTxt("bigBang","big_dic");
    }

}

