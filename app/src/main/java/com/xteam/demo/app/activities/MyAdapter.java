package com.xteam.demo.app.activities;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xteam.demo.R;
import com.xteam.demo.app.domain.Product;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Product> products;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        public TextView faceTextView;
        public TextView priceTextView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            faceTextView = (TextView) cardView.findViewById(R.id.main_activity__card_view__face_text_view);
            priceTextView = (TextView) cardView.findViewById(R.id.main_activity__card_view__price_text_view);
        }
    }

    public void setNewItems(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(final List<Product> products) {
        this.products = products;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.main_activity_card_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder viewHolder = new ViewHolder(cardView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager) todo remove all comments
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.faceTextView.setText(products.get(position).getFace());
        holder.priceTextView.setText(String.valueOf(products.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

}