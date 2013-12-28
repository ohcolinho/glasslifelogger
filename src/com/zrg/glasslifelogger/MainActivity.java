/*
Copyright 2013 Michael DiGiovanni glass@mikedg.com

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
/*
 * Modified by Zachary Giles 2013, for life logging app. Thanks for the base Micahel.
 * All licenses apply from above. I release any of my changes in public domain or 
 * whatever the necessary licenses are for others to use it.
 */

package com.zrg.glasslifelogger;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
// import android.hardware.Camera;

public class MainActivity extends Activity {

//    private static String TAG = "dgGestureService";
//    Object glassGestureManager;
    private Timer myTimer;
    private TakeBackgroundPicture backGroundPictureTaker;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backGroundPictureTaker = new TakeBackgroundPicture();
        myTimer = new Timer();
		myTimer.schedule(new TimerTask() {			
			@Override
			public void run() {
				TimerMethod();
			}
			
		}, 0, 60000);
		
    }
    
	private void TimerMethod()
	{
		this.runOnUiThread(Timer_Tick);
	}


	private Runnable Timer_Tick = new Runnable() {
		public void run() {
		/* something
		* SurfaceView view = new SurfaceView(this);
		* c.setPreviewDisplay(view.getHolder());
		* c.startPreview();
		* c.takePicture(shutterCallback, rawPictureCallback, jpegPictureCallback);
		*/
		takePicture(getBaseContext(), getIntent());
		// backGroundPictureTaker.takePicture();
		}
	};
	
/*
    private void setWinkReceiverEnabled(boolean b) {
        ComponentName component = new ComponentName(this, EyeGestureReceiver.class);
        getPackageManager().setComponentEnabledSetting(component,
                b ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED : PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);        
    }

*/    

    public void takePicture(Context context, Intent intent) {
        //The sloppy way of launching the default camera taking app
        Intent i = new Intent("android.intent.action.CAMERA_BUTTON");
        context.sendBroadcast(i);
    }

	
	
}
