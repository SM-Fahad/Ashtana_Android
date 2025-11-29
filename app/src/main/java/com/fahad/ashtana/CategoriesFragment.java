package com.fahad.ashtana;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;

public class CategoriesFragment extends Fragment {

    private GridView categoriesGrid;
    private List<Category> categoryList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        initializeCategories();
        setupCategoriesGrid(view);

        return view;
    }

    private void initializeCategories() {
        categoryList = new ArrayList<>();
        categoryList.add(new Category("Makeup", R.drawable.product1));
        categoryList.add(new Category("Skincare", R.drawable.product2));
        categoryList.add(new Category("Haircare", R.drawable.product3));
        categoryList.add(new Category("Fragrance", R.drawable.product4));
        categoryList.add(new Category("Tools", R.drawable.product5));
        categoryList.add(new Category("Brushes", R.drawable.product6));
    }

    private void setupCategoriesGrid(View view) {
        categoriesGrid = view.findViewById(R.id.categoriesGrid);
        CategoryAdapter adapter = new CategoryAdapter(getContext(), categoryList);
        categoriesGrid.setAdapter(adapter);

        categoriesGrid.setOnItemClickListener((parent, view1, position, id) -> {
            Category category = categoryList.get(position);
            Toast.makeText(getContext(), "Opening " + category.getName(), Toast.LENGTH_SHORT).show();
            // Navigate to category products
        });
    }
}