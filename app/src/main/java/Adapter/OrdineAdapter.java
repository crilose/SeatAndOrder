package Adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.seatandorder.Activity.ShowDetailActivity;
import com.example.seatandorder.R;

import java.util.ArrayList;

import Domain.FoodDomain;
import Helper.ManagementCard;
import Interface.ChangeNumberItemsListener;

public class OrdineAdapter extends RecyclerView.Adapter<OrdineAdapter.ViewHolder>{
    ArrayList<FoodDomain> foodDomains;
    private ManagementCard managementCard;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public OrdineAdapter(ArrayList<FoodDomain> FoodDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener) {

        this.foodDomains = FoodDomains;
        managementCard = new ManagementCard(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_ordine, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.title.setText((foodDomains.get(position).getTitle()));
    holder.prezzoPerItem.setText(String.valueOf(foodDomains.get(position).getFee()));
    holder.prezzoTotaleItems.setText(String.valueOf(Math.round((foodDomains.get(position).getNumberInCard()*foodDomains.get(position).getFee())*100.0)/100.0));
    holder.num.setText(String.valueOf(foodDomains.get(position).getNumberInCard()));

    int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

    Glide.with(holder.itemView.getContext())
            .load(drawableResourceId)
            .into(holder.pic);

    holder.plusItem.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            managementCard.plusNumberFood(foodDomains, position, new ChangeNumberItemsListener(){
                @Override
                public void changed(){
                    notifyDataSetChanged();
                    changeNumberItemsListener.changed();
                }
            });
        }
    });

    holder.minusItem.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            managementCard.minusNumberFood(foodDomains, position, new ChangeNumberItemsListener(){
                @Override
                public void changed(){
                    notifyDataSetChanged();
                    changeNumberItemsListener.changed();
                }
            });
        }
    });
    }


    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, prezzoPerItem, prezzoTotaleItems;
        ImageView pic,plusItem,minusItem;
        TextView num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title2Txt);
            prezzoPerItem = itemView.findViewById(R.id.prezzoSingoloItem);
            pic = itemView.findViewById(R.id.picOrdine);
            plusItem = itemView.findViewById(R.id.plusItem);
            minusItem = itemView.findViewById(R.id.minusItem);
            prezzoTotaleItems=itemView.findViewById(R.id.prezzoTotaleItems);
            num = itemView.findViewById(R.id.numeroItems);
        }
    }
}
