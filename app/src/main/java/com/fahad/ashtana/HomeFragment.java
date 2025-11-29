package com.fahad.ashtana;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView productsRecyclerView;
    private ProductRecyclerAdapter productAdapter;
    private List<Product> productList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home_fragment, container, false);

        initializeProducts();
        setupRecyclerView(view);

        return view;
    }

    private void initializeProducts() {
        productList = new ArrayList<>();

        // Sample products for ASHTANA
        productList.add(new Product("Huda Beauty Makeout Sesh", 51.00, 45.00, R.drawable.product1, "Professional makeup set for complete look with premium brushes and high-quality pigments"));
        productList.add(new Product("Huda Beauty Blur & Set Duo", 55.00, 49.00, R.drawable.product2, "Perfect blur and set combination for flawless skin finish"));
        productList.add(new Product("Lipstick Set", 42.00, 35.00, R.drawable.product3, "6 vibrant shades collection with long-lasting matte finish"));
        productList.add(new Product("Foundation", 65.00, 55.00, R.drawable.product4, "Full coverage liquid foundation with SPF protection"));
        productList.add(new Product("Eyeshadow Palette", 72.00, 60.00, R.drawable.product5, "12-color professional palette with matte and shimmer finishes"));
        productList.add(new Product("Makeup Brushes", 38.00, 32.00, R.drawable.product6, "8-piece brush set made with synthetic fibers"));

        Log.d("HomeFragment", "Total products: " + productList.size());
    }

    private void setupRecyclerView(View view) {
        productsRecyclerView = view.findViewById(R.id.productsGrid);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        productsRecyclerView.setLayoutManager(layoutManager);

        productAdapter = new ProductRecyclerAdapter(getContext(), productList, new ProductRecyclerAdapter.OnProductClickListener() {
            @Override
            public void onProductClick(Product product) {
                // Open ProductDetailActivity when product is clicked
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("product", product);
                startActivity(intent);

                Toast.makeText(getContext(), "Opening " + product.getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAddToCartClick(Product product) {
                // Handle add to cart click
                Toast.makeText(getContext(), product.getName() + " added to cart!", Toast.LENGTH_SHORT).show();
            }
        });

        productsRecyclerView.setAdapter(productAdapter);
    }
}