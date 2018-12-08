package com.nancy.newapplication.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nancy.newapplication.Model.Flowers;
import com.nancy.newapplication.R;
import com.nancy.newapplication.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder> {


    private final LayoutInflater mInflater;
    private List<Flowers> mFlowerList;
    private FlowerClickListener mListener;

    public RecyclerViewAdapter(FlowerClickListener listener, LayoutInflater inflater,List<Flowers>list) {
        mListener = listener;
        mInflater = inflater;
        mFlowerList = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(mInflater.inflate(R.layout.item_row, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Flowers currFlower = mFlowerList.get(position);

        holder.mName.setText(currFlower.getName());
        holder.mPrice.setText(String.format("$%.2f", currFlower.getPrice()));

        Glide.with(holder.itemView.getContext()).load(Constants.PHOTO_URL + currFlower.getPhoto()).into(holder.mPhoto);
    }

    @Override
    public int getItemCount() {
        return mFlowerList.size();
    }

    public void addFlowers(List<Flowers> flowerResponses) {
        mFlowerList.addAll(flowerResponses);
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mPhoto;
        private TextView mName, mPrice;

        public Holder(View itemView) {
            super(itemView);
            mPhoto = (ImageView) itemView.findViewById(R.id.flowerPhoto);
            mName = (TextView) itemView.findViewById(R.id.flowerName);
            mPrice = (TextView) itemView.findViewById(R.id.flowerPrice);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(getLayoutPosition(), mFlowerList.get(getAdapterPosition()).getName());
        }
    }

    public interface FlowerClickListener {

        void onClick(int position, String name);
    }
}
