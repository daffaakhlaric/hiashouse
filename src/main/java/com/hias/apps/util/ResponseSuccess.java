package com.hias.apps.util;

//import com.fineoz.sec.payload.UserSummary;

public class ResponseSuccess {
    private Boolean success = true;
    private Object data;

    public ResponseSuccess(Object data, String s) {
        this.data = data;
    }

//    public ResponseSuccess(UserSummary data) {
//        this.data = data;
//    }
//
//    public ResponseSuccess(String message) {
//        this.data = new MessageError(message);
//    }

    public Boolean getSuccess() {
        return success;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

}
