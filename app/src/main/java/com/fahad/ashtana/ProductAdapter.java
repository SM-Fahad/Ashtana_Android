package com.fahad.ashtana;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.content.Context;
import android.widget.Toast;
import java.util.List;

public class ProductAdapter extends BaseAdapter {

    private Context context;
    private List<Product> productList;
    private LayoutInflater inflater;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_product_item, parent, false);
            holder = new ViewHolder();
            holder.productImage = convertView.findViewById(R.id.productImage);
            holder.productName = convertView.findViewById(R.id.productName);
            holder.originalPrice = convertView.findViewById(R.id.originalPrice);
            holder.discountedPrice = convertView.findViewById(R.id.discountedPrice);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Product product = productList.get(position);

        holder.productImage.setImageResource(product.getImageResource());
        holder.productName.setText(product.getName());
        holder.originalPrice.setText(String.format("$%.2f", product.getOriginalPrice()));
        holder.discountedPrice.setText(String.format("$%.2f", product.getDiscountedPrice()));

        // Add strikethrough to original price
        holder.originalPrice.setPaintFlags(holder.originalPrice.getPaintFlags() | android.graphics.Paint.STRIKE_THRU_TEXT_FLAG);

        return convertView;
    }

    private static class ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView originalPrice;
        TextView discountedPrice;
    }
}