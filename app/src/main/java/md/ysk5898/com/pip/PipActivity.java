/*
 * Create by SangKwon on 2019. 10. 25.
 */

package md.ysk5898.com.pip;

import android.app.PendingIntent;
import android.app.RemoteAction;
import android.app.PictureInPictureParams;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Rational;
import android.view.View;

import java.util.ArrayList;

import md.ysk5898.com.R;
import md.ysk5898.com.databinding.ActivityPipBinding;
import md.ysk5898.com.pip.widget.MovieView;

public class PipActivity extends AppCompatActivity {

    private static final String TAG = "PipActivity";
    /**
     * Intent action for media controls from Picture-in-Picture mode.
     */
    private static final String ACTION_MEDIA_CONTROL = "media_control";

    /**
     * Intent extra for media controls from Picture-in-Picture mode.
     */
    private static final String EXTRA_CONTROL_TYPE = "control_type";

    /**
     * The request code for play action PendingIntent.
     */
    private static final int REQUEST_PLAY = 1;

    /**
     * The request code for pause action PendingIntent.
     */
    private static final int REQUEST_PAUSE = 2;

    /**
     * The request code for info action PendingIntent.
     */
    private static final int REQUEST_INFO = 3;

    /**
     * The intent extra value for play action.
     */
    private static final int CONTROL_TYPE_PLAY = 1;

    /**
     * The intent extra value for pause action.
     */
    private static final int CONTROL_TYPE_PAUSE = 2;

    ActivityPipBinding binding;

    MovieView movieView;
    PictureInPictureParams.Builder builder = new PictureInPictureParams.Builder();

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null
                    || !ACTION_MEDIA_CONTROL.equals(intent.getAction())) {
                return;
            }

            // This is where we are called back from Picture-in-Picture action
            // items.
            final int controlType = intent.getIntExtra(EXTRA_CONTROL_TYPE, 0);
            switch (controlType) {
                case CONTROL_TYPE_PLAY:
                    movieView.play();
                    break;
                case CONTROL_TYPE_PAUSE:
                    movieView.pause();
                    break;
            }
        }
    };;

    private String mPlay;
    private String mPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pip);
        binding.setActivity(this);
        movieView = binding.movie;
        mPlay = getString(R.string.play);
        mPause = getString(R.string.pause);

        movieView.setMovieListener(movieListener);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_minimize) {
            minimize();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();
        movieView.pause();
        Log.e(TAG, "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (!isInPictureInPictureMode()) {
            movieView.showControls();
        }
        Log.e(TAG, "onRestart");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        adjustFullScreen(newConfig);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            adjustFullScreen(getResources().getConfiguration());
        }
    }

    @Override
    public void onBackPressed() {
        if(movieView.isPlaying()){
            minimize();
        }else{
            super.onBackPressed();
        }
    }

    void minimize() {
        movieView.hideControls();
        Rational rational = new Rational(movieView.getWidth(), movieView.getHeight());
        builder.setAspectRatio(rational);
        enterPictureInPictureMode(builder.build());
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
        if (isInPictureInPictureMode) {
            Log.e(TAG, "onPictureInPictureModeChanged 1");
            registerReceiver(mReceiver, new IntentFilter(ACTION_MEDIA_CONTROL));
        } else {
            Log.e(TAG, "onPictureInPictureModeChanged 2");
            unregisterReceiver(mReceiver);
            // Show the video controls if the video is not playing
            if (movieView != null && !movieView.isPlaying()) {
                movieView.showControls();
            }
        }
    }

    private MovieView.MovieListener movieListener = new MovieView.MovieListener() {
        @Override
        public void onMovieStarted() {
            updatePictureInPictureActions(R.drawable.ic_pause_24dp, mPause, CONTROL_TYPE_PAUSE, REQUEST_PAUSE);
        }

        @Override
        public void onMovieStopped() {
            updatePictureInPictureActions(R.drawable.ic_play_arrow_24dp, mPlay, CONTROL_TYPE_PLAY, REQUEST_PLAY);
        }

        @Override
        public void onMovieMinimized() {
            minimize();
        }
    };

    /**
     * Update the state of pause/resume action item in Picture-in-Picture mode.
     *
     * @param iconId      The icon to be used.
     * @param title       The title text.
     * @param controlType The type of the action. either {@link #CONTROL_TYPE_PLAY} or {@link
     *                    #CONTROL_TYPE_PAUSE}.
     * @param requestCode The request code for the {@link PendingIntent}.
     */
    void updatePictureInPictureActions(
            @DrawableRes int iconId, String title, int controlType, int requestCode) {
        final ArrayList<RemoteAction> actions = new ArrayList<>();
        final PendingIntent intent = PendingIntent.getBroadcast(PipActivity.this, requestCode, new Intent(ACTION_MEDIA_CONTROL).putExtra(EXTRA_CONTROL_TYPE, controlType), 0);
        final Icon icon = Icon.createWithResource(PipActivity.this, iconId);
        actions.add(new RemoteAction(icon, title, title, intent));
        actions.add( new RemoteAction(Icon.createWithResource(PipActivity.this, R.drawable.ic_info_24dp), getString(R.string.info), getString(R.string.info_description),
                        PendingIntent.getActivity(PipActivity.this, REQUEST_INFO, new Intent(Intent.ACTION_VIEW,Uri.parse(getString(R.string.info_uri))),0)));
        builder.setActions(actions);
        setPictureInPictureParams(builder.build());
    }

    /**
     * Adjusts immersive full-screen flags depending on the screen orientation.
     *
     * @param config The current {@link Configuration}.
     */
    private void adjustFullScreen(Configuration config) {
        final View decorView = getWindow().getDecorView();
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            movieView.setAdjustViewBounds(false);
        } else {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            movieView.setAdjustViewBounds(true);
        }
    }


}
