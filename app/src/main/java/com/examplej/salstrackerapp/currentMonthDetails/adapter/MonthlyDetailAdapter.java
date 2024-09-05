package com.examplej.salstrackerapp.currentMonthDetails.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examplej.salstrackerapp.R;
import com.examplej.salstrackerapp.addItems.modelClass.AddProductItemModel;
import com.examplej.salstrackerapp.dailyDataDetail.adapter.TodayDataAdapter;

import java.util.List;

public class MonthlyDetailAdapter extends RecyclerView.Adapter<MonthlyDetailAdapter.MonthlyDetailViewHolder> {
    List<AddProductItemModel> addProductItemModelList;

    public MonthlyDetailAdapter(List<AddProductItemModel> addProductItemModelList) {
        this.addProductItemModelList = addProductItemModelList;
    }

    @NonNull
    @Override
    public MonthlyDetailAdapter.MonthlyDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.today_sale_item_view, parent, false);
        return new MonthlyDetailAdapter.MonthlyDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonthlyDetailAdapter.MonthlyDetailViewHolder holder, int position) {
        AddProductItemModel addProductItemModel = addProductItemModelList.get(position);
        holder.todayDate.setText(addProductItemModel.getDate());
        holder.customerName.setText(addProductItemModel.getCustomerName());
        holder.productName.setText(addProductItemModel.getProductItemName());
        holder.quantity.setText(String.valueOf(addProductItemModel.getQuantity()));
        holder.amount.setText(holder.itemView.getContext().getString(R.string.npr) + addProductItemModel.getAmount());
    }

    @Override
    public int getItemCount() {
        return addProductItemModelList.size();
    }

    class MonthlyDetailViewHolder extends RecyclerView.ViewHolder {
        TextView todayDate, customerName, productName, quantity, amount;
        LinearLayout topView;

        public MonthlyDetailViewHolder(@NonNull View itemView) {
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
