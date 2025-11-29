package com.fahad.ashtana;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartFragment extends Fragment {

    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private List<CartItem> cartItems;
    private TextView totalPrice, emptyCartText, cartItemCount;
    private Button checkoutButton, clearCartButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        initializeViews(view);
        setupRecyclerView();
        updateCartUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateCartUI();
    }

    private void initializeViews(View view) {
        cartRecyclerView = view.findViewById(R.id.cartRecyclerView);
        totalPrice = view.findViewById(R.id.totalPrice);
        emptyCartText = view.findViewById(R.id.emptyCartText);
        cartItemCount = view.findViewById(R.id.cartItemCount);
        checkoutButton = view.findViewById(R.id.checkoutButton);
        clearCartButton = view.findViewById(R.id.clearCartButton);

        checkoutButton.setOnClickListener(v -> {
            if (CartManager.getInstance().getCartItems().isEmpty()) {
                Toast.makeText(getContext(), "Your cart is empty", Toast.LENGTH_SHORT).show();
            } else {
                double total = CartManager.getInstance().getTotalPrice();
                Toast.makeText(getContext(), "Proceeding to checkout - Total: $" +
                        String.format("%.2f", total), Toast.LENGTH_SHORT).show();
                // Implement checkout logic
            }
        });

        clearCartButton.setOnClickListener(v -> {
            CartManager.getInstance().clearCart();
            updateCartUI();
            Toast.makeText(getContext(), "Cart cleared", Toast.LENGTH_SHORT).show();
        });
    }

    private void setupRecyclerView() {
        cartItems = CartManager.getInstance().getCartItems();
        cartAdapter = new CartAdapter(cartItems, new CartAdapter.CartItemListener() {
            @Override
            public void onQuantityChanged(int position, int newQuantity) {
                CartManager.getInstance().updateQuantity(position, newQuantity);
                updateCartUI();
            }

            @Override
            public void onItemRemoved(int position) {
                CartManager.getInstance().removeFromCart(position);
                updateCartUI();
            }
        });

        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cartRecyclerView.setAdapter(cartAdapter);
    }

    private void updateCartUI() {
        cartItems = CartManager.getInstance().getCartItems();

        if (cartAdapter != null) {
            cartAdapter.updateData(cartItems);
        }

        double total = CartManager.getInstance().getTotalPrice();
        int itemCount = CartManager.getInstance().getCartItemCount();

        totalPrice.setText(String.format("$%.2f", total));
        cartItemCount.setText(itemCount + " items");

        if (cartItems.isEmpty()) {
            emptyCartText.setVisibility(View.VISIBLE);
            cartRecyclerView.setVisibility(View.GONE);
            checkoutButton.setEnabled(false);
            clearCartButton.setEnabled(false);
        } else {
            emptyCartText.setVisibility(View.GONE);
            cartRecyclerView.setVisibility(View.VISIBLE);
            checkoutButton.setEnabled(true);
            clearCartButton.setEnabled(true);
        }
    }
}