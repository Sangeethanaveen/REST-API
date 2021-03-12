package restassured.request;
/*
{

@Test
    public void deleteBookByID(){
     RestAssured.baseURI="http://216.10.245.166";
     Response delResponse = given().queryParam("ID","0001")
             .header("Content-Type","application/json")
             .body("\"ID\" : \"0300\"")
             .when()
             .get("/Library/DeleteBook.php")
             .then()
             .statusCode(200)
             .extract().response();
     //method to get the status code
     int statusCode = delResponse.getStatusCode();
     System.out.println(delResponse.asString());
     Assert.assertEquals(statusCode,200);
     //DeleteResponse delete = delResponse.as(DeleteResponse.class);
     //String message = delete.getMsg();
     //System.out.println(message);
}
 */
public class AddBookDetailRequest {
    private String name;
    private String isbn;
    private String aisle;
    private String author;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
}
