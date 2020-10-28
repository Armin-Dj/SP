public class Image implements Element{
    String imageName;

    public Image (String image){
        this.imageName = image;
    }
    public void print() {
        System.out.println("The image is "+ this.imageName);
    }
}
