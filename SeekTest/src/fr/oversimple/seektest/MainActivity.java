package fr.oversimple.seektest;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView tv = (TextView) findViewById(R.id.versionStringTextview);
		String version = getScapiVersion();
		
		if(version.equals("")) {
			tv.setText("SEEK is not supported by this mobile");
		} else {
			tv.setText("La version est : "  + version);
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private String getScapiVersion() {
		try {
			PackageInfo packageInfo = getPackageManager().getPackageInfo(
					"android.smartcard", 0);
			return packageInfo.versionName;
		} catch (PackageManager.NameNotFoundException e1) {
			try {
				PackageInfo packageInfo = getPackageManager().getPackageInfo(
						"org.simalliance.openmobileapi.service", 0);
				return packageInfo.versionName;
			} catch (PackageManager.NameNotFoundException e2) {
				return "";
			}
		}
	}

}
