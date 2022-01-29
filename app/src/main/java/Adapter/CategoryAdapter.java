package Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.seatandorder.R;

import java.util.ArrayList;

import Domain.CategoryDomain;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    ArrayList<CategoryDomain> categoryDomains;

    public CategoryAdapter(ArrayList<CategoryDomain> categoryDomains) {
        this.categoryDomains = categoryDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cat, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.categoryName.setText((categoryDomains.get(position).getTitle()));
    String picUrl = "";
    int resId = 0;
    switch (position){
        case 0:{
            picUrl="cat_1";
            holder.mainLayout.setBackground(ContextCompat.getDrawable((holder.itemView.getContext()), R.drawable.category_background1));
            resId = R.drawable.cat_1;
        }
        case 1:{
            picUrl="cat_2";
            holder.mainLayout.setBackground(ContextCompat.getDrawable((holder.itemView.getContext()), R.drawable.category_background2));
            resId = R.drawable.cat_2;
        }
        case 2:{
            picUrl="cat_3";
            holder.mainLayout.setBackground(ContextCompat.getDrawable((holder.itemView.getContext()), R.drawable.category_background3));
            resId = R.drawable.cat_3;
        }
        case 3:{
            picUrl="cat_4";
            holder.mainLayout.setBackground(ContextCompat.getDrawable((holder.itemView.getContext()), R.drawable.category_background4));
            resId = R.drawable.cat_4;
        }
        case 4:{
            picUrl="cat_5";
            holder.mainLayout.setBackground(ContextCompat.getDrawable((holder.itemView.getContext()), R.drawable.category_background5));
            resId = R.drawable.cat_5;
        }
    }

    int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());

    Glide.with(holder.itemView.getContext())
            .load(resId)
            .into(holder.categoryImg);
    }


    @Override
    public int getItemCount() {
        return categoryDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView categoryName;
        ImageView categoryImg;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryImg = itemView.findViewById(R.id.categoryImg);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
