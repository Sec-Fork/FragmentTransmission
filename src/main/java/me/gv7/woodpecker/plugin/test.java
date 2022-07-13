package me.gv7.woodpecker.plugin;

import me.gv7.woodpecker.uitis.CodeGeneration;
import me.gv7.woodpecker.uitis.FileSplit;
import me.gv7.woodpecker.uitis.File_loader;

import java.io.IOException;

public class test {
    public static void main(String[] args) throws Exception {
        CodeGeneration codeGeneration = new CodeGeneration();
        String FilePath = "C:\\Users\\Public\\calcwin.jar";
        String ClassPath = "C:\\Users\\sangfor\\IdeaProjects\\untitled\\src\\file\\";
        String num = "10";
        String FilNameonly = "calcwin.jar";
        String ClassName = "calc";

        codeGeneration.CreateFileTmpPath(ClassPath);
        codeGeneration.FileSplit(FilePath,ClassPath,Integer.parseInt(num));
        codeGeneration.mergeByNameCode(FilNameonly,ClassPath);
        codeGeneration.FileloaderInvoke(FilNameonly,ClassName,ClassPath);


//        codeGeneration.CreateFile("/tmp/","");
//        FileSplit fileSplit = new FileSplit();
//        fileSplit.ReadTempPathFile("/Users/nice0e3/Desktop/路线/调试笔记/分块传输/untitled/demo/src/main/java/calc.jpg",10);
//        File_loader file_loader = new File_loader();

//
//        fileSplit.mergeByName_Code("calc.jpg");
//        file_loader.FileloaderInvoke("calc.jpg","calc");
    }
}
