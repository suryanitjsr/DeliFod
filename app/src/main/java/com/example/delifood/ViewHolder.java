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
    }

    public void setDetails(Context cxt, String title, String image, String address, String description){
        TextView mTitleRes = mView.findViewById(R.id.resName);
        TextView mAddressRes = mView.findViewById(R.id.resAd);
        TextView mDescriptionRes = mView.findViewById(R.id.resDesc);
        ImageView mImageRes = mView.findViewById(R.id.resImage);

        mTitleRes.setText(title);
        mAddressRes.setText(address);
        mDescriptionRes.setText(description);
        Picasso.get().load(image).into(mImageRes);
    }
}
