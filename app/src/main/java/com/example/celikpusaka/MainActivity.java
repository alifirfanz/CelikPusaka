
package com.example.celikpusaka;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.celikpusaka.Admin.AddVideoAdmin;
import com.example.celikpusaka.Admin.DashboardAdmin;
import com.example.celikpusaka.Admin.InfoAdmin;
import com.example.celikpusaka.Admin.ViewFeedback;
import com.example.celikpusaka.Admin.ViewVideoAdmin;
import com.example.celikpusaka.user.DashboardPengiraanHarta;
import com.example.celikpusaka.user.DashboardUser;
import com.example.celikpusaka.user.Login;
import com.example.celikpusaka.user.PengiraanHarta;
import com.example.celikpusaka.user.SignUp;
import com.example.celikpusaka.user.StartingScreenActivity;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN =5000;

    //Variables
    Animation topAnim, bottomAnim;
    ImageView logo;
    TextView text;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animation
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        logo =findViewById(R.id.ImageView);
        text =findViewById(R.id.logo_name);
        lottieAnimationView = findViewById(R.id.lottie);

        logo.setAnimation(topAnim);
        text.setAnimation(bottomAnim);
        lottieAnimationView.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Login.class);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View,String>(logo,"logo_image");
                pairs[1] = new Pair<View,String>(text,"logo_text");

                ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                startActivity(intent,options.toBundle());

            }
        }, SPLASH_SCREEN);

    }
}
