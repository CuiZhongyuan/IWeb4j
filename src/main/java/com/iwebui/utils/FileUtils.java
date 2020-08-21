package com.iwebui.utils;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件操作工具类
 *
 * @author lgl
 */
public class FileUtils {

    public static void uploadFile(String filePath, byte[] data) {
        String rootpath = new File(".").getAbsolutePath();
        if (-1 != rootpath.indexOf("bin")) {
            rootpath = rootpath.substring(0, rootpath.indexOf("bin")) + "webapps";
        }
        filePath = filePath.replace("/", File.separator);
        filePath = filePath.replace("\\", File.separator);
        try {
            FileCopyUtils.copy(data, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void uploadFile1(String filePath, byte[] data) {
        filePath = filePath.replace("/", File.separator);
        filePath = filePath.replace("\\", File.separator);
        try {
            FileCopyUtils.copy(data, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param file
     * @param modelName
     * @param realPath
     * @param deleteFlag
     * @return
     * @throws Exception
     */
    public static String uploadFiles(MultipartFile file, String modelName, String realPath, Boolean deleteFlag) throws Exception {
        return uploadFileAndClearOldFile(file, modelName + "_", realPath, deleteFlag);
    }

    /**
     * 确定要保存 把临时文件夹中的临时图片 保存到正式文件夹中
     * dirPath 为临时文件夹路径。默认的临时文件夹和正式文件夹路径区别 如：
     * 临时：/模块名/数据id/字段名_  正式：模块名/数据id/字段名
     *
     * @param dirPath
     * @return
     * @throws Exception
     * @author hy
     */
    public static String copyFileToDir(String dirPath) throws Exception {
        dirPath = dirPath.replace("/", File.separator);
        dirPath = dirPath.replace("\\", File.separator);
        //从所传的临时文件路径中 得到临时文件夹路径 去掉ROOT_PATH
        String lsDirPath = dirPath.substring(0, dirPath.lastIndexOf(File.separator));
        String filename = dirPath.substring(dirPath.lastIndexOf(File.separator));
        //从临时文件夹路径中 获得正式文件夹路径 即比临时文件夹的字段名上 少了"_"
        String zsDirPath = lsDirPath.substring(0, dirPath.lastIndexOf("_"));
        //如果没有正式文件夹 就创建
        File zsFile = new File(zsDirPath);
        if (!zsFile.exists()) {
            zsFile.mkdirs();
        } else {
            //删除原正式文件中的文件
            deleteDirAndFile(zsDirPath);
            //创建正式的文件夹
            createDir(zsDirPath);
        }
        copyFile(dirPath, new File(zsDirPath + filename));
        String zsFilePath = zsDirPath + File.separator + filename;
        zsFilePath = zsFilePath.replace("\\", "/");
        return zsFilePath;
    }

    /**
     * @param file
     * @param modelName
     * @param realPath
     * @param deleteFlag 是否删除已经上传过的文件
     * @return
     * @throws Exception
     */
    public static String uploadFileAndClearOldFile(MultipartFile file, String modelName, String realPath, Boolean deleteFlag) throws Exception {
        // 获取了文件整个名称及路径
        String attachName = file.getOriginalFilename();
        // 获取文件类型
        String fileType = attachName
                .substring((attachName.lastIndexOf(".")) + 1);
        String photo = System.currentTimeMillis() + "." + fileType;
        //modelName的文件夹的路径
        String dirPath = realPath + "/upfile/" + modelName;
        String resPath = "/upfile/" + modelName + File.separator + photo;
        dirPath = dirPath.replace("/", File.separator);
        dirPath = dirPath.replace("\\", File.separator);
        //modelName的文件夹下要上传文件的路径
        String filePath = dirPath + File.separator + photo;
        filePath = filePath.replace("/", File.separator);
        filePath = filePath.replace("\\", File.separator);
        if (deleteFlag) {
            // 先删除已有附件
            deleteDirAndFile(dirPath);
        }
        // 将文件上传到指定系统目录下
        createDir(dirPath);
        uploadFile(filePath, file.getBytes());
        //返回到前台的路径 要处理一下（把'\'都换成'/'）否则 前台显示不出来
        resPath = resPath.replace("\\", "/");
        return resPath;
    }

    /**
     * 上传文件
     *
     * @param file      文件名称
     * @param modelName 模块名称（分类名称）
     * @param realPath  真实路径（一般为获取tomcat下项目跟目录）
     * @return
     * @throws Exception
     */
    public static String uploadFile(MultipartFile file, String modelName, String realPath) throws Exception {
        // 获取了文件整个名称及路径
        String attachName = file.getOriginalFilename();
        // 获取文件类型
        String fileType = attachName
                .substring((attachName.lastIndexOf(".")) + 1);
        //创建文件名
        String fileName = System.currentTimeMillis() + "." + fileType;
        //modelName的文件夹的路径
        String dirPath = realPath + "/upfile/" + modelName;
        String resPath = "/upfile/" + modelName + File.separator + fileName;
        dirPath = dirPath.replace("/", File.separator);
        dirPath = dirPath.replace("\\", File.separator);
        //modelName的文件夹下要上传文件的路径
        String filePath = dirPath + File.separator + fileName;
        filePath = filePath.replace("/", File.separator);
        filePath = filePath.replace("\\", File.separator);
        // 将文件上传到指定系统目录下
        createDir(dirPath);
        uploadFile(filePath, file.getBytes());
        //返回到前台的路径 要处理一下（把'\'都换成'/'）否则 前台显示不出来
        resPath = resPath.replace("\\", "/");
        return resPath;
    }

    /**
     * 上传图片并且把图片按照比例缩下
     *
     * @param file      文件名称
     * @param modelName 模块名称（分类名称）
     * @param realPath  真实路径（一般为获取tomcat下项目跟目录）
     * @return
     * @throws Exception
     */
    public static Map<String, String> uploadImgFileAndScale(MultipartFile file, String modelName, String realPath) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        // 获取了文件整个名称及路径
        String attachName = file.getOriginalFilename();
        // 获取文件类型
        String fileType = attachName
                .substring((attachName.lastIndexOf(".")) + 1);
        //创建文件名
        String fileName = System.currentTimeMillis() + "." + fileType;
        //原始图片
        //modelName的文件夹的路径
        String dirPath = realPath + "/upfile/" + modelName;
        //判断文件夹是否存在，不存在就创建
        createDir(dirPath);
        //modelName的文件夹下要上传文件的路径
        String filePath = dirPath + File.separator + fileName;
        //缩小中等大小，缩放比例为50%
        String scaleMiddlePath = dirPath + "/middle/";
        //判断文件夹是否存在，不存在就创建
        createDir(scaleMiddlePath);
        String fileScaleMiddlePath = dirPath + "/middle/" + fileName;
        //缩小到最下，缩放比例为10%
        String scaleSmallPath = dirPath + "/small/";
        //判断文件夹是否存在，不存在就创建
        createDir(scaleSmallPath);
        String fileScaleSmallPath = dirPath + "/small/" + fileName;

        filePath = filePath.replace("/", File.separator);
        filePath = filePath.replace("\\", File.separator);
        // 将文件上传到指定系统目录
        uploadFile(filePath, file.getBytes());
        //上传完成以后进行图片缩小
//        ImageUtil.scaleForProportion(filePath, fileScaleMiddlePath, 0.5f);//按照50%比例缩小
//        //上传完成以后进行图片缩小
//        ImageUtil.scaleForProportion(filePath, fileScaleSmallPath, 0.1f);//按照10%比例缩小

        //返回到前台的路径 要处理一下（把'\'都换成'/'）否则 前台显示不出来
        String resPath = "/upfile/" + modelName + File.separator + fileName;
        String resMiddlePath = "/upfile/" + modelName + "/middle/" + fileName;
        String resSmallPath = "/upfile/" + modelName + "/small/" + fileName;
        resPath = resPath.replace("\\", "/");
        resMiddlePath = resMiddlePath.replace("\\", "/");
        resSmallPath = resSmallPath.replace("\\", "/");
        map.put("resPath", resPath);
        map.put("resMiddlePath", resMiddlePath);
        map.put("resSmallPath", resSmallPath);
        return map;
    }

    /**
     * 下载文件
     *
     * @param filePath
     * @return
     */
    public static byte[] downloadFile(String filePath) {
        filePath = filePath.replace("/", File.separator);
        filePath = filePath.replace("\\", File.separator);
        File returnFile = new File(filePath);
        byte[] data = new byte[0];
        try {
            data = FileCopyUtils.copyToByteArray(returnFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 下载文件
     *
     * @param filePath
     * @return
     */
    public static byte[] downloadFileNoRootPath(String filePath) {
        filePath = filePath.replace("/", File.separator);
        filePath = filePath.replace("\\", File.separator);
        String path = filePath;
        File returnFile = new File(path);
        byte[] data = new byte[0];
        try {
            data = FileCopyUtils.copyToByteArray(returnFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 根据文件路径删除文件
     *
     * @param filePath
     */
    public static void deleteFile(String filePath) {
        filePath = filePath.replace("/", File.separator);
        filePath = filePath.replace("\\", File.separator);
        File returnFile = new File(filePath);
        returnFile.delete();
    }

    /**
     * 创建文件夹
     *
     * @author hy
     */
    public static void createDir(String dirPath) {
        dirPath.replace("\\", File.separator);
        dirPath.replace("/", File.separator);
        File channelDir = new File(dirPath);
        if (!channelDir.exists()) {
            channelDir.mkdirs();
        }
    }

    /**
     * 删除目录
     *
     * @param dirPath
     */
    public static void deleteDir(String dirPath) {
        dirPath.replace("\\", File.separator);
        dirPath.replace("/", File.separator);
        File channelDir = new File(dirPath);
        if (channelDir.exists() && channelDir.isDirectory()) {
            channelDir.delete();
        }
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param dirPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirAndFile(String dirPath) {
        boolean flag = false;
        //文件流的启动位置在tomcat的bin下
        String rootpath = new File(".").getAbsolutePath();
        if (-1 != rootpath.indexOf("bin")) {
            rootpath = rootpath.substring(0, rootpath.indexOf("bin")) + "webapps";
        }
//		dirPath = rootpath+File.separator+ROOT_PATH + dirPath;
        dirPath.replace("\\", File.separator);
        dirPath.replace("/", File.separator);
        // 如果dirPath不以文件分隔符结尾，自动添加文件分隔符
        if (!dirPath.endsWith(File.separator)) {
            dirPath = dirPath + File.separator;
        }
        File dirFile = new File(dirPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (File file : files) {
            // 删除子文件
            if (file.isFile()) {
                file = new File(file.getAbsolutePath());
                // 路径为文件且不为空则进行删除
                if (file.isFile() && file.exists()) {
                    file.delete();
                    flag = true;
                }
                if (!flag) {
                    break;
                }
            } // 删除子目录
            else {
                flag = deleteDirAndFile(file.getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag) {
            return false;
        }
        // 删除当前目录
        return dirFile.delete();
    }

    public static void copyFile(String filePath, File f2) throws IOException {
        filePath = filePath.replace("/", File.separator);
        filePath = filePath.replace("\\", File.separator);
        File f1 = new File(filePath);
        FileCopyUtils.copy(f1, f2);
    }

    //复制方法
    public static void copy(String src, String des) throws Exception {
        //初始化文件复制
        File file1 = new File(src);
        //把文件里面内容放进数组
        File[] fs = file1.listFiles();
        //初始化文件粘贴
        File file2 = new File(des);
        //判断是否有这个文件有不管没有创建
        if (!file2.exists()) {
            file2.mkdirs();
        }
        //遍历文件及文件夹
        for (File f : fs) {
            if (f.isFile()) {
                //文件 //调用文件拷贝的方法
                fileCopy(f.getPath(), des + "\\" + f.getName());
            } else if (f.isDirectory()) {
                //文件夹 //继续调用复制方法      递归的地方,自己调用自己的方法,就可以复制文件夹的文件夹了
                copy(f.getPath(), des + "\\" + f.getName());
            }
        }

    }

    /**
     * 文件复制的具体方法
     *
     * @param src --待复制的路径
     * @param des --复制后的路径
     * @throws Exception
     */
    private static void fileCopy(String src, String des) throws Exception {
        //io流固定格式
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(des));
        //记录获取长度
        int i = -1;
        //缓冲区
        byte[] bt = new byte[2014];
        while ((i = bis.read(bt)) != -1) {
            bos.write(bt, 0, i);
        }
        bis.close();
        bos.close();
        //关闭流
    }
}
