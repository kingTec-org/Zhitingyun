package com.xiyoukeji.zhitingyun.data.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/1.
 */

public class OssMessageEntity implements Serializable {

    private static final long serialVersionUID = -7795918780900968372L;
    /**
     * OSSAccessKeyId : LTAIlyYzgqd6IsOE
     * Signature : 2N/pf8YE6QE+QbyDTkjbS2g3nlw=
     * callback : eyJjYWxsYmFja1VybCI6Imh0dHA6Ly9kb2xsbWFjaGluZS54aXlvdWtlamkuY29tL3VwbG9hZF9zdWNjZXNzIiwiY2FsbGJhY2tCb2R5IjoibWltZVR5cGVcdTAwM2Qke21pbWVUeXBlfVx1MDAyNmlkXHUwMDNkMTcifQ==
     * key : 347bcad3-2265-4309-b549-e9b052cf9b9a.jpg
     * policy : eyJleHBpcmF0aW9uIjoiMjAxNy0xMi0wMVQwOTo0NTozOS41NzlaIiwiY29uZGl0aW9ucyI6W3sia2V5IjoiMzQ3YmNhZDMtMjI2NS00MzA5LWI1NDktZTliMDUyY2Y5YjlhLmpwZyJ9LFsiY29udGVudC1sZW5ndGgtcmFuZ2UiLDAsMTA0ODU3NjAwMF0seyJjYWxsYmFjayI6ImV5SmpZV3hzWW1GamExVnliQ0k2SW1oMGRIQTZMeTlrYjJ4c2JXRmphR2x1WlM1NGFYbHZkV3RsYW1rdVkyOXRMM1Z3Ykc5aFpGOXpkV05qWlhOeklpd2lZMkZzYkdKaFkydENiMlI1SWpvaWJXbHRaVlI1Y0dWY2RUQXdNMlFrZTIxcGJXVlVlWEJsZlZ4MU1EQXlObWxrWEhVd01ETmtNVGNpZlFcdTAwM2RcdTAwM2QifV19
     */

    @SerializedName("OSSAccessKeyId")
    private String mOSSAccessKeyId;
    @SerializedName("Signature")
    private String mSignature;
    @SerializedName("callback")
    private String mCallback;
    @SerializedName("key")
    private String mKey;
    @SerializedName("policy")
    private String mPolicy;

    public String getOSSAccessKeyId() {
        return mOSSAccessKeyId;
    }

    public void setOSSAccessKeyId(String OSSAccessKeyId) {
        mOSSAccessKeyId = OSSAccessKeyId;
    }

    public String getSignature() {
        return mSignature;
    }

    public void setSignature(String signature) {
        mSignature = signature;
    }

    public String getCallback() {
        return mCallback;
    }

    public void setCallback(String callback) {
        mCallback = callback;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getPolicy() {
        return mPolicy;
    }

    public void setPolicy(String policy) {
        mPolicy = policy;
    }
}
