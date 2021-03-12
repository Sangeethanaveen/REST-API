package restassured.test;

public class ReturnBook {
    private String bookId;

    public String getBookId() {
        return bookId;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    private String successMessage;

    public ReturnBook(String bookId,String successMessage){
        this.bookId = bookId;
        this.successMessage = successMessage;
    }
}
