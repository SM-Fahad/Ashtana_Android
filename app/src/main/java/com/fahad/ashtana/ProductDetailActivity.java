package com.fahad.ashtana;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView productImage;
    private TextView productName, productDescription, originalPrice, discountedPrice;
    private Button addToCartButton, buyNowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        initializeViews();
        setupProductData();
        setupClickListeners();
    }

    private void initializeViews() {
        productImage = findViewById(R.id.productImage);
        productName = findViewById(R.id.productName);
        productDescription = findViewById(R.id.productDescription);
        originalPrice = findViewById(R.id.originalPrice);
        discountedPrice = findViewById(R.id.discountedPrice);
        addToCartButton = findViewById(R.id.addToCartButton);
        buyNowButton = findViewById(R.id.buyNowButton);
    }



    private void setupProductData() {
        // Get product data from intent
        Product product = getIntent().getParcelableExtra("product");

        if (product != null) {
            productImage.setImageResource(product.getImageResource());
            productName.setText(product.getName());
            productDescription.setText(product.getDescription());
            originalPrice.setText(String.format("$%.2f", product.getOriginalPrice()));
            discountedPrice.setText(String.format("$%.2f", product.getDiscountedPrice()));

            // Add strikethrough to original price
            originalPrice.setPaintFlags(originalPrice.getPaintFlags() | android.graphics.Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            // Fallback data if no product is passed
            productImage.setImageResource(R.drawable.category_24px);
            productName.setText("Product Name");
            productDescription.setText("Product description goes here");
            originalPrice.setText("$0.00");
            discountedPrice.setText("$0.00");
        }
    }

//    private void setupClickListeners() {
//        addToCartButton.setOnClickListener(v -> {
//            Toast.makeText(this, "Product added to cart!", Toast.LENGTH_SHORT).show();
//            // Implement add to cart logic
//        });
//
//        buyNowButton.setOnClickListener(v -> {
//            Toast.makeText(this, "Proceeding to checkout!", Toast.LENGTH_SHORT).show();
//            // Implement buy now logic
//        });
//    }

    // Add this to setupClickListeners method:
    private void setupClickListeners() {
        addToCartButton.setOnClickListener(v -> {
            Product product = getIntent().getParcelableExtra("product");
            if (product != null) {
                CartManager.getInstance().addToCart(product);
                Toast.makeText(this, product.getName() + " added to cart!", Toast.LENGTH_SHORT).show();
            }
        });

        buyNowButton.setOnClickListener(v -> {
            Product product = getIntent().getParcelableExtra("product");
            if (product != null) {
                CartManager.getInstance().addToCart(product);
                Toast.makeText(this, "Product added to cart! Proceeding to checkout.", Toast.LENGTH_SHORT).show();
                // Navigate to checkout
            }
        });
    }
}