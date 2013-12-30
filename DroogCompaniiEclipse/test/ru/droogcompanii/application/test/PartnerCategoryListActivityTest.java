package ru.droogcompanii.application.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import ru.droogcompanii.application.activities.helpers.ActionBarListActivity;
import ru.droogcompanii.application.activities.partner_category_list_activity.PartnerCategoryListActivity;
import ru.droogcompanii.application.activities.partner_list_activity.PartnerListActivity;
import ru.droogcompanii.application.data.data_structure.PartnerCategory;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

@RunWith(RobolectricTestRunner.class)
public class PartnerCategoryListActivityTest {
	
	private ActionBarListActivity activity;
	private List<String> items;
	private ListView listView;
	private ListAdapter adapter;

	@Before
	public void setUp() {
		activity = Robolectric.buildActivity(PartnerCategoryListActivity.class)
		                      .create()
		                      .get();
		listView = activity.getListView();
		int numberOfItems = listView.getCount();
		adapter = listView.getAdapter();
		for (int i = 0; i < numberOfItems; ++i) {
			PartnerCategory item = (PartnerCategory) adapter.getItem(i);
			items.add(item.title);
		}
	}

    @Test
    public void testAppNameResource() throws Exception {
    	performListItemClick(0);
        ShadowActivity shadowActivity = Robolectric.shadowOf_((Activity) activity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = Robolectric.shadowOf_(startedIntent);
        String actualStartedActivityName = shadowIntent.getComponent().getClassName();
        String expectedStartedActivityName = PartnerListActivity.class.getName();
        assertEquals(expectedStartedActivityName, actualStartedActivityName);
    }
    
    private void performListItemClick(int position) {
    	View view = adapter.getView(position, null, null);
    	long id = adapter.getItemId(position);
    	listView.performItemClick(view, position, id);
    }
    
}
