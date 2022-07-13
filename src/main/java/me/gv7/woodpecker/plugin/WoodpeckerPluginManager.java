package me.gv7.woodpecker.plugin;


public class WoodpeckerPluginManager implements IPluginManager{
    static IPluginManagerCallbacks iPluginManagerCallbacks;
    public static IPluginHelper pluginHelper;
    public void registerPluginManagerCallbacks(IPluginManagerCallbacks iPluginManagerCallbacks) {
     //   WoodpeckerPluginManager.iPluginManagerCallbacks = iPluginManagerCallbacks;

        // 注册漏洞信息

        Chunked_Plugin chunked_plugin = new Chunked_Plugin();
        iPluginManagerCallbacks.registerHelperPlugin(chunked_plugin);

    }
}
