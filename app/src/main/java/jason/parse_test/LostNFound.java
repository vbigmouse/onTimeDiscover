package jason.parse_test;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.util.List;

/**
 * Created by vbigmouse on 2018/4/26.
 */

public class LostNFound extends AppCompatActivity {

	private GestureDetectorCompat mDetectorCompat;
	private LostNFoundFragment mLostNFoundFragment;
	private FragmentManager mFragmentManager;

	@Override
	protected  void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lost);

		mLostNFoundFragment = new LostNFoundFragment();
		mFragmentManager = getFragmentManager();
		mDetectorCompat = new GestureDetectorCompat(LostNFound.this, new LostNFoundGestureListener());

		TextView tx_lost = findViewById(R.id.tx_lost);
		tx_lost.setText(R.string.lost_N_found);
		ListView listView = findViewById(R.id.list_view_lost);

		final LostNFoundAdapter<ParseObject> adapter = new LostNFoundAdapter<>(LostNFound.this,
				new ParseQueryAdapter.QueryFactory<ParseObject>() {
					@Override
					public ParseQuery<ParseObject> create() {
						ParseQuery<ParseObject> query = ParseQuery.getQuery("LostAndFound")
								//.orderByAscending("start_time")
								.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);
						return query;
					}

				}, R.layout.lost_item_detail);
		adapter.setObjectsPerPage(3);
		adapter.addOnQueryLoadListener(new ParseQueryAdapter.OnQueryLoadListener<ParseObject>() {
			@Override
			public void onLoading() {
				Toast.makeText(LostNFound.this, "on Loading", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onLoaded(List<ParseObject> objects, Exception e) {
				//Toast.makeText(Riddle.this, "Loaded", Toast.LENGTH_SHORT).show();
				if (e != null && e instanceof ParseException
						&& ((ParseException) e).getCode() != ParseException.CACHE_MISS) {

					Toast.makeText(LostNFound.this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
				}
			}
		});
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ParseObject object = (ParseObject) parent.getItemAtPosition(position);

				mLostNFoundFragment.updateAns(object);
				mFragmentManager.beginTransaction()
						.add(R.id.frame_layout_lost, mLostNFoundFragment)
						.addToBackStack(null)
						.commit();

			}
		});

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mDetectorCompat.onTouchEvent(event);
		return super.onTouchEvent(event);
	}

	class LostNFoundGestureListener extends GestureDetector.SimpleOnGestureListener
	{
		/* handle swipe left back to discover main page */
		@Override
		public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX,
		                       float velocityY) {
			if (event2.getX() > event1.getX()) {
				Intent intent = new Intent(LostNFound.this, Discover.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_right_out, R.anim.slide_right_in);
			}

			return true;

		}
	}

	public void onClickShowAnswer(View v) {
		Toast.makeText(LostNFound.this, "Click Text ", Toast.LENGTH_SHORT).show();
	}
}
