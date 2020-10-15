public class Table implements Element{
    private String title;

    public Table (String nameTable){
        this.title = nameTable;
    }
    public void print() {
        System.out.println("The author is "+ this.title);
    }
}
