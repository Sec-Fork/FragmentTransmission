package me.gv7.woodpecker.uitis;

import javassist.*;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.HashMap;

public class CodeGeneration {

//    /**
//     * 合并分块文件,基于tmp目录下文件文件进行合并
//     * @throws IOException
//     * @throws CannotCompileException
//     */
//    public void FileMerge() throws IOException, CannotCompileException {
//        ClassPool pool = ClassPool.getDefault();
//        pool.insertClassPath(new ClassClassPath(this.getClass()));
//        CtClass ctClass3 = pool.makeClass("FileMergebyname");
//        CtConstructor ctConstructor = ctClass3.makeClassInitializer();
//        ctConstructor.insertAfter("String property = System.getProperty(\"java.io.tmpdir\");" +
//                "String tmp_File_Path = System.getProperty(\"java.io.tmpdir\")+\"/cXTTr94nRMPRv8/\";" +
//                "java.io.File file = new java.io.File(tmp_File_Path);" +
//                "java.io.File list[] = file.listFiles();" +
//                "java.util.Map/*<String, String>*/ map = new java.util.HashMap/*<String, String>*/();" +
//                "String newFileName = null;" +
//                "StringBuffer stringBuffer = new StringBuffer();" +
//
//                "for (int i = 0; i < list.length; i++) {\n" +
//                "            java.io.BufferedReader reader = null;\n" +
//                "            try {\n" +
//                "                reader = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(list[i])));\n" +
//                "                String str_head = reader.readLine();\n" +
//                "                String id = str_head.substring(0, str_head.indexOf(\" \"));\n" +
//                "                newFileName = str_head.substring(str_head.indexOf(\" \") + 1);\n" +
//                "                map.put(id, list[i].getAbsolutePath());\n" +
//                "                reader.close();\n" +
//                "            } catch (java.io.FileNotFoundException e) {\n" +
//                "                throw new java.lang.RuntimeException(e);\n" +
//                "            } catch (java.io.IOException e) {\n" +
//                "                throw new java.lang.RuntimeException(e);\n" +
//                "            }\n" +
//                "        }" +
//                " for (int i = 0; i < list.length - 1; i++) {java.io.BufferedReader reader = null;" +
//                        "String gg = map.get(String.valueOf(i));" +
//                        "java.io.File f = new java.io.File(gg);" +
//                        "try {" +
//                        "reader = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(f)));" +
//                        "reader.readLine();" +
//                        " String temp = null;" +
//                        " while ((temp = reader.readLine()) != null) {" +
//                        "  stringBuffer.append(temp);" +
//                        "}" +
//                        "reader.close();" +
//                        " java.io.BufferedOutputStream out = new java.io.BufferedOutputStream(new java.io.FileOutputStream(property + newFileName));" +
//                        " out.write(new sun.misc.BASE64Decoder().decodeBuffer(stringBuffer.toString()));" +
//                        "out.close();" +
//                        " } catch (java.lang.Exception e) {" +
//                        " throw new RuntimeException(e);" +
//                        "}" +
//                        "" +
//                        "}"
//
//        );
//
//        byte[] bytes = ctClass3.toBytecode();
//        String filepath ="FileMergebyname.class";
//        FileStore(filepath,bytes);
//
//    }

