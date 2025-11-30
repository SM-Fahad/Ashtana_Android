package com.fahad.ashtana;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {

    private List<Order> orders;

    public OrdersAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orders.get(position);

        holder.orderId.setText("Order #" + order.getOrderId());
        holder.orderDate.setText(order.getOrderDate());
        holder.orderTotal.setText(String.format("$%.2f", order.getTotalAmount()));
        holder.orderStatus.setText(order.getStatus());

        // Set status color
        switch (order.getStatus()) {
            case "Processing":
                holder.orderStatus.setTextColor(0xFFFFA000); // Orange
                break;
            case "Shipped":
                holder.orderStatus.setTextColor(0xFF2196F3); // Blue
                break;
            case "Delivered":
                holder.orderStatus.setTextColor(0xFF4CAF50); // Green
                break;
            default:
                holder.orderStatus.setTextColor(0xFF757575); // Gray
        }
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderId, orderDate, orderTotal, orderStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.orderId);
            orderDate = itemView.findViewById(R.id.orderDate);
            orderTotal = itemView.findViewById(R.id.orderTotal);
            orderStatus = itemView.findViewById(R.id.orderStatus);
        }
    }
}