package API;

import android.os.Build;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static API.Tls12SocketFactory.enableTls12OnPreLollipop;

public class API {

    private static final String BASE_URL = "https://api.nasa.gov/planetary/";
    private static Retrofit retrofit=null;
    public static final String APPKEY =  "ykEnnTgMAnkzZJJOIPMZSN9QmsIDFsNrEBAnvNwB";
    public static final int cantElementos =  29;

    public static Retrofit getApi()
    {
        if(retrofit == null)
        {
            if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT <= 19)
            {
                OkHttpClient.Builder client = new OkHttpClient.Builder()
                        .followRedirects(true)
                        .followSslRedirects(true)
                        .retryOnConnectionFailure(true)
                        .cache(null)
                        .connectTimeout(5, TimeUnit.SECONDS)
                        .writeTimeout(5, TimeUnit.SECONDS)
                        .readTimeout(5, TimeUnit.SECONDS);

                OkHttpClient clientOldVersion = enableTls12OnPreLollipop(client).build();

                retrofit =  new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(clientOldVersion)
                        .build();
            }
            else
            {
                retrofit =  new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }

        }
        return retrofit;
    }

}
