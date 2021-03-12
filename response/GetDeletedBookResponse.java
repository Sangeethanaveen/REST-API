package restassured.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
{"msg":"The book by requested bookid \/ author name does not exists!"}
 */
public class GetDeletedBookResponse {
    @JsonProperty("msg")
    private String msg;
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }


}
