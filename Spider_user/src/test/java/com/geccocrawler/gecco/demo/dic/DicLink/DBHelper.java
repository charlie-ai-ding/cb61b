package com.geccocrawler.gecco.demo.dic.DicLink;

import com.geccocrawler.gecco.demo.dic.Collo.ColloBox;
import com.geccocrawler.gecco.demo.dic.Collo.ColloExample;
import com.geccocrawler.gecco.demo.dic.Collo.ColloSection;
import com.geccocrawler.gecco.demo.dic.Collo.CollocateItem;
import com.geccocrawler.gecco.demo.dic.Sense.ColloExa;
import com.geccocrawler.gecco.demo.dic.Sense.DefContent;
import com.geccocrawler.gecco.demo.dic.Sense.GramExa;
import com.geccocrawler.gecco.demo.dic.Sense.GramExample;
import com.geccocrawler.gecco.demo.dic.Thes.ThesBox;
import com.geccocrawler.gecco.demo.dic.Thes.ThesExample;
import com.geccocrawler.gecco.demo.dic.Thes.ThesSection;
import com.geccocrawler.gecco.demo.dic.Thes.ThesSectionItem;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DBHelper {
    private IdoecEntry idoecEntry;
    private int wordid=0;

    public DBHelper(IdoecEntry idoecEntry){
        this.idoecEntry = idoecEntry;
    }

    private  int generateId(String word){
        Random r = new Random();
        int low = 10;
        int high = 10000;
        int random = r.nextInt(high-low) + low;

        int sum =0;
        if(word!=null){
            if(!word.equals("")){
               // word.chars().forEach(System.out::println);
                sum=  word.chars().reduce(0,(x,y)->x+y);
            }
        }else{
            sum=7980;
        }


        Integer id = Integer.valueOf(String.valueOf(sum)+String.valueOf(random));
        this.wordid=id;
        return id;
    }


    /**
     * "INSERT INTO english.T_words " +
     *                         "(id,literal, hyphenation, proncode, pos, freq, gram, topic_id, tag_id)" +
     *                         "VALUES(?,?, ?, ?, ?, ?, ?, 0, 0)");
     * @param
     * @return
     * @throws SQLException
     * in‧for‧ma‧tion
     */

    private  String getWord(String word){
        String rs=word;
        if(word==null){
            rs="";
        }else if(word.contains("‧")) {
                String [] ss= word.split("‧");
                rs = Arrays.stream(ss).reduce("",(x,y)->x+y);
        }

        return rs;


    }


    public void makeHeadStmt(PreparedStatement pstmt_head) throws SQLException {

        String hyphen = this.idoecEntry.getHead().getHyphenAction();
        String word = getWord(hyphen);
        int id= generateId(word);
        String proncode = this.idoecEntry.getHead().getPronCodes().toString();
        String pos= this.idoecEntry.getHead().getPos();
        String freq= this.idoecEntry.getHead().getLevel().stream().reduce("",(x,y)->x+y);
        String gram= this.idoecEntry.getHead().getGram();

        pstmt_head.setInt(1, id);
        pstmt_head.setString(2,word);
        pstmt_head.setString(3, hyphen);
        pstmt_head.setString(4, proncode);
        pstmt_head.setString(5, pos);
        pstmt_head.setString(6, freq);
        pstmt_head.setString(7, gram);
        // Add row to the batch.
        pstmt_head.addBatch();

    }

    /**
     *   "INSERT INTO english.T_sentences " +
     *                       "(word_id, type_id, sent_key, sent_value,sent_key_exten_1)" +
     *                         "VALUES(?, ?, ?, ?,?)");
     *
     *
     * INSERT INTO english.T_sences
     * (word_id, signpost, def, gram)
     * VALUES(0, '', '', '');
     *
     * type_id from 10 means come from sence:  11 sense_Ex 12 gram_Ex 13 collo_Ex  99 : null
     */

    public void makeSenceSStmt(PreparedStatement pstmt_sence,PreparedStatement pstmt_sentence) throws SQLException {

        List<Sense> senseList = this.idoecEntry.getSenseList();
        int word_id= this.wordid;
        int type_id=10;

        if(senseList.size()==0){
            pstmt_sence.setInt(1, word_id);
            pstmt_sence.setString(2, "");
            pstmt_sence.setString(3, "");
            pstmt_sence.setString(4, " ");
            pstmt_sence.addBatch();

        }
        for(int i=0;i<senseList.size();i++){

            DefContent defContent= senseList.get(i).getSense();

            String signpost = defContent.getSignpost();
            String def = defContent.getDef();
            String gram = defContent.getGram();
            List<GramExample> gramExampleList = defContent.getGramExampleList();
            List<GramExa> gramExaList = defContent.getGramExaList();
            List<ColloExa> colloExaList= defContent.getColloExaList();

            pstmt_sence.setInt(1, word_id);
            pstmt_sence.setString(2,signpost);
            pstmt_sence.setString(3, def);
            pstmt_sence.setString(4, gram);

            // Add row to the batch.
            pstmt_sence.addBatch();


            if(gramExampleList.size()>1){
                for(int g=0;g<gramExampleList.size();g++){
                    GramExample gramExample= gramExampleList.get(g);

                    String sent_key= signpost;
                    String sent_value = gramExample.getExample();

                    pstmt_sentence.setInt(1,word_id);
                    pstmt_sentence.setInt(2,type_id+1);
                    pstmt_sentence.setString(3, sent_key);
                    pstmt_sentence.setString(4, sent_value);
                    pstmt_sentence.setString(5, "");
                    pstmt_sentence.addBatch();
                }
            }else if (gramExaList.size()>1){
                for(int gr=0;gr<gramExaList.size();gr++){
                    GramExa gramExa= gramExaList.get(gr);
                    String sent_key= gramExa.getPropform();
                    String sent_value = gramExa.getExample();
                    pstmt_sentence.setInt(1,word_id);
                    pstmt_sentence.setInt(2,type_id+2);
                    pstmt_sentence.setString(3, sent_key);
                    pstmt_sentence.setString(4, sent_value);
                    pstmt_sentence.setString(5, "");
                    pstmt_sentence.addBatch();
                }
            }else if (colloExaList.size()>1){
                for(int c=0;c<colloExaList.size();c++){
                    ColloExa colloExa= colloExaList.get(c);
                    String sent_key= colloExa.getCollo();
                    String sent_value = colloExa.getExample();

                    pstmt_sentence.setInt(1,word_id);
                    pstmt_sentence.setInt(2,type_id+3);
                    pstmt_sentence.setString(3, sent_key);
                    pstmt_sentence.setString(4, sent_value);
                    pstmt_sentence.setString(5, "");
                    pstmt_sentence.addBatch();
                }
            }else{
                pstmt_sentence.setInt(1,word_id);
                pstmt_sentence.setInt(2, 99);
                pstmt_sentence.setString(3, "");
                pstmt_sentence.setString(4, "");
                pstmt_sentence.setString(5, "");
                pstmt_sentence.addBatch();
            }



        }

    }


    /**
     *   "INSERT INTO english.T_sentences " +
     *                         "(word_id, type_id, sent_key, sent_value,sent_key_exten_1)" +
     *                         "VALUES(?, ?, ?, ?,?)");
     *
     *
     * "INSERT INTO english.T_colls" +
     *                         "(word_id, coll_value)" +
     *                         "VALUES(?, ?)");
     *
     * type_id from  20 mean from collocations
     */
    public void makeColloStmt(PreparedStatement pstmt_collo,PreparedStatement pstmt_sentence) throws SQLException {

        ColloBox colloBox = this.idoecEntry.getTail().getColloBox();
        List<ColloSection> colloSections = colloBox.getColloSectionList();
        int word_id= this.wordid;
        int type_id=20;

        if(colloSections.size()==0){
            pstmt_collo.setInt(1,word_id);
            pstmt_collo.setString(2,"");
            pstmt_collo.addBatch();
        }

        for(int i=0;i<colloSections.size();i++){
             type_id=20;

            ColloSection colloSection= colloSections.get(i);
            String coll_value= colloSection.getSectionName();
            pstmt_collo.setInt(1,word_id);
            pstmt_collo.setString(2,coll_value);
            pstmt_collo.addBatch();

            List<CollocateItem> collocates = colloSection.getCollocates();
            if(collocates.size()>0){
                for(int j=0;j<collocates.size();j++){
                    CollocateItem collocateItem= collocates.get(j);
                     type_id=21;
                     String sent_key= collocateItem.getCollocate();
                     String sent_key_exten_1= collocateItem.getCollGloss();
                     List<ColloExample> colloExampleList= collocateItem.getColloExampleList();
                     if(colloExampleList.size()>0){
                         for(int k=0;k<colloExampleList.size();k++){
                             String sentence = colloExampleList.get(k).getExample();
                             pstmt_sentence.setInt(1,word_id);
                             pstmt_sentence.setInt(2,type_id);
                             pstmt_sentence.setString(3,sent_key);
                             pstmt_sentence.setString(4,sent_key_exten_1);
                             pstmt_sentence.setString(5,sentence);
                             pstmt_sentence.addBatch();
                         }
                     }
                }
            }else{
                pstmt_sentence.setInt(1,word_id);
                pstmt_sentence.setInt(2, 99);
                pstmt_sentence.setString(3, "");
                pstmt_sentence.setString(4, "");
                pstmt_sentence.setString(5, "");
                pstmt_sentence.addBatch();
                pstmt_sentence.addBatch();
            }
        }

    }

    /**
     *   "INSERT INTO english.T_sentences " +
     *                         "(word_id, type_id, sent_key, sent_value,sent_key_exten_1)" +
     *                         "VALUES(?, ?, ?, ?,?)");
     *
     *"INSERT INTO english.T_thess" +
     *                         "(word_id, thes_value)" +
     *                         "VALUES(?, ?)");
     *
     * type_id from  20 mean from collocations
     */
    public void makeThesStmt(PreparedStatement pstmt_thes,PreparedStatement pstmt_sentence) throws SQLException {

        ThesBox thesBox = this.idoecEntry.getTail().getThesBox();
        List<ThesSection> thesSections = thesBox.getThesSections();
        int word_id = this.wordid;
        int type_id = 20;

        if(thesSections.size()==0){
            pstmt_thes.setInt(1,word_id);
            pstmt_thes.setString(2,"");
            pstmt_thes.addBatch();
        }

        for (int i = 0; i < thesSections.size(); i++) {
            type_id = 22;
            ThesSection thesSection = thesSections.get(i);
            List<ThesSectionItem> thesSectionItemList = thesSection.getSectionItems();
            if (thesSectionItemList.size() > 0) {

                for (int j = 0; j < thesSectionItemList.size(); j++) {
                    ThesSectionItem thesSectionItem = thesSectionItemList.get(j);
                    String displayEXP = thesSectionItem.getDisplayEXP();
                    String def = thesSectionItem.getDef();
                    List<ThesExample> examples = thesSectionItem.getExamples();
                    pstmt_thes.setInt(1, word_id);
                    pstmt_thes.setString(2, displayEXP);
                    pstmt_thes.addBatch();

                    if (examples.size() > 0) {
                        for (int k = 0; k < examples.size(); k++) {
                            String example = examples.get(k).getExample();
                            pstmt_sentence.setInt(1, word_id);
                            pstmt_sentence.setInt(2, type_id);
                            pstmt_sentence.setString(3, displayEXP);
                            pstmt_sentence.setString(4, example);
                            pstmt_sentence.setString(5, def);
                            pstmt_sentence.addBatch();
                        }
                    }
                }

            }else{
                pstmt_sentence.setInt(1, word_id);
                pstmt_sentence.setInt(2, 99);
                pstmt_sentence.setString(3, "");
                pstmt_sentence.setString(4, "");
                pstmt_sentence.setString(5, "");
                pstmt_sentence.addBatch();
                pstmt_sentence.addBatch();
            }

        }
    }


    public static void main(String[] args) {

    }


}
