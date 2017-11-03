package com.example.vasskob.testrotation.presentation.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vasskob.testrotation.R;
import com.example.vasskob.testrotation.domain.model.Store;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoreListAdapter extends RecyclerView.Adapter<StoreListAdapter.StoreViewHolder> {

    private List<Store> mStores = new ArrayList<>();
    private final onStoreClickListener listener;

    public StoreListAdapter(onStoreClickListener listener) {
        this.listener = listener;
    }

    public void addItems(List<Store> stores) {
        mStores = stores;
        notifyDataSetChanged();
    }

    @Override
    public StoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store, parent, false);
        return new StoreViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(StoreViewHolder holder, int position) {
        if (mStores != null) holder.populate(mStores.get(position));
    }

    @Override
    public int getItemCount() {
        if (mStores != null) {
            return mStores.size();
        } else return 0;
    }

    public Store getItem(int position) {
        return mStores.get(position);
    }

    public void clearItems() {
        mStores.clear();
        notifyDataSetChanged();
    }

    class StoreViewHolder extends RecyclerView.ViewHolder {

        private final onStoreClickListener mOnStoreClickListener;

        @OnClick(R.id.cv_store)
        void onClick(View v) {
            mOnStoreClickListener.onStoreClick(v);
        }

        @BindView(R.id.tv_store_name)
        TextView storeName;
        @BindView(R.id.tv_store_location)
        TextView storeLocation;

        StoreViewHolder(View v, onStoreClickListener listener) {
            super(v);
            ButterKnife.bind(this, v);
            mOnStoreClickListener = listener;
        }

        void populate(Store store) {
            storeName.setText(store.getName());
            storeLocation.setText(String.format("%s, %s", store.getCity(), store.getAddress1()));
        }
    }

    public interface onStoreClickListener {
        void onStoreClick(View view);
    }
}
