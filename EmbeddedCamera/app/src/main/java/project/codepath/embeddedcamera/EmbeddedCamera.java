package project.codepath.embeddedcamera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class EmbeddedCamera extends AppCompatActivity {

    private CameraSurfaceView cameraSurfaceView;
    private ImageView imageView;
    private FrameLayout frameLayout;
    private Button snapPhoto;
    private boolean takePicture = false;
    private Bitmap image = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_embedded_camera);

        setUpCamera();
    }

    public void setUpCamera() {
        cameraSurfaceView = new CameraSurfaceView(getApplicationContext());
        imageView = new ImageView(getApplicationContext());

        imageView.setBackgroundColor(Color.GRAY);
        frameLayout = (FrameLayout) findViewById(R.id.flCamera);
        snapPhoto = (Button) findViewById(R.id.btnSnap);
        frameLayout.addView(imageView);
        frameLayout.addView(cameraSurfaceView);

        frameLayout.bringChildToFront(imageView);

    }

    public void captureHandler(View view) {

        if(takePicture) {
            cameraSurfaceView.capture(jpegHandler);

        }else {
            takePicture = true;
            frameLayout.bringChildToFront(cameraSurfaceView);
            imageView.setImageBitmap(null);
            snapPhoto.setText("Capture");
        }

    }

    public Camera.PictureCallback jpegHandler = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            image = BitmapFactory.decodeByteArray(data, 0, data.length);
            imageView.setImageBitmap(image);
            frameLayout.bringChildToFront(imageView);
            snapPhoto.setText("Take Picture");
            takePicture = false;
        }
    };
}
