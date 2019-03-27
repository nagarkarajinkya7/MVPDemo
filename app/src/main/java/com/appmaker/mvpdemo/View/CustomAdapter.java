package com.appmaker.mvpdemo.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appmaker.mvpdemo.Model.DataModel;
import com.appmaker.mvpdemo.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context mContext;
    private List<DataModel> data;
    DataModel currentData;
    private LayoutInflater mInflater;

    public CustomAdapter(Context mContext, List<DataModel> data) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        this.data = data;
    }


    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View mView = mInflater.inflate(R.layout.row_view, viewGroup, false);
        ViewHolder mHolder = new ViewHolder(mView);

        return mHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder viewHolder, int i) {

        ViewHolder mHolder = (ViewHolder) viewHolder;
        currentData = data.get(i);
        mHolder.tv_fName.setText(currentData.getfName());
        mHolder.tv_size.setText("Size : " + currentData.getSize());
        mHolder.tv_price.setText("Category : " + currentData.getCategory());
        //mHolder.tv_type.setText("Rs. : " + currentData.getPrice());

        Glide.with(mContext).load("http://192.168.1.7/test/images/" + currentData.getfImage())
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(mHolder.iv_fImage);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_fName, tv_size, tv_price, tv_type;
        ImageView iv_fImage;

        public ViewHolder(View viewItem) {
            super(viewItem);

            tv_fName = viewItem.findViewById(R.id.textFishName);
            tv_size = viewItem.findViewById(R.id.textSize);
            tv_price = viewItem.findViewById(R.id.textPrice);
            tv_type = viewItem.findViewById(R.id.textType);

            iv_fImage = viewItem.findViewById(R.id.ivFish);
        }
    }
}
