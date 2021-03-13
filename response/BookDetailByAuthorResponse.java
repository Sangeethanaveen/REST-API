package restassured.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookDetailByAuthorResponse {
    @JsonProperty("book_name")
    private String book_name;
    private String isbn;
    private String aisle;

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

}
