package server.Models.TCP;

import com.google.gson.Gson;
import server.Enums.ResponseStatus;

public class Response {
    private ResponseStatus responseStatus;
    private String responseData;
    private String responseMessage;
    private Object returnUser;

    public Response() {
    }

    public Response(ResponseStatus responseStatus, String responseMessage, Object returnUser) {
        this.responseStatus = responseStatus;
        this.responseMessage = responseMessage;
        this.returnUser = returnUser;
    }
    public Response(ResponseStatus responseStatus, String responseData) {
        this.responseStatus = responseStatus;
        this.responseData = responseData;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setReturnUser(String returnUser) {
        this.returnUser = returnUser;
    }

    public Object getReturnUser() {
        return returnUser;
    }
}