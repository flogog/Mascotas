package com.diegog.mascotas.restAPI.deserializador;

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

/**
 * Created by flogog on 7/10/16.
 */
public class UsuarioInstagram implements JsonDeserializer<MascotaResponse> {
    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();
        MascotaResponse mascotaResponse             = gson.fromJson(json, MascotaResponse.class);
        JsonArray       mascotaResponseData         = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        JsonObject      mascotaResponseDataObject   = mascotaResponseData.get(0).getAsJsonObject();
        String          userString                  = mascotaResponseDataObject.get(JsonKeys.USER_ID).getAsString();

        mascotaResponse.setUserId(userString);
        System.out.println("****************************"+mascotaResponse.getUserId());
        return mascotaResponse;

    }
}
