package org.tea.service;

import org.tea.entity.TabStructure;

import java.util.List;

/**
 * 通过相关的结构信息来得到和数据库持久化实体相关的源文件的统一接口
 *
 * @author lxh
 * @date 2022/6/4-下午9:27
 */
public interface GenClassService {
    /**
     * 通过查询到的表结构信息得到对应的实体类
     * @param structures    对应的表结构信息列表
     * @param pack  生成的实体类所在的包
     * @return  生成的实体类源文件（以字符串表示）
     */
    String genEntityByStruct(List<TabStructure> structures, String pack);

    /**
     * 通过相关的表结构，生成和 MyBatis 对应的 XML 映射文件
     * @param structures    相关的表结构信息列表
     * @param entity 对应的映射文件的实体类 （全路径名）
     * @param mapper 和 XML 对应的 dao 接口
     * @return  生成的 XML 格式的 XML 映射文件
     */
    String genXmlMapperByStruct(List<TabStructure> structures, String entity, String mapper);

    /**
     * 通过相关的表结构来生成和 MyBatis 结构中对应的 Mapper
     *
     * @param structures 相关的表结构
     * @param pack  该 Mapper 最后所在的包
     * @return  生成的 Mapper 源文件
     */
    String genMapperByStruct(List<TabStructure> structures, String pack);

    /**
     * 通过相关的表结构来生成和 JPA 中对应的 Repo 接口
     * @param structures    相关的表结构信息
     * @param entity    对应的实体类的名称（全路径名）
     * @param pack  该 repo 最后所在的包
     * @return  生成的 jpa repo 接口
     */
    String genJpaRepoByStruct(List<TabStructure> structures, String entity, String pack);
}
