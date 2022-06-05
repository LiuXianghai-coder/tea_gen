package org.tea.ui;

import javax.swing.*;

public class MainUI {
    final BaseScreen screen = new BaseScreen();

    public void start() {
        JFrame frame = screen.defaultFrame();
    }
}
