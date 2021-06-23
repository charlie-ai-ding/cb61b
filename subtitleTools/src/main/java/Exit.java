
/*****************************************************************************
 * 本程序仅演示，如何在Java应用程序中添加系统退出事件处理机制
 *****************************************************************************/
import java.util.*;
import java.io.*;
/**
 * This application is used to demo how to hook the event of an application
 */

public class Exit {

    public Exit() {
        doShutDownWork();
    }

    /***************************************************************************
     * This is the right work that will do before the system shutdown
     * 这里为了演示，为应用程序的退出增加了一个事件处理，
     * 当应用程序退出时候，将程序退出的日期写入 d:\t.log文件
     **************************************************************************/

    private void doShutDownWork() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    FileWriter fw = new FileWriter("/Users/dzgygmdhx/work/java/github/subtitleTools/src/main/json/t.log");
                    System.out.println("Im going to end");
                    fw.write("the application ended! " + (new Date()).toString());
                    fw.close();
                } catch (IOException ex) {
                }
            }
        });

    }

    /****************************************************
     * 这是程序的入口，仅为演示，方法中的代码无关紧要
     ***************************************************/

    public static void main(String[] args) {

        Exit untitled11 = new Exit();
        long s = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long se = System.currentTimeMillis();
        System.out.println(se - s);
    }
}
