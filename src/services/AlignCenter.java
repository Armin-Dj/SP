package services;
import models.Context;
import models.Paragraph;
import services.AlignStrategy;
public class AlignCenter implements AlignStrategy{
    public void render(Paragraph p, Context c){
        System.out.println("Aligned with AlignCenter: " + p.getText());
    }
}