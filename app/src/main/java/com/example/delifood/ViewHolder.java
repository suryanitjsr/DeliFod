package com.example.delifood;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        mView=itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        });
    }

    public void setDetails(Context cxt, String title, String image, String address, String description){
        TextView mTitleRes = mView.findViewById(R.id.resName);
        TextView mAddressRes = mView.findViewById(R.id.resAd);
        TextView mImgUrl = mView.findViewById(R.id.resImgUrl);
        TextView mDescriptionRes = mView.findViewById(R.id.resDesc);
        ImageView mImageRes = mView.findViewById(R.id.resImage);

        mTitleRes.setText(title);
        mAddressRes.setText(address);
        mDescriptionRes.setText(description);
        mImgUrl.setText(image);
        Picasso.get().load(image).into(mImageRes);
    }

    private ViewHolder.ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view,int position);
    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener=clickListener;
    }
}