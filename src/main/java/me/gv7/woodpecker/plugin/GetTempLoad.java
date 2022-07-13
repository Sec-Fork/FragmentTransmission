package me.gv7.woodpecker.plugin;

import me.gv7.woodpecker.uitis.FileSplit;
import me.gv7.woodpecker.uitis.File_loader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetTempLoad implements IHelper{
    @Override
    public String getHelperTabCaption() {
        return "GetTempLoad";
    }

    @Override
    public IArgsUsageBinder getHelperCutomArgs() {
        IArgsUsageBinder argsUsageBinder = Chunked_Plugin.pluginHelper.createArgsUsageBinder();
        List<IArg> args = new ArrayList<>();
        IArg args1 = Chunked_Plugin.pluginHelper.createArg();
        IArg args2 = Chunked_Plugin.pluginHelper.createArg();
        args1.setName("Filename");
        args1.setDescription("读取前的文件名");
        args1.setDefaultValue("calc.jar");
        args1.setRequired(true);

        args2.setName("ClassName");
        args2.setDescription("Jar包需要加载的类名");
        args2.setDefaultValue("calc");
        args2.setRequired(true);
        args.add(args1);
        args.add(args2);
        argsUsageBinder.setArgsList(args);
        return argsUsageBinder;
    }

    @Override
    public void doHelp(Map<String, Object> map, IResultOutput iResultOutput) throws Throwable {
        String Filename = (String) map.get("Filename");
        String ClassName = (String) map.get("ClassName");
        File_loader file_loader = new File_loader();
        FileSplit fileSplit = new FileSplit();
        String property = System.getProperty("java.io.tmpdir");
        String tmp_File_Path = property+"/cXTTr94nRMPRv8/";
        fileSplit.mergeByName_Code(Filename);
        iResultOutput.rawPrintln("OutFilePath: "+tmp_File_Path+Filename);
        file_loader.FileloaderInvoke(Filename,ClassName);
        iResultOutput.rawPrintln("FileloaderInvoke Execution completed");


    }
}
//GenerateBlockClass