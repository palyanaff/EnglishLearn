package ru.palyanaff.englishlearn.screens.dictionary;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.List;

import ru.palyanaff.englishlearn.R;
import ru.palyanaff.englishlearn.adapter.DictionaryHeaderAdapter;
import ru.palyanaff.englishlearn.databinding.FragmentDictionaryBinding;

public class DictionaryFragment extends Fragment {

    private static final String TAG = "DictionaryFragment";
    private FragmentDictionaryBinding binding;
    private FloatingActionButton button;
    private AppCompatActivity activity;
    private NavController navController;

    public List<String> headers;

    public DictionaryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentDictionaryBinding.inflate(getLayoutInflater());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dictionary, container, false);
        workWithHeaders(view);

        setHasOptionsMenu(true);
        button = view.findViewById(R.id.add_button);
        button.setOnClickListener(v -> {
            activity = (AppCompatActivity) v.getContext();
            navController = Navigation.findNavController(activity, R.id.nav_host_fragment);

            navController.navigate(R.id.action_dictionaryFragment_to_newDictionary);

        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_dictionary_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem menuItem = menu.findItem(R.id.search_button);
        menuItem.setOnMenuItemClickListener(item -> {
            activity = (AppCompatActivity) getContext();
            navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
            navController.navigate(R.id.action_dictionaryFragment_to_translationFragment);
            return true;
        });
    }

    private void initRecyclerView(View view, List<String> headers){
        RecyclerView recyclerView = view.findViewById(R.id.dictionary_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        DictionaryHeaderAdapter dictionaryHeaderAdapter = new DictionaryHeaderAdapter(headers);
        recyclerView.setAdapter(dictionaryHeaderAdapter);
    }

    private void workWithHeaders(View view) {
        /*FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("Users");

        usersRef.child(firebaseUser.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.getValue(User.class) != null) {
                            user = new User(snapshot.getValue(User.class));
                            headers = user.getDictionaryHeaders();

                            Collections.sort(headers);
                            List<String> totalHeaders = new Datasource(getContext()).loadDictionaryHeaders();
                            totalHeaders.addAll(headers);

                            initRecyclerView(view, totalHeaders);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(),
                                "Failed to get actual data", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "Failed to get actual data");
                    }
                });*/
    }
}