package com.example.a98max1.milpaus.modelo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a98max1.milpaus.R;

import java.util.ArrayList;

public class RecicleViewAdapter extends RecyclerView.Adapter<RecicleViewAdapter.ViewHolder>{

    private static final String TAG = "RecicleViewAdapter";

    private ArrayList<String> produtos = new ArrayList<>();
    private Context context;

    public RecicleViewAdapter(ArrayList<String> produtos, Context context) {
        this.produtos = produtos;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.txtProduto.setText(produtos.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + produtos.get(position));
                Toast.makeText(context,produtos.get(position),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtProduto;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            txtProduto = itemView.findViewById(R.id.txtProduto);
            parentLayout=itemView.findViewById(R.id.parent_layout);
        }
    }
}
