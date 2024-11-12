package server.Models.TCP;

import server.Enums.RequestType;

public class Request {
    private RequestType requestType;
    private String requestMessage;

    public Request() {
    }

    public Request(RequestType requestType, String requestMessage) { // то, что хотим сделать, то, что передаём
        this.requestType = requestType;
        this.requestMessage = requestMessage;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }
}