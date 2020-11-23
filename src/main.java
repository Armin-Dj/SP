package lab8;
import services.*;
import models.*;
// package lab7;

// pre lab 7
// public class main {

//     public static void main(String[] args) throws Exception {
//         Section cap1 = new Section("Capitolul 1");
//         Paragraph p1 = new Paragraph("models.Paragraph 1");
//         cap1.add(p1);
//         Paragraph p2 = new Paragraph("models.Paragraph 2");
//         cap1.add(p2);
//         Paragraph p3 = new Paragraph("models.Paragraph 3");
//         cap1.add(p3);
//         Paragraph p4 = new Paragraph("models.Paragraph 4");
//         cap1.add(p4);

//         cap1.add(new ImageProxy("ImageOne"));
//         cap1.add(new Image("ImageTwo"));
//         cap1.add(new Paragraph("Some text"));
//         cap1.add(new Table("Table 1"));
//         BookStatistics stats = new BookStatistics();
//         cap1.accept(stats);
//         stats.print();
//         new RenderContentVisitor().visitBook(c);
//     }
// }

public class main{
    public static void main(String[] args) throws Exception {
        Command cmd1 = new OpenCommand("book.json");
        cmd1.execute();
        Command cmd2 = new StatisticsCommand();
        cmd2.execute();
        DocumentManager.getBook().print();
        }
}