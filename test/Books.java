package restassured.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import restassured.request.AddBookDetailRequest;

import java.util.List;

public class Books extends AddGetDeleteBook {
    @Test
    public void addANewBook() {
        addBook(aisle1, "ABY02", "Life is what you make it", "Preethy Shenoy");
        ReturnBook book = addBookResponse(aisle1);
        Assert.assertEquals(book.getSuccessMessage(), "successfully added");
    }

    @Test
    public void addExistingBook() {
        addBook(aisle1, "ABY02", "Life is what you make it", "Preethy Shenoy");
        String existingMsg = existingBookResponse(aisle1);
        Assert.assertEquals(existingMsg, "Add Book operation failed, looks like the book already exists");
    }

    @Test
    public void addGetDeleteAndAddAgain() {
        addBook(aisle2, "AY10", "Life is what you make it", "Preethy Shenoy");
        ReturnBook book = addBookResponse(aisle2);
        Assert.assertEquals(book.getSuccessMessage(), "successfully added");
        String bookName = getBookByID(book.getBookId());
        Assert.assertEquals(bookName, "Life is what you make it");
        String delMessage = deleteBook(book.getBookId());
        //verifyDeleteBook(book.getBookId());
        Assert.assertEquals(delMessage, "book is successfully deleted");
        Boolean del = getDeletedBook(book.getBookId());
        Assert.assertTrue(del);
        addBook(aisle2, "AY10", "Life is what you make it", "Preethy Shenoy");
        ReturnBook book1 = addBookResponse(aisle2);
        Assert.assertEquals(book1.getSuccessMessage(), "successfully added");
    }

    @Test
    public void addBooksOfSameAuthorAndGetBookNames() {
        AddBookDetailRequest addBook = new AddBookDetailRequest();
        addBook(aisle3, "CY010", "2 States", "Chetan Bhagat");
        ReturnBook book1 = addBookResponse(aisle3);
        Assert.assertEquals(book1.getSuccessMessage(), "successfully added");
        addBook(aisle4, "CY010", "Half Girlfriend", "Chetan Bhagat");
        ReturnBook book2 = addBookResponse(aisle4);
        Assert.assertEquals(book2.getSuccessMessage(), "successfully added");
        addBook(aisle5, "CY010", "5 point By Someone", "Chetan Bhagat");
        ReturnBook book3 = addBookResponse(aisle5);
        Assert.assertEquals(book3.getSuccessMessage(), "successfully added");
        getBookByAuthor("chetan Bhagat");
        List<String> verifyBooks = verifyGetBookByAuthor();
        System.out.println(verifyBooks);
        Assert.assertEquals(verifyBooks, bookNameList, "List are not equal");
    }


}

