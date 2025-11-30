package com.fahad.ashtana;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputEditText;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CheckoutActivity extends AppCompatActivity {

    private RecyclerView checkoutRecyclerView;
    private CheckoutAdapter checkoutAdapter;
    private TextView totalPrice, subtotal, shippingFee, finalTotal;
    private TextInputEditText nameEditText, emailEditText, phoneEditText, addressEditText, cityEditText, zipEditText;
    private Button placeOrderButton;
    private List<CartItem> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        initializeViews();
        setupCheckoutList();
        calculateTotals();
        setupPlaceOrderButton();
    }

    private void initializeViews() {
        checkoutRecyclerView = findViewById(R.id.checkoutRecyclerView);
        totalPrice = findViewById(R.id.totalPrice);
        subtotal = findViewById(R.id.subtotal);
        shippingFee = findViewById(R.id.shippingFee);
        finalTotal = findViewById(R.id.finalTotal);

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        addressEditText = findViewById(R.id.addressEditText);
        cityEditText = findViewById(R.id.cityEditText);
        zipEditText = findViewById(R.id.zipEditText);

        placeOrderButton = findViewById(R.id.placeOrderButton);

        // Set demo user data
        nameEditText.setText("Mohammad Fahad");
        emailEditText.setText("fahad@ashtana.com");
        phoneEditText.setText("+1234567890");
        addressEditText.setText("123 Beauty Street");
        cityEditText.setText("Riyadh");
        zipEditText.setText("12345");
    }

    private void setupCheckoutList() {
        cartItems = CartManager.getInstance().getCartItems();
        checkoutAdapter = new CheckoutAdapter(cartItems);
        checkoutRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        checkoutRecyclerView.setAdapter(checkoutAdapter);
    }

    private void calculateTotals() {
        double subtotalValue = CartManager.getInstance().getTotalPrice();
        double shippingValue = 5.99; // Fixed shipping fee
        double finalTotalValue = subtotalValue + shippingValue;

        subtotal.setText(String.format("$%.2f", subtotalValue));
        shippingFee.setText(String.format("$%.2f", shippingValue));
        finalTotal.setText(String.format("$%.2f", finalTotalValue));
        totalPrice.setText(String.format("$%.2f", finalTotalValue));
    }

    private void setupPlaceOrderButton() {
        placeOrderButton.setOnClickListener(v -> {
            if (validateForm()) {
                // Create order
                Order order = createOrder();

                // Add to order history
                OrderManager.getInstance().addOrder(order);

                // Clear cart
                CartManager.getInstance().clearCart();

                Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_LONG).show();
                finish(); // Go back to main activity
            }
        });
    }

    private boolean validateForm() {
        if (nameEditText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (emailEditText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (phoneEditText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (addressEditText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter your address", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private Order createOrder() {
        String orderId = "ORD" + new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
        String orderDate = new SimpleDateFormat("MMM dd, yyyy - HH:mm", Locale.getDefault()).format(new Date());
        double totalAmount = Double.parseDouble(finalTotal.getText().toString().replace("$", ""));

        return new Order(orderId, orderDate, totalAmount, cartItems, "Processing");
    }
}