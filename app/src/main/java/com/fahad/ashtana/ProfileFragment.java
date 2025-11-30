package com.fahad.ashtana;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private Button logoutButton, ordersButton, wishlistButton, settingsButton;
    private TextView welcomeText, userName, userEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        initializeViews(view);
        setupUserInfo();
        setupClickListeners();

        return view;
    }

    private void initializeViews(View view) {
        logoutButton = view.findViewById(R.id.logoutButton);
        ordersButton = view.findViewById(R.id.ordersButton);
        wishlistButton = view.findViewById(R.id.wishlistButton);
        settingsButton = view.findViewById(R.id.settingsButton);
        welcomeText = view.findViewById(R.id.welcomeText);
        userName = view.findViewById(R.id.userName);
        userEmail = view.findViewById(R.id.userEmail);
    }

    private void setupUserInfo() {
        // Set user information
        userName.setText("Mohammad Fahad");
        userEmail.setText("fahad@ashtana.com");
        welcomeText.setText("Welcome back!");
    }

    private void setupClickListeners() {
        logoutButton.setOnClickListener(v -> {
            // Show logout confirmation
            Toast.makeText(getContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();

            // Navigate to LoginActivity
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            if (getActivity() != null) {
                getActivity().finish();
            }
        });

        ordersButton.setOnClickListener(v -> {
            // Navigate to ProfileActivity to view orders
            Intent intent = new Intent(getActivity(), ProfileActivity.class);
            startActivity(intent);
        });

        wishlistButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Viewing Wishlist", Toast.LENGTH_SHORT).show();
            // Navigate to wishlist (you can create this later)
        });

        settingsButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Opening Settings", Toast.LENGTH_SHORT).show();
            // Navigate to settings (you can create this later)
        });
    }
}