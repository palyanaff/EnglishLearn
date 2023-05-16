package ru.palyanaff.englishlearn.screens.dictionary;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.palyanaff.englishlearn.adapter.MeaningAdapter;
import ru.palyanaff.englishlearn.adapter.PhoneticAdapter;
import ru.palyanaff.englishlearn.databinding.FragmentTranslationBinding;
import ru.palyanaff.englishlearn.translation.OnFetchDataListener;
import ru.palyanaff.englishlearn.translation.RequestManager;
import ru.palyanaff.englishlearn.translation.models.APIResponse;

public class TranslationFragment extends Fragment {
    private static final String TAG = "TranslationFragment";
    private FragmentTranslationBinding binding;
    private SearchView searchView;
    private TextView textView_translation_word;
    private RecyclerView recycler_view_translation_phonetics, recycler_view_translation_meaning;
    private ProgressDialog progressDialog;
    PhoneticAdapter phoneticAdapter;
    MeaningAdapter meaningAdapter;


    public TranslationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentTranslationBinding.inflate(getLayoutInflater());

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //searchView = binding.searchView;
        textView_translation_word = binding.textViewTranslationWord;
        recycler_view_translation_meaning = binding.recyclerViewTranslationMeaning;
        recycler_view_translation_phonetics = binding.recyclerViewTranslationPhonetics;

        progressDialog = new ProgressDialog(getContext());

        progressDialog.setTitle("Loading...");
        progressDialog.show();
        RequestManager manager = new RequestManager(getContext());
        manager.getWordMeaning(listener, "hello");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("Fetching response for " + query);
                progressDialog.show();
                RequestManager manager = new RequestManager(getContext());
                manager.getWordMeaning(listener, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return binding.getRoot();
    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(APIResponse apiResponse, String message) {
            progressDialog.dismiss();
            if (apiResponse == null){
                Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
                return;
            }
            showData(apiResponse);
        }

        @Override
        public void onError(String message) {
            progressDialog.dismiss();
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    };

    private void showData(APIResponse apiResponse) {
        String textWord = textView_translation_word.getText().toString().split(" ")[0] + " ";
        textView_translation_word.setText(textWord + apiResponse.getWord());

        recycler_view_translation_phonetics.setLayoutManager(new LinearLayoutManager(getContext()));
        phoneticAdapter = new PhoneticAdapter(apiResponse.getPhonetics());
        recycler_view_translation_phonetics.setAdapter(phoneticAdapter);

        recycler_view_translation_meaning.setLayoutManager(new LinearLayoutManager(getContext()));
        meaningAdapter = new MeaningAdapter(getContext(), apiResponse.getMeanings());
        recycler_view_translation_meaning.setAdapter(meaningAdapter);

    }


}
