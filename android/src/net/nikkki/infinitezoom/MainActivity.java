package net.nikkki.infinitezoom;



import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

        @Override
        public void onCreate(Bundle icicle) {
                super.onCreate(icicle);

                Intent intent = new Intent();

                if (Build.VERSION.SDK_INT >= 16) {
                        /*
                         * Open live wallpaper preview (API Level 16 or greater).
                         */
                        ComponentName component = new ComponentName(getPackageName(), getPackageName() + ".LWP_Android");
                        intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
                        intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, component);
                } else {
                        /*
                         * Open live wallpaper picker (API Level 15 or lower).
                         */
                        intent.setAction(WallpaperManager.ACTION_LIVE_WALLPAPER_CHOOSER);
                        Resources res = getResources();
                        String hint = res.getString(R.string.picker_toast_prefix)
                                        + res.getString(R.string.app_name)
                                        + res.getString(R.string.picker_toast_suffix);
                        Toast toast = Toast.makeText(this, hint, Toast.LENGTH_LONG);
                        toast.show();
                }

                startActivity(intent);
                finish();
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode,
                        Intent intent) {
                super.onActivityResult(requestCode, resultCode, intent);
                finish();
        }
}
