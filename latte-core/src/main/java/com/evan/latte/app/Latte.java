package com.evan.latte.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * Author Evan_zch
 * Time 2018/9/13 20:07
 */
public  final  class Latte {

    public static Configurator init(Context context) {
         getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
         return Configurator.getInstance();
    }

    public static WeakHashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplicationContext(){
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }




}
