package com.diegog.mascotas.restAPI.model;

/**
 * Created by flogog on 7/23/16.
 */
public class UserResponse {

    private String resp;

    public UserResponse() {
    }

    public UserResponse(String resp) {
        this.resp = resp;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }
}
