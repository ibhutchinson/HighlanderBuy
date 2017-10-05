package com.example.isaachutchinson1.highlanderbuy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by isaachutchinson1 on 10/1/17.
 * Source: https://www.youtube.com/watch?v=5T144CbTwjc&index=2&list=PLk7v1Z2rk4hjHrGKo9GqOtLs1e2bglHHA
 */

public class ListingAdaptor extends RecyclerView.Adapter<ListingAdaptor.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public ListingAdaptor(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);

        holder.textViewHead.setText(listItem.getHead());
        holder.textViewDesc.setText(listItem.getDesc());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewHead;
        public TextView textViewDesc;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
        }
    }
}
