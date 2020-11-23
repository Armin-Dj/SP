package services;

import models.*;

public class BookStatistics  implements Visitor {

    private int imageC = 0;
    private int tablesC = 0;
    private int paragraphsC = 0;
    private int sectionC = 0;



    @Override
    public void visit(Section section) {
        sectionC++;
    }

    @Override
    public void visit(Paragraph paragraph) {
        paragraphsC++;
    }

    @Override
    public void visit(ImageProxy imageProxy) {
        imageC++;
    }

    @Override
    public void visit(Image image) {
        imageC++;
    }

    @Override
    public void visit(Table table) {
        tablesC++;
    }

    public void print(){
        System.out.println("Book Statistics:");
        System.out.println("***Number of images: " + imageC);
        System.out.println("***Number of tables: " + tablesC );
        System.out.println("***Number of paragraphs: " + paragraphsC);
    }
}