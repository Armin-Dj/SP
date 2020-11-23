package models;
import java.util.ArrayList;
import java.util.List;

public class Chapter {
    private String nameCh;
    private List<SubChapter> listSC = new ArrayList<SubChapter>();
//    private int subchapterIndex = 0;

    public Chapter (String nameChapter){
        this.nameCh= nameChapter;
    }

    public int createSubChapter(String chName){
        SubChapter sCH = new SubChapter(chName);
        listSC.add(sCH);
        return listSC.indexOf(sCH);
//        return subchapterIndex++;
    }
    public SubChapter getSubChapter(int indexOfSubchapter) {
        return listSC.get(indexOfSubchapter);
    }
    public void print(){
        System.out.println("The chapter is "+ this.nameCh);
    }
}
