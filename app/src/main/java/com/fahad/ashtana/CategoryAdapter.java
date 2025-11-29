package com.fahad.ashtana;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    private Context context;
    private List<Category> categoryList;
    private LayoutInflater inflater;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override public int getCount() { return categoryList.size(); }
    @Override public Object getItem(int position) { return categoryList.get(position); }
    @Override public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_category, parent, false);
        }

        ImageView categoryImage = convertView.findViewById(R.id.categoryImage);
        TextView categoryName = convertView.findViewById(R.id.categoryName);

        Category category = categoryList.get(position);
        categoryImage.setImageResource(category.getImageResource());
        categoryName.setText(category.getName());

        return convertView;
    }
}