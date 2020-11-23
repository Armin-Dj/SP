public class DocumentManager {
    static private DocumentManager instance = null;
    private Book book = new Book("");

    private DocumentManager(){
        if(null == instance){
            instance = new DocumentManager();
        }
    }

    static public getInstance(){
        if(null == instance){
            instance = new DocumentManager();
        }
        return instance;
    }

    public Book getBook(){
        return this.book;
    }
    
    private setBook(Book book){
        this.book = book;
    }
}
