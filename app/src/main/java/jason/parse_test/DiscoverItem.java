package jason.parse_test;

/**
 * Created by vbigmouse on 2018/4/12.
 */

public class DiscoverItem {
	private int mImageDrawable;
	private int mCategory;
	private int mIntro;

	public DiscoverItem(int mImageDrawable, int mCategory, int mIntro) {
		this.mImageDrawable = mImageDrawable;
		this.mCategory = mCategory;
		this.mIntro = mIntro;
	}

	public void setImageDrawable(int mImageDrawable) {
		this.mImageDrawable = mImageDrawable;
	}

	public int getImageDrawable() {
		return this.mImageDrawable;
	}

	public void setCategory(int mCategory) {
		this.mCategory = mCategory;
	}

	public int getCategory() {
		return this.mCategory;
	}

	public void setIntro(int mIntro) {
		this.mIntro = mIntro;
	}

	public int getIntro() {
		return this.mIntro;
	}
}
