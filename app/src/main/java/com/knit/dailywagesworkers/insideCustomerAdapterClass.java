package com.knit.dailywagesworkers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class insideCustomerAdapterClass extends FirebaseRecyclerAdapter<homeRvMainModel,insideCustomerAdapterClass.myViewHolder> {

    private Context context;

    public insideCustomerAdapterClass(Context context,FirebaseRecyclerOptions<homeRvMainModel>options){
        super(options);
        this.context = context;

    }

    @Override
    protected void onBindViewHolder(@NonNull insideCustomerAdapterClass.myViewHolder holder, int position, @NonNull homeRvMainModel model) {
        holder.name.setText(model.getName());

        holder.profession.setText(model.getProfession());
        holder.address.setText(model.getAddress());






        Glide.with(holder.img.getContext())
                .load(model.getSurl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

    }












    @NonNull
    @Override
    public insideCustomerAdapterClass.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hire_customer,parent,false);

        return new myViewHolder(view);


    }





    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        Button btnH;
        TextView name,profession,address;
        ImageView map;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            name=(TextView) itemView.findViewById(R.id.empName);

            profession=(TextView) itemView.findViewById(R.id.empProfession);
            img=(CircleImageView)itemView.findViewById(R.id.img1);
            address=itemView.findViewById(R.id.empAddress);
            map=itemView.findViewById(R.id.empMap);
            btnH=itemView.findViewById(R.id.btnHire);



            map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String addressStr = address.getText().toString();
                    Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode(addressStr));
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, geoUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    context.startActivity(mapIntent);
                }
            });



             btnH.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Intent intent=new Intent(context,payNow.class);
                     context.startActivity(intent);
                 }
             });


        }

    }

}
