package com.examplej.salstrackerapp.dailyDataDetail.adapter;

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

public class TodayDataAdapter extends RecyclerView.Adapter<TodayDataAdapter.TodayDataViewHolder> {
    List<AddProductItemModel> addProductItemModelList;

    public TodayDataAdapter(List<AddProductItemModel> addProductItemModelList) {
        this.addProductItemModelList = addProductItemModelList;
    }

    @NonNull
    @Override
    public TodayDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.today_sale_item_view, parent, false);
        return new TodayDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodayDataViewHolder holder, int position) {
        AddProductItemModel addProductItemModel = addProductItemModelList.get(position);
        if (position == 0) {
            holder.todayDate.setText(addProductItemModel.getDate());
            holder.topView.setVisibility(View.VISIBLE);
        } else {
            holder.todayDate.setVisibility(View.GONE);
            holder.topView.setVisibility(View.GONE);

        }
        holder.customerName.setText(addProductItemModel.getCustomerName());
        holder.productName.setText(addProductItemModel.getProductItemName());
        holder.quantity.setText(String.valueOf(addProductItemModel.getQuantity()));
        holder.amount.setText(holder.itemView.getContext().getString(R.string.npr) + addProductItemModel.getAmount());
    }

    @Override
    public int getItemCount() {
        return addProductItemModelList.size();
    }

    class TodayDataViewHolder extends RecyclerView.ViewHolder {
        TextView todayDate, customerName, productName, quantity, amount;
        LinearLayout topView;

        public TodayDataViewHolder(@NonNull View itemView) {
            super(itemView);
            todayDate = itemView.findViewById(R.id.currentDateTV);
            productName = itemView.findViewById(R.id.productTV);
            quantity = itemView.findViewById(R.id.quantityTV);
            amount = itemView.findViewById(R.id.amountTV);
            customerName = itemView.findViewById(R.id.customerNameTV);
            topView = itemView.findViewById(R.id.topView);
        }
    }
}
