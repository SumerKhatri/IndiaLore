package com.example.indialore.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.indialore.R;
import com.example.indialore.adapters.SliderAdapter;

public class OnboardingActivity extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout dotsLayot;
    SliderAdapter sliderAdapter;
    TextView dots[];
    Button button;

    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
//        getSupportActionBar().hide();
        viewPager=findViewById(R.id.slider);
        dotsLayot=findViewById(R.id.dots);
        button=findViewById(R.id.get_started_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnboardingActivity.this,RegistrationActivity.class));
                finish();
            }
        });
        sliderAdapter=new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        viewPager.addOnPageChangeListener(changeListener);
        addDots(0);
    }

    private void addDots(int position) {
            dotsLayot.removeAllViews();
            dots = new TextView[3];
            for (int i = 0; i < dots.length; i++) {
                dots[i] = new TextView(this);
                dots[i].setText(Html.fromHtml("&#8226;"));
                dots[i].setTextSize(35);
                dotsLayot.addView(dots[i]);
            }

            dots[position].setTextColor(getResources().getColor(R.color.pink));

    }
    ViewPager.OnPageChangeListener changeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDots(position);
            if(position==2){
                animation= AnimationUtils.loadAnimation(OnboardingActivity.this,R.anim.slider_animation);
                button.setAnimation(animation);
                button.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}