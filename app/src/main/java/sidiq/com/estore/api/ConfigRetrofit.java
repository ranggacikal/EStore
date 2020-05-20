package sidiq.com.estore.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit {

    private static ConfigRetrofit mInstance;

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://himti-umt.org/backend_estore/index.php/API_estore/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ApiService service = retrofit.create(ApiService.class);
}
