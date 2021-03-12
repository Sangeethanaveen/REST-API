package restassured.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
"msg": "Add Book operation failed, looks like the book already exists"
 */
public class ResponseExistingBook {

    @JsonProperty("msg")
    private String msg;
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }



}
