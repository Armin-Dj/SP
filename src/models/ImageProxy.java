package models;
import java.awt.*;

public class ImageProxy implements Element{
    public String url;
    public Dimension dim;
    private Image realImage;

    ImageProxy(String url){
        this.url = url;
    }

    private Image loadImage(){
        if(null == realImage){
            realImage = new Image(url);
        }
        return realImage;
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }

    public void print(){
        loadImage();
        realImage.print();
    }

}
