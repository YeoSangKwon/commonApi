/*
 * Create by SangKwon on 2019. 8. 20.
 */

package md.ysk5898.com.remote;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import md.ysk5898.com.BuildConfig;
import md.ysk5898.com.MainActivity;
import md.ysk5898.com.R;

public class RemoteConfigActivity extends AppCompatActivity {
    private static final String TAG = "RemoteConfigActivity";


    // Remote Config keys
    private static final String LOADING_PHRASE_CONFIG_KEY = "loading_phrase";
    private static final String WELCOME_MESSAGE_KEY = "welcome_message";
    private static final String WELCOME_MESSAGE_CAPS_KEY = "welcome_message_caps";

    FirebaseRemoteConfig mFirebaseRemoteConfig;
    private TextView mWelcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_config);

        mWelcomeTextView = findViewById(R.id.welcomeTextView);

        Button fetchButton = findViewById(R.id.fetchButton);
        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activate();
            }
        });

        init();
    }

    private void init() {
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .setMinimumFetchIntervalInSeconds(3600)
                .build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);
        mFirebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);

        Activate();
    }

    private void Activate() {

        mWelcomeTextView.setText(mFirebaseRemoteConfig.getString(LOADING_PHRASE_CONFIG_KEY));

        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(this, new OnCompleteListener<Boolean>() {
            @Override
            public void onComplete(@NonNull Task<Boolean> task) {
                if (task.isSuccessful()) {
                    boolean updated = task.getResult();
                    Log.e(TAG, "Config params updated: " + updated);
                    Toast.makeText(RemoteConfigActivity.this, "Fetch and activate succeeded",Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(RemoteConfigActivity.this, "Fetch failed",Toast.LENGTH_SHORT).show();
                }
                displayWelcomeMessage();

            }
        });
    }

    private void displayWelcomeMessage() {
        String welcomeMessage = mFirebaseRemoteConfig.getString(WELCOME_MESSAGE_KEY);
        Log.e(TAG, "welcomeMessage =  " + welcomeMessage);
        Log.e(TAG, "WELCOME_MESSAGE_CAPS_KEY =  " + mFirebaseRemoteConfig.getBoolean(WELCOME_MESSAGE_CAPS_KEY));
        // [END get_config_values]
        if (mFirebaseRemoteConfig.getBoolean(WELCOME_MESSAGE_CAPS_KEY)) {
            mWelcomeTextView.setAllCaps(true);
        } else {
            mWelcomeTextView.setAllCaps(false);
        }
        mWelcomeTextView.setText(welcomeMessage);
    }
}
