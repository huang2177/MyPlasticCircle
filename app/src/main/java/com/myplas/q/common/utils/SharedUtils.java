package com.myplas.q.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/11/4.
 */
public class SharedUtils {
    private static SharedPreferences.Editor editor;
    private static SharedPreferences sharedPreferences;
    private static final String FILE_NAME = "save_file_name";
    //构造器私有化
    private SharedUtils(){
    }
    //提供一个静态私有对象常量
    private static final SharedUtils sh=new SharedUtils();
    //提供一个公开的方法来获取对象实例
    public static SharedUtils getSharedUtils(){
        return sh;
    }
    public void setData(Context context, String key, String values){
        try {
            sharedPreferences=context.getSharedPreferences("data",Context.MODE_PRIVATE);
            editor=sharedPreferences.edit();
            editor.putString(key,values);
            editor.commit();
        } catch (Exception e) {
        }
    }
    public void setBooloean(Context context, String key, boolean values){
        try {
            sharedPreferences=context.getSharedPreferences("data",Context.MODE_PRIVATE);
            editor=sharedPreferences.edit();
            editor.putBoolean(key,values);
            editor.commit();
        } catch (Exception e) {
        }
    }
    public String getData(Context context,String key){
        try {
            sharedPreferences=context.getSharedPreferences("data",Context.MODE_PRIVATE);
            ;
            String data=sharedPreferences.getString(key,"");
            return data;
        } catch (Exception e) {
            return null;
        }
    }
    public boolean getBoolean(Context context,String key){
        try {
            sharedPreferences=context.getSharedPreferences("data",Context.MODE_PRIVATE);
            ;
            boolean data=sharedPreferences.getBoolean(key,false);
            return data;
        } catch (Exception e) {
            return false;
        }
    }
    public void clearData(Context context){
        sharedPreferences=context.getSharedPreferences("data",Context.MODE_PRIVATE);;
        editor=sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
    public static void saveData(Context context, String key,Object data){
        String type = data.getClass().getSimpleName();
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if ("Integer".equals(type)){
            editor.putInt(key, (Integer)data);
        }else if ("Boolean".equals(type)){
            editor.putBoolean(key, (Boolean)data);
        }else if ("String".equals(type)){
            editor.putString(key, (String)data);
        }else if ("Float".equals(type)){
            editor.putFloat(key, (Float)data);
        }else if ("Long".equals(type)){
            editor.putLong(key, (Long)data);
        }
        editor.commit();
    }
    /**
     * 从文件中读取数据
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static Object getData(Context context, String key, Object defValue){
        if(null!=defValue){
            String type = defValue.getClass().getSimpleName();
            SharedPreferences sharedPreferences = context.getSharedPreferences
                    (FILE_NAME, Context.MODE_PRIVATE);
            //defValue为为默认值，如果当前获取不到数据就返回它
            if ("Integer".equals(type)){
                return sharedPreferences.getInt(key, (Integer)defValue);
            }else if ("Boolean".equals(type)){
                return sharedPreferences.getBoolean(key, (Boolean)defValue);
            }else if ("String".equals(type)){
                return sharedPreferences.getString(key, (String)defValue);
            }else if ("Float".equals(type)){
                return sharedPreferences.getFloat(key, (Float)defValue);
            }else if ("Long".equals(type)){
                return sharedPreferences.getLong(key, (Long)defValue);
            }
        }
        return null;
    }
}
