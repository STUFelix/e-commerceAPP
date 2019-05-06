package com.evan.latte.app;

import java.util.WeakHashMap;

/**
 * Program: FastEC
 * Author Evan-zch
 * Time  2018-09-13 20:16
 */
public class Configurator {

    private static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();

    /**
     * 使用静态内部类实现单利
     */
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    public WeakHashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    /**
     * 初始化
     */

    private Configurator() {
        // 刚开始配置，初始化未完成
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    public void configure() {
        // 配置完成
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    public Configurator withHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    public <T> T getConfigure(Enum<ConfigType> configTypeEnum) {
        checkConfigure();
        return (T) LATTE_CONFIGS.get(configTypeEnum);

    }


    private void checkConfigure() {
        boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("configure is not ready,call latte.init() first");
        }
    }


}
