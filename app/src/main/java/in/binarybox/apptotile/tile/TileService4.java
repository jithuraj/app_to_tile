package in.binarybox.apptotile.tile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.N)
public class TileService4 extends TileService {
    private static final String TAG = "jithu";
    private static final String SHARED_PREFERENCES_NAME = "tiles_data";
    private SharedPreferences sharedPreferences;


    public TileService4() {
        super();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onTileAdded() {
        updateTileDetailsFn();

        super.onTileAdded();
    }

    private void updateTileDetailsFn() {
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        Tile tile = getQsTile();
        tile.setLabel(sharedPreferences.getString("item4_app_name", "no app"));
        tile.updateTile();
    }

    @Override
    public void onTileRemoved() {
        super.onTileRemoved();
    }

    @Override
    public void onStartListening() {
        updateTileDetailsFn();
        super.onStartListening();
    }

    @Override
    public void onStopListening() {
        super.onStopListening();
    }

    @Override
    public void onClick() {
        super.onClick();

        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        PackageManager packageManager = getApplicationContext().getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(sharedPreferences.getString("item4_package_name", getPackageName()));
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        startActivityAndCollapse(intent);

    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

}
