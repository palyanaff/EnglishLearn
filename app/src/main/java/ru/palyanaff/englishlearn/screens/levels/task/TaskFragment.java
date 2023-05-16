package ru.palyanaff.englishlearn.screens.levels.task;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
/*import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;*/

import java.util.Locale;

import ru.palyanaff.englishlearn.R;
import ru.palyanaff.englishlearn.data.User;
import ru.palyanaff.englishlearn.databinding.FragmentTaskBinding;

public class TaskFragment extends Fragment {
    private static final String TAG = "TaskFragment";

    private FragmentTaskBinding binding;

    /*private FirebaseUser firebaseUser;
    private DatabaseReference usersRef;*/

    private User user;

    private String levelId;
    private String taskHeaderText;
    private String taskTextText;
    private String taskAnswer;

    public TaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentTaskBinding.inflate(getLayoutInflater());

        /*FirebaseAuth auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("Users");

        setUserByUserFromDB();*/
    }

    /*private void setUserByUserFromDB() {
        usersRef.child(firebaseUser.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.getValue(User.class) != null) {
                            user = new User(snapshot.getValue(User.class));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(),
                                "Failed to get actual data", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "Failed to get actual data");
                    }
                });
    }*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        levelId = TaskFragmentArgs.fromBundle(getArguments()).getLevelId();
        taskHeaderText = TaskFragmentArgs.fromBundle(getArguments()).getTask()[0];
        taskTextText = TaskFragmentArgs.fromBundle(getArguments()).getTask()[1];
        taskAnswer = TaskFragmentArgs.fromBundle(getArguments()).getTask()[2];
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.taskHeader.setText(taskHeaderText);
        binding.taskText.setText(taskTextText);
        binding.taskCheckButton.setOnClickListener(onSubmitWord());
        binding.taskHintButton.setOnClickListener(onHintWord());
    }

    private View.OnClickListener onSubmitWord(){
        return v -> {
            String playerWord = binding.textInputEditText.getText()
                    .toString().toLowerCase(Locale.ROOT).trim();
            if (playerWord.equals(taskAnswer)){
                setErrorTextField(false);
                Toast.makeText(getContext(), "Correct", Toast.LENGTH_LONG).show();
                user.addCompleteLevel(levelId);

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);

                navController.navigate(R.id.action_taskFragment_to_congratulationFragment);
            } else {
                setErrorTextField(true);
            }
        };
    }

    private View.OnClickListener onHintWord(){
        return v -> {
            String playerWord = binding.textInputEditText.getText()
                    .toString().toLowerCase(Locale.ROOT).trim();
            String hintWord = getHintWord(playerWord);

            binding.taskEditText.setErrorEnabled(false);
            binding.textInputEditText.setText(hintWord);
        };
    }

    private String getHintWord(String playerWord){
        int i = 0;
        String hintWord = "";
        if (!playerWord.isEmpty()) {
            for (i = 0; i < playerWord.length(); i++) {
                if (playerWord.charAt(i) == taskAnswer.charAt(i)) {
                    hintWord += taskAnswer.charAt(i);
                } else {
                    hintWord += taskAnswer.charAt(i);
                    return hintWord;
                }
            }

            if (i < taskAnswer.length()){
                hintWord += taskAnswer.charAt(i);
            }
        }
        else {
            hintWord += taskAnswer.charAt(i);
        }
        return hintWord;
    }

    private void setErrorTextField(Boolean error) {
        if (error) {
            binding.taskEditText.setErrorEnabled(true);
            binding.taskEditText.setError("Try again");
        } else {
            binding.taskEditText.setErrorEnabled(false);
            binding.textInputEditText.setText(null);
        }
    }

    /*@Override
    public void onPause() {
        super.onPause();

        if (user != null) {
            usersRef.child(firebaseUser.getUid())
                    .setValue(user).addOnCompleteListener(setValueOnComplete());
        } else {
            Log.d(TAG, "User is null on saving user in DB");
        }
    }*/

    @NonNull
    private OnCompleteListener<Void> setValueOnComplete() {
        return setValueTask -> {
            if (!setValueTask.isSuccessful()) {
                Toast.makeText(getContext(), "Failed to save word", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Failed on saving word");
            }
        };
    }
}
