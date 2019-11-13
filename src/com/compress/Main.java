package com.compress;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static final String JPG = "jpg";
    public static final String BMP = "bmp";
    public static final String PNG = "png";
    public static final String TXT = "txt";
    public static final String LOG = "log";
    public static final String CUE = "cue";
    private static final Set<String> sets = new HashSet<>();
    static{
        sets.add(JPG);
        sets.add(BMP);
        sets.add(PNG);
        sets.add(TXT);
        sets.add(LOG);
        sets.add(CUE);
    }
    public static void main(String[] args) {
        new Test();
    }
    public static void  compress(File file){
        File[] files = file.listFiles();
        if(files.length>0){
            List<String> sourceFilePaths = new ArrayList<String>();
            for (File fl : files) {
                if(fl.isDirectory()){
                    compress(fl);
                }else{
                    String fileName = fl.getName();
                    String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                    if(contains(suffix)){
                        System.out.println("包含压缩文件:::->"+fileName);
                        sourceFilePaths.add(fl.getAbsolutePath());
                    }
                }
            }
            if(sourceFilePaths.size()>0){
                try {
                    ZipUtils.compress(sourceFilePaths, file.getAbsolutePath()+File.separator+file.getName()+".zip", false);
                    deleteFile(sourceFilePaths);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private static void deleteFile(List<String> sourceFilePaths) {
        for (String filePath : sourceFilePaths) {
            File file = new File(filePath);
            String fileName = file.getName();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            if(!LOG.equalsIgnoreCase(suffix)){
                delete(filePath);
            }
        }
    }

    private static boolean contains(String suffix) {
        for (String set : sets) {
            if(set.equalsIgnoreCase(suffix)){
                return true;
            }
        }
        return false;
    }

    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName
     *            要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile()){
                return deleteFile(fileName);
            }

        }
        return false;
    }
    /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }


}
