package me.gv7.woodpecker.uitis;

import java.io.*;
import java.net.URLClassLoader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class File_loader {

    /**
     * 加载本地jar执行
     *
     * @param FileName  合并后的文件名
     * @param ClassName 加载的ClassName
     */
    public void FileloaderInvoke(String FileName,String ClassName) {


        String property = System.getProperty("java.io.tmpdir");
        String s = property + FileName;
        java.net.URL url = null;
        try {
            url = new File(s).toURI().toURL();
            new URLClassLoader(new java.net.URL[]{new java.net.URL(url.toString())}).loadClass(ClassName).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 验证MD5值，确保文件是否完整
     *
     *
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static boolean Check_File_Md5() throws NoSuchAlgorithmException {
        String property = System.getProperty("java.io.tmpdir");
        String ChunkedPath = property + "/cXTTr94nRMPRv8/";
        String old_File_MD5=null;
        String new_File_md5=null;
        File file = new File(ChunkedPath);
        File[] files = file.listFiles();
        for (File file1 : files) {
            if (file1.getName().contains("end")){
                int end = file1.getName().indexOf("end");
                old_File_MD5 = file1.getName().substring(0, end-1);
                System.out.println("old_file:"+old_File_MD5);
                break;
            }

        }

        String ClassPath = property + GET_FileName();
        new_File_md5 = getMD5(ClassPath);
        System.out.println("new_file_md5:"+new_File_md5);
        if (new_File_md5!=null && old_File_MD5!=null){
            if (new_File_md5.equals(old_File_MD5)){
                return true;
            }
        }

        return false;






    }

    /**
     * 获取未分块前的FIleName
     * @return
     */
    public static String GET_FileName(){
        String substring=null;
        String property = System.getProperty("java.io.tmpdir");
        String ChunkedPath = property + "/cXTTr94nRMPRv8/";
        File file = new File(ChunkedPath);
        File file1 = file.listFiles()[0];


        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file1)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String str_head = null;
        try {
            str_head = reader.readLine();
            substring = str_head.substring(str_head.indexOf(" ") + 1);
//            System.out.println(substring);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return substring;
    }


    /**
     * 获取文件MD5值
     * @param path
     * @return
     */

    public static String getMD5(String path) {
        String[] strHex = { "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = md.digest(FileToByteArray(path));
            for (int i = 0; i < b.length; i++) {
                int d = b[i];
                if (d < 0) {
                    d += 256;
                }
                int d1 = d / 16;
                int d2 = d % 16;
                sb.append(strHex[d1] + strHex[d2]);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 读取文件内容转换成byte
     * @param fileName
     * @return
     */
    public static byte[] FileToByteArray(String fileName) {
        //选择源
        File src = new File(fileName);
        //选择流
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            //搬运
            bos = new ByteArrayOutputStream();
            fis = new FileInputStream(src);
            int temp;
            //用来暂时存放数据的，FileInputStream 的read方法会重复向里面读数据，
            //接着通过ByteArrayOutputStream写，这是一个重复的过程。直到temp= -1 代表读完。然后return。
            byte[] bt = new byte[1024*10];
            while((temp = fis.read(bt))!= -1) {
                bos.write(bt, 0, temp);
            }
            bos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关流
            try {
                if(null != fis)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bos.toByteArray();
    }




    public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException {

//        String md5One = getMD5("/Users/nice0e3/Desktop/路线/调试笔记/分块传输/untitled/demo/src/main/java/calc.jpg");
//        String md5 = getMD5("/var/folders/p6/bcm1mdpn75l__wc0yc280xx00000gn/T/calc.jpg");
//        System.out.println(md5One);
//        System.out.println(md5);
//        boolean b = Check_File_Md5();



    }
}

