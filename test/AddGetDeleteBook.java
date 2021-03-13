package restassured.test;

import deserialization.BookDetailsByAuthor;
import restassured.request.AddBookDetailRequest;
import restassured.request.RequestDeleteBody;
import restassured.response.DeleteResponse;
import restassured.response.GetBookResponse;
import restassured.response.ResponseAddBook;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import restassured.response.ResponseExistingBook;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class AddGetDeleteBook {
    private Response response = null;
    private Response getResponse = null;
    private Response delResponse =null;
    List<String> bookNameList = new ArrayList<>();
    HashMap<String,String> bookIdMap = new HashMap<>();

    String aisle1 = String.valueOf(generateAisle());
    String aisle2 = String.valueOf(generateAisle());
    String aisle3 = String.valueOf(generateAisle());
    String aisle4 = String.valueOf(generateAisle());
    String aisle5 = String.valueOf(generateAisle());

    public int generateAisle() {
        Random ran = new Random();
        int aisle = ran.nextInt(1000);
        return aisle;
    }

    public void addBook(String aisle,String isbn,String bName,String author) {
        RestAssured.baseURI = "http://216.10.245.166";
        AddBookDetailRequest addBook = new AddBookDetailRequest();
        addBook.setName(bName);
        addBook.setIsbn(isbn);
        addBook.setAisle(aisle);
        addBook.setAuthor(author);
        bookNameList.add(bName);
        response = given()
                .header("Content-Type", "application/json")
                .log().all()
                .body(addBook)
                .when().post("/Library/Addbook.php").then()
                .extract().response();
        System.out.println(response.asString());
        ResponseAddBook addBookResponse = response.body().as(ResponseAddBook.class);
        String searchById = addBookResponse.getId();
        bookIdMap.put(bName,searchById);

    }

    public ReturnBook addBookResponse(String aisle) {
        ResponseAddBook addBookResponse = response.body().as(ResponseAddBook.class);
        String success = addBookResponse.getMsg();
        String searchById = addBookResponse.getId();
        System.out.println(searchById);
        ReturnBook rbook = new ReturnBook(searchById, success);
        return rbook;
    }

    public String existingBookResponse(String aisle) {
        ResponseExistingBook existingBookResponse = response.body().as(ResponseExistingBook.class);
        String existing = existingBookResponse.getMsg();
        return existing;

    }

    public String getBookByID(String searchById) {
        RestAssured.baseURI = "http://216.10.245.166";
        System.out.println("Search book by Id  " + searchById);
        Response response = given().queryParam("ID", searchById)
                .header("Content-Type", "application/json")
                .when()
                .get("/Library/GetBook.php")
                .then()
                .statusCode(200)
                .extract().response();
        //method to get the status code
        int statusCode = response.getStatusCode();
        System.out.println(response.asString());
        Assert.assertEquals(statusCode, 200);
        GetBookResponse[] book = response.body().as(GetBookResponse[].class);
        String bookName = book[0].getBookName();
        return bookName;

    }

    public String deleteBook(String searchById) {
        RestAssured.baseURI = "http://216.10.245.166";
        RequestDeleteBody delBook = new RequestDeleteBody();
        delBook.setId(searchById);
        Response delResponse = given()
                .header("Content-Type", "application/json")
                .log().all()
                .body(delBook)
                .when()
                .get("/Library/DeleteBook.php")
                .then()
                .statusCode(200)
                .extract().response();
        int statusCode = delResponse.getStatusCode();
        System.out.println(delResponse.asString());
        Assert.assertEquals(statusCode, 200);
        DeleteResponse delete = delResponse.body().as(DeleteResponse.class);
        String delMessage = delete.getMsg();
        System.out.println(delMessage);
        return delMessage;

    }

    public boolean getDeletedBook(String searchById) {
        RestAssured.baseURI = "http://216.10.245.166";
        System.out.println("Search book by Id  " + searchById);
        Response response = given().queryParam("ID", searchById)
                .header("Content-Type", "application/json")
                .when()
                .get("/Library/GetBook.php")
                .then().assertThat()
                .extract().response();
        System.out.println(response.asString());
        int status = response.getStatusCode();
        boolean value = (status == 404);
        return value;
    }
    public void getBookByAuthor(String author){
        RestAssured.baseURI="http://216.10.245.166";
        getResponse = RestAssured.given().queryParam("AuthorName",author)
                .header("Content-Type","application/json")
                .when()
                .get("Library/GetBook.php")//post
                .then()
                .assertThat().log().all()
                //.statusCode(200)
                .extract().response();
        System.out.println(getResponse.asString());

    }
    public List<String> verifyGetBookByAuthor(){
        BookDetailsByAuthor[] getBookResponse = getResponse.as(BookDetailsByAuthor[].class);
        int noOfBook = getBookResponse.length;
        System.out.println(noOfBook);
        for(int i=0;i<noOfBook;i++) {
            String bookName = getBookResponse[i].getBook_name();
            //System.out.println(bookName);
            bookNameList.add(bookName);
        }
        return bookNameList;

    }
    public void deleteBookByIdWithAuthorName(){
       int bookNameSize =  bookNameList.size();
       for (int i=0;i<bookNameSize;i++)
       {
           String book = bookNameList.get(i);
           String delId =bookIdMap.get(book);
           deleteBook(delId);
       }
    }

}



