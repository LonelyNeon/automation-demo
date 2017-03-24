package com.example.uiautomatortest;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.util.Log;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class UiAutomatorTest {

    private static final String BASIC_SAMPLE_PACKAGE
            = "com.skysoft.kkbox.android";
    private static final int LAUNCH_TIMEOUT = 50000;
    //private static final String STRING_TO_BE_TYPED = "UiAutomator";
    private UiDevice mDevice;
    private static final String LOGTAG = "UiAutomator";
    private UiSelector backSelector = new UiSelector().description("Navigate up");
    private boolean startFromHome = false;



    @Before
    public void startActivityFromHomeScreen()
    {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        /*
        Log.d(LOGTAG, "press home");

        mDevice.pressHome();


        final String launcherPackage = mDevice.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);


        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);


        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT);

        Log.d(LOGTAG, "activity start success");
        */


    }

    @Test
    public void discoveryJumboCoverTest() throws UiObjectNotFoundException {
        UiObject featureTab = mDevice.findObject(new UiSelector()
                .text("FEATURED")
                .className("android.widget.TextView"));

        assertTrue(featureTab.exists());
        featureTab.click();

        UiObject jumboCard = mDevice.findObject(new UiSelector()
                .resourceId("com.skysoft.kkbox.android:id/header_cover_layout"));

        assertTrue(jumboCard.exists());

        UiObject cover = mDevice.findObject(new UiSelector()
                .resourceId("com.skysoft.kkbox.android:id/image_cover_second"));

        cover.click();
        //SystemClock.sleep(10000);
        mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE,"view_blur_background")),20000);

        UiObject detailView = mDevice.findObject(new UiSelector()
                .resourceId("com.skysoft.kkbox.android:id/view_blur_background"));

        assertTrue(detailView.exists());

        SystemClock.sleep(5000);
        UiObject back = mDevice.findObject(backSelector);
        back.click();

        assertTrue(checkReturnToDiscovery());
    }

    @Test
    public void discoveryChartTest() throws UiObjectNotFoundException {
        UiObject chartTab = mDevice.findObject(new UiSelector()
                .text("CHART")
                .className("android.widget.TextView"));

        assertTrue(chartTab.exists());
        chartTab.click();

        UiObject viewCapsuleRecycle = mDevice.findObject(new UiSelector()
                .resourceId("com.skysoft.kkbox.android:id/view_capsule_recycler"));

        assertTrue(viewCapsuleRecycle.exists());
        assertEquals(2, viewCapsuleRecycle.getChildCount());

        UiObject layoutRating = mDevice.findObject(new UiSelector()
                .resourceId("com.skysoft.kkbox.android:id/layout_rating_text"));

        layoutRating.click();
        //SystemClock.sleep(10000);
        mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE,"view_blur_background")),20000);

        UiObject detailView = mDevice.findObject(new UiSelector()
                .resourceId("com.skysoft.kkbox.android:id/view_blur_background"));

        assertTrue(detailView.exists());

        SystemClock.sleep(5000);
        UiObject back = mDevice.findObject(backSelector);
        back.click();

        assertTrue(checkReturnToDiscovery());
    }

    @Test
    public void discoveryChartMoreActionTest() throws UiObjectNotFoundException {
        UiObject chartTab = mDevice.findObject(new UiSelector()
                .text("CHART")
                .className("android.widget.TextView"));

        assertTrue(chartTab.exists());
        chartTab.click();

        UiObject layoutRating = mDevice.findObject(new UiSelector()
                .resourceId("com.skysoft.kkbox.android:id/layout_rating"));

        assertTrue(layoutRating.exists());

        UiObject moreAction = layoutRating.getChild(new UiSelector().index(2)
                                .resourceId("com.skysoft.kkbox.android:id/button_overflow"));

        assertTrue(moreAction.exists());
        moreAction.click();

        UiObject listView = mDevice.findObject(new UiSelector().className("android.widget.ListView"));
        assertTrue(listView.exists());
        //Log.d(LOGTAG, String.valueOf(listView.getChildCount()));
        assertEquals(2,listView.getChildCount());
        assertEquals("Add to My Library", listView.getChild(new UiSelector().index(0))
                .getChild(new UiSelector().index(0))
                .getChild(new UiSelector().index(0))
                .getText());
        assertEquals("Share", listView.getChild(new UiSelector().index(1))
                .getChild(new UiSelector().index(0))
                .getChild(new UiSelector().index(0))
                .getText());
        SystemClock.sleep(3000);
        mDevice.pressBack();
        assertTrue(checkReturnToDiscovery());
    }

    @Test
    public void searchTest() throws UiObjectNotFoundException {
        UiObject searchIcon = mDevice.findObject(new UiSelector()
                .resourceId("com.skysoft.kkbox.android:id/menu_global_search")
                .description("Search"));

        searchIcon.click();
        //SystemClock.sleep(20000);


        mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE,"search_bar")),20000);

        UiObject searchBar = mDevice.findObject(new UiSelector()
                .resourceId("com.skysoft.kkbox.android:id/search_bar"));

        assertTrue(searchBar.exists());

        UiObject searchEditText = mDevice.findObject(new UiSelector()
                .resourceId("com.skysoft.kkbox.android:id/search_src_text"));

        assertTrue(searchEditText.exists());

        searchEditText.setText("Taylor Swift");
        mDevice.pressEnter();

        mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE,"button_more")),30000);
        //SystemClock.sleep(30000);
        UiObject allTab = mDevice.findObject(new UiSelector()
                .text("Top Result")
                .className("android.widget.TextView"));

        assertTrue(allTab.exists());
        //allTab.click();

        UiObject labelTitle = mDevice.findObject(new UiSelector()
                .resourceId("com.skysoft.kkbox.android:id/button_more"));

        assertTrue(labelTitle.exists());

        UiObject labelTitleText = mDevice.findObject(new UiSelector()
                .text("Top Result")
                .resourceId("com.skysoft.kkbox.android:id/label_title"));

        assertTrue(labelTitleText.exists());

        UiObject layoutView = mDevice.findObject(new UiSelector()
                                .resourceId("com.skysoft.kkbox.android:id/layout_view"));

        assertTrue(layoutView.exists());
        layoutView.click();

        mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE,"view_blur_background")),20000);

        UiObject detailView = mDevice.findObject(new UiSelector()
                .resourceId("com.skysoft.kkbox.android:id/view_blur_background"));

        assertTrue(detailView.exists());

        SystemClock.sleep(5000);

        UiObject back = mDevice.findObject(backSelector);
        back.click();
        back.click();
        back.click();

        assertTrue(checkReturnToDiscovery());

    }

    @Test
    public void trackLayoutTest() throws UiObjectNotFoundException {
        //com.skysoft.kkbox.android:id/button_overflow

        UiObject searchIcon = mDevice.findObject(new UiSelector()
                .resourceId("com.skysoft.kkbox.android:id/menu_global_search")
                .description("Search"));

        searchIcon.click();
        //SystemClock.sleep(20000);


        mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE,"search_bar")),20000);

        UiObject layoutTrackMoreAction = mDevice.findObject(new UiSelector()
                .resourceId("com.skysoft.kkbox.android:id/button_overflow"));

        assertTrue(layoutTrackMoreAction.exists());

        layoutTrackMoreAction.click();

        mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "button_cancel")),20000);
        //android:id/content
        UiObject contentLayout = mDevice.findObject(new UiSelector()
                                    .resourceId("android:id/content"));

        assertTrue(contentLayout.exists());
        //com.skysoft.kkbox.android:id/button_cancel

        UiObject cancelButton = mDevice.findObject(new UiSelector()
                .resourceId("com.skysoft.kkbox.android:id/button_cancel"));

        assertTrue(cancelButton.exists());

        cancelButton.click();

        UiObject back = mDevice.findObject(backSelector);
        back.click();

        assertTrue(checkReturnToDiscovery());


    }

    @Test
    public void discoveryGenreAndMoodTest() throws UiObjectNotFoundException {
        UiObject genreTab = mDevice.findObject(new UiSelector()
                .textContains("GENRE")
                .className("android.widget.TextView"));

        assertTrue(genreTab.exists());
        genreTab.click();

        UiObject categoryCard = mDevice.findObject(new UiSelector()
                .resourceId("com.skysoft.kkbox.android:id/mih_category_card1"));

        assertTrue(categoryCard.exists());

        categoryCard.click();

        mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE,"collapsing_toolbar")), 20000);

        UiObject collapsingToolbar = mDevice.findObject(new UiSelector()
                                        .resourceId("com.skysoft.kkbox.android:id/collapsing_toolbar"));

        assertTrue(collapsingToolbar.exists());

        SystemClock.sleep(5000);
        UiObject toolbar = mDevice.findObject(new UiSelector()
                            .resourceId("com.skysoft.kkbox.android:id/toolbar"));

        assertTrue(toolbar.exists());

        UiObject back = toolbar.getChild(new UiSelector().index(0));
        back.click();

        assertTrue(checkReturnToDiscovery());
    }


    private boolean checkReturnToDiscovery() throws UiObjectNotFoundException {
        //com.skysoft.kkbox.android:id/discover_content_layout

        mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "discover_content_layout")),20000);
        UiObject discoveryLayout = mDevice.findObject(new UiSelector()
                .resourceId("com.skysoft.kkbox.android:id/discover_content_layout"));

        if (discoveryLayout.exists()){
            return true;
        } else {
            return false;
        }


    }

}
