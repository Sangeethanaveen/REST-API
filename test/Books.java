package restassured.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import restassured.request.AddBookDetailRequest;

import java.util.List;

public class Books extends AddGetDeleteBook {

    @DataProvider
    public Object[][] addNewBook() {
        return new Object[][]{
                {aisle1, "ABY02", "Life is what you make it", "Preethy Shenoy"}
        };
    }

    @Test(dataProvider = "addNewBook")
    public void addANewBook(String aisle, String isbn, String bookName, String author) {
        addBook(aisle, isbn, bookName, author);
        ReturnBook book = addBookResponse(aisle1);
        Assert.assertEquals(book.getSuccessMessage(), "successfully added");
    }

    @Test(dataProvider = "addNewBook")
    public void addExistingBook(String aisle, String isbn, String bookName, String author) {
        addBook(aisle, isbn, bookName, author);
        String existingMsg = existingBookResponse(aisle1);
        Assert.assertEquals(existingMsg, "Add Book operation failed, looks like the book already exists");
    }

    @DataProvider
    public Object[][] addBook() {
        return new Object[][]{
                {aisle2, "ABY02", "Life is what you make it", "Preethy Shenoy"}
        };
    }

    @Test(dataProvider = "addBook")
    public void addGetDeleteAndAddAgain(String aisle, String isbn, String bName, String author) {
        addBook(aisle, isbn, bName, author);
        ReturnBook book = addBookResponse(aisle2);
        Assert.assertEquals(book.getSuccessMessage(), "successfully added");
        String bookName = getBookByID(book.getBookId());
        Assert.assertEquals(bookName, "Life is what you make it");
        String delMessage = deleteBook(book.getBookId());
        //verifyDeleteBook(book.getBookId());
        Assert.assertEquals(delMessage, "book is successfully deleted");
        Boolean del = getDeletedBook(book.getBookId());
        Assert.assertTrue(del);
        addBook(aisle, isbn, bName, author);
        ReturnBook book1 = addBookResponse(aisle2);
        Assert.assertEquals(book1.getSuccessMessage(), "successfully added");
    }

    @DataProvider
    public Object[][] addBookOfSameAuthor() {
        return new Object[][]{
                {"ACY0",  "Stephen"},
        };
    }

    @Test(dataProvider = "addBookOfSameAuthor")
    public void addBooksOfSameAuthorAndGetBookNames(String isbn, String author) {
        AddBookDetailRequest addBook = new AddBookDetailRequest();
        addBook(aisle3, isbn, "Learning API", author);
        ReturnBook book1 = addBookResponse(aisle3);
        Assert.assertEquals(book1.getSuccessMessage(), "successfully added");
        addBook(aisle4, isbn, "Learning Java", author);
        ReturnBook book2 = addBookResponse(aisle4);
        Assert.assertEquals(book2.getSuccessMessage(), "successfully added");
        addBook(aisle5, isbn, "Learning Ajax", author);
        ReturnBook book3 = addBookResponse(aisle5);
        Assert.assertEquals(book3.getSuccessMessage(), "successfully added");
        getBookByAuthor(author);
        List<String> verifyBooks = verifyGetBookByAuthor();
        System.out.println(verifyBooks);
        Assert.assertEquals(verifyBooks, bookNameList, "List are not equal");
    }

    @DataProvider
    public Object[][] deleteBookByAuthor() {
        return new Object[][]{
                {"CY10B","Hello Programming"},
        };
    }
    @Test(dataProvider = "deleteBookByAuthor")
    public void deleteBookByAuthorWithSimilarNames(String isbn,String bName) {
        AddBookDetailRequest addBook = new AddBookDetailRequest();
        addBook(aisle3,isbn, bName, "Boxom");
        int noOfBooksBefore = getBookByAuthor("Boxom");
        addBook(aisle4, isbn, bName, "Boom");
        getBookByAuthor("Boom");
        String deleteId = getIdOfTheBook();
        deleteBook(deleteId);
        int noOfBooksAfter = getBookByAuthor("Boxom");
        List<String> verifyBooksAfter = verifyGetBookByAuthor();
        System.out.println(verifyBooksAfter);
        Assert.assertEquals(noOfBooksAfter, noOfBooksBefore);
    }
}

