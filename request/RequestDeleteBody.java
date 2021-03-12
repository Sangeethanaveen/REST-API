package restassured.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
"ID": "A10005000"
 */
public class RequestDeleteBody {
    @JsonProperty("ID")
    private String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
