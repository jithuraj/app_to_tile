package in.binarybox.apptotile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.myViewHolder> {

    private List<String> appNamesList = new ArrayList<>();
    private List<String> appPackageNames = new ArrayList<>();
    private List<Drawable> appIcons = new ArrayList<>();
    private Context context;
    private String TAG = "jithu";
    private static final String TILE_ITEM = "tileitem";
    private static final String SHARED_PREFERENCES_NAME = "tiles_data";
    private int selectedItemFromIntent;

    public RecyclerViewAdapter(List<String> appNamesList, List<String> appPackageNames, List<Drawable> appIcons, Context context,int selectedItemFromIntent) {
        this.appNamesList = appNamesList;
        this.appPackageNames = appPackageNames;
        this.context = context;
        this.appIcons = appIcons;
        this.selectedItemFromIntent=selectedItemFromIntent;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false);

        myViewHolder myViewHolder = new myViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.myViewHolder holder, final int position) {

        holder.tvAppName.setText("" + appNamesList.get(position));
        holder.ivThumbnail.setImageDrawable(appIcons.get(position));
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToSharedPreferencesFn(position);
            }

        });

    }



    private void saveDataToSharedPreferencesFn(int position) {

        SharedPreferences sharedpreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean("item" + selectedItemFromIntent + "_active", true);
        editor.putString("item" + selectedItemFromIntent + "_package_name", "" + appPackageNames.get(position));
        editor.putString("item" + selectedItemFromIntent + "_app_name", "" + appNamesList.get(position));
        editor.commit();



    }


    @Override
    public int getItemCount() {
        return appNamesList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        private TextView tvAppName;
        private ImageView ivThumbnail;
        private ConstraintLayout itemLayout;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAppName = itemView.findViewById(R.id.tvAppName);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
            itemLayout = itemView.findViewById(R.id.itemLayout);

        }
    }

}
