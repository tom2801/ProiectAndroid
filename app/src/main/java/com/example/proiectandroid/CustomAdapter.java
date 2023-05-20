package com.example.proiectandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ProductViewHolder> {

    private List<ProductModel> localDataSet;

    private static OnItemClickListener itemClickListener;

    public CustomAdapter(List<ProductModel> localDataSet, OnItemClickListener itemClickListener) {
        this.localDataSet = localDataSet;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false); // aici tre sa fac xml pt item product
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView price;

        private final ConstraintLayout layout;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.Product_name);
            price = itemView.findViewById(R.id.tv_price);  // de modificat astfel incat id-urile sa corespund acu componenta mea
            layout = itemView.findViewById(R.id.container);
        }

        public void bind(ProductModel item) {
            name.setText(item.getName());
            price.setText(item.getPrice());


            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(item);
                }
            });

        }

    }
}
