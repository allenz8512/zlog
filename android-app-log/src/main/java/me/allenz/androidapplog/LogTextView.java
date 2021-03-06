package me.allenz.androidapplog;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class LogTextView extends TextView {

	public LogTextView(final Context context) {
		super(context);
		setDefaultAttributes();
		setAutoScrolling();
	}

	public LogTextView(final Context context, final AttributeSet attrs) {
		super(context, attrs);
		setAutoScrolling();
	}

	public LogTextView(final Context context, final AttributeSet attrs,
			final int defStyle) {
		super(context, attrs, defStyle);
		setAutoScrolling();
	}

	private void setDefaultAttributes() {
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		setTextSize(TypedValue.COMPLEX_UNIT_SP, 10f);
		setBackgroundColor(Color.parseColor("#00ffffff"));
	}

	private void setAutoScrolling() {
		setGravity(Gravity.BOTTOM);
		setSingleLine(false);
		setKeyListener(null);
		setMovementMethod(ScrollingMovementMethod.getInstance());
		addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(final CharSequence s, final int start,
					final int before, final int count) {
			}

			@Override
			public void beforeTextChanged(final CharSequence s,
					final int start, final int count, final int after) {
			}

			@Override
			public void afterTextChanged(final Editable s) {
				final int scrollAmount = getLayout().getLineTop(getLineCount())
						- getHeight();
				if (scrollAmount > 0) {
					scrollTo(0, scrollAmount);
				} else {
					scrollTo(0, 0);
				}
			}
		});
	}

	@Override
	public boolean dispatchTouchEvent(final MotionEvent event) {
		return false;
	}

}
