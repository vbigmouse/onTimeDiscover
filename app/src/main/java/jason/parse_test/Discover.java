package jason.parse_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by vbigmouse on 2018/4/12.
 */

public class Discover extends AppCompatActivity {

	private GestureDetectorCompat mDetectorCompat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.discover);
		mDetectorCompat = new GestureDetectorCompat(Discover.this, new DiscoverGestureListener());

	    /* listview adapter setting */
		final ListView listView = findViewById(R.id.list_view_discover);

		ArrayList<DiscoverItem> discoverItemList= new ArrayList<>();

		discoverItemList.add(new DiscoverItem(R.drawable.sbu_no_bg, R.string.commuter_jam, R.string.commuter_jam_intro));
		discoverItemList.add(new DiscoverItem(R.drawable.sbu_no_bg, R.string.riddles, R.string.riddles_intro));
		discoverItemList.add(new DiscoverItem(R.drawable.sbu_no_bg, R.string.quotes, R.string.quotes_intro));
		discoverItemList.add(new DiscoverItem(R.drawable.sbu_no_bg, R.string.lost_N_found, R.string.lost_N_found_intro));
		DiscoverItemAdapter adapter = new DiscoverItemAdapter(this, discoverItemList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				DiscoverItem item = (DiscoverItem) parent.getItemAtPosition(position);
				Intent intent = categoryToIntent(item.getCategory());
				if (intent != null) {
					startActivity(intent);
					overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
				}

			}
		});

	}

	public Intent categoryToIntent(int category) {
		Intent intent;
		if (category == R.string.quotes) {
			intent = new Intent(Discover.this, Quote.class);
		} else if (category == R.string.riddles) {
			intent = new Intent(Discover.this, Riddle.class);
		} else if (category == R.string.lost_N_found) {
			intent = new Intent(Discover.this, LostNFound.class);
		} else {
			intent = null;
		}

		return intent;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mDetectorCompat.onTouchEvent(event);
		return super.onTouchEvent(event);
	}

	class DiscoverGestureListener extends GestureDetector.SimpleOnGestureListener
	{
		/* handle swipe left back to discover main page */
		@Override
		public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX,
		                       float velocityY) {
			if (event2.getX() > event1.getX()) {
				Intent intent = new Intent(Discover.this, MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_right_out, R.anim.slide_right_in);
			}

			return true;

		}
	}

}
