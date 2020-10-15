import java.util.ArrayList;
import java.util.List;

public class SubChapter {
    private String name;
    private List<Element> elements= new ArrayList<Element>();

    public SubChapter (String nameSChapter){
        this.name = nameSChapter;
    }
    public void createNewParagraph(String nameParagraph){
        Paragraph p = new Paragraph(nameParagraph);
        elements.add(p);
    }
    public void createNewTable(String nameTable){
        Table t = new Table(nameTable);
        elements.add(t);
    }
    public void createNewImage(String nameImage){
        Image i = new Image(nameImage);
        elements.add(i);
    }
    public void print() {

        System.out.println("The name of the chapter is: " + this.name);

        for (Element element: elements)
        {
            element.print();
        }
    }
}
