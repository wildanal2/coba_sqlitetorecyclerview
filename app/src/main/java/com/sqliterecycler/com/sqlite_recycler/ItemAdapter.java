package com.sqliterecycler.com.sqlite_recycler;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ItemAdapter  extends RecyclerView.Adapter<ItemAdapter.ItemsViewHolder>{
    private Context mContext;
    private Cursor mCursor;

    public ItemAdapter(Context contx, Cursor crsor) {
        mContext = contx;
        mCursor = crsor;
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder{
        TextView nameText,jumlahtext;

        public ItemsViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.txv_namaBarang);
            jumlahtext = itemView.findViewById(R.id.txv_jumlahBarang);
        }
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(mContext);
        View v = inf.inflate(R.layout.item_list,parent,false);
        return new ItemsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)){
            return;
        }

        String name = mCursor.getString(mCursor.getColumnIndex("nama"));
        int amount = mCursor.getInt(mCursor.getColumnIndex("jumlah"));

        holder.nameText.setText(name);
        holder.jumlahtext.setText(String.valueOf(amount)+"  item");
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public  void  swapCursor(Cursor newCursor){
        if (mCursor != null){
            mCursor.close();
        }

        mCursor = newCursor;
        if (newCursor != null){
            notifyDataSetChanged();
        }
    }
}
