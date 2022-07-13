package me.gv7.woodpecker.plugin;

import me.gv7.woodpecker.uitis.CodeGeneration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenerateBlockClassPathPlugin  implements IHelper{
    @Override
    public String getHelperTabCaption() {
        return "GenerateBlockClassPath";
    }

    @Override
    public IArgsUsageBinder getHelperCutomArgs() {
        IArgsUsageBinder argsUsageBinder = Chunked_Plugin.pluginHelper.createArgsUsageBinder();
        List<IArg> args = new ArrayList<>();
        IArg args1 = Chunked_Plugin.pluginHelper.createArg();
        IArg args2 = Chunked_Plugin.pluginHelper.createArg();
        IArg args3 = Chunked_Plugin.pluginHelper.createArg();
        IArg args4 = Chunked_Plugin.pluginHelper.createArg();
        IArg args5 = Chunked_Plugin.pluginHelper.createArg();
        IArg args6 = Chunked_Plugin.pluginHelper.createArg();
        args1.setName("FilePath");
        args2.setName("ClassPath");
        args3.setName("FilNameonly");
        args4.setName("ClassName");
        args5.setName("Num");
        args6.setName("StoragePath");

        args1.setDefaultValue("/tmp/calc.jar");
        args1.setDescription("分块的文件");
        args1.setRequired(true);

        args2.setDefaultValue("/tmp/");
        args2.setRequired(true);
        args2.setDescription("生成Class存储位置");


        args3.setDefaultValue("calc.jar");
        args3.setDescription("FilePath");
        args3.setRequired(true);

        args4.setDefaultValue("calc");
        args4.setDescription("需要加载的Class名");
        args4.setRequired(true);

        args5.setDefaultValue("10");
        args5.setDescription("分块次数");
        args5.setRequired(true);

        args6.setDefaultValue("/tmp/");
        args6.setDescription("写入目标机器的指定目录");
        args6.setRequired(true);



        args.add(args1);
        args.add(args2);
        args.add(args3);
        args.add(args4);
        args.add(args5);
        args.add(args6);

        argsUsageBinder.setArgsList(args);
        return argsUsageBinder;
    }

    @Override
    public void doHelp(Map<String, Object> map, IResultOutput iResultOutput) throws Throwable {

        CodeGeneration codeGeneration = new CodeGeneration();

        String FilePath = (String) map.get("FilePath");
        String ClassPath = (String) map.get("ClassPath");
        String FilNameonly = (String) map.get("FilNameonly");
        String ClassName = (String) map.get("ClassName");
        String num = (String) map.get("Num");
        String StoragePath = (String) map.get("StoragePath");

        codeGeneration.CreateFile(StoragePath,ClassPath);
        codeGeneration.FileSplit(FilePath,ClassPath,Integer.parseInt(num),StoragePath);
        codeGeneration.mergeByNameCode(FilNameonly,StoragePath,ClassPath);
        codeGeneration.FileloaderInvoke(FilNameonly,ClassName,ClassPath);
        iResultOutput.rawPrintln("Output to:"+ClassPath);
        iResultOutput.rawPrintln("GenerateBlockClassPath Execution completed");

//        iResultOutput.rawPrintln(FilePath);
//        iResultOutput.rawPrintln(FilNameonly);
//        iResultOutput.rawPrintln(ClassName);

    }
}
