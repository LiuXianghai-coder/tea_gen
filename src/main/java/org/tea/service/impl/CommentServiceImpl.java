package org.tea.service.impl;

import org.tea.service.CommentService;

/**
 * @author lxh
 * @date 2022/7/16-下午10:13
 */
public class CommentServiceImpl implements CommentService {

    private final static String PRE = "/**";
    private final static String END = "**/";

    @Override
    public String genComment(String content) {
        return PRE + "\n" + "\t* " + content + "\n\t" + END;
    }
}
