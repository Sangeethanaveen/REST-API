package restassured.response;
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
    private String msg;
}
