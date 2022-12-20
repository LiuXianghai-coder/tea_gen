package org.tea.rule;

/**
 * Java Bean 中生成 Setter 方法的约定
 *
 * @author lxh
 */
public interface SetterRule {

    /**
     * 通过相关的属性名称，按照相关的 Java Bean 约定生成对应的 Setter 方法名，这里生成的方法
     * 名不包含返回类型、参数类型以及方法提信息 <br />
     * 具体的生成约定如下: <br />
     * <ol>
     *     <li>
     *         如果属性名的前两个字母都是小写，那么生成的方法名就是 "set" + 属性名的第一个字母转大写的字符串形式
     *     </li>
     *     <li>
     *         如果属性名的前两个字母中存在大写，那么生成的方法名为 "set" + 属性名
     *     </li>
     *     <li>
     *         如果属性名以 "is" 开头，那么生成的方法名将会舍弃掉 "is" 前缀，然后将其放到 "set" 后
     *         组合成为对应的 Setter 方法
     *     </li>
     * </ol>
     *
     * @param fieldName 需要生成 Setter 方法的属性名称
     * @return  按照约定规则生成的 Setter 方法名
     */
    String setterName(String fieldName);
}
