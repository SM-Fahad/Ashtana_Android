package com.fahad.ashtana;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private GridView productsGrid;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initializeProducts();
        setupGridView(view);

        return view;
    }

    private void initializeProducts() {
        productList = new ArrayList<>();

        // Sample products for ASHTANA
        productList.add(new Product("Huda Beauty Makeout Sesh", 51.00, 45.00, R.drawable.product1, "Professional makeup set for complete look"));
        productList.add(new Product("Huda Beauty Blur & Set Duo", 55.00, 49.00, R.drawable.product2, "Perfect blur and set combination"));
        productList.add(new Product("Lipstick Set", 42.00, 35.00, R.drawable.product3, "6 vibrant shades collection"));
        productList.add(new Product("Foundation", 65.00, 55.00, R.drawable.product4, "Full coverage liquid foundation"));
        productList.add(new Product("Eyeshadow Palette", 72.00, 60.00, R.drawable.product5, "12-color professional palette"));
        productList.add(new Product("Makeup Brushes", 38.00, 32.00, R.drawable.product6, "8-piece brush set"));
    }

    private void setupGridView(View view) {
        productsGrid = view.findViewById(R.id.productsGrid);
        productAdapter = new ProductAdapter(getContext(), productList);
        productsGrid.setAdapter(productAdapter);

        productsGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = productList.get(position);
                // Navigate to product detail
                Toast.makeText(getContext(), "Opening " + product.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}