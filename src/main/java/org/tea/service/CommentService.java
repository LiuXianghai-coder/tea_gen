package org.tea.service;

/**
 * 和注释相关的生成服务的统一接口
 *
 * @author lxh
 */
public interface CommentService {
    String genComment(String content);

    String genGetter(String fieldName);

    String genSetter(String fieldName);
}