    /**
     * 合并文件，基于动态生成方式实现，FileName必须指定生成前的FIlename
     * @param Filename 指定生成前的Filename
     * @param OutClassFilePath 指定输出路径
     */
    public void mergeByNameCode(String Filename,String OutClassFilePath) throws CannotCompileException, IOException {
        File file1 = new File(OutClassFilePath);
        if(!file1.exists()){
            file1.mkdirs();
        }
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(this.getClass()));
        CtClass ctClass3 = pool.makeClass("MergeByNameCode");
        CtConstructor ctConstructor = ctClass3.makeClassInitializer();
        ctConstructor.insertAfter("String Filename= \""+Filename+"\";" +
                "String property = System.getProperty(\"java.io.tmpdir\");" +
                "String tmp_File_Path = property+\"/cXTTr94nRMPRv8/\";" +
                "java.io.File file = new java.io.File(tmp_File_Path);" +
                "java.io.File list[] = file.listFiles();" +
                "java.util.Map/*<String, String>*/ map = new java.util.HashMap/*<String, String>*/();" +
                "for (int i = 0; i < list.length; i++) {\n" +
                "\n" +
                "            String str_head = null;\n" +
                "\n" +
                "            try {\n" +
                "                java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(list[i])));\n" +
                "                str_head = reader.readLine();\n" +
                "                String id = str_head.substring(0, str_head.indexOf(\" \"));\n" +
                "\n" +
                "                map.put(id, list[i].getAbsolutePath());\n" +
                "                reader.close();\n" +
                "            } catch (java.io.IOException e) {\n" +
                "                throw new RuntimeException(e);\n" +
                "            }\n" +
                "\n" +
                "        }" +
                " StringBuffer stringBuffer = new StringBuffer();" +
                " for (int i = 0; i < list.length ; i++) {" +
                "String s = map.get(String.valueOf(i));\n" +
                "            java.io.File f = new java.io.File(s);" +
                "  String temp = null;" +
                "try {" +
                " java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(f)));" +
                " while ((temp = reader.readLine()) != null) {" +
                "  int i1 = temp.lastIndexOf(Filename) + Filename.length();" +
                " String substring = temp.substring(i1);" +
                "  stringBuffer.append(substring);" +
                " }reader.close();}catch (java.io.IOException e) {throw new RuntimeException(e);}" +
                "}" +
                "try {\n" +
                "            java.io.BufferedOutputStream out = new java.io.BufferedOutputStream(new java.io.FileOutputStream(property + Filename));\n" +
                "            out.write(new sun.misc.BASE64Decoder().decodeBuffer((stringBuffer.toString())));\n" +
                "            out.close();\n" +
                "        } catch (java.io.IOException e) {\n" +
                "            throw new RuntimeException(e);\n" +
                "        }");


