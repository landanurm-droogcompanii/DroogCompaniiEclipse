package ru.droogcompanii.application.test;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import android.content.res.Resources;

import ru.droogcompanii.application.R;
import ru.droogcompanii.application.activities.partner_category_list_activity.PartnerCategoryListActivity;

@RunWith(RobolectricTestRunner.class)
public class TestRobotiumInstallation {

    @Test
    public void testAppNameResource() throws Exception {
        Resources resources = new PartnerCategoryListActivity().getResources();
        String actualAppName = resources.getString(R.string.app_name);
        String expectedAppName = "DroogCompanii";
        Assert.assertEquals(expectedAppName, actualAppName);
    }
    
}
