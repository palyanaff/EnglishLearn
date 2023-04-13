package ru.palyanaff.englishlearn.screens.runner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Locale;
import java.util.Objects;

import ru.palyanaff.englishlearn.databinding.FragmentRunnerBinding;

public class RunnerFragment extends Fragment {
    private static final String TAG = "RunnerFragment";

    private FragmentRunnerBinding binding;

    public RunnerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentRunnerBinding.inflate(getLayoutInflater());

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.runnerCheckButton.setOnClickListener(onSubmitWord());
        //binding.runnerSkipButton.setOnClickListener(onSkipWord());
    }

    private View.OnClickListener onSubmitWord(){
        return v -> {
            String playerWord = Objects.requireNonNull(binding.runnerInputEditText.getText())
                    .toString().toLowerCase(Locale.ROOT).trim();
            /*if (playerWord.equals(viewModel.getAnswerWord().getValue().toLowerCase(Locale.ROOT))){

                viewModel.getNextWord();
                binding.runnerHeader.setText(viewModel.getCurrentWord().getValue());
                binding.progressBar.setProgress(viewModel.getWordCounter().getValue());
                user.addEducatedWord(viewModel.getWord());
                setErrorTextField(false);
            } else {
                setErrorTextField(true);
            }*/
        };
    }

/*    private View.OnClickListener onSkipWord(){
        return v -> {
            viewModel.getSkipWord();
            binding.runnerHeader.setText(viewModel.getCurrentWord().getValue());
            binding.progressBar.setProgress(viewModel.getWordCounter().getValue());

            binding.runnerEditText.setErrorEnabled(false);
            binding.runnerInputEditText.setText(null);
        };
    }*/
/*
    private void setErrorTextField(Boolean error) {
        if (error) {
            binding.runnerEditText.setErrorEnabled(true);
            binding.runnerEditText.setError("Try again");
        } else {
            binding.runnerEditText.setErrorEnabled(false);
            binding.runnerInputEditText.setText(null);
        }
    }*/

/*    @Override
    public void onPause() {
        super.onPause();

        *//*usersRef.child(userID).setValue(user)
                .addOnCompleteListener(setValueOnComplete());*//*
    }

    @NonNull
    private OnCompleteListener<Void> setValueOnComplete() {
        return setValueTask -> {
            if (!setValueTask.isSuccessful()) {
                Toast.makeText(getContext(), "Failed to save word", Toast.LENGTH_SHORT).show();
            }
        };
    }*/
}