        byte[] bytes = ctClass3.toBytecode();
        String filepath ="MergeByNameCode.class";
        FileStore(OutClassFilePath+filepath,bytes);
//        File file  = new File(OutClassFilePath+filepath);
//
//        FileOutputStream fos = new FileOutputStream(file);
//        fos.write(bytes,0,bytes.length);
//        fos.flush();
//        fos.close();


    }


    /**
     * 合并文件，基于动态生成方式实现，FileName必须指定生成前的FIlename
     * @param Filename 指定生成前的Filename
     * @param mergePath 指定分块路径
     * @param OutClassFilePath 指定输出路径
     */
    public void mergeByNameCode(String Filename,String mergePath,String OutClassFilePath) throws CannotCompileException, IOException {
        File file1 = new File(OutClassFilePath);
        if(!file1.exists()){
            file1.mkdirs();
        }

        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(this.getClass()));
        CtClass ctClass3 = pool.makeClass("MergeByNameCode1");
        CtConstructor ctConstructor = ctClass3.makeClassInitializer();

        ctConstructor.insertAfter("String property = System.getProperty(\"java.io.tmpdir\");" +
                "String Filename= \""+Filename+"\";" +
                "String mergePath= \""+mergePath+"\";" +
//                "String tmp_File_Path = property+\"/cXTTr94nRMPRv8/\";" +
                "java.io.File file = new java.io.File(mergePath);" +
                "java.io.File list[] = file.listFiles();" +
                "java.util.Map/*<String, String>*/ map = new java.util.HashMap/*<String, String>*/();" +
                "for (int i = 0; i < list.length; i++) {\n" +
                "\n" +
                "            String str_head = null;\n" +
                "\n" +
                "            try {\n" +
                "                java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(list[i])));\n" +
                "                str_head = reader.readLine();\n" +
                "                String id = str_head.substring(0, str_head.indexOf(\" \"));\n" +
                "\n" +
                "                map.put(id, list[i].getAbsolutePath());\n" +
                "                reader.close();\n" +
                "            } catch (java.io.IOException e) {\n" +
                "                throw new RuntimeException(e);\n" +
                "            }\n" +
                "\n" +
                "        }" +
                " StringBuffer stringBuffer = new StringBuffer();" +
                " for (int i = 0; i < list.length ; i++) {" +
                "String s = map.get(String.valueOf(i));\n" +
                "            java.io.File f = new java.io.File(s);" +
                "  String temp = null;" +
                "try {" +
                " java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(f)));" +
                " while ((temp = reader.readLine()) != null) {" +
                "  int i1 = temp.lastIndexOf(Filename) + Filename.length();" +
                " String substring = temp.substring(i1);" +
                "  stringBuffer.append(substring);" +
                " }reader.close();}catch (java.io.IOException e) {throw new RuntimeException(e);}" +
                "}" +
                "try {\n" +
                "            java.io.BufferedOutputStream out = new java.io.BufferedOutputStream(new java.io.FileOutputStream(property + Filename));\n" +
                "            out.write(new sun.misc.BASE64Decoder().decodeBuffer((stringBuffer.toString())));\n" +
                "            out.close();\n" +
                "        } catch (java.io.IOException e) {\n" +
                "            throw new RuntimeException(e);\n" +
                "        }");


        byte[] bytes = ctClass3.toBytecode();
        String filepath ="MergeByNameCode1.class";

        FileStore(OutClassFilePath+filepath,bytes);



    }



    /**
     * 动态生成创建临时文件夹代码
     * @throws CannotCompileException
     */

    public void CreateFileTmpPath(String OutFilePath) throws CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(this.getClass()));
        CtClass ctClass3 = pool.makeClass("CreateFile");
        CtConstructor ctConstructor = ctClass3.makeClassInitializer();
        ctConstructor.insertAfter("String property = System.getProperty(\"java.io.tmpdir\");" +
                " String tmp_File_Path = property+\"/cXTTr94nRMPRv8/\";" +
                " java.io.File file = new java.io.File(tmp_File_Path);" +
                "if (!file.exists()){file.mkdirs();}" +
                "else {java.io.File[] files = file.listFiles();java.io.File file1 = null;for (int i = 0; i < files.length; i++) {file1 = files[i];if (file1.getName().contains(\"end\")) {file1.delete();}}}");
        byte[] bytes = ctClass3.toBytecode();
        String filepath ="CreateFile.class";
        FileStore(OutFilePath+filepath,bytes);



    }

    public void CreateFile(String FilePath,String OutFilePath) throws CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(this.getClass()));
        CtClass ctClass3 = pool.makeClass("CreateFilePath");
        CtConstructor ctConstructor = ctClass3.makeClassInitializer();
        ctConstructor.insertAfter("String tmp_File_Path= \""+FilePath+"\";" +
                " java.io.File file = new java.io.File(tmp_File_Path);" +
                "if (!file.exists()){file.mkdirs();}" +
                "else {java.io.File[] files = file.listFiles();java.io.File file1 = null;for (int i = 0; i < files.length; i++) {file1 = files[i];if (file1.getName().contains(\"end\")) {file1.delete();}}}");
        byte[] bytes = ctClass3.toBytecode();
        String filepath ="CreateFilePath.class";
        FileStore(OutFilePath+filepath,bytes);

//        File file  = new File(filepath);
//
//        FileOutputStream fos = new FileOutputStream(file);
//        fos.write(bytes,0,bytes.length);
//        fos.flush();
//        fos.close();


    }

    /**
     * 动态生成文件分块代码,分块到tmp中
     * @param FileName
     * @param Number
     */
    public void FileSplit(String FileName,String OutFilePath,int Number) throws IOException, CannotCompileException {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(this.getClass()));
        HashMap<Integer, String> map = new HashMap<Integer,String>();

        String property = System.getProperty("java.io.tmpdir");
