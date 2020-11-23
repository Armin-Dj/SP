package models;
public class Table implements Element {
    private String title;

    public void print()
    {
        System.out.println("models.Table with title: " + this.title);
    }

    public Table(String title) {
        this.title = title;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}