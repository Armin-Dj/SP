import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private Author author;
    private List<Chapter> chapters= new ArrayList<Chapter>();

    public Book (String nameBook){
        this.title= nameBook;
    }

    public void addAuthor(Author authorName){
        this.author = authorName;
    }
    public int createChapter(String bookName){
        Chapter cH = new Chapter(bookName);
        chapters.add(cH);
        return chapters.indexOf(cH);
    }
    public Chapter getChapter(int indexOfSubchapter) {
        return chapters.get(indexOfSubchapter);
    }

    public void print(){
        System.out.println("The book is named: "+ this.title);
    }
}
