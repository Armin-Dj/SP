public class OpenCommand implements Command{
    public void execute(){
        Book book1;
        // Section cap1 = new Section("Capitolul 1");
        // Paragraph p1 = new Paragraph("models.Paragraph 1");
        // cap1.add(p1);
        // Paragraph p2 = new Paragraph("models.Paragraph 2");
        // cap1.add(p2);
        // Paragraph p3 = new Paragraph("models.Paragraph 3");
        // cap1.add(p3);
        // Paragraph p4 = new Paragraph("models.Paragraph 4");
        // cap1.add(p4);

        // cap1.add(new ImageProxy("ImageOne"));
        // cap1.add(new Image("ImageTwo"));
        // cap1.add(new Paragraph("Some text"));
        // cap1.add(new Table("Table 1"));
        // System.out.println("Opening...");
        // DocumentManager.getInstance().setBook(book1);
        Author a1 = new Author("Author1");
        Author a2 = new Author("Author2");
        book1.addAuthor(a1);
        book1.addAuthor(a2);

        Section cap1 = new Section("Chapter 1");
        Paragraph paragraph1 = new Paragraph("Paragraph 1");
        cap1.add(paragraph1);
        Paragraph paragraph2 = new Paragraph("Paragraph 2");
        cap1.add(paragraph2);
        Paragraph paragraph3 = new Paragraph("Paragraph 3");
        cap1.add(paragraph3);
        book1.add(cap1);

        Table t1 = new Table("Table1");
        book1.add(t1);

        Image im = new Image("Image1");
        book1.add(im);

        System.out.println("Opening...");
        DocumentManager.getInstance().setBook(book1);
    }
}
