package com.example.delifood;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class resItemActivity extends AppCompatActivity {

    private RecyclerView mItemList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_item);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Items List");
        String headerText=getIntent().getStringExtra("title");
        String imggUrl = getIntent().getStringExtra("image");

        TextView hText = findViewById(R.id.headerText);
        hText.setText(headerText);

        ImageView hImage = findViewById(R.id.headerImage);
        Picasso.get().load(imggUrl).into(hImage);
//        Toast.makeText(this, headerText, Toast.LENGTH_SHORT).show();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("ResDet/" + headerText);
        mDatabase.keepSynced(true);

        mItemList = findViewById(R.id.container);
        mItemList.setHasFixedSize(true);
        mItemList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Item,ItemViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Item, ItemViewHolder>
                (Item.class,R.layout.itemsmodel,ItemViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(ItemViewHolder viewHolder, Item model, int position) {
                viewHolder.setItemDetails(getApplicationContext(),model.getFood(),model.getPrice());
            }
        };
        mItemList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public ItemViewHolder(View itemView){
            super(itemView);
            mView=itemView;
        }

        public void setItemDetails(Context cxt, String food, String price){
            TextView mNameRes = mView.findViewById(R.id.itemName);
            TextView mPriceRes = mView.findViewById(R.id.itemPrice);

            mNameRes.setText(food);
            mPriceRes.setText(price);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
