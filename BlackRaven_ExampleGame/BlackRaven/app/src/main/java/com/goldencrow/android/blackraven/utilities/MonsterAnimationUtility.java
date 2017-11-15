package com.goldencrow.android.blackraven.utilities;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * @author Philipp Hermüller
 * @version 15.11.2017
 */

public class MonsterAnimationUtility {

    public static TranslateAnimation getFlyingAnimation() {
        TranslateAnimation anim = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0.15f,
                TranslateAnimation.RELATIVE_TO_SELF, -0.15f);

        anim.setDuration(1000);
        anim.setFillAfter(true);
        anim.setRepeatCount(Animation.INFINITE);
        anim.setRepeatMode(Animation.REVERSE);

        return anim;
    }

    public static AlphaAnimation getGhostAnimation() {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 0.5f);
        anim.setDuration(2000);
        anim.setRepeatCount(0);
        anim.setFillAfter(true);

        return anim;
    }

    public static AlphaAnimation getGhostAttackAnimation() {
        AlphaAnimation anim = new AlphaAnimation(0.5f, 0.0f);
        anim.setDuration(2000);
        anim.setRepeatCount(1);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setFillAfter(true);

        return anim;
    }

    public static TranslateAnimation getSimpleAttackAnimation() {
        TranslateAnimation anim = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0.25f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f);

        anim.setDuration(400);
        anim.setFillAfter(true);
        anim.setRepeatCount(1);
        anim.setRepeatMode(Animation.REVERSE);

        return anim;
    }
}
