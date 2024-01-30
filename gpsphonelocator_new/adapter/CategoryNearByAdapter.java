package com.app.gpsphonelocator_new.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.gpsphonelocator_new.R;
import com.app.gpsphonelocator_new.databinding.ItemCategoryNearByBinding;
import com.app.gpsphonelocator_new.extensions.AppExtensionKt;
import com.app.gpsphonelocator_new.phone.callback.CallBack;
import com.app.gpsphonelocator_new.phone.model.CategoryNearBy;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.internal.Intrinsics;


public final class CategoryNearByAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public CallBack.CallBackCategory callBackCategory;

    public List<CategoryNearBy> listCategory = new ArrayList();


    public final void callBackStyleMap(CallBack.CallBackCategory callBackCategory2) {
        Intrinsics.checkNotNullParameter(callBackCategory2, "callBackCategory");
        this.callBackCategory = callBackCategory2;
    }

    public final void setData(List<CategoryNearBy> list) {
        Intrinsics.checkNotNullParameter(list, "listCategory");
        this.listCategory = list;

    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        ItemCategoryNearByBinding inflate = ItemCategoryNearByBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new ViewHolder(this, inflate);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        ((ViewHolder) viewHolder).bindData(i);

    }

    public int getItemCount() {
        return this.listCategory.size();
    }


    public   final class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemCategoryNearByBinding binding;
        final CategoryNearByAdapter this$0;


        public ViewHolder(CategoryNearByAdapter categoryNearByAdapter, ItemCategoryNearByBinding itemCategoryNearByBinding) {
            super(itemCategoryNearByBinding.getRoot());
            Intrinsics.checkNotNullParameter(itemCategoryNearByBinding, "binding");
            this.this$0 = categoryNearByAdapter;
            this.binding = itemCategoryNearByBinding;
        }

        public final void bindData(int i) {
            this.binding.tvMap.setText(this.this$0.listCategory.get(i).getTitle());
            Glide.with(this.binding.getRoot().getContext()).load(((CategoryNearBy) this.this$0.listCategory.get(i)).getImage()).into(this.binding.ivMap);
            this.binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intrinsics.checkNotNullParameter(this, "this$0");
                    CallBack.CallBackCategory access$getCallBackCategory$p = this$0.callBackCategory;
                    if (access$getCallBackCategory$p == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("callBackCategory");
                        access$getCallBackCategory$p = null;
                    }
                    access$getCallBackCategory$p.selectCategory(((CategoryNearBy) this$0.listCategory.get(i)).getTitle());
                }
            });
        }


    }
}
