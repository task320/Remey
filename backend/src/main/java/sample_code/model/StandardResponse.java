package sample_code.model;

import com.google.gson.JsonElement;
import constant.StatusResponse;

public class StandardResponse {
    private StatusResponse status;
    private String message;
    private JsonElement values;

    public StandardResponse(StatusResponse status) {
    	this.status = status;
    }
    public StandardResponse(StatusResponse status, String message) {
    	this.status = status;
    	this.message = message;
    }
    public StandardResponse(StatusResponse status, JsonElement data) {
    	this.status = status;
        this.values = data;
    }
	public StatusResponse getStatus() {
		return status;
	}
	public void setStatus(StatusResponse status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public JsonElement getValues() {
		return values;
	}
	public void setValues(JsonElement data) {
		this.values = data;
	}

}
