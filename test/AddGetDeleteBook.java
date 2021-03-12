package restassured.test;

import restassured.request.AddBookDetailRequest;
import restassured.request.RequestDeleteBody;
import restassured.response.DeleteResponse;
import restassured.response.GetBookResponse;
import restassured.response.ResponseAddBook;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import restassured.response.ResponseExistingBook;


import java.util.Random;

import static io.restassured.RestAssured.given;

public class AddGetDeleteBook {
    private Response response = null;
    String book = "Life is what you make it";
    String isbn = "AYLC";
    String aisle1 = String.valueOf(generateAisle());
    String author = "Preethy Shenoy";
    String aisle2 = String.valueOf(generateAisle());

    public int generateAisle() {
        Random ran = new Random();
        int aisle = ran.nextInt(25000025);
        System.out.println(isbn);
        return aisle;
    }

    public Response addBook(String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";
        AddBookDetailRequest addBook = new AddBookDetailRequest();
        addBook.setName(book);
        addBook.setIsbn(isbn);
        addBook.setAisle(aisle);
        addBook.setAuthor(author);
        response = given()
                .header("Content-Type", "application/json")
                .log().all()
                .body(addBook)
                .when().post("/Library/Addbook.php").then()
                .extract().response();
        System.out.println(response.asString());
        return response;
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
        DeleteResponse delete = delResponse.as(DeleteResponse.class);
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

}



