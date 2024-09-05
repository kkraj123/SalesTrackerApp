package com.examplej.salstrackerapp.addItems.customer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examplej.salstrackerapp.R;
import com.examplej.salstrackerapp.addItems.customer.interfaces.ClickListener;
import com.examplej.salstrackerapp.addItems.customer.viewmodel.Customer;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {
    List<Customer> customerList;

    ClickListener clickListener;

    public CustomerAdapter(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public void setOnClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_row, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        Customer customer = customerList.get(position);
        holder.customerNameTV.setText(customer.getName());
        holder.itemView.setOnClickListener(v -> clickListener.clickListener(position));
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public void updateCustomer(List<Customer> customers) {
        this.customerList = customers;
        notifyDataSetChanged();
    }

    class CustomerViewHolder extends RecyclerView.ViewHolder {
        TextView customerNameTV;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            customerNameTV = itemView.findViewById(R.id.nameTV);

        }
    }


}
