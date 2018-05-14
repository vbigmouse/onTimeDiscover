package jason.parse_test;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;

/**
 * Created by vbigmouse on 2018/5/1.
 */

public class LostNFoundFragment extends Fragment {
	private String ans;
	public void updateAns(ParseObject obj) {
		this.ans = obj.getString("item");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.lost_detail, container, false);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		final TextView tx_lost_item = getView().findViewById(R.id.tx_lost_item);
		tx_lost_item.setText(ans);
	}
}
