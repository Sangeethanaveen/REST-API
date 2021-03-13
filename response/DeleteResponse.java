package restassured.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
"msg": "book is successfully deleted"
 */
public class DeleteResponse {
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @JsonProperty("msg")
    private String msg;
}
