package org.tea.tool;

/**
 * 相关的工具类
 *
 * @author lxh
 * @date 2022/6/26-下午8:21
 */
public class ConstTools {
    public static String toClassName(String str) {
        String ans = toCamel(str);
        return ans.substring(0, 1).toUpperCase() + ans.substring(1);
    }

    public static String toMapperName(String str) {
        return toClassName(str) + "Mapper";
    }

    public static String toCamel(String str) {
        String[] split = str.split("_");
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            if (i == 0) ans.append(split[i].toLowerCase());
            else {
                String first = split[i].substring(0, 1);
                String nxt = split[i].toLowerCase().substring(1);
                ans.append(first.toUpperCase()).append(nxt);
            }
        }

        return ans.toString();
    }

    public static String pathToPack(String path) {
        String tmp = path.replaceAll("/", "\\.");
        if (!tmp.endsWith(".")) tmp += ".";
        return tmp;
    }

    public static String packToPath(String pack) {
        String tmp = pack.replaceAll("\\.", "/");
        if (!tmp.endsWith("/")) tmp += "/";
        return tmp;
    }
}
