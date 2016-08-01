package com.diegog.mascotas.restAPI;

/**
 * Created by flogog on 6/26/16.
 */
public final class ConstantsRestAPI {

    public static final String QUESTION_MARK           = "?";
    public static final String SIGN_AND                = "&";


    public static final String VERSION                 = "/v1/";
    public static final String ROOT_URL                = "https://api.instagram.com"   +  VERSION;
    public static final String ACCESS_TOKEN            = "3453931761.9331ab0.9a41c42cdbe44396bd93cbe90478702d";

    public static final String KEY_ACCESS_TOKEN        = "access_token=";
    public static final String KEY_GET_RECENT_MEDIA    = "users/self/media/recent/";
    public static final String KEY_GET_USER            = "users/";
    public static final String KEY_GET_USER_NAME       = "users/{user-id}/";
    public static final String KEY_GET_USER_MEDIA      = "{user-id}/media/recent/";
    public static final String KEY_SEARCH_USER         = "search";
    public static final String KEY_FOLLOW_USER         = "{user-id}/relationship/";

    public static final String URL_GET_RECENT_MEDIA    = KEY_GET_RECENT_MEDIA + QUESTION_MARK   + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    public static final String URL_GET_USER_NAME       = KEY_GET_USER +  KEY_GET_USER_NAME      + QUESTION_MARK +   KEY_ACCESS_TOKEN    +   ACCESS_TOKEN;
    public static final String URL_GET_USER_MEDIA      = KEY_GET_USER +  KEY_GET_USER_MEDIA     + QUESTION_MARK +   KEY_ACCESS_TOKEN    +   ACCESS_TOKEN;
    public static final String URL_POST_FOLLOW_USER    = KEY_GET_USER +  KEY_FOLLOW_USER        + QUESTION_MARK +   KEY_ACCESS_TOKEN    +   ACCESS_TOKEN;



    public static final String URL_SEARCH_USER         = KEY_GET_USER + KEY_SEARCH_USER + QUESTION_MARK + SIGN_AND+KEY_ACCESS_TOKEN + ACCESS_TOKEN;


    //ADDING CONSTANTS FOR HEROKU AND FIREBASE
    public static final String HEROKU_ROOT_URL      = "https://guarded-springs-46050.herokuapp.com/";
    public static final String HEROKU_POST_ID_TOKEN = "token-device/";
    public static final String HEROKU_POST_USER     = "registrar-usuario/";
    public static final String HEROKU_POST_LIKE     = "instagram-likes/{foto}/{dispositivo}/{usuario}/";



}