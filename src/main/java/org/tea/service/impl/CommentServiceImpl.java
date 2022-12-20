package org.tea.service.impl;

import org.tea.rule.GetterRule;
import org.tea.rule.SetterRule;
import org.tea.rule.impl.BeanGetterRule;
import org.tea.rule.impl.BeanSetterRule;
import org.tea.service.CommentService;

/**
 * @author lxh
 */
public class CommentServiceImpl implements CommentService {

    private final static String PRE = "/**";
    private final static String END = "**/";

    private final static GetterRule getterRule = new BeanGetterRule();

    private final static SetterRule setterRule = new BeanSetterRule();

    @Override
    public String genComment(String content) {
        return PRE + "\n" + "\t* " + content + "\n\t" + END;
    }

    @Override
    public String genGetter(String fieldName) {
        return getterRule.getterName(fieldName);
    }

    @Override
    public String genSetter(String fieldName) {
        return setterRule.setterName(fieldName);
    }
}
