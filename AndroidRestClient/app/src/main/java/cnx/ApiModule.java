package cnx;

import config.Config;
import retrofit.RestAdapter;

/**
 * Created by HanzelCruz on 7/17/14.
 */
public class ApiModule {
    private static RestAdapter restAdapter;
    private static InterfaceApiRest service;

    public static RestAdapter getProvideRestAdapter(){
        if (restAdapter == null){
            restAdapter = new RestAdapter.Builder()
                    .setEndpoint(Config.APIURL)
                    .build();
        }
        return restAdapter;
    }

    public static InterfaceApiRest getService(){
        if (service == null){
            service = getProvideRestAdapter().create(InterfaceApiRest.class);
        }
        return service;
    }
}
