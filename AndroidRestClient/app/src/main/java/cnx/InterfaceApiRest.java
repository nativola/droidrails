package cnx;


import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Objects;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by HanzelCruz on 7/22/14.
 */
public interface InterfaceApiRest {

    /**
     * @param resource it will be the resource from the api that you want to access (/users, /category)
     * add the resource on config/Config
     */
    @POST("/{resource}")
    void post(@Path("resource") String resource,   @Body Object obj, Callback<Object> cb);

    @GET("/{resource}")
    void get(@Path("resource")  String resource, Callback<Object> cb);

    @PUT("/{resource}/{id}")
    void put(@Path("resource")  String resource, @Path("id")  String id,  @Body Object obj, Callback<Object> cb);

    @DELETE("/{resource}/{id}")
    void delete(@Path("resource") String resource, @Path("id") String id, @Body Object obj, Callback<Object> cb);

}
