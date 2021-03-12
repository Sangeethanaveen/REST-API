package restassured.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
{
    "Msg": "successfully added",
    "ID": "11110002222"
}
 */
public class ResponseAddBook {
    @JsonProperty("Msg")
    private String Msg;

    @JsonProperty("ID")
    private String ID;

    public String getId() {
        return ID;
    }
    public void setId(String ID) {
        this.ID = ID;
    }
    public String getMsg() {
        return Msg;
    }
    public void setMsg(String msg) {
        Msg = Msg;
    }
}
