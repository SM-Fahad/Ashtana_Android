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

    private Button loginButton, ordersButton, wishlistButton, settingsButton;
    private TextView welcomeText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        initializeViews(view);
        setupClickListeners();

        return view;
    }

    private void initializeViews(View view) {
        loginButton = view.findViewById(R.id.loginButton);
        ordersButton = view.findViewById(R.id.ordersButton);
        wishlistButton = view.findViewById(R.id.wishlistButton);
        settingsButton = view.findViewById(R.id.settingsButton);
        welcomeText = view.findViewById(R.id.welcomeText);
    }

    private void setupClickListeners() {
        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });

        ordersButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Viewing Orders", Toast.LENGTH_SHORT).show();
            // Navigate to orders activity
        });

        wishlistButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Viewing Wishlist", Toast.LENGTH_SHORT).show();
            // Navigate to wishlist
        });

        settingsButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Opening Settings", Toast.LENGTH_SHORT).show();
            // Navigate to settings
        });
    }
}