package jason.parse_test;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by vbigmouse on 2018/4/26.
 */

public class LostNFoundAdapter<T extends ParseObject> extends ParseQueryAdapter {

	private GestureDetectorCompat mDetectorCompat;
	private LostNFoundFragment mLostNFoundFragment;
	private FragmentManager mFragmentManager;

	public LostNFoundAdapter(Context context, QueryFactory<T> queryFactory, int itemViewResource) {
		super(context, queryFactory, itemViewResource);
		//mLostNFoundFragment = new LostNFoundFragment();
		//mFragmentManager = ((Activity)context).getFragmentManager();
	}

	@Override
	public View getItemView(final ParseObject object, View v, ViewGroup parent) {
		if (v == null) {
			v = View.inflate(getContext(), R.layout.lost_item_detail, null);
		}
		super.getItemView(object, v, parent);

		if (object != null) {
			TextView tx_lost_time = v.findViewById(R.id.tx_lost_time);
			TextView tx_lost_status = v.findViewById(R.id.tx_lost_status);
			TextView tx_lost_type = v.findViewById(R.id.tx_lost_type);
			TextView tx_lost_item = v.findViewById(R.id.tx_lost_item);
			TextView tx_lost_item_desc = v.findViewById(R.id.tx_lost_item_desc);
			tx_lost_item.setText(object.getString("item"));
			tx_lost_item_desc.setText(object.getString("itemDescription"));
			tx_lost_time.setText(new SimpleDateFormat("MM-dd-yyyy HH:mm", Locale.getDefault()).format(object.getCreatedAt()));
			if (object.getBoolean("isClosed"))
				tx_lost_status.setText(R.string.lost_N_found_status_closed);
			else
				tx_lost_status.setText(R.string.lost_N_found_status_open);
			if (object.getInt("type") == 0)
				tx_lost_type.setText(R.string.lost_N_found_type_lost);
			else
				tx_lost_type.setText(R.string.lost_N_found_type_found);
		}
		/*
		TextView tx_ans = v.findViewById(R.id.tx_riddle_answer);
		TextView tx_show_ans = v.findViewById(R.id.tx_riddle_show_answer);
		tx_show_ans.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mLostNFoundFragment.updateAns(object);
				mFragmentManager.beginTransaction()
						.add(R.id.frame_layout_riddle, mLostNFoundFragment)
						.addToBackStack(null)
						.commit();
			}
		});
		*/
		return v;
	}

	@Override
	public View getNextPageView(View v, ViewGroup parent) {
		if (v == null) {
			v = View.inflate(getContext(), R.layout.category_item_load_more, null);
        /*
         *R.layout.activity_home_footer is your custom layout which contains a TextView named btn_load_more*
         */
		}
		//TextView textView = v.findViewById(R.id.tx_load_more); //set the button
		return v;
	}
}
