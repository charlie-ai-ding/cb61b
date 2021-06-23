package com.geccocrawler.gecco.demo.mysql;

import com.alibaba.fastjson.JSON;
import com.geccocrawler.gecco.demo.dic.DicCrawler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


// Notice, do not import com.mysql.cj.jdbc.*
// or you will have problems!

class DataBean{
    private String sql="insert t1(jdoc) values ";

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public DataBean(String doc){
        this.sql =this.sql + "('"+doc+"')";
    }

}
public class Connect {


    public static void connect(){

        String ts = testJSON();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/english?" +
                            "user=root&password=dzg19791110");
            //smt(conn,ts);

            smt(conn);

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public static void smt( Connection conn ){
        Statement stmt = null;
        ResultSet rs = null;

        String content=null;

        try {
            stmt = conn.createStatement();

            if(stmt.execute("SELECT * FROM word_json_table where id=1")){
               rs= stmt.getResultSet();
            }

            if(rs.next()){
                 content=  rs.getObject("json",String.class);
               DicCrawler dicCrawler= JSON.toJavaObject(JSON.parseObject(content),DicCrawler.class);
                System.out.println(dicCrawler);
            }



        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
    }

    public static String testJSON(){
            DataBean dataBean= new DataBean("This's a new example at Mar 2");
            String jsonString=JSON.toJSONString(dataBean); // {"sql":"insert t1(jdoc) values ('This's a new example at Mar 2')"}
            String sqlStype =jsonString.replace("\"","\\\"").replace("\'","\\\'");

       return sqlStype;


    }

    public static void main(String[] args)
    {
          connect();

          //testJSON();
    }
}