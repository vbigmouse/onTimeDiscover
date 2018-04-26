package jason.parse_test;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.GestureDetectorCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

/**
 * Created by vbigmouse on 2018/4/26.
 */

public class RiddleAdapter<T extends ParseObject> extends ParseQueryAdapter {

	private GestureDetectorCompat mDetectorCompat;

	public RiddleAdapter(Context context, QueryFactory<T> queryFactory, int itemViewResource) {
		super(context, queryFactory, itemViewResource);
	}

	@Override
	public View getItemView(ParseObject object, View v, ViewGroup parent) {
		if (v == null) {
			v = View.inflate(getContext(), R.layout.riddle_item_detail, null);
		}
		super.getItemView(object, v, parent);

		TextView tx_riddle = v.findViewById(R.id.tx_riddle_content);
		if (object != null) {
			tx_riddle.setText(object.getString("riddle"));
		}
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
