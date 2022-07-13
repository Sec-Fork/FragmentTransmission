package me.gv7.woodpecker.plugin;

import me.gv7.woodpecker.uitis.FileSplit;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileSplitPlugin implements IHelper {
    @Override
    public String getHelperTabCaption() {
        return "FileSplit";
    }

    @Override
    public IArgsUsageBinder getHelperCutomArgs() {
        IArgsUsageBinder argsUsageBinder = Chunked_Plugin.pluginHelper.createArgsUsageBinder();
        List<IArg> args = new ArrayList<>();
        IArg args1 = Chunked_Plugin.pluginHelper.createArg();
        IArg args2 = Chunked_Plugin.pluginHelper.createArg();
        IArg args3 = Chunked_Plugin.pluginHelper.createArg();
        args1.setName("SrcFilePath");
        args2.setName("OutFilePath");
        args3.setName("num");
        args1.setDefaultValue("/tmp/Woodpecker.class");
        args1.setDescription("分块文件");
        args1.setRequired(true);

        args2.setDefaultValue("default");
        args2.setRequired(true);
        args2.setDescription("分块文件输出路径，default输出到临时目录");
        args3.setDefaultValue("10");
        args3.setDescription("分块位数");

        args3.setRequired(true);
        args.add(args1);
        args.add(args2);
        args.add(args3);

        argsUsageBinder.setArgsList(args);


        return argsUsageBinder;
    }

    @Override
    public void doHelp(Map<String, Object> customArgs, IResultOutput iResultOutput) throws Throwable {
        FileSplit fileSplit = new FileSplit();
        String SrcFilePath = (String) customArgs.get("SrcFilePath");
        String OutFilePath = (String) customArgs.get("OutFilePath");
//            Integer num = Integer.valueOf(customArgs.get("num").toString());
        String num = (String) customArgs.get("num");
        System.out.println(SrcFilePath);
        System.out.println(OutFilePath);
        if(OutFilePath.equals("default")){
            fileSplit.ReadTempPathFile(SrcFilePath,Integer.valueOf(num));
            String property = System.getProperty("java.io.tmpdir");
            String s = property + File.separator + "cXTTr94nRMPRv8" + File.separator;

            iResultOutput.rawPrintln("OutFilePath"+":"+" "+s);
        }else {
            fileSplit.ReadTempPathFile(SrcFilePath,OutFilePath,Integer.valueOf(num));
            iResultOutput.rawPrintln("OutFilePath"+":"+" "+OutFilePath);
        }




    }
}
