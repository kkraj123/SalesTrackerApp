package com.examplej.salstrackerapp.addItems.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examplej.salstrackerapp.R;
import com.examplej.salstrackerapp.addItems.modelClass.ProductItemModel;

import java.util.List;

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.ProductItemViewHolder>{

    List<ProductItemModel> productItemModelList;
    ProductItemClick productItemClick;

    public void setOnProductItemClick(ProductItemClick productItemClick){
        this.productItemClick = productItemClick;
    }

    public ProductItemAdapter(List<ProductItemModel> productItemModelList) {
        this.productItemModelList = productItemModelList;
    }

    @NonNull
    @Override
    public ProductItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout, parent, false);
        return new ProductItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductItemViewHolder holder, int position) {
          ProductItemModel productItemModel = productItemModelList.get(position);
          holder.productName.setText(productItemModel.getProductName());
          holder.itemView.setOnClickListener(v->{
              productItemClick.onCLick(productItemModel);
          });
    }

    @Override
    public int getItemCount() {
        return productItemModelList.size();
    }

    class ProductItemViewHolder extends RecyclerView.ViewHolder{
        TextView productName;
        public ProductItemViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productNameTV);
        }
    }
    public interface ProductItemClick{
        void onCLick(ProductItemModel productItemModel);
    }
}


