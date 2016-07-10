package com.diegog.mascotas.restAPI.model;

import com.diegog.mascotas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by flogog on 6/26/16.
 */
public class MascotaResponse {

    String userId;

    String profilePicture;

    ArrayList<Mascota> mascotas;

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
