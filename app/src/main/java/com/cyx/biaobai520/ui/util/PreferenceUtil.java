package com.cyx.biaobai520.ui.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * 本地Preference保存的值
 * 
 */
public class PreferenceUtil {
    /** preferences名称 */
    private static final String PREFERENCENAME = "GuardThief";
    // 上下文
    private Context context;
    /** 静态字段 */
    private static PreferenceUtil preferenceManager = null;
    /** 静态字段 */
    private SharedPreferences preferences = null;
    /** 静态字段 */
    private SharedPreferences.Editor editor = null;

    /** 私有构造函数 */
    private PreferenceUtil(Context context) {
        this.context = context;
        /** 其他应用可以读取 */
        this.preferences = context.getSharedPreferences(PREFERENCENAME,
                Context.MODE_WORLD_READABLE);
        this.editor = preferences.edit();
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }

    public SharedPreferences.Editor getEditor() {
        return editor;
    }

    /**
     * 单例获取CmPreferenceManager
     * 
     * @param context
     *            上下文
     * @return
     */
    public static PreferenceUtil getInstance(Context context) {
        if (preferenceManager != null) {
            return preferenceManager;
        }
        synchronized (PreferenceUtil.class) {
            if (preferenceManager == null) {
                preferenceManager = new PreferenceUtil(context);
            }
        }
        return preferenceManager;
    }

    /**
     * 获取值
     * 
     * @param enumPreference
     * @return
     */
    public String getPreference(Enum_ShootPreference enumPreference) {
        String value = preferences.getString(enumPreference.getName(),
                enumPreference.getDefault());
        // 有可能为空(""),所以还是要判断下
        if (TextUtils.isEmpty(value)) {
            value = enumPreference.getDefault();
        }
        return value;
    }

    /**
     * 设置值
     * 
     * @param enumPreference
     * @param value
     */
    public void setPreference(Enum_ShootPreference enumPreference, String value) {
        editor.putString(enumPreference.getName(), value);
        editor.commit();
    }

    /**
     * preference枚举
     * 
     * @author guoruiliang
     * 
     */
    public static enum Enum_ShootPreference {

        /**登录名电话号码*/
    	LOGINNAME("loginName","");

        /**
         * 构造函数
         * 
         * @param name
         */
        Enum_ShootPreference(String name, String defaultValue) {
            this.name = name;
            this.defaultValue = defaultValue;
        }

        /** 获取名称 */
        protected String getName() {
            return this.name;
        }

        /** 获取默认 */
        protected String getDefault() {
            return this.defaultValue;
        }

        private String name;
        private String defaultValue;
    }

}
