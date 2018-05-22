package com.myplas.q;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.animation.Animation;
import android.widget.ImageView;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {
        String className = "com.myplas.q.myself.vip.EstablishedVipActivity";
        try {
            Class<?> aClass = Class.forName(className);
            Constructor<?> constructor = aClass.getConstructor();
            Object o = constructor.newInstance();

            Method test = o.getClass().getMethod("test",String.class);
            test.invoke(o,"123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}