//        String tmp_Class_Path = property+"/tmp_Class_Path/";
        String tmp_File_Path = property+"/cXTTr94nRMPRv8/";

        File filedir = new File(tmp_File_Path);
        if (!filedir.exists()){
            filedir.mkdirs();

        }
        File oldFile = new File(FileName);
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(oldFile));
        byte[] data = new byte[in.available()];
        in.read(data);

        String file = new BASE64Encoder().encode(data);
        int length = file.length();
        System.out.println("字符串长度：" + length);
        int size = length / Number;
        int start = 0, end = size;


        String str_temp = null;
        int i = 0;
        for (; i < Number -1; i++) {
            str_temp = i + " " + oldFile.getName();
            str_temp += file.substring(start, end).replaceAll("\r\\n|\\n","");
            map.put(i, str_temp);

            start += size;
            end += size;
        }
        str_temp = Number - 1 + " " + oldFile.getName() ;
        str_temp += file.substring(start).replaceAll("\r\\n|\\n","");
        map.put(i,str_temp);




//        File file_class_dir = new File(tmp_Class_Path);
//        if (!file_class_dir.exists()){
//            file_class_dir.mkdirs();
//        }

        for (int j = 0; j < map.size(); j++) {


            String s = map.get(j);
            s = s.replaceAll("\r\\n|\\n","");
            CtClass ctClass3 = pool.makeClass("FileSplit"+"_"+j);
            CtConstructor ctConstructor = ctClass3.makeClassInitializer();
            ctConstructor.insertAfter("String property = System.getProperty(\"java.io.tmpdir\");" +
                            " String tmp_File_Path = property+\"/cXTTr94nRMPRv8/\";" +
                            "java.io.File file1 = new java.io.File(tmp_File_Path+\""+ j +".file\");" +
//                            " java.io.File file1 = new java.io.File(tmp_File_Path);" +
                            "java.io.BufferedOutputStream out = new java.io.BufferedOutputStream(new java.io.FileOutputStream(file1));" +
//                    "System.out.println(\""+s+"\");");
                            "out.write(\""+s+"\".getBytes());" +
                            "out.close();"

            );

            byte[] bytes = ctClass3.toBytecode();
            String filepath ="FileSplit"+"_"+j+".class";
//            File file2  = new File(filepath);
//            File file1  = new File(tmp_Class_Path+filepath);
            FileStore(OutFilePath+filepath,bytes);




        }
    }
    /**
     * 动态生成文件分块代码，分块并存储到指定位置
     * @param FileName
     * @param Number
     */
    public void FileSplit(String FileName,String OutFilePath,int Number,String storage) throws IOException, CannotCompileException {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(this.getClass()));
        HashMap<Integer, String> map = new HashMap<Integer,String>();


        File oldFile = new File(FileName);
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(oldFile));
        byte[] data = new byte[in.available()];
        in.read(data);

        String file = new BASE64Encoder().encode(data);
        int length = file.length();
        System.out.println("字符串长度：" + length);
        int size = length / Number;
        int start = 0, end = size;


        String str_temp = null;
        int i = 0;
        for (; i < Number -1; i++) {
            str_temp = i + " " + oldFile.getName();
            str_temp += file.substring(start, end).replaceAll("\r\\n|\\n","");
            map.put(i, str_temp);

            start += size;
            end += size;
        }
        str_temp = Number - 1 + " " + oldFile.getName() ;
        str_temp += file.substring(start).replaceAll("\r\\n|\\n","");
        map.put(i,str_temp);




