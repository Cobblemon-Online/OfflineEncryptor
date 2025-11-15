package io.github.lumine1909.offlineencryptor;

import java.util.List;

public interface ConfigHandler {

    Object get(String node);

    String getStringOr(String node, String def);

    List<String> getStringList(String node);

    int getIntOr(String node, int def);

    boolean getBooleanOr(String node, boolean def);

    void put(String node, Object value);

    void save();

    void reload();
}