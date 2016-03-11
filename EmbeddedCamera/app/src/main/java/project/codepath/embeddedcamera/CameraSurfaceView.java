package project.codepath.embeddedcamera;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by jnagaraj on 3/3/16.
 */
public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder mHolder;
    public Camera camera;

    public CameraSurfaceView(Context context) {
        super(context);
        mHolder = getHolder();
        mHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        camera = Camera.open();

        try {
            camera.setPreviewDisplay(mHolder);

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        Camera.Parameters params = camera.getParameters();

        Camera.Size size = params.getSupportedPreviewSizes().get(0);

        params.setPreviewSize(size.width, size.height);
        camera.setParameters(params);
        camera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {


        camera.stopPreview();
        camera = null;

    }

    public void capture(Camera.PictureCallback jpegHandler) {
        camera.takePicture(null, null, jpegHandler);

    }
}
