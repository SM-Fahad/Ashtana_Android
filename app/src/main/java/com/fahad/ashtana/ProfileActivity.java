package com.fahad.ashtana;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private TextView userName, userEmail, joinDate;
    private RecyclerView ordersRecyclerView;
    private OrdersAdapter ordersAdapter;
    private List<Order> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initializeViews();
        setupUserInfo();
        setupOrdersList();
    }

    private void initializeViews() {
        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);
        joinDate = findViewById(R.id.joinDate);
        ordersRecyclerView = findViewById(R.id.ordersRecyclerView);
    }

    private void setupUserInfo() {
        userName.setText("Mohammad Fahad");
        userEmail.setText("fahad@ashtana.com");
        joinDate.setText("Member since January 2024");
    }

    private void setupOrdersList() {
        orders = OrderManager.getInstance().getOrders();
        ordersAdapter = new OrdersAdapter(orders);
        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ordersRecyclerView.setAdapter(ordersAdapter);

        // Show empty state if no orders
        TextView emptyOrders = findViewById(R.id.emptyOrders);
        if (orders.isEmpty()) {
            emptyOrders.setVisibility(TextView.VISIBLE);
            ordersRecyclerView.setVisibility(TextView.GONE);
        } else {
            emptyOrders.setVisibility(TextView.GONE);
            ordersRecyclerView.setVisibility(TextView.VISIBLE);
        }
    }
}