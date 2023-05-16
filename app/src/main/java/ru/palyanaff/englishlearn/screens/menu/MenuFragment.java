package ru.palyanaff.englishlearn.screens.menu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import ru.palyanaff.englishlearn.R;
import ru.palyanaff.englishlearn.data.User;
import ru.palyanaff.englishlearn.databinding.FragmentMenuBinding;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    private static final String TAG = "MenuFragment";

    private User user;

    private FragmentMenuBinding binding;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentMenuBinding.inflate(getLayoutInflater());

        /*mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("Users");
        this.workWithUser();*/
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /*verifyButtonController();
        binding.verifyEmailButton.setOnClickListener(verifyEmailOnClick());*/

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //binding.logOutButton.setOnClickListener(logOut());
    }

   /* private void workWithUser() {
        usersRef.child(firebaseUser.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.getValue(User.class) != null) {
                            user = new User(snapshot.getValue(User.class));
                        }

                        setProfileData();
                        setProfileStats();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e(TAG, "Failed to get current user data", error.toException());
                    }
                });
    }*/

    private void setProfileStats() {
        if (user != null) {
            binding.usernameEducatedWordsCounter.setText(
                    String.valueOf(user.getEducatedWords().size()));
            binding.usernameCompletedLevelsCounter.setText(
                    String.valueOf(user.getCompleteLevels().size()));
            binding.usernameInputDictionaries.setText(
                    String.valueOf(user.getDictionaryHeaders().size()));
            binding.usernameInputTotalWords.setText(
                    String.valueOf(user.getTotalWords()));
        }
    }

    private void setProfileData() {
        if (user != null) {
            binding.usernameInput.setText(user.getName());
            binding.emailInput.setText(user.getEmail());
        }
    }

    /*@NonNull
    private View.OnClickListener verifyEmailOnClick() {
        return (View v) -> firebaseUser.sendEmailVerification()
                .addOnCompleteListener(sendOnComplete());
    }*/

    @NonNull
    private OnCompleteListener<Void> sendOnComplete() {
        return (Task<Void> sendTask) -> {
            String toastSendText;
            if (sendTask.isSuccessful()) {
                toastSendText = "Check your e-mail to verify your account";
            } else {
                toastSendText = "Failed to send verify message on your e-mail. " +
                        "Please try again later";
            }

            Toast.makeText(getContext(), toastSendText, Toast.LENGTH_LONG).show();
        };
    }

    /*private void verifyButtonController() {
        if (firebaseUser.isEmailVerified()) {
            binding.verifyEmailButton.setBackgroundTintList(
                    AppCompatResources.getColorStateList(getContext(), R.color.green));
            binding.verifyEmailButton.setText(getResources().getString(R.string.email_verified));
            binding.verifyEmailButton.setEnabled(false);
        }
    }

    private View.OnClickListener logOut() {
        return (View v) -> {
            mAuth.signOut();
            Toast.makeText(MenuFragment.this.getActivity(),
                    "Successfully logged out", Toast.LENGTH_LONG).show();
            startActivity(new Intent(MenuFragment.this.getActivity(), LoginActivity.class));
        };
    }*/
}