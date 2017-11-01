package com.example.vasskob.testrotation.presentation.detail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.vasskob.testrotation.R;
import com.example.vasskob.testrotation.domain.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    private List<Product> mProducts = new ArrayList<>();
    private final Context context;

    public ProductListAdapter(Context context) {
        this.context = context;
    }

    public void addItems(List<Product> productList) {
        mProducts = productList;
        notifyDataSetChanged();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        if (mProducts != null) holder.populate(mProducts.get(position));
    }

    @Override
    public int getItemCount() {
        if (mProducts != null) {
            return mProducts.size();
        } else return 0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_product_name)
        TextView productName;
        @BindView(R.id.tv_product_price)
        TextView productPrice;
        @BindView(R.id.tv_product_category)
        TextView productCategory;
        @BindView(R.id.iv_product_icon)
        ImageView productIcon;

        ProductViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        void populate(Product product) {
            productName.setText(product.getName());
            productPrice.setText(String.format(Locale.US, "Price: %d $", product.getPriceInCents() / 100));
            productCategory.setText(String.format(Locale.US, "Category: %s", product.getPrimaryCategory()));
            Glide.with(context)
                    .load(product.getImageUrl())
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).placeholder(R.drawable.ic_food))
                    .into(productIcon);
        }
    }
}
