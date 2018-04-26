package jason.parse_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vbigmouse on 2018/4/12.
 */

public class DiscoverItemAdapter extends ArrayAdapter<DiscoverItem> {
	//private Context mContext;
	//private List<DiscoverItem> mDiscoverList = new ArrayList<>();

	public DiscoverItemAdapter(Context context, ArrayList<DiscoverItem> list) {
		super(context, 0, list);
	//	mContext = context;
	//	mDiscoverList = list;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		DiscoverItem item = getItem(position);

		if (v == null) {
			v = LayoutInflater.from(getContext()).inflate(R.layout.discover_item_detail, parent, false);
		}
		ImageView image= v.findViewById(R.id.img_discover_item);
		TextView category = v.findViewById(R.id.tx_discover_item_category);
		TextView intro = v.findViewById(R.id.tx_discover_item_intro);

		image.setImageResource(item.getImageDrawable());
		category.setText(item.getCategory());
		intro.setText(item.getIntro());

		return v;
	}
}
