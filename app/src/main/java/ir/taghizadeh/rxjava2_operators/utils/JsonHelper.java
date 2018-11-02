package ir.taghizadeh.rxjava2_operators.utils;


import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class JsonHelper {

    public String LoadJSONFromAsset(String address, Context context) {
        String json;
        try {
            InputStream is = context.getAssets().open(address);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            return null;
        }
        return json;
    }
}
