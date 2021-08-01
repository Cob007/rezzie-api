package com.rezzie.api.user;

public class Res<T> {
    private Boolean status;
    private String message;
    private T data;

    public Res() {
    }

    public Res(Boolean status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Res(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }


    public static <T> Res<T> errorResponse(String errorMsg){
        return new Res<T>(false, errorMsg, null);
    }

    public static <T> Res<T> successResponse(String successMsg, T data){
        return new Res<T>(true, successMsg, data);
    }

    public static <T> Res<T> successNoDataResponse(String successMsg){
        return new Res<T>(true, successMsg);
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
