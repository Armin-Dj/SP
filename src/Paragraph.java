public class Paragraph implements Element{
        private String text;

        public Paragraph (String textP){
        this.text = textP;
    }
        public void print() {
            System.out.println("The paragraph is "+ this.text);
        }
}
