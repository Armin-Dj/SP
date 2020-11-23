package services;
import models.Context;
import models.Paragraph;
import services.AlignStrategy;
public class AlignRight implements AlignStrategy{
    public void render(Paragraph p, Context c){
        System.out.println("Aligned with AlignRight: " + p.getText());
    }
}