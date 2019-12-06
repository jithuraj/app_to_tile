package in.binarybox.apptotile.tile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.util.Log;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.N)
public class TileService1 extends TileService {
    private static final String TAG = "jithu";
    private static final String SHARED_PREFERENCES_NAME ="tiles_data";
    private SharedPreferences sharedPreferences;


    public TileService1() {
        super();
//        Log.i(TAG,"constructor");
    }

    @Override
    public void onDestroy() {
//        Log.i(TAG,"onDestroy");
        super.onDestroy();
    }

    @Override
    public void onTileAdded() {
//        Log.i(TAG,"onTileAdded");

        updateTileDetailsFn();


        super.onTileAdded();
    }

    private void updateTileDetailsFn() {

        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME,MODE_PRIVATE);
        Tile tile = getQsTile();
        tile.setLabel(sharedPreferences.getString("item1_app_name","no app"));
        tile.updateTile();
    }

    @Override
    public void onTileRemoved() {
//        Log.i(TAG,"onTileRemoved");
        super.onTileRemoved();
    }

    @Override
    public void onStartListening() {
//        Log.i(TAG,"onStartListening");
        updateTileDetailsFn();
        super.onStartListening();
    }

    @Override
    public void onStopListening() {
//        Log.i(TAG,"onStopListening");
        super.onStopListening();
    }

    @Override
    public void onClick() {
        super.onClick();

        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME,MODE_PRIVATE);
        PackageManager packageManager = getApplicationContext().getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(sharedPreferences.getString("item1_package_name",getPackageName()));
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        startActivityAndCollapse(intent);

    }

    @Override
    public IBinder onBind(Intent intent) {
//        Log.i(TAG,"onBind");
        return super.onBind(intent);
    }

}
