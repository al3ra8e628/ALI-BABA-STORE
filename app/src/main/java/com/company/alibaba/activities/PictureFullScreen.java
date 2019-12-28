package com.company.alibaba.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.company.alibaba.R;
import com.company.alibaba.entities.FullScreenPicture;
import com.company.alibaba.utils.Languages;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class PictureFullScreen extends AppCompatActivity implements Languages {
    ImageView pictureFullScreen ;
    FullScreenPicture picture ;
    ProgressBar progressBar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_full_screen);
        initial() ;
        loadPicture() ;
    }


    private void initial() {
        pictureFullScreen = (ImageView) findViewById(R.id.pictureFullScreen);
        progressBar = (ProgressBar) findViewById(R.id.fullScreenProgressBar);
        picture = (FullScreenPicture) getIntent().getSerializableExtra("picture");
    }


    private void loadPicture() {
        Picasso.get()
                .load(picture.getUrl())
                //.resize(150 , 250)
                .into(pictureFullScreen, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }
                    @Override
                    public void onError(Exception e) {

                    }
                });
    }


    @Override
    public void setDirection(String lang) {

    }
}
