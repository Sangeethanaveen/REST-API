package restassured.test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Books extends AddGetDeleteBook {
    @Test
    public void addANewBook() {
        addBook(aisle1);
        ReturnBook book = addBookResponse(aisle1);
        Assert.assertEquals(book.getSuccessMessage(), "successfully added");
    }

    @Test
    public void addExistingBook() {
        addBook(aisle1);
        String existingMsg = existingBookResponse(aisle1);
        Assert.assertEquals(existingMsg,"Add Book operation failed, looks like the book already exists");
    }

    @Test
    public void addGetDeleteAndAddAgain() {
        addBook(aisle2);
        ReturnBook book = addBookResponse(aisle2);
        Assert.assertEquals(book.getSuccessMessage(), "successfully added");
        String bookName = getBookByID(book.getBookId());
        Assert.assertEquals(bookName, "Life is what you make it");
        String delMessage = deleteBook(book.getBookId());
        Assert.assertEquals(delMessage, "book is successfully deleted");
        Boolean del = getDeletedBook(book.getBookId());
        Assert.assertTrue(del);
        addBook(aisle2);
        ReturnBook book1 = addBookResponse(aisle2);
        Assert.assertEquals(book1.getSuccessMessage(), "successfully added");
    }
}
