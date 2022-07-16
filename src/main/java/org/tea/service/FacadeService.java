package org.tea.service;

/**
 * "外观模式"，用于统一相关的服务接口
 *
 * @author lxh
 * @date 2022/7/16-下午10:20
 */
public class FacadeService implements CommentService {

    private CommentService commentService;

    @Override
    public String genComment(String content) {
        return commentService.genComment(content);
    }
}
