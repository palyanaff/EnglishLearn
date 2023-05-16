package ru.palyanaff.englishlearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import java.util.Objects;

/**
 * MainActivity contains all fragments of application
 */
public class MainActivity extends AppCompatActivity {
    private NavController navController; // Navigation controller helps with navigation between fragments

    /**
     * Method that starts application
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        navController = Objects.requireNonNull(navHostFragment).getNavController();
    }
}