package org.tea.ui;

import org.tea.domain.mysql.service.impl.MySQLGenClassService;
import org.tea.domain.mysql.service.impl.MySQLTableInfoService;
import org.tea.domain.mysql.service.impl.MySQLTableStructService;
import org.tea.domain.psql.entity.PsqlTableStructure;
import org.tea.entity.SchemaStructure;
import org.tea.entity.TabStructure;
import org.tea.service.GenClassService;
import org.tea.service.TableInfoService;
import org.tea.service.TableStructureService;
import org.tea.domain.psql.service.impl.PsqlGenClassService;
import org.tea.domain.psql.service.impl.PsqlTableInfoService;
import org.tea.domain.psql.service.impl.PsqlTableStructService;
import org.tea.entity.PropertiesEntity;
import org.tea.tool.DataBaseTools;
import org.tea.tool.FileTools;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import static javax.swing.ListSelectionModel.SINGLE_SELECTION;
import static javax.swing.SwingConstants.LEFT;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class BaseScreen {
    private final int PRE_LAB_WID = 10;
    private final int PRE_LAB_HIG = 3;
    private final int TEXT_COLS = 50;

    private JTextField nameField, urlField, passField, tabField, fileField;

    private JList<String> daList;

    private JFrame mainFrame;

    private Clipboard clipboard;

    private PropertiesEntity prop;

    public JFrame defaultFrame() {
        mainFrame = new JFrame("Tea Generator");
        clipboard = mainFrame.getToolkit().getSystemClipboard(); // 粘贴板
        setSize(mainFrame);

        mainFrame.getToolkit();

        mainFrame.setLayout(new BorderLayout());

        JPanel center = getCenterPanel();
        JPanel bottom = getBottom();

        mainFrame.add(center, BorderLayout.CENTER);
        mainFrame.add(bottom, BorderLayout.SOUTH);

        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        initFields();

        return mainFrame;
    }

    private void initFields() {
        prop = FileTools.readProperties();
        urlField.setText(prop.getUrl());
        nameField.setText(prop.getUserName());
        passField.setText(prop.getPassword());
        tabField.setText(prop.getTableName());
        fileField.setText(prop.getFilePath());
    }

    private JPanel getBottom() {
        JPanel bottom = new JPanel(new FlowLayout());
        JButton testButton = new JButton("Test Connection");
        JButton genButton = new JButton("Generate");
        bottom.add(testButton);
        bottom.add(genButton);

        testButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = urlField.getText();
                String user = nameField.getText();
                String pass = passField.getText();
                String da = daList.getSelectedValue();
                String tabName = tabField.getText();
                String path = fileField.getText();

                boolean check = DataBaseTools.checkConnect(url, user, pass, da);
                if (!check) {
                    JOptionPane.showMessageDialog(mainFrame, "连接失败");
                    return;
                }

                JOptionPane.showMessageDialog(mainFrame, "连接成功");
                prop = PropertiesEntity.Builder.builder()
                        .withUrl(url).withUserName(user).withPassword(pass)
                        .withTableName(tabName).withFilePath(path)
                        .withDaType(da)
                        .build();

                FileTools.writeProperties(prop);
            }
        });

        genButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = urlField.getText();
                String user = nameField.getText();
                String pass = passField.getText();
                String da = daList.getSelectedValue();
                String tabName = tabField.getText();
                String path = fileField.getText();

                TableInfoService infoService = null;
                TableStructureService structureService = null;
                GenClassService genClassService = null;

                if (da.equalsIgnoreCase("MySQL")) {
                    infoService = new MySQLTableInfoService();
                    structureService = new MySQLTableStructService();
                    genClassService = new MySQLGenClassService();
                } else if (da.equalsIgnoreCase("PostgresSQL")) {
                    infoService = new PsqlTableInfoService();
                    structureService = new PsqlTableStructService();
                    genClassService = new PsqlGenClassService();
                } else {
                    throw new RuntimeException("不能处理的数据库类型");
                }

                String dbName = url.substring(url.lastIndexOf("/") + 1);
                List<SchemaStructure> dbTables = infoService.selectAllTables(dbName);
                for (SchemaStructure struct : dbTables) {
                    String tab = struct.getTableName();

                    List<TabStructure> structures = structureService.selectByTableName(dbName, tab);
                    String entity = genClassService.genEntityByStruct(structures, "org.xhliu.entity");
                    String xmlMapper = genClassService.genXmlMapperByStruct(
                            structures,
                            "org.xhliu.entity." + DataBaseTools.toClassName(tab),
                            "org.xhliu.mapper.UserInfoMapper"
                    );
                    String mapper = genClassService.genMapperByStruct(structures, "org.xhliu.mapper");

                    FileTools.writeJavaToFile(entity, path);
                    FileTools.writeXmlMapper(xmlMapper, path);
                    FileTools.writeJavaToFile(mapper, path);

                    JOptionPane.showMessageDialog(mainFrame, "写入成功");

                    prop = PropertiesEntity.Builder.builder()
                            .withUrl(url).withUserName(user).withPassword(pass)
                            .withTableName(tabName).withFilePath(path)
                            .withDaType(da)
                            .build();
                    FileTools.writeProperties(prop);
                }
            }
        });

        return bottom;
    }

    private JPanel getCenterPanel() {
        JPanel urlPanel = getUrlPanel();
        JPanel namePanel = getNamePanel();
        JPanel passPanel = getPassPanel();
        JPanel typePanel = getTypePanel();
        JPanel tabPanel = getTabNamePanel();
        JPanel filePanel = getFilePanel();

        JPanel center = new JPanel();
        center.add(urlPanel);
        center.add(namePanel);
        center.add(passPanel);
        center.add(typePanel);
        center.add(tabPanel);
        center.add(filePanel);

        return center;
    }

    private JPanel getFilePanel() {
        JPanel jPanel = new JPanel();
        JLabel tabLabel = new JLabel("filePath");
        fileField = new JTextField(TEXT_COLS);
        fileField.addActionListener(new PasteAction(fileField));

        jPanel.add(tabLabel);
        jPanel.add(fileField);

        return jPanel;
    }

    private JPanel getTabNamePanel() {
        JPanel jPanel = new JPanel();
        JLabel tabLabel = new JLabel("tableName");
        tabField = new JTextField(TEXT_COLS);
        tabField.addActionListener(new PasteAction(tabField));

        jPanel.add(tabLabel);
        jPanel.add(tabField);

        return jPanel;
    }

    private JPanel getTypePanel() {
        String[] databases = new String[]{"MySQL", "PostgresSQL"};
        daList = new JList<>(databases);
        daList.setSelectedIndex(0);
        daList.setSelectionMode(SINGLE_SELECTION);
        daList.setVisibleRowCount(-1);
        daList.setLayoutOrientation(JList.VERTICAL);

        JScrollPane daScroll = new JScrollPane(daList);
        daScroll.setPreferredSize(new Dimension(100, 60));

        JPanel typePanel = new JPanel();
        typePanel.add(daScroll);
        return typePanel;
    }

    private JPanel getPassPanel() {
        JPanel passPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel passLabel = new JLabel("password:");
        passField = new JPasswordField(TEXT_COLS);
        passField.addActionListener(new PasteAction(passField));

        passPanel.add(passLabel);
        passPanel.add(passField);
        return passPanel;
    }

    private JPanel getUrlPanel() {
        JPanel urlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel urlLabel = new JLabel("URL:", LEFT);
        urlField = new JTextField(TEXT_COLS);
        urlField.addActionListener(new PasteAction(urlField));

        urlPanel.add(urlLabel);
        urlPanel.add(urlField);
        return urlPanel;
    }

    private JPanel getNamePanel() {
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel("userName:", LEFT);
        nameField = new JTextField(TEXT_COLS);
        nameField.addActionListener(new PasteAction(nameField));

        namePanel.add(nameLabel);
        namePanel.add(nameField);

        return namePanel;
    }

    private void setSize(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        frame.setSize(width / 2, height / 2);
    }

    private class PasteAction implements ActionListener {
        private final JTextField textField;

        private PasteAction(JTextField textField) {
            this.textField = textField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Transferable clipData = clipboard.getContents(this);
            try {
                String clipString = (String) clipData.getTransferData(DataFlavor.stringFlavor);
                textField.replaceSelection(clipString);
            } catch (UnsupportedFlavorException | IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
