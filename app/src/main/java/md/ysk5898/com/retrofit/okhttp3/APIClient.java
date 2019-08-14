/*
 * Create by SangKwon on 2019. 8. 14.
 */

package md.ysk5898.com.retrofit.okhttp3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import md.ysk5898.com.retrofit.inf.retroInterface;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String URLS) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        // .baseUrl(TestRetroIF.API_URL3)                      : 해당 URL과 통신 (마지막에 / 꼭 넣어줘야함)
        // .client                                             : 통신클라이언트를 OKhttp를 사용한다
        // .addConverterFactory(GsonConverterFactory.create()) : 통신이 완료된 후 어떤 Convert를 이용하여 데이터를 파싱 할 것인지 선언
        retrofit = new Retrofit.Builder().baseUrl(URLS).addConverterFactory(GsonConverterFactory.create(gson)).client(client).build();

        return retrofit;
    }
}
