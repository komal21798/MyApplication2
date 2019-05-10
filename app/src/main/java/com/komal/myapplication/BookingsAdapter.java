package com.komal.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.BookingsViewHolder> {
    private Context mCtx;
    private List<Bookings> bookingsList;

    public BookingsAdapter(Context mCtx, List<Bookings> bookingsList) {
        this.mCtx = mCtx;
        this.bookingsList = bookingsList;
    }

    @Override
    public BookingsAdapter.BookingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.bookings_list, null);
        return new BookingsAdapter.BookingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BookingsAdapter.BookingsViewHolder holder, int position) {
        final Bookings bookings = bookingsList.get(position);

        holder.roomTitle.setText(bookings.getRoom_title());
        holder.quantity.setText(String.valueOf(bookings.getQuantity()) + " Rooms");
        holder.totalPrice.setText("INR " + String.valueOf(bookings.getTotal()));
        holder.viewInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(mCtx, CancelActivity.class);
                myIntent.putExtra("Total", bookings.getTotal());
                mCtx.startActivity(myIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookingsList.size();
    }

    class BookingsViewHolder extends RecyclerView.ViewHolder {

        TextView roomTitle, quantity, totalPrice;
        Button viewInvoice;

        public BookingsViewHolder(View itemView) {
            super(itemView);

            roomTitle = itemView.findViewById(R.id.roomTitle);
            quantity = itemView.findViewById(R.id.quantity);
            totalPrice = itemView.findViewById(R.id.totalPrice);
            viewInvoice = itemView.findViewById(R.id.viewInvoice);
        }
    }
}
