package com.knit.dailywagesworkers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class homeAdapterMainClass extends FirebaseRecyclerAdapter<homeRvMainModel,homeAdapterMainClass.myViewHolder> {


    private Context context;
     public homeAdapterMainClass(Context context,FirebaseRecyclerOptions<homeRvMainModel> options){
         super(options);
         this.context = context;
     }

    @Override
    protected void onBindViewHolder(@NonNull homeAdapterMainClass.myViewHolder holder, int position, @NonNull homeRvMainModel model) {

        holder.address.setText(model.getAddress());

        holder.profession.setText(model.getProfession());


        Glide.with(holder.img.getContext())
                .load(model.getSurl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_rv_profile,parent,false);

        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView profession,address;
        Button btnHire;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            address=(TextView) itemView.findViewById(R.id.empAddress);

            profession=(TextView) itemView.findViewById(R.id.empProfession);
            img=(CircleImageView)itemView.findViewById(R.id.img1);

            btnHire=itemView.findViewById(R.id.btnHire);

            btnHire.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Customer_login.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
     }
}
