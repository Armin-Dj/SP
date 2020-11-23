package services;

public interface Visitor {
    public abstract void visit(Section section);
    public abstract void visit(Paragraph paragraph);
    public abstract void visit(ImageProxy imageProxy);
    public abstract void visit(Image image);
    public abstract void visit(Table table);
}