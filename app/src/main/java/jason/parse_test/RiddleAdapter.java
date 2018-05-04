package jason.parse_test;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

/**
 * Created by vbigmouse on 2018/4/26.
 */

public class RiddleAdapter<T extends ParseObject> extends ParseQueryAdapter {

	private RiddleAnsFragment mRiddleAnsFragment;
	private FragmentManager mFragmentManager;
	private GestureDetectorCompat mDetectorCompat;

	public RiddleAdapter(Context context, QueryFactory<T> queryFactory, int itemViewResource) {
		super(context, queryFactory, itemViewResource);
		mRiddleAnsFragment = new RiddleAnsFragment();
		mFragmentManager = ((Activity)context).getFragmentManager();
	}

	@Override
	public View getItemView(final ParseObject object, View v, ViewGroup parent) {
		if (v == null) {
			v = View.inflate(getContext(), R.layout.riddle_item_detail, null);
		}
		super.getItemView(object, v, parent);

		TextView tx_riddle = v.findViewById(R.id.tx_riddle_content);
		if (object != null) {
			tx_riddle.setText(object.getString("riddle"));
		}
		TextView tx_ans = v.findViewById(R.id.tx_riddle_answer);
		TextView tx_show_ans = v.findViewById(R.id.tx_riddle_show_answer);
		tx_show_ans.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mRiddleAnsFragment.updateAns(object);
				mFragmentManager.beginTransaction()
						.add(R.id.frame_layout_riddle, mRiddleAnsFragment)
						.addToBackStack(null)
						.commit();
			}
		});
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
