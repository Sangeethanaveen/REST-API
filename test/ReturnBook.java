package restassured.test;

public class ReturnBook {
    private String bookId;
    private String successMessage;


    public String getBookId() {
        return bookId;
    }

    public String getSuccessMessage() {
        return successMessage;
    }
    public ReturnBook(String bookId, String successMessage) {
        this.bookId = bookId;
        this.successMessage = successMessage;
    }
}
