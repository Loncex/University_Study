package com.example.app_universidad3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GruposAdapter extends RecyclerView.Adapter<GruposAdapter.ViewHolder> {

    ArrayList<String> arrayList;
    Context context;

    public GruposAdapter(ArrayList<String> arrayList,Context context ) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public GruposAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View v = layoutInflater.inflate(R.layout.item_grupos,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GruposAdapter.ViewHolder holder, int position) {

        holder.textView.setText(arrayList.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.t1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAbsoluteAdapterPosition();
                    Intent intent = new Intent(context, DetailsGrupos.class);
                    String value = arrayList.get(position);
                    intent.putExtra("key", value);
                    context.startActivity(intent);

                }
            });

        }
    }
}
