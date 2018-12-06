package com.example.revelroot.mmrandotracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private IconStateManager iconManager;
    private ArrayList<ImageButton> allButtons = new ArrayList<>();
    private ArrayList<ImageButton> allNoteButtons = new ArrayList<>();
    private ArrayList<Integer> prizeNotesIDs = new ArrayList<>();
    private int iconWidth, iconHeight;

    private static final String SHARED_PREFS = "shared_preferences";
    private final float MARKED_ALPHA = 1.0f, UNMARKED_ALPHA = 0.4f;
    private static int idStart = 100;
    private static int idCounter = idStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ConstraintLayout trackerPanel;
        ConstraintSet cset = new ConstraintSet();
        ImageButton prevButton = null;
        int screenWidth, screenHeight, iconSizeRestriction, newRowCounter = 1, noteIndex = 0;
        final int maxIcons = 7;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        iconManager = new IconStateManager(sp);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        iconSizeRestriction = Math.min(screenWidth, screenHeight);
        iconWidth = (int) Math.floor(iconSizeRestriction / maxIcons);
        iconHeight = iconWidth;
        Bitmap bmp = ((BitmapDrawable) getDrawable(R.drawable.bomb)).getBitmap();
        // you know, actual icon files, eventually
        Bitmap resized = Bitmap.createScaledBitmap(bmp, iconWidth, iconHeight, false);
    }
}
