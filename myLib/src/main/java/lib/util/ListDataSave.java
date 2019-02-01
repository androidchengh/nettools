package lib.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import httploglib.lib.been.IpConfigBeen;


public class ListDataSave {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public final static String listTag = "ip_list";
    public final static String netLIst = "net_lIst";
    public static String ListDataSave = "ListDataSave";
    private static ListDataSave instance;

    public static synchronized ListDataSave getInstance(Context context, String preferenceName) {
        if (instance == null) {
            instance = new ListDataSave(context, preferenceName);
        }
        return instance;
    }

    public ListDataSave(Context mContext, String preferenceName) {
        if (TextUtils.isEmpty(preferenceName)) {
            preferenceName = ListDataSave;
        }
        try {
            preferences = mContext.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
            editor = preferences.edit();
        } catch (Exception e) {

        }
    }

    //设置用户数据
    public void setStringToPreference(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }


    /**
     * 保存List
     *
     * @param tag
     * @param datalist
     */
    public <T> void setDataList(String tag, List<T> datalist) {
        if (null == datalist || datalist.size() <= 0)
            return;
        String strJson = changeArrayDateToJson((ArrayList<IpConfigBeen>) datalist);
//        Gson gson = new Gson();
//        //转换成json数据，再保存
//        String strJson = gson.toJson(datalist);
        editor.clear();
        editor.putString(tag, strJson);
        editor.commit();

    }

    /**
     * 获取lIst
     *
     * @param tag
     * @return
     */
    public <T> List<T> getDataList(String tag) {
        List<T> datalist = null;
        try {
            datalist = new ArrayList<T>();
            String strJson = "";
            if (null == preferences) {
                strJson = preferences.getString(tag, "");
                if (null == strJson) {
                    return datalist;
                }
            }
            datalist = (List<T>) changeJsonToArray(strJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datalist;

    }


    /**
     * 获取lIst
     *
     * @param tag
     * @return
     */
    public String getDataListString(String tag) {
        String strJson = preferences.getString(tag, null);
        if (null == strJson) {
            return strJson;
        }
        return strJson;

    }

//    /**
//     * 获取lIst
//     *
//     * @param tag
//     * @return
//     */
//    public <T> List<T> getList(String tag) {
//        List<T> datalist = new ArrayList<T>();
//        String strJson = preferences.getString(tag, null);
//        if (null == strJson) {
//            return datalist;
//        }
//        Gson gson = new Gson();
//        datalist = gson.fromJson(strJson, new TypeToken<List<T>>() {
//        }.getType());
//        return datalist;
//
//    }

    /**
     * 将数组转换为JSON格式的数据。
     *
     * @param stoneList 数据源
     * @return JSON格式的数据
     */
    public static String changeArrayDateToJson(ArrayList<IpConfigBeen> stoneList) {
        try {
            JSONArray array = new JSONArray();
            JSONObject object = new JSONObject();
            int length = stoneList.size();
            for (int i = 0; i < length; i++) {
                IpConfigBeen stone = stoneList.get(i);
                String name = stone.getUrlName();
                String url = stone.getUrl();
                boolean select = stone.isSelect();
                JSONObject stoneObject = new JSONObject();
                stoneObject.put("name", name);
                stoneObject.put("url", url);
                stoneObject.put("select", select);
                array.put(stoneObject);
            }
            object.put("stones", array);
            return object.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将JSON转化为数组并返回。
     *
     * @param Json
     * @return ArrayList<Stone>
     */
    public static ArrayList<IpConfigBeen> changeJsonToArray(String Json) {
        ArrayList<IpConfigBeen> gameList = new ArrayList<IpConfigBeen>();
        try {
            JSONObject jsonObject = new JSONObject(Json);
            if (!jsonObject.isNull("stones")) {
                String aString = jsonObject.getString("stones");
                JSONArray aJsonArray = new JSONArray(aString);
                int length = aJsonArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject stoneJson = aJsonArray.getJSONObject(i);
                    String name = stoneJson.getString("name");
                    String url = stoneJson.getString("url");
                    boolean select = stoneJson.getBoolean("select");
                    IpConfigBeen stone = new IpConfigBeen(url, name, select);
                    gameList.add(stone);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gameList;
    }

}