package ru.palyanaff.englishlearn.translation;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.palyanaff.englishlearn.translation.models.APIResponse;

public class RequestManager {

    private final Context context;
    private static final String TAG = "RequestManager";
    private static final String BASE_URL = "https://api.dictionaryapi.dev/api/v2/";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getWordMeaning(OnFetchDataListener listener, String word){
        CallDictionary callDictionary = retrofit.create(CallDictionary.class);
        Call<List<APIResponse>> call = callDictionary.callMeanings(word);

        try {
            call.enqueue(new Callback<List<APIResponse>>() {
                @Override
                public void onResponse(@NonNull Call<List<APIResponse>> call, @NonNull Response<List<APIResponse>> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Response is not successful", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    listener.onFetchData(response.body().get(0), response.message());
                }

                @Override
                public void onFailure(@NonNull Call<List<APIResponse>> call, @NonNull Throwable t) {
                    listener.onError("Request failed!");
                }
            });
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context, "An error", Toast.LENGTH_SHORT).show();
        }
    }
}
