package in.binarybox.apptotile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ChooseAppsActivity extends AppCompatActivity {
    private static final String TAG = "jithu";
    private List<String> appNamesList= new ArrayList<>();
    private List<String> appPackageNames = new ArrayList<>();
    private List<Drawable> appIcons = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private int selectedItemFromIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_apps);


        recyclerView=findViewById(R.id.recyclerView);

        //get data from intent
        selectedItemFromIntent=getIntent().getIntExtra("selectedItem",0);



        recyclerViewAdapter=new RecyclerViewAdapter(appNamesList,appPackageNames,appIcons,getApplicationContext(),selectedItemFromIntent);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pkgAppsList = this.getPackageManager().queryIntentActivities(mainIntent, 0);


        for (ResolveInfo item : pkgAppsList){
            appNamesList.add(item.loadLabel(this.getPackageManager()).toString());
            appPackageNames.add(item.activityInfo.packageName);
            appIcons.add(item.loadIcon(this.getPackageManager()));
        }

        recyclerViewAdapter.notifyDataSetChanged();


    }
}
