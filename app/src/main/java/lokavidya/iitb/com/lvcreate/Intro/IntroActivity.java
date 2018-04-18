package lokavidya.iitb.com.lvcreate.Intro;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import lokavidya.iitb.com.lvcreate.MainActivity;
import lokavidya.iitb.com.lvcreate.R;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int[] images = new int[]{R.drawable.intro1, R.drawable.intro2, R.drawable.intro3};

        addSlide(AppIntroFragment.newInstance("",
                "",
                "Add pictures or videos that describe your project.",
                "",
                images[0],
                Color.parseColor("#FFFFFF"),
                0,
                Color.parseColor("#3b3b3b")));

        addSlide(AppIntroFragment.newInstance("",
                "",
                "Add voiceover & edit the pictures and videos taken.",
                "",
                images[1],
                Color.parseColor("#FFFFFF"),
                0,
                Color.parseColor("#3b3b3b")));

        addSlide(AppIntroFragment.newInstance("",
                "",
                "Upload & share the project to the community.",
                "",
                images[2],
                Color.parseColor("#FFFFFF"),
                0,
                Color.parseColor("#3b3b3b")));

        setBarColor(Color.parseColor("#FFFFFF"));
        setSeparatorColor(Color.parseColor("#3b3b3b"));

        setTitleColor(Color.parseColor("#3b3b3b"));
        setIndicatorColor(Color.parseColor("#ebebeb"),Color.parseColor("#3b3b3b"));
        setColorSkipButton(Color.parseColor("#3b3b3b"));
        setColorDoneText(Color.parseColor("#3b3b3b"));
        setNextArrowColor(Color.parseColor("#3b3b3b"));

        showSkipButton(true);
        setProgressButtonEnabled(true);

        setDepthAnimation();
        //setFlowAnimation();

        setVibrate(true);
        setVibrateIntensity(30);

    }

    private void loadMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        loadMainActivity();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        loadMainActivity();
    }

    @Override
    public void onSkipPressed() {
        super.onSkipPressed();
    }

}

