package me.gv7.woodpecker.uitis;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nice0e3
 */
public class FileSplit {

//    /**
//     * 分割
//     *
//     * @param fileName 文件路径
//     * @param Number   分块文件个数
//     * @throws Exception
//     */
//
//    public void splitByNumber(String fileName, int Number) throws Exception {
//        Create_Mkdir();
//        String md5 = File_loader.getMD5(fileName);
//        String property = System.getProperty("java.io.tmpdir");
//        String tmp_File_Path = property+"/cXTTr94nRMPRv8/";
//        File oldFile = new File(fileName);
//        BufferedInputStream in = new BufferedInputStream(new FileInputStream(oldFile));
//        String file = encode(in);
//        int length = file.length();
//        System.out.println("字符串长度：" + length);
//        int size = length / Number;
//        int start = 0, end = size;
//        BufferedOutputStream out = null;
//        File newFile = null;
//        String str_temp = null;
//        for (int i = 0; i < Number - 1; i++) {
//            str_temp = i + " " + oldFile.getName() + "\n";
//            str_temp += file.substring(start, end);
//            newFile = new File(tmp_File_Path + i + ".file");
//            out = new BufferedOutputStream(new FileOutputStream(newFile));
//
//            out.write(str_temp.getBytes());
//            out.close();
//            start += size;
//            end += size;
//        }
//        str_temp = Number - 1 + " " + oldFile.getName() + "\n";
//        str_temp += file.substring(start);
//        newFile = new File(tmp_File_Path + md5+"_"+"end" + ".file");
//        out = new BufferedOutputStream(new FileOutputStream(newFile));
//        out.write(str_temp.getBytes());
//        out.close();
//        return;
//    }


//    /**
//     * 分块存储指定路径
//     * @param fileName 需要分块文件的路径
//     * @param outPath  分片文件输出路径
//     * @param Number   分片的位数
//     * @throws Exception
//     */
//    public void splitByNumber(String fileName, String outPath, int Number) throws Exception {
//        File file1 = new File(outPath);
//        if(!file1.exists()){
//            file1.mkdirs();
//        }
//
//        File oldFile = new File(fileName);
//        BufferedInputStream in = new BufferedInputStream(new FileInputStream(oldFile));
//        String file = encode(in);
//        int length = file.length();
//        System.out.println("字符串长度：" + length);
//        int size = length / Number;
//        int start = 0, end = size;
//        BufferedOutputStream out = null;
//        File newFile = null;
//        String str_temp = null;
//        int i = 0;
//        for (; i < Number - 1; i++) {
//            str_temp = i + " " + oldFile.getName() + "\n";
//            str_temp += file.substring(start, end);
//
//            newFile = new File(outPath + i + ".file");
//            out = new BufferedOutputStream(new FileOutputStream(newFile));
//            out.write(str_temp.getBytes());
//            out.close();
//            start += size;
//            end += size;
//        }
//        str_temp = Number - 1 + " " + oldFile.getName() + "\n";
//        str_temp += file.substring(start);
//        newFile = new File(outPath +i + ".file");
//        out = new BufferedOutputStream(new FileOutputStream(newFile));
//        out.write(str_temp.getBytes());
//        out.close();
//        return;
//    }

    /**
     * 文件合并
     * @param Filename
     * @throws Exception
     */

    public void mergeByName(String Filename) throws Exception {

        String property = System.getProperty("java.io.tmpdir");
//        String tmp_File_Path = property+"/cXTTr94nRMPRv8/";
        String tmp_File_Path = property+File.separator+"cXTTr94nRMPRv8"+File.separator;

        File file = new File(tmp_File_Path);
        File list[] = file.listFiles();
        Map<String, String> map = new HashMap<String, String>();
//        String newFileName = null;

        for (int i = 0; i < list.length; i++) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(list[i])));
            String str_head = reader.readLine();
            String id = str_head.substring(0, str_head.indexOf(" "));
//            newFileName = str_head.substring(str_head.indexOf(" ") + 1);
            map.put(id, list[i].getAbsolutePath());
            reader.close();
        }
