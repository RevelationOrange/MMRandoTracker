package com.example.revelroot.mmrandotracker;

import android.content.SharedPreferences;
import java.util.ArrayList;

public class IconStateManager {
    private ArrayList<Boolean[]> iconAlphas = new ArrayList<>();
    private ArrayList<Integer[]> iconBGs = new ArrayList<>();
    private ArrayList<Integer[]> noteIconBGs = new ArrayList<>();
    private ArrayList<Integer> iconStates = new ArrayList<>();
    private ArrayList<Integer> noteIconStates = new ArrayList<>();

    IconStateManager(SharedPreferences sp) {
        this.iconBGs.add(new Integer[]{});
        this.iconAlphas.add(new Boolean[]{});
        // ...
    }

    public int getNIcons() { return this.iconBGs.size(); }
    public int getNNoteIcons() { return this.noteIconBGs.size(); }

    public int getState(int index) { return this.iconStates.get(index); }
    public int getNoteState(int index) { return this.noteIconStates.get(index); }

    public int getBackground(int index) { return this.iconBGs.get(index)[this.iconStates.get(index)]; }
    public int getNoteBackground(int index) { return this.noteIconBGs.get(index)[this.noteIconStates.get(index)]; }

    public boolean getAlpha(int index) { return this.iconAlphas.get(index)[this.iconStates.get(index)]; }

    public int[] advState(int index) {
        int nextState = this.iconStates.get(index)+1;
        int retAlpha;
        if (nextState == this.iconBGs.get(index).length) { nextState = 0; }
        this.iconStates.set(index, nextState);
        retAlpha = this.iconAlphas.get(index)[nextState] ? 1 : 0;
        return new int[]{this.iconBGs.get(index)[this.iconStates.get(index)], retAlpha};
    }
    public int advNoteState(int index) {
        int nextState = this.noteIconStates.get(index)+1;
        if (nextState == this.noteIconBGs.get(index).length) { nextState = 0; }
        this.noteIconStates.set(index, nextState);
        return this.noteIconBGs.get(index)[this.noteIconStates.get(index)];
    }

    public int[] resetState(int index) {
        int retAlpha;
        this.iconStates.set(index, 0);
        retAlpha = this.iconAlphas.get(index)[0] ? 1 : 0;
        return new int[]{this.iconBGs.get(index)[0], retAlpha};
    }
    public int resetNoteState(int index) {
        this.noteIconStates.set(index, 0);
        return this.noteIconBGs.get(index)[0];
    }
}
