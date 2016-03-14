package weekselection.com.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.TextView;


import weekselection.com.library.util.TypefaceUtil;

/**
 * Created by lhz on 3/11/2016.
 */
public class CheckedTextView extends android.widget.CheckedTextView {

	private CircleDrawable mBackground;

	public interface OnCheckedChangeListener{
		void onCheckedChanged(CheckedTextView view, boolean checked);
	}

	private OnCheckedChangeListener mCheckedChangeListener;

	public CheckedTextView(Context context) {
		super(context);

		init(context, null, 0, 0);
	}

	public CheckedTextView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init(context, attrs, 0, 0);
	}

	public CheckedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		init(context, attrs, defStyleAttr, 0);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public CheckedTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);

		init(context, attrs, defStyleAttr, defStyleRes);
	}

	protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
		setGravity(Gravity.CENTER);
		setPadding(0, 0, 0, 0);

		mBackground = new CircleDrawable();
		mBackground.setInEditMode(isInEditMode());
		mBackground.setAnimEnable(false);
		setBackground(this, mBackground);
		mBackground.setAnimEnable(true);
	}

	public void setOnCheckedChangeListener(OnCheckedChangeListener listener){
		mCheckedChangeListener = listener;
	}

	@Override
	public void setTextAppearance(int resId) {
		applyTextAppearance(this, resId);
	}

	@Override
	public void setTextAppearance(Context context, int resId) {
		applyTextAppearance(this, resId);
	}

	@Override
	public void setBackgroundColor(int color) {
		mBackground.setColor(color);
	}

	/**
	 * Set the duration of background's animation.
	 * @param duration The duration
	 */
	public void setAnimDuration(int duration) {
		mBackground.setAnimDuration(duration);
	}

	public void setInterpolator(Interpolator in, Interpolator out) {
		mBackground.setInterpolator(in, out);
	}

	@Override
	public void setChecked(boolean checked) {
		boolean oldCheck = isChecked();

		if(oldCheck != checked) {
			super.setChecked(checked);

			if(mCheckedChangeListener != null)
				mCheckedChangeListener.onCheckedChanged(this, checked);
		}
	}

	public void setCheckedImmediately(boolean checked){
		mBackground.setAnimEnable(false);
		setChecked(checked);
		mBackground.setAnimEnable(true);
	}
	public static void setBackground(View v, Drawable drawable){
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
			v.setBackground(drawable);
		else
			v.setBackgroundDrawable(drawable);
	}
	public static void applyTextAppearance(TextView v, int resId){
		if(resId == 0)
			return;

		String fontFamily = null;
		int typefaceIndex = -1;
		int styleIndex = -1;
		int shadowColor = 0;
		float dx = 0, dy = 0, r = 0;

		TypedArray appearance = v.getContext().obtainStyledAttributes(resId, R.styleable.TextAppearance);
		if (appearance != null) {
			int n = appearance.getIndexCount();
			for (int i = 0; i < n; i++) {
				int attr = appearance.getIndex(i);

				if (attr == R.styleable.TextAppearance_android_textColorHighlight) {
					v.setHighlightColor(appearance.getColor(attr, 0));

				} else if (attr == R.styleable.TextAppearance_android_textColor) {
					v.setTextColor(appearance.getColorStateList(attr));

				} else if (attr == R.styleable.TextAppearance_android_textColorHint) {
					v.setHintTextColor(appearance.getColorStateList(attr));

				} else if (attr == R.styleable.TextAppearance_android_textColorLink) {
					v.setLinkTextColor(appearance.getColorStateList(attr));

				} else if (attr == R.styleable.TextAppearance_android_textSize) {
					v.setTextSize(TypedValue.COMPLEX_UNIT_PX, appearance.getDimensionPixelSize(attr, 0));

				} else if (attr == R.styleable.TextAppearance_android_typeface) {
					typefaceIndex = appearance.getInt(attr, -1);

				} else if (attr == R.styleable.TextAppearance_android_fontFamily) {
					fontFamily = appearance.getString(attr);

				} else if (attr == R.styleable.TextAppearance_tv_fontFamily) {
					fontFamily = appearance.getString(attr);

				} else if (attr == R.styleable.TextAppearance_android_textStyle) {
					styleIndex = appearance.getInt(attr, -1);

				} else if (attr == R.styleable.TextAppearance_android_textAllCaps) {
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH)
						v.setAllCaps(appearance.getBoolean(attr, false));

				} else if (attr == R.styleable.TextAppearance_android_shadowColor) {
					shadowColor = appearance.getInt(attr, 0);

				} else if (attr == R.styleable.TextAppearance_android_shadowDx) {
					dx = appearance.getFloat(attr, 0);

				} else if (attr == R.styleable.TextAppearance_android_shadowDy) {
					dy = appearance.getFloat(attr, 0);

				} else if (attr == R.styleable.TextAppearance_android_shadowRadius) {
					r = appearance.getFloat(attr, 0);

				} else if (attr == R.styleable.TextAppearance_android_elegantTextHeight) {
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
						v.setElegantTextHeight(appearance.getBoolean(attr, false));

				} else if (attr == R.styleable.TextAppearance_android_letterSpacing) {
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
						v.setLetterSpacing(appearance.getFloat(attr, 0));

				} else if (attr == R.styleable.TextAppearance_android_fontFeatureSettings) {
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
						v.setFontFeatureSettings(appearance.getString(attr));

				}
			}

			appearance.recycle();
		}

		if (shadowColor != 0)
			v.setShadowLayer(r, dx, dy, shadowColor);

		Typeface tf = null;
		if (fontFamily != null) {
			tf = TypefaceUtil.load(v.getContext(), fontFamily, styleIndex);
			if (tf != null)
				v.setTypeface(tf);
		}
		if(tf != null) {
			switch (typefaceIndex) {
				case 1:
					tf = Typeface.SANS_SERIF;
					break;
				case 2:
					tf = Typeface.SERIF;
					break;
				case 3:
					tf = Typeface.MONOSPACE;
					break;
			}
			v.setTypeface(tf, styleIndex);
		}
	}

}