//        for (File f : list) {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
//            String str_head = reader.readLine();
//            String id = str_head.substring(0, str_head.indexOf(" "));
//            newFileName = str_head.substring(str_head.indexOf(" ") + 1);
//            map.put(id, f.getAbsolutePath());
//            reader.close();
//        }

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < list.length - 1; i++) {

            File f = new File(map.get(String.valueOf(i)));
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

            String temp = null;
            while ((temp = reader.readLine()) != null) {
                int var19 = temp.lastIndexOf(Filename) + Filename.length();
                String payload = temp.substring(var19);
                stringBuffer.append(payload);
            }
            reader.close();
        }
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(property + Filename));
        out.write(decode(stringBuffer.toString()));
        out.close();
    }

    /**
     * 合并代码，为动态生成代码而生
     * @param Filename 输入分块前的文件名
     * @throws Exception
     */
    public void mergeByName_Code(String Filename) throws Exception {

        String property = System.getProperty("java.io.tmpdir");
        String tmp_File_Path = property+File.separator+"cXTTr94nRMPRv8"+File.separator;

        File file = new File(tmp_File_Path);
        File list[] = file.listFiles();
        Map<String, String> map = new HashMap<String, String>();
        String newFileName = null;

        for (int i = 0; i < list.length; i++) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(list[i])));
            String str_head = reader.readLine();
            String id = str_head.substring(0, str_head.indexOf(" "));

            map.put(id, list[i].getAbsolutePath());
            reader.close();
        }


        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < list.length ; i++) {
            File f = new File(map.get(String.valueOf(i)));
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

            String temp = null;
            while ((temp = reader.readLine()) != null) {
                int i1 = temp.lastIndexOf(Filename) + Filename.length();
                String substring = temp.substring(i1);
                stringBuffer.append(substring);
            }
            reader.close();
        }
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(property + Filename));
        out.write(decode(stringBuffer.toString()));
        out.close();
    }

    /**
     * 读取文件内容进行分块文件生成
     * @param FileName 指定需要分块的文件
     * @param Number   分片位数
     * @throws IOException
     */
    public void ReadTempPathFile(String FileName,int Number) throws IOException {

        HashMap<Integer, String> map = new HashMap<Integer,String>();

            String property = System.getProperty("java.io.tmpdir");
            String tmp_Class_Path = property+"/tmp_Class_Path/";
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




        File file_class_dir = new File(tmp_Class_Path);
        if (!file_class_dir.exists()){
            file_class_dir.mkdirs();
        }

        for (int j = 0; j < map.size(); j++) {

            File file1 = new File(tmp_File_Path + j + ".file");
            String s = map.get(j);
            s = s.replaceAll("\r\\n|\\n","");
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file1));
            out.write(s.getBytes(),0,s.length());

            out.close();

            }
        }
    /**
     * 读取文件内容进行分块文件生成
     * @param FileName 指定需要分块的文件
     * @param outFiledir 指定输出路径
     * @param Number   分片位数
     * @throws IOException
     */
    public void ReadTempPathFile(String FileName,String outFiledir,int Number) throws IOException {

        HashMap<Integer, String> map = new HashMap<Integer,String>();

        String property = System.getProperty("java.io.tmpdir");
        String tmp_Class_Path = property+"/tmp_Class_Path/";


        File filedir = new File(outFiledir);
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
            str_temp = i + " " + oldFile.getName() ;
            str_temp += file.substring(start, end).replaceAll("\r\\n|\\n","");
            map.put(i, str_temp);

            start += size;
            end += size;
        }
        str_temp = Number - 1 + " " + oldFile.getName() ;
        str_temp += file.substring(start).replaceAll("\r\\n|\\n","");
        map.put(i,str_temp);




        File file_class_dir = new File(tmp_Class_Path);
        if (!file_class_dir.exists()){
            file_class_dir.mkdirs();
        }

        for (int j = 0; j < map.size(); j++) {

            File file1 = new File(outFiledir + j + ".file");
            String s = map.get(j);
            s = s.replaceAll("\r\\n|\\n","");
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file1));
            out.write(s.getBytes(),0,s.length());

            out.close();

        }
    }










    /**
     * 编码
     *
     * @param in
     * @return
     * @throws IOException
     */
    public String encode(InputStream in) throws IOException {
        byte[] data = new byte[in.available()];
        in.read(data);
        String encode = new BASE64Encoder().encode(data);
        return encode;
    }

    /**
     * 解码
     *
     * @param base64Str
     * @return
     * @throws IOException
     */
    public byte[] decode(String base64Str) throws IOException {
        byte[] bytes = new BASE64Decoder().decodeBuffer(base64Str);

        return bytes;
    }

    /**
     * 随机数
     *
     * @return
     */
    public String randNumber() {
        double number = Math.random();
        String str = String.valueOf(number);
        str = str.replace(".", "");
        return str;
    }

    public  static void Create_Mkdir(){
        String property = System.getProperty("java.io.tmpdir");
        String tmp_File_Path = property+"/cXTTr94nRMPRv8/";
        File file = new File(tmp_File_Path);
        if (!file.exists()){
            file.mkdirs();

        }
        else {

            File[] files = file.listFiles();
            File file1 = null;
            for (int i = 0; i < files.length; i++) {
                file1 = files[i];
                if (file1.getName().contains("end")) {
                    file1.delete();
                }
            }

        }
//                }


//            for (java.io.File file1 : files) {
//                if (file1.getName().contains("end")){
//                    file1.delete();
//                }
//            }
            //文件存在的情况，或传输不完整的情况待写


    }




    }