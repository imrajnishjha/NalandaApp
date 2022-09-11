package com.wormos.nalandaapp;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.Objects;

public class FoodMenuAdapter extends FirebaseRecyclerAdapter<FoodMenuModel,FoodMenuAdapter.foodMenuItemViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     */
    public FoodMenuAdapter(@NonNull FirebaseRecyclerOptions<FoodMenuModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull foodMenuItemViewHolder holder, int position, @NonNull FoodMenuModel model) {
        holder.foodList.setText(model.getMenu());
        holder.messTime.setText(model.getTime());
        if(Objects.equals(getRef(position).getKey(), "005lunch")){
            holder.dash.setBackgroundColor(Color.parseColor("#A6F6945D"));
            holder.edge.setBackgroundColor(Color.parseColor("#F6945D"));
            holder.foodList.setBackgroundColor(Color.parseColor("#66F6945D"));
        } else if(Objects.equals(getRef(position).getKey(), "010snacks")){
            holder.dash.setBackgroundColor(Color.parseColor("#A6E23424"));
            holder.edge.setBackgroundColor(Color.parseColor("#E23424"));
            holder.foodList.setBackgroundColor(Color.parseColor("#66E23424"));
        }else if(Objects.equals(getRef(position).getKey(), "015dinner")){
            holder.dash.setBackgroundColor(Color.parseColor("#A6ffb703"));
            holder.edge.setBackgroundColor(Color.parseColor("#ffb703"));
            holder.foodList.setBackgroundColor(Color.parseColor("#66ffb703"));
        }

    }

    @NonNull
    @Override
    public foodMenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_menu_item,parent,false);
        return new foodMenuItemViewHolder(view);
    }

    static class foodMenuItemViewHolder extends RecyclerView.ViewHolder{

        TextView messTime,foodList,dash,edge;

        public foodMenuItemViewHolder(@NonNull View itemView) {
            super(itemView);
            messTime = itemView.findViewById(R.id.mess_time);
            foodList = itemView.findViewById(R.id.food_menu_list);
            edge = itemView.findViewById(R.id.food_item_sidebar);
            dash = itemView.findViewById(R.id.food_item_sidedash);
        }
    }
}
