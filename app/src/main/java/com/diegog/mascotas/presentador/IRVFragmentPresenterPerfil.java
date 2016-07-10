package com.diegog.mascotas.presentador;

/**
 * Created by flogog on 6/26/16.
 */
public interface IRVFragmentPresenterPerfil {

    public void getRecentMedia();
    public void showMascotasRV();

    public void getMediaByUser(String userId);

    public void getUserIdByUsername(String username);

}
