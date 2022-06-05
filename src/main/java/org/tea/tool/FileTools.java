package org.tea.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tea.entity.PropertiesEntity;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lxh
 * @date 2022/6/4-下午10:10
 */
public class FileTools {
    private final static Logger log = LoggerFactory.getLogger(FileTools.class);

    private static String CONFIG_PATH;

    private final static int BUFFER_SIZE = 4 * 1024;

    private final static Pattern FILE_NAME_REGX = Pattern.compile("([A-Z]\\w+)\\s?\\{");

    private final static Pattern XML_NAME_REGEX = Pattern.compile("namespace=\".+\\.(\\w+)\"");

    static {
        CONFIG_PATH = System.getProperty("user.home") + "/.config/tea_gen.properties";
        File CONFIG_FILE = new File(CONFIG_PATH);
        try {
            if (!CONFIG_FILE.getParentFile().exists()
                    && CONFIG_FILE.getParentFile().mkdirs())
                log.info("创建配置文件的父文件夹成功");

            if (!CONFIG_FILE.exists() &&
                    CONFIG_FILE.createNewFile())
                log.info("创建配置文件成功....");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将生成的 Java 源文件代码写入到指定的路径中，这里的路径指的是绝对路径
     *
     * @param srcFile 生成的源文件的具体内容
     * @param path    写入的路径
     */
    public static void writeJavaToFile(String srcFile, String path) {
        Matcher matcher = FILE_NAME_REGX.matcher(srcFile);
        if (!matcher.find()) {
            log.info("无法匹配到对应的文件名，本次写入失败");
            return;
        }

        String cname = matcher.group(1).concat(".java");
        writeToFile(srcFile, path, cname);
    }

    /**
     * 将生成的 Mybatis 的 XML 映射文件写入到文件中
     *
     * @param srcFile 待写入的源文件的具体内容
     * @param path    写入的文件的所在路径
     */
    public static void writeXmlMapper(String srcFile, String path) {
        Matcher matcher = XML_NAME_REGEX.matcher(srcFile);
        if (!matcher.find()) {
            log.info("无法找到对应的 XML 文件名，本次写入退出");
            return;
        }

        String xmlName = matcher.group(1).concat(".xml");
        writeToFile(srcFile, path, xmlName);
    }

    private static void writeToFile(String srcFile, String path, String xmlName) {
        File file = new File(path + "/" + xmlName);
        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    log.info("创建文件 {}", file.getName());
                }
            }
            Files.writeString(Paths.get(file.toURI()), srcFile);
            log.info("写入文件 {} 成功", xmlName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeProperties(PropertiesEntity properties) {
        Properties prop = new Properties();
        Field[] fields = PropertiesEntity.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            try {
                Object obj = field.get(properties);
                if (obj == null) continue;
                prop.put(field.getName(), obj);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        try (
                OutputStream out = new FileOutputStream(CONFIG_PATH)
        ) {
            prop.store(out, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static PropertiesEntity readProperties() {
        File file = new File(CONFIG_PATH);
        Properties prop = new Properties();

        try (
                InputStream in = new FileInputStream(file)
        ) {
            prop.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        PropertiesEntity ans = PropertiesEntity.Builder
                .builder().build();
        Field[] fields = PropertiesEntity.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            try {
                field.set(ans, prop.get(field.getName()));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return ans;
    }
}
