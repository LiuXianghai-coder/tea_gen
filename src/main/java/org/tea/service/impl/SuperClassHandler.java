package org.tea.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tea.entity.TabStructure;
import org.tea.service.SuperClassService;
import org.tea.tool.ClassTools;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author lxh
 * @date 2022/7/24-下午1:13
 */
public class SuperClassHandler implements SuperClassService {

    private final static Logger log = LoggerFactory.getLogger(SuperClassHandler.class);

    @Override
    public boolean willGen(TabStructure struct, Class<?> sc) {
        return false;
    }
}
