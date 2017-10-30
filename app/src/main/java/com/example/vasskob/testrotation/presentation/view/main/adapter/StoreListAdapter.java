package com.example.vasskob.testrotation.presentation.view.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vasskob.testrotation.R;
import com.example.vasskob.testrotation.presentation.model.StoreModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoreListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String COMA = ", ";
    private List<StoreModel> mStores = new ArrayList<>();
    private final onStoreClickListener listener;

    public StoreListAdapter(onStoreClickListener listener) {
        this.listener = listener;
    }

    public void addItems(List<StoreModel> stores) {
        mStores = stores;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store, parent, false);
        return new StoreViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        StoreViewHolder storeHolder = (StoreViewHolder) holder;
        if (mStores != null) {
            StoreModel store = mStores.get(position);
            storeHolder.storeName.setText(store.getName());
            storeHolder.storeLocation.setText(String.format("%s%s%s", store.getCity(), COMA, store.getAddress1()));
        }
    }

    @Override
    public int getItemCount() {
        if (mStores != null) {
            return mStores.size();
        } else return 0;
    }

    public StoreModel getItem(int position) {
        return mStores.get(position);
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
    }

    public interface onStoreClickListener {
        void onStoreClick(View view);
    }
}
