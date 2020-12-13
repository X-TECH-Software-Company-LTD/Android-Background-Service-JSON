package com.xtsmm.library.android.json;

import android.app.Activity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class xJson {
    private Activity activity;
    public xJson(Activity activity) {
        this.activity = activity;
    }
    public void request(String method, String url, JSONArray headers,JSONArray body, OnEventListener callback){
        Map<String, String>  params = new HashMap<String, String>();
        if(headers==null){ params = null ;}else {
            Map<String, String> finalParams1 = params;
            xEach(headers, new OnEachArray() {
                @Override
                public void getObject(JSONObject object) {
                    Iterator<String> iter = object.keys();
                    while (iter.hasNext()) {
                        String key = iter.next();
                        try {
                            finalParams1.put(key, object.getString(key));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void getArray(JSONArray arr) {

                }
            });
        }

        Map<String, String>  params2 = new HashMap<String, String>();
        if(headers==null){ params2 = null ;}else {
            Map<String, String> finalParams2 = params2;
            xEach(body, new OnEachArray() {
                @Override
                public void getObject(JSONObject object) {
                    Iterator<String> iter = object.keys();
                    while (iter.hasNext()) {
                        String key = iter.next();
                        try {
                            finalParams2.put(key, object.getString(key));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void getArray(JSONArray arr) {

                }
            });
        }

        Map<String, String> finalParams = params;
        int requestMethod=Request.Method.GET;
        if(method.toUpperCase().equals("GET") || method.equals("get")){
            requestMethod=Request.Method.GET;
        }
        else if(method.toUpperCase().equals("DELETE") || method.equals("delete")){
            requestMethod=Request.Method.DELETE;
        }
        else if(method.toUpperCase().equals("HEAD") || method.equals("head")){
            requestMethod=Request.Method.HEAD;
        }
        else if(method.toUpperCase().equals("OPTIONS") || method.equals("options")){
            requestMethod=Request.Method.OPTIONS;
        }
        else if(method.toUpperCase().equals("PATCH") || method.equals("patch")){
            requestMethod=Request.Method.PATCH;
        }
        else if(method.toUpperCase().equals("POST") || method.equals("post")){
            requestMethod=Request.Method.POST;
        }
        else if(method.toUpperCase().equals("PUT") || method.equals("put")){
            requestMethod=Request.Method.PUT;
        }
        else if(method.toUpperCase().equals("TRACE") || method.equals("trace")){
            requestMethod=Request.Method.TRACE;
        }

        Map<String, String> finalParams1 = params2;
        StringRequest jsonObjectRequest = new StringRequest
                (requestMethod, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onFailure(error);
                    }
                })

                {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return finalParams;
                }
                @Override
                public Map<String, String> getParams() throws AuthFailureError {
                    return finalParams1;
                }
                };

        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(jsonObjectRequest);
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                requestQueue.getCache().clear();
            }
        });

    }

    public void xEach(JSONArray arr,OnEachArray callback) {
        for (int i=0; i<arr.length(); i++) {

            try {
                callback.getObject(arr.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                callback.getArray(arr.getJSONArray(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
