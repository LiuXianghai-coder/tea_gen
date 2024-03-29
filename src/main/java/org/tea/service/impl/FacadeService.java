package org.tea.service.impl;

import org.tea.entity.TabStructure;
import org.tea.service.CommentService;
import org.tea.service.SuperClassService;

/**
 * "外观模式"，用于统一相关的服务接口
 *
 * @author lxh
 */
public class FacadeService implements CommentService, SuperClassService {

    private final CommentService commentService;

    public FacadeService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public String genComment(String content) {
        return commentService.genComment(content);
    }

    @Override
    public boolean willGen(TabStructure struct, Class<?> sc) {
        return false;
    }

    @Override
    public String genGetter(String fieldName) {
        return commentService.genGetter(fieldName);
    }

    @Override
    public String genSetter(String fieldName) {
        return commentService.genSetter(fieldName);
    }
}
