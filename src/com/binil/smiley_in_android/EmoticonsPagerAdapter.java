package com.binil.smiley_in_android;

import java.util.ArrayList;

import com.binil.smiley_in_android.EmoticonsGridAdapter.KeyClickListener;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

public class EmoticonsPagerAdapter extends PagerAdapter {

	ArrayList<String> emoticons;
	private static final int NO_OF_EMOTICONS_PER_PAGE = 30;
	FragmentActivity mActivity;
	KeyClickListener mListener;

	public EmoticonsPagerAdapter(FragmentActivity activity,
			ArrayList<String> emoticons, KeyClickListener listener) {
		this.emoticons = emoticons;
		this.mActivity = activity;
		this.mListener = listener;
	}

	@Override
	public int getCount() {
		return (int) Math.ceil((double) emoticons.size()
				/ (double) NO_OF_EMOTICONS_PER_PAGE);
	}

	@Override
	public Object instantiateItem(View collection, int position) {

		View layout = mActivity.getLayoutInflater().inflate(
				R.layout.emoticons_grid, null);

		int initialPosition = position * NO_OF_EMOTICONS_PER_PAGE;
		ArrayList<String> emoticonsInAPage = new ArrayList<String>();

		Log.e("tag", "initialPosition " +  initialPosition);
		Log.e("tag", " NO_OF_EMOTICONS_PER_PAGE) " +  NO_OF_EMOTICONS_PER_PAGE);
		Log.e("tag", "position " +  position);
		Log.e("tag", " emoticons.size() " +  emoticons.size());
		
		for (int i = initialPosition; i < initialPosition
				+ NO_OF_EMOTICONS_PER_PAGE
				&& i < emoticons.size(); i++) {
			emoticonsInAPage.add(emoticons.get(i));
		}

		GridView grid = (GridView) layout.findViewById(R.id.emoticons_grid);
		EmoticonsGridAdapter adapter = new EmoticonsGridAdapter(
				mActivity.getApplicationContext(), emoticonsInAPage, position,
				mListener);
		grid.setAdapter(adapter);

		((ViewPager) collection).addView(layout);

		return layout;
	}

	@Override
	public void destroyItem(View collection, int position, Object view) {
		((ViewPager) collection).removeView((View) view);
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}
}