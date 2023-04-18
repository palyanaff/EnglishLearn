package ru.palyanaff.englishlearn.translation;

import ru.palyanaff.englishlearn.translation.models.APIResponse;

public interface OnFetchDataListener {
    void onFetchData(APIResponse apiResponse, String message);
    void onError(String message);
}
