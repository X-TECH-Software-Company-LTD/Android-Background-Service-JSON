package com.xtsmm.library.android.json;

import org.json.JSONArray;
import org.json.JSONObject;

public interface OnEachArray<T> {
    void getObject(JSONObject object);
    void getArray(JSONArray object);
}
