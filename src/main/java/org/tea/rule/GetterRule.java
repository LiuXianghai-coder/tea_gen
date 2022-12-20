package org.tea.rule;

/**
 * Java Bean 类型中，约定生成的 Getter 方法规则
 *
 * @author lxh
 */
public interface GetterRule {

    /**
     * 通过指定的属性名称格式，生成对应的 Getter 方法名称，这里生成的方法名称不包含返回类型、参数类型以及具体的方法体内容 <br />
     * 具体的约定如下: <br />
     * <ol>
     *     <li>
     *         如果属性名称的前两个字母都是小写，并且不是以 "is" 开头，那么生成的 Getter
     *         方法名称将会将 "get" 和属性名称的第一个字母大写拼成对应的 Getter 方法名称
     *     </li>
     *     <li>
     *         如果属性名称的前两个字母存在大写，那么将会直接将 "get" 和属性名称拼接组合成为
     *         对应的 Getter 方法名称
     *     </li>
     *     <li>
     *         如果属性名称以 "is" 开头，那么将会省略 "get" 前缀，直接将属性名作为 Getter 方法名
     *     </li>
     * </ol>
     *
     * @param filedName 相关的字段属性名称
     * @return 按照约定生成的 Getter 方法名称
     */
    String getterName(String filedName);
}
