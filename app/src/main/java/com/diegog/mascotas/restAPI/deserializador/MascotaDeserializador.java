package com.diegog.mascotas.restAPI.deserializador;

import com.diegog.mascotas.pojo.Mascota;
import com.diegog.mascotas.restAPI.JsonKeys;
import com.diegog.mascotas.restAPI.model.MascotaResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by flogog on 6/26/16.
 */
public class MascotaDeserializador implements JsonDeserializer<MascotaResponse> {

    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaResponse mascotaResponse = gson.fromJson(json, MascotaResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        mascotaResponse.setMascotas(deserializaMascotaDeJSON(mascotaResponseData));
        mascotaResponse.setProfilePicture(getProfilePicture(mascotaResponseData));
        return mascotaResponse;
    }

    private ArrayList<Mascota> deserializaMascotaDeJSON(JsonArray mascotaResponseData){
        ArrayList<Mascota> mascotas = new ArrayList<>();
        for(int i=0; i<mascotaResponseData.size(); i++){

            JsonObject mascotaResponseDataObject    = mascotaResponseData.get(i).getAsJsonObject();
            JsonObject userJson                     = mascotaResponseDataObject.getAsJsonObject(JsonKeys.USER);
            JsonObject imageJson                    = mascotaResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject likes                        = mascotaResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            String     idLike                      = mascotaResponseDataObject.get(JsonKeys.USER_ID).getAsString();

            JsonObject image                        = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RES);

            String  id      = userJson.get(JsonKeys.USER_ID).getAsString();
            String  username      = userJson.get(JsonKeys.USER_NAME).getAsString();
            String  nombre  = userJson.get(JsonKeys.USER_FULL_NAME).getAsString();
            String  url     = image.get(JsonKeys.MEDIA_URL).getAsString();
            int     count   = likes.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            Mascota mascota = new Mascota(id, nombre,url,count, idLike);
            mascota.setUsername(username);
            mascotas.add(mascota);

        }
        return mascotas;
    }

    private String getProfilePicture(JsonArray mascotaResponseData){
        JsonObject mascotaResponseDataObject    = mascotaResponseData.get(0).getAsJsonObject();
        JsonObject userJson                     = mascotaResponseDataObject.getAsJsonObject(JsonKeys.USER);
        return userJson.get(JsonKeys.USER_PROFILE_PIC).getAsString();
    }
}
