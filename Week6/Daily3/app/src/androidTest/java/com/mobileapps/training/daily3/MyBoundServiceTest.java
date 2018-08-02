package com.mobileapps.training.daily3;

import android.content.Intent;
import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ServiceTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class MyBoundServiceTest {


    @Rule
    public final ServiceTestRule mServiceRule = ServiceTestRule.withTimeout(60L, TimeUnit.SECONDS);

    @Test
    // test for a service which is started with bindService
    public void testWithBoundService() throws TimeoutException {
        Intent serviceIntent =
                new Intent(InstrumentationRegistry.getTargetContext(),
                        MyBoundService.class);
        // Bind the service and grab a reference to the binder.
        IBinder binder = mServiceRule.bindService(serviceIntent);
        MyBoundService service =
                ((MyBoundService.MyBinder) binder).getService();
        assertTrue("True wasn't returned", service.returnState());
    }
}
