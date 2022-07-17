package org.tea.tool;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 和类相关操作的工具类
 *
 * @author lxh
 * @date 2022/7/16-下午10:58
 */
public class ClassTools {
    public static Set<Class<?>> findAllClasses(String packName) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream resource = loader.getResourceAsStream(packName.replaceAll("\\.", "/"));
        assert resource != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource));
        return reader.lines().filter(line -> line.endsWith(".class"))
                .map(obj -> getClass(obj, packName))
                .collect(Collectors.toSet());
    }

    private static Class<?> getClass(String className, String packName) {
        try {
            return Class.forName(packName + "." +
                    className.substring(0, className.lastIndexOf(".")));
        } catch (ClassNotFoundException e) {
            // ignore
        }
        return null;
    }

    /**
     * 检查当前的类型是否是基础类型，这里的基础类型指的是位于 `java.lang` 包下的类，
     * 这些类不需要显式地进行导入
     * @param type  相关的类的类型
     * @return  当前的类型是否是 `java.lang` 包下的类
     */
    public static boolean isBasicClass(Class<?> type) {
        if (type.isPrimitive()) return true;
        String name = type.getName();
        int lastIdx = name.lastIndexOf(".");
        String pack = name.substring(0, lastIdx);
        return pack.equals("java.lang");
    }
}
