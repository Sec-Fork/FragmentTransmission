package me.gv7.woodpecker.plugin;

import me.gv7.woodpecker.uitis.FileSplit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Chunked_Plugin implements IHelperPlugin{
    public static IPluginHelper pluginHelper;
    public static IHelperPluginCallbacks HelperPluginCallbacks;
    @Override
    public void HelperPluginMain(IHelperPluginCallbacks iHelperPluginCallbacks) {
        this.HelperPluginCallbacks = iHelperPluginCallbacks;
        pluginHelper = iHelperPluginCallbacks.getPluginHelper();

        HelperPluginCallbacks.setHelperPluginName("FragmentTransmission");
        HelperPluginCallbacks.setHelperPluginVersion("0.1.0");
        HelperPluginCallbacks.setHelperPluginAutor("nice0e3");
        HelperPluginCallbacks.setHelperPluginDescription("1.FileSplitPlugin文件分块\n2.GetTempLoad读取tmp目录下的文件合并，加载class测试分块文件完整使用\n3.GenerateBlockClassTmpPlugin动态生成分块与合并class加载执行，写分块文件到tmp中\n4.与3功能一致，可指定写入分块的路径，用于已知路径写入，推荐使用3");
        List<IHelper> helperList = new ArrayList<IHelper>();

        helperList.add(new FileSplitPlugin());
        helperList.add(new GetTempLoad());
        helperList.add(new GenerateBlockClassTmpPlugin());
        helperList.add(new GenerateBlockClassPathPlugin());

        HelperPluginCallbacks.registerHelper(helperList);

    }



}
