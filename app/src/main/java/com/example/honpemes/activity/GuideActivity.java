package com.example.honpemes.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;

import com.example.honpemes.R;
import com.example.honpemes.base.BaseActivity;
import com.example.honpemes.utils.ToastUtil;
import com.example.honpemes.utils.Util;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;

import java.util.Random;

import butterknife.BindView;

/**
 * Created by Lixixiang on 2023/2/27 10:40
 * 引导页
 */
public class GuideActivity extends BaseActivity {
    @BindView(R.id.splash_h)
    TextView splashH;
    @BindView(R.id.splash_p)
    TextView splashP;
    @BindView(R.id.splash_m)
    TextView splashM;
    @BindView(R.id.splash_e)
    TextView splashE;
    @BindView(R.id.splash_s)
    TextView splashS;
    @BindView(R.id.splash_x)
    TextView splashX;
    @BindView(R.id.splash_t)
    TextView splashT;
    @BindView(R.id.splash_name)
    TextView splashName;
    @BindView(R.id.splash_logo)
    ImageView splashLogo;
    @BindView(R.id.guideline19)
    Guideline guideline19;
    @BindView(R.id.guideline20)
    Guideline guideline20;
    @BindView(R.id.splash_container)
    ConstraintLayout container;

    private TextView[] ts;
    private boolean animComplete;
    private boolean initComplete;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_guid;
    }

    @Override
    public void initView() {
        ts = new TextView[]{splashH, splashP, splashM, splashE, splashS, splashX, splashT};
        ImmersionBar.with(_mActivity).statusBarAlpha(0f).hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR).init();
        GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TL_BR,
                new int[]{getResources().getColor(R.color.blue_l),
                        getResources().getColor(R.color.blue_l_l),
                        getResources().getColor(R.color.blue_l_l_l)});
        container.setBackground(gd);
        container.setClickable(false);
        ts[0].post(new Runnable() {
            @Override
            public void run() {
                for (TextView t : ts) {
                    t.setVisibility(View.VISIBLE);
                    startTextInAnim(t);
                }
            }
        });
    }

    private void startTextInAnim(TextView t) {
        Random r = new Random();
        DisplayMetrics metrics = Util.getMetrics(_mActivity);
        int x = r.nextInt(metrics.widthPixels * 4 / 3);
        int y = r.nextInt(metrics.heightPixels * 4 / 3);

        float s = r.nextFloat() + 4.0f;
        ValueAnimator tranY = ObjectAnimator.ofFloat(t, "translationY", y - t.getY(), 0);
        ValueAnimator tranX = ObjectAnimator.ofFloat(t, "translationX", x - t.getX(), 0);
        ValueAnimator scaleX = ObjectAnimator.ofFloat(t, "scaleX", s, 1.0f);
        ValueAnimator scaleY = ObjectAnimator.ofFloat(t, "scaleY", s, 1.0f);
        ValueAnimator alpha = ObjectAnimator.ofFloat(t, "alpha", 0.0f, 1.0f);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(1800);
        set.play(tranX).with(tranY).with(scaleX).with(scaleY).with(alpha);
        if (t == splashT) {
            set.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    startFinalAnim();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
        set.start();
    }

    private void startFinalAnim() {
        ValueAnimator alpha = ObjectAnimator.ofFloat(splashLogo, "alpha", 0.0f, 1.0f);
        alpha.setDuration(1000);
        ValueAnimator alphaN = ObjectAnimator.ofFloat(splashName, "alpha", 0.0f, 1.0f);
        alphaN.setDuration(1000);
        ValueAnimator tranY = ObjectAnimator.ofFloat(splashLogo, "translationY", -splashLogo.getHeight() / 3, 0f);
        tranY.setDuration(1000);
        ValueAnimator wait = ObjectAnimator.ofInt(0, 100);
        wait.setDuration(1000);
        wait.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (initComplete) {
                            ToastUtil.getInstance().showToast( "已经成功调用！");
                        } else {
                            animComplete = true;
                        }
                    }
                });
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new LinearInterpolator());

        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                splashLogo.setVisibility(View.VISIBLE);
                splashName.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(_mActivity, MainActivity.class));
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.play(alpha).with(alphaN).with(tranY).before(wait);
        set.start();
    }


}















































