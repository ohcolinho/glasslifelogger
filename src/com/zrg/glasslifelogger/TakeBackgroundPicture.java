/* Public Domain - Zachary Giles 2013 */

package com.zrg.glasslifelogger;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.Window;
import android.view.WindowManager;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;

public class TakeBackgroundPicture extends Activity {
    private Camera camera;
    private SurfaceView mSurfaceView;
    SurfaceHolder mSurfaceHolder;

    ShutterCallback shutter = new ShutterCallback(){

        @Override
        public void onShutter() {
            // TODO Auto-generated method stub
            // No action to be perfomed on the Shutter callback.

        }

       };
       
    PictureCallback raw = new PictureCallback(){
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            // TODO Auto-generated method stub
            // No action taken on the raw data. Only action taken on jpeg data.
      }

       };

    PictureCallback jpeg = new PictureCallback(){

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            // TODO Auto-generated method stub

            FileOutputStream outStream = null;
            try{
                outStream = new FileOutputStream("/sdcard/test.jpg");
                outStream.write(data);
                outStream.close();
            }catch(FileNotFoundException e){
                Log.d("Camera", e.getMessage());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                Log.d("Camera", e.getMessage());
            }

        }

       };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		SurfaceView view = new SurfaceView(this);
		try {
			camera.setPreviewDisplay(view.getHolder());
			camera.startPreview();
		} catch ( Exception e ) {
				Log.d("Camera", e.getMessage());
		}
		}

    void takePicture() {
        // TODO Auto-generated method stub
        camera.takePicture(shutter, raw, jpeg);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    	camera = Camera.open();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        camera.stopPreview();
        camera.release();
    }
    }