//        File file_class_dir = new File(storage);
//        if (!file_class_dir.exists()){
//            file_class_dir.mkdirs();
//        }

        for (int j = 0; j < map.size(); j++) {


            String s = map.get(j);
            s = s.replaceAll("\r\\n|\\n","");
            CtClass ctClass3 = pool.makeClass("FileSplit"+"_"+j);
            CtConstructor ctConstructor = ctClass3.makeClassInitializer();
            ctConstructor.insertAfter("java.io.File file1 = new java.io.File(\""+storage + j +".file\");" +
                            "java.io.BufferedOutputStream out = new java.io.BufferedOutputStream(new java.io.FileOutputStream(file1));" +
//                    "System.out.println(\""+s+"\");");
                            "out.write(\""+s+"\".getBytes());" +
                            "out.close();"

            );
            byte[] bytes = ctClass3.toBytecode();
            String filepath ="FileSplit"+"_"+j+".class";
            filepath = OutFilePath + filepath;
//            File file2  = new File(OutFilePath+filepath);
////            File file1  = new File(tmp_Class_Path+filepath);
//            FileOutputStream fos = new FileOutputStream(file2);
//            fos.write(bytes,0,bytes.length);
//            fos.flush();
//            fos.close();
            FileStore(filepath,bytes);




        }
    }




    /**
     * 动态生成执行jar代码
     * @param ClassName 指定Jar中的ClassName
     * @throws CannotCompileException
     */


//    public void FileloaderInvoke(String ClassName,String OutFilePath) throws CannotCompileException {
//        ClassPool pool = ClassPool.getDefault();
//        pool.insertClassPath(new ClassClassPath(this.getClass()));
//        CtClass ctClass3 = pool.makeClass("FileSplitWrite");
//        CtConstructor ctConstructor = ctClass3.makeClassInitializer();
//        ctConstructor.insertAfter("" +
//                "String FileName=null;" +
//                "String str_head = null;" +
//                "java.net.URL url = null;" +
//                "java.io.BufferedReader reader = null;" +
//                "String property = System.getProperty(\"java.io.tmpdir\");" +
//                " String ChunkedPath = property + \"/cXTTr94nRMPRv8/\";" +
//                "java.io.File file = new java.io.File(ChunkedPath);" +
//                "java.io.File file1 = file.listFiles()[0];" +
//                "try {" +
//                "reader = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(file1)));" +
//                " str_head = reader.readLine();" +
//                " FileName = str_head.substring(str_head.indexOf(\" \") + 1);" +
//                " } catch (Exception e) {" +
//                "  throw new RuntimeException(e);" +
//                "}" +
//                "String s = property + FileName;" +
//                "try {" +
//                "url = new java.io.File(s).toURI().toURL();" +
//                "new java.net.URLClassLoader(new java.net.URL[]{new java.net.URL(url.toString())}).loadClass(\""+ClassName+"\").newInstance();" +
//                "  } catch (Exception e) {" +
//                " throw new RuntimeException(e);" +
//                "}");
//        byte[] bytes = new byte[0];
//        try {
//            bytes = ctClass3.toBytecode();
//            String filepath ="FileSplitWrite.class";
//            FileStore(OutFilePath+filepath,bytes);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    /**
     * 动态生成执行jar代码
     * @param Filename 指定加载的tmp下的文件名
     * @param ClassName 指定加载jar中的类名
     * @throws CannotCompileException
     */

    public void FileloaderInvoke(String Filename ,String ClassName,String OutFilePath) throws CannotCompileException {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(this.getClass()));
        CtClass ctClass3 = pool.makeClass("FileloaderInvokeFIlename");
        CtConstructor ctConstructor = ctClass3.makeClassInitializer();
        ctConstructor.insertAfter("String Filename= \""+Filename+"\";"  +
                "String property = System.getProperty(\"java.io.tmpdir\");\n" +
                "        String s = property + Filename;\n" +
                "        java.net.URL url = null;\n" +
                "        try {\n" +
                "            url = new java.io.File(s).toURI().toURL();\n" +
                        "new java.net.URLClassLoader(new java.net.URL[]{new java.net.URL(url.toString())}).loadClass(\""+ClassName+"\").newInstance();"  +
                "        } catch (Exception e) {\n" +
                "            throw new RuntimeException(e);\n" +
                "        }");
        byte[] bytes = new byte[0];
        try {
            bytes = ctClass3.toBytecode();
            String filepath ="FileloaderInvokeFIlename.class";
            FileStore(OutFilePath+filepath,bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

        public void FileStore(String FilePath,byte[] bytes) throws IOException {
            File file  = new File(FilePath);

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes,0,bytes.length);
            fos.flush();
            fos.close();
        }
}
