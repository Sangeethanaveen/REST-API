package restassured.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/*

    {
        "book_name": "Life is what you make it",
        "isbn": "AB1234",
        "aisle": "4567800",
        "author": "Preethy Shenoy"
    }
 */
public class GetBookResponse {

    @JsonProperty("book_name")
    private String bookName;
    private String isbn;
    private String aisle;
    private String author;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }



    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

}
