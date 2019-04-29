package com.helo.kwp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.helo.kwp.R;
import com.helo.kwp.mapas.model.Point;

import java.util.ArrayList;
import java.util.List;

public class PointsAdapter extends RecyclerView.Adapter<PointsAdapter.ViewHolder> {

    private static final String TAG = "POINTS_ADAPTER: ";

    private List<Point> items;
    private Context context;
    private OnItemClickListener clickListener;

    public PointsAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_points, parent, false);
        return new ViewHolder(itemView);
    }

    public void setClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.vDate.setText(items.get(position).getFechaEnvio());
        viewHolder.vSupport.setText(items.get(position).getFolioOperacion());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void getPointList(ArrayList<Point> listaPagos) {
        items.addAll(listaPagos);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        private TextView vSupport;
        private TextView vDate;

        public ViewHolder(View v) {
            super(v);
            vSupport = v.findViewById(R.id.supporting_text);
            vDate = v.findViewById(R.id.institute_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onClick(view, getAdapterPosition());
        }

    }
}