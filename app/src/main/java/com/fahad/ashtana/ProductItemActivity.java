package com.fahad.ashtana;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProductItemActivity extends AppCompatActivity {

    private ImageView productImage;
    private TextView productName, originalPrice, discountedPrice, productDescription;
    private Button addToCartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_item);

        initializeViews();
        setupProductData();
    }

    private void initializeViews() {
        productImage = findViewById(R.id.productImage);
        productName = findViewById(R.id.productName);
        originalPrice = findViewById(R.id.originalPrice);
        discountedPrice = findViewById(R.id.discountedPrice);
        addToCartButton = findViewById(R.id.addToCartButton);

        // Add click listener for add to cart button
        addToCartButton.setOnClickListener(v -> {
            Toast.makeText(this, "Product added to cart!", Toast.LENGTH_SHORT).show();
            // Implement add to cart logic
        });
    }

    private void setupProductData() {
        // Get product data from intent
        // For now, using sample data
        productImage.setImageResource(R.drawable.product1);
        productName.setText("Huda Beauty Makeout Sesh");
        originalPrice.setText("$51.00");
        discountedPrice.setText("$45.00");

        // Add strikethrough to original price
        originalPrice.setPaintFlags(originalPrice.getPaintFlags() | android.graphics.Paint.STRIKE_THRU_TEXT_FLAG);
    }
}