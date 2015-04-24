package network;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.vitor.aplicativo.MyApplication;

/**
 * Created by Vitor on 20/04/2015.
 */
public class VolleySingleTon {
    private static VolleySingleTon sInstance=null;
    private ImageLoader mImageLoader;
    private RequestQueue mRequestQueue;
    private VolleySingleTon(){
        mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
        mImageLoader = new ImageLoader(mRequestQueue,new ImageLoader.ImageCache() {
            private LruCache<String,Bitmap> cache=new LruCache<>((int) (Runtime.getRuntime().maxMemory()/1024/8));
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }
    public static VolleySingleTon getsInstance(){
        if(sInstance==null){
            sInstance=new VolleySingleTon();
        }
        return sInstance;
    }
    public RequestQueue getmRequestQueue(){
        return mRequestQueue;
    }
    public ImageLoader getImageLoader(){
        return mImageLoader;
    }
}
