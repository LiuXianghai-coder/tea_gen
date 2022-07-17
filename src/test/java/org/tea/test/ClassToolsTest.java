package org.tea.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lxh
 * @date 2022/7/16-下午11:05
 */
public class ClassToolsTest {
    private final static Logger log = LoggerFactory.getLogger(ClassToolsTest.class);

    @Test
    public void test() {
        log.info(String.class.getName());
    }
}
