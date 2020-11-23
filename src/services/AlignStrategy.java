package services;
import models.Context;
import models.Paragraph;
import services.AlignStrategy;
public interface AlignStrategy{
    public void render(Paragraph p, Context c);
}