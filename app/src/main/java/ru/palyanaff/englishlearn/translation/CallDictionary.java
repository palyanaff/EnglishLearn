package ru.palyanaff.englishlearn.translation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.palyanaff.englishlearn.translation.models.APIResponse;

public interface CallDictionary {
    @GET("entries/en/{word}")
    Call<List<APIResponse>> callMeanings(
            @Path("word") String word
    );
}