package com.em.activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.em.R;

public class PreferencesActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}