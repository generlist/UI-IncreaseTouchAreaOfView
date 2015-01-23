package com.example.increasetoucharea;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TouchDelegate;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	LinearLayout parent;
	ImageView img1, img2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		parent = (LinearLayout) findViewById(R.id.parent);
		img1 = (ImageView) findViewById(R.id.imageView1);
		img2 = (ImageView) findViewById(R.id.imageView2);

		increaseClickArea(parent, img2);

		img1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this,
						"touch area seems to be small	", Toast.LENGTH_LONG)
						.show();
			}
		});

		img2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this,
						"touch area seems to be large	", Toast.LENGTH_LONG)
						.show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public static void increaseClickArea(View parent, View child) {

		// increase the click area with delegateArea, can be used in + create
		// icon
		final View chicld = child;
		parent.post(new Runnable() {
			public void run() {
				// Post in the parent's message queue to make sure the
				// parent
				// lays out its children before we call getHitRect()
				Rect delegateArea = new Rect();
				View delegate = chicld;
				delegate.getHitRect(delegateArea);
				delegateArea.top -= 100;
				delegateArea.bottom += 100;
				delegateArea.left -= 100;
				delegateArea.right += 100;
				TouchDelegate expandedArea = new TouchDelegate(delegateArea,
						delegate);
				// give the delegate to an ancestor of the view we're
				// delegating the
				// area to
				if (View.class.isInstance(delegate.getParent())) {
					((View) delegate.getParent())
							.setTouchDelegate(expandedArea);
				}
			};
		});

	}
}
