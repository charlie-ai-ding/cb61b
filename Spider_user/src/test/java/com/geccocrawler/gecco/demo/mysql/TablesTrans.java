package com.geccocrawler.gecco.demo.mysql;

import com.alibaba.fastjson.JSON;
import com.geccocrawler.gecco.demo.dic.DicCrawler;
import com.geccocrawler.gecco.demo.dic.DicLink.DBHelper;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TablesTrans {

    private Connection conn;
    private PreparedStatement words;
    private PreparedStatement sentences;
    private PreparedStatement sences;
    private PreparedStatement collos;
    private PreparedStatement thess;


    public TablesTrans(){
        try {

            this.conn = DriverManager.getConnection("jdbc:mysql://localhost/english?" + "user=root&password=dzg19791110");

            this.words= this.conn.prepareStatement(
                    "INSERT INTO english.T_words " +
                            "(id,literal, hyphenation, proncode, pos, freq, gram, topic_id, tag_id)" +
                            "VALUES(?,?, ?, ?, ?, ?, ?, 0, 0)");

            this.sentences= this.conn.prepareStatement(
                    "INSERT INTO english.T_sentences " +
                            "(word_id, type_id, sent_key, sent_value,sent_key_exten_1)" +
                            "VALUES(?, ?, ?, ?,?)");

           this.collos= this.conn.prepareStatement(
                    "INSERT INTO english.T_colls" +
                            "(word_id, coll_value)" +
                            "VALUES(?, ?)");

           this.thess= this.conn.prepareStatement(
                    "INSERT INTO english.T_thess" +
                            "(word_id, thes_value)" +
                            "VALUES(?, ?)");

           this.sences= this.conn.prepareStatement(
                    "INSERT INTO english.T_sences" +
                            "(word_id, signpost, def, gram)" +
                            "VALUES(?, ?,?,?)");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }



    private void batchInsertData(DicCrawler dicCrawler) throws SQLException {
        //1 : update all PreparedStatements
        this.words.clearParameters();
        this.sences.clearParameters();
        this.thess.clearParameters();
        this.collos.clearParameters();
        this.sentences.clearParameters();

         DBHelper dbHelper = new DBHelper(dicCrawler.getIdoecEntry());


          dbHelper.makeHeadStmt(this.words);
          dbHelper.makeSenceSStmt(this.sences,this.sentences);
          dbHelper.makeColloStmt(this.collos,this.sentences);
          dbHelper.makeThesStmt(this.thess,this.sentences);


        //2: executed all PreparedStatements;
        try{
            conn.setAutoCommit(false);
                this.words.executeBatch();
                this.sences.executeBatch();
                this.collos.executeBatch();
                this.thess.executeBatch();
                this.sentences.executeBatch();
            conn.commit();
        }catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());

            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return;

        }finally {
            conn.close();
        }

    }

    private void transBigTableIntoTables() throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        String content="";


        try {
            stmt = this.conn.createStatement();

            if(stmt.execute("SELECT * FROM word_json_table ")){
                rs= stmt.getResultSet();
            }

            while(rs.next()){
                content=  rs.getObject("json",String.class);
                DicCrawler dicCrawler= JSON.toJavaObject(JSON.parseObject(content),DicCrawler.class);
                batchInsertData(dicCrawler);
            }

        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {

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

    public static void main(String[] args) {
        TablesTrans tablesTrans = new TablesTrans();

        try {
            tablesTrans.transBigTableIntoTables();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
