package com.examplej.salstrackerapp.addItems.adapter;

import static android.provider.Settings.System.getString;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examplej.salstrackerapp.R;
import com.examplej.salstrackerapp.addItems.modelClass.AddProductItemModel;

import java.util.List;

public class AddProductItemAdapter extends RecyclerView.Adapter<AddProductItemAdapter.AddProductItemViewHolder>{
    List<AddProductItemModel> addProductItemList;

    public AddProductItemAdapter(List<AddProductItemModel> addProductItemList) {
        this.addProductItemList = addProductItemList;
    }

    @NonNull
    @Override
    public AddProductItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_product_view, parent, false);
        return new AddProductItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddProductItemViewHolder holder, int position) {
         AddProductItemModel productItemModel = addProductItemList.get(position);
         holder.productNameTV.setText(productItemModel.getProductItemName());
         holder.quantityCountTV.setText(String.valueOf(productItemModel.getQuantity()));
         holder.date.setText(productItemModel.getDate());
         holder.totalAmountTV.setText(holder.itemView.getContext().getString(R.string.npr)+ productItemModel.getAmount());
         holder.customerName.setText(productItemModel.getCustomerName());
         holder.removeBtn.setOnClickListener(v -> {
             removeItem(position);
         });

    }

    private void removeItem(int position) {
        if (position >= 0 && position < addProductItemList.size()){
            addProductItemList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void addItem(AddProductItemModel productItemModel){
        addProductItemList.add(productItemModel);
        notifyItemInserted(addProductItemList.size() - 1);
    }
    @Override
    public int getItemCount() {
        return addProductItemList.size();
    }

    class AddProductItemViewHolder extends RecyclerView.ViewHolder{
        TextView productNameTV,quantityCountTV,totalAmountTV, date, customerName;
        LinearLayout removeBtn;


        public AddProductItemViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTV = itemView.findViewById(R.id.productNameTV);
            quantityCountTV = itemView.findViewById(R.id.quantityCountTV);
            totalAmountTV = itemView.findViewById(R.id.totalAmountTV);
            date = itemView.findViewById(R.id.dateTV);
            customerName = itemView.findViewById(R.id.customerName);
            removeBtn = itemView.findViewById(R.id.removeBtn);

        }
    }
}
