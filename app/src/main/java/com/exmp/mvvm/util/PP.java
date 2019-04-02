package com.exmp.mvvm.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

public enum PP {
    NOTE,
    LAST_SEQNO
    ;

    private static final String DEFVALUE_STRING = "";
    private static final float DEFVALUE_FLOAT = -1f;
    private static final int DEFVALUE_INT = -1;
    private static final long DEFVALUE_LONG = -1L;
    private static final boolean DEFVALUE_BOOLEAN = false;

    public static void CREATE(Context context) {
        PREFERENCES = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener var) {
        PREFERENCES.registerOnSharedPreferenceChangeListener(var);
    }

    public static void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener var) {
        PREFERENCES.registerOnSharedPreferenceChangeListener(var);
    }

    private static SharedPreferences PREFERENCES;

    //@formatter:off
    public void set(boolean v) {
        PREFERENCES.edit().putBoolean(name(), v).apply();
    }

    public void set(int v) {
        PREFERENCES.edit().putInt(name(), v).apply();
    }

    public void set(long v) {
        PREFERENCES.edit().putLong(name(), v).apply();
    }

    public void set(float v) {
        PREFERENCES.edit().putFloat(name(), v).apply();
    }

    public void set(String v) {
        PREFERENCES.edit().putString(name(), v).apply();
    }

    public void set(Set<String> v) {
        PREFERENCES.edit().putStringSet(name(), v).apply();
    }

    public boolean getBoolean() {
        return getBoolean(DEFVALUE_BOOLEAN);
    }

    public int getInt() {
        return getInt(DEFVALUE_INT);
    }

    public long getLong() {
        return getLong(DEFVALUE_LONG);
    }

    public float getFloat() {
        return getFloat(DEFVALUE_FLOAT);
    }

    public String getString() {
        return getString(DEFVALUE_STRING);
    }

    public Set<String> getStringSet() {
        return getStringSet(null);
    }

    public boolean getBoolean(boolean defValues) {
        return PREFERENCES.getBoolean(name(), defValues);
    }

    public int getInt(int defValues) {
        return PREFERENCES.getInt(name(), defValues);
    }

    public long getLong(long defValues) {
        return PREFERENCES.getLong(name(), defValues);
    }

    public float getFloat(float defValues) {
        return PREFERENCES.getFloat(name(), defValues);
    }

    public String getString(String defValues) {
        return PREFERENCES.getString(name(), defValues);
    }

    public Set<String> getStringSet(Set<String> defValues) {
        return PREFERENCES.getStringSet(name(), defValues);
    }
    //@formatter:on

    public void toggle() {
        set(!is());
    }

    public String get(String defValue) {
        return getString(defValue);
    }

    public String get() {
        return getString();
    }

    public boolean is() {
        return getBoolean();
    }

    public boolean is(boolean defValues) {
        return getBoolean(defValues);
    }

    public boolean contain() {
        return PREFERENCES.contains(name());
    }

    public boolean remove() {
        return PREFERENCES.edit().remove(name()).commit();
    }

    public static boolean clear() {
        return PREFERENCES.edit().clear().commit();
    }

    //custom
    public static void noMoreDlg(Object message, boolean b) {
    }
}
