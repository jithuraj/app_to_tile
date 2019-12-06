package in.binarybox.apptotile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.net.FileNameMap;
import java.util.ArrayList;
import java.util.List;

public class TileItemsActivity extends AppCompatActivity {

    private static final String TAG="jithu";
    private ConstraintLayout item1Layout, item2Layout, item3Layout, item4Layout, item5Layout;
    private static final String SHARED_PREFERENCES_NAME = "tiles_data";
    private List<Boolean> enabledItems = new ArrayList<>();
    private List<String> packageNames = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private ImageView ivAppIcon1, ivAppIcon2, ivAppIcon3, ivAppIcon4, ivAppIcon5;
    private List<ImageView> ivAppIcons = new ArrayList<>();
    private List<ConstraintLayout> layouts = new ArrayList<>();
    private List<Animation> inAnimations = new ArrayList<>();
    private List<Animation> outAnimations = new ArrayList<>();
    private List<ProgressBar> progressBars = new ArrayList<>();
    private ProgressBar progressBar1, progressBar2, progressBar3, progressBar4, progressBar5;
    private Boolean isOnResumeFirstTime = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setIdsToViewsFn();
        addImagesToListFn();
        addProgressBarsToListFn();
        initializeSharedPreferencesFn();
        getDataFromSharedPreferencesFn();
        setUpAnimationsFn();
        inAnimationsFn();
        onClickListenersFn();

    }

    private void addProgressBarsToListFn() {

        progressBars.add(progressBar1);
        progressBars.add(progressBar2);
        progressBars.add(progressBar3);
        progressBars.add(progressBar4);
        progressBars.add(progressBar5);

    }

    private void setUpAnimationsFn() {

        final int ANIMATION_DURATION = 500;
        final Interpolator ANIMATION_INTERPOLATOR = new AccelerateDecelerateInterpolator();
        final int OFFSET_TIME = 100;
        final int OFFSET_INTERVAL = 100;
        int offsetTime, offsetInterval;

        //in animations
        Animation inAnim1 = AnimationUtils.loadAnimation(this, R.anim.tile_items_in_anim);
        Animation inAnim2 = AnimationUtils.loadAnimation(this, R.anim.tile_items_in_anim);
        Animation inAnim3 = AnimationUtils.loadAnimation(this, R.anim.tile_items_in_anim);
        Animation inAnim4 = AnimationUtils.loadAnimation(this, R.anim.tile_items_in_anim);
        Animation inAnim5 = AnimationUtils.loadAnimation(this, R.anim.tile_items_in_anim);

        //out animations
        Animation outAnim1 = AnimationUtils.loadAnimation(this, R.anim.tile_items_out_anim);
        Animation outAnim2 = AnimationUtils.loadAnimation(this, R.anim.tile_items_out_anim);
        Animation outAnim3 = AnimationUtils.loadAnimation(this, R.anim.tile_items_out_anim);
        Animation outAnim4 = AnimationUtils.loadAnimation(this, R.anim.tile_items_out_anim);
        Animation outAnim5 = AnimationUtils.loadAnimation(this, R.anim.tile_items_out_anim);

        //add in animations to list
        inAnimations.add(inAnim1);
        inAnimations.add(inAnim2);
        inAnimations.add(inAnim3);
        inAnimations.add(inAnim4);
        inAnimations.add(inAnim5);

        //add out animations to list
        outAnimations.add(outAnim1);
        outAnimations.add(outAnim2);
        outAnimations.add(outAnim3);
        outAnimations.add(outAnim4);
        outAnimations.add(outAnim5);

        //set fresh values to variables
        offsetTime = OFFSET_TIME;
        offsetInterval = OFFSET_INTERVAL;

        //set interpolator, duration and start offset for in animations
        for (Animation animation : inAnimations) {
            offsetTime += offsetInterval;
            animation.setInterpolator(ANIMATION_INTERPOLATOR);
            animation.setDuration(ANIMATION_DURATION);
            animation.setStartOffset(offsetTime);
        }

        // set fresh values to variables so that to fix the variables uses the final values from previous for loop.
        offsetTime = OFFSET_TIME;
        offsetInterval = OFFSET_INTERVAL;

        //set interpolator, duration and start offset for out animations
        for (Animation animation : outAnimations) {
            offsetTime += offsetInterval;
            animation.setInterpolator(ANIMATION_INTERPOLATOR);
            animation.setDuration(ANIMATION_DURATION);
            animation.setStartOffset(offsetTime);
        }

        //add layouts into list for future use
        layouts.add(item1Layout);
        layouts.add(item2Layout);
        layouts.add(item3Layout);
        layouts.add(item4Layout);
        layouts.add(item5Layout);


    }

    private void inAnimationsFn() {

        int i = 0;

        for (ConstraintLayout layout : layouts) {
            layout.startAnimation(inAnimations.get(i));
            i++;
        }


    }

    private void onClickListenersFn() {

        item1Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChooseAppsActivityFn(1);
                turnOnProgressBarFn(1);
            }
        });
        item2Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChooseAppsActivityFn(2);
                turnOnProgressBarFn(2);
            }
        });
        item3Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChooseAppsActivityFn(3);
                turnOnProgressBarFn(3);
            }
        });
        item4Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChooseAppsActivityFn(4);
                turnOnProgressBarFn(4);
            }
        });
        item5Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChooseAppsActivityFn(5);
                turnOnProgressBarFn(5);
            }
        });

    }

    private void turnOnProgressBarFn(int item) {

        progressBars.get(item - 1).setVisibility(View.VISIBLE);

    }

    private void turnOffProgressBarFn() {

        for (ProgressBar progressBar : progressBars) {
            progressBar.setVisibility(View.GONE);
        }

    }

//    private void outAnimationsFn() {
//
//
//        int i = 0;
//
//        for (ConstraintLayout layout : layouts) {
//            layout.startAnimation(outAnimations.get(i));
//            i++;
//        }
//
//    }

    private void initializeSharedPreferencesFn() {

        //initialize shared preferences
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);

    }

    private void addImagesToListFn() {

        //add imageviews to list
        ivAppIcons.add(ivAppIcon1);
        ivAppIcons.add(ivAppIcon2);
        ivAppIcons.add(ivAppIcon3);
        ivAppIcons.add(ivAppIcon4);
        ivAppIcons.add(ivAppIcon5);
    }

    private void setIdsToViewsFn() {

        //set ids to views
        item1Layout = findViewById(R.id.item1Layout);
        item2Layout = findViewById(R.id.item2Layout);
        item3Layout = findViewById(R.id.item3Layout);
        item4Layout = findViewById(R.id.item4Layout);
        item5Layout = findViewById(R.id.item5Layout);
        ivAppIcon1 = findViewById(R.id.ivAppIcon1);
        ivAppIcon2 = findViewById(R.id.ivAppIcon2);
        ivAppIcon3 = findViewById(R.id.ivAppIcon3);
        ivAppIcon4 = findViewById(R.id.ivAppIcon4);
        ivAppIcon5 = findViewById(R.id.ivAppIcon5);
        progressBar1 = findViewById(R.id.progressBar1);
        progressBar2 = findViewById(R.id.progressBar2);
        progressBar3 = findViewById(R.id.progressBar3);
        progressBar4 = findViewById(R.id.progressBar4);
        progressBar5 = findViewById(R.id.progressBar5);
    }

    private void getDataFromSharedPreferencesFn() {


        enabledItems.add(sharedPreferences.getBoolean("item1_active", false));
        enabledItems.add(sharedPreferences.getBoolean("item2_active", false));
        enabledItems.add(sharedPreferences.getBoolean("item3_active", false));
        enabledItems.add(sharedPreferences.getBoolean("item4_active", false));
        enabledItems.add(sharedPreferences.getBoolean("item5_active", false));

        setDatasToViewsFn();

    }

    private void setDatasToViewsFn() {
        int currentItem = 0;
        String currentPackageName;
        for (Boolean i : enabledItems) {
            currentItem++;
            if (i) {
                currentPackageName = sharedPreferences.getString("item" + currentItem + "_package_name", "");
                try {
                    Drawable icon = this.getPackageManager().getApplicationIcon(currentPackageName);
                    ivAppIcons.get(currentItem - 1).setImageDrawable(icon);
                } catch (PackageManager.NameNotFoundException ne) {
                    ivAppIcons.get(currentItem - 1).setBackgroundResource(R.drawable.icon_question_mark);
                }
            } else {
                ivAppIcons.get(currentItem - 1).setBackgroundResource(R.drawable.icon_question_mark);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


    }

    private void startChooseAppsActivityFn(int selectedItem) {

        Intent intent = new Intent(getApplicationContext(), ChooseAppsActivity.class);
        intent.putExtra("selectedItem", selectedItem);
        startActivity(intent);
    }

    @Override
    protected void onResume() {

        //check onResume is calling for the first time to prevent it from loading after onCreate.

        if (isOnResumeFirstTime) {
            //first time

            isOnResumeFirstTime = false;

        } else {
            //not first time

//            Log.i(TAG,"onResume called");

            setDatasToViewsFn();
            turnOffProgressBarFn();
            isOnResumeFirstTime = true;
        }


        super.onResume();
    }
}
