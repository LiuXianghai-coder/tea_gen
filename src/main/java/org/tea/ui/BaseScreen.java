package org.tea.ui;

import org.tea.domain.mysql.service.impl.MySQLGenClassService;
import org.tea.domain.mysql.service.impl.MySQLTableInfoService;
import org.tea.domain.mysql.service.impl.MySQLTableStructService;
import org.tea.domain.psql.service.impl.PsqlGenClassService;
import org.tea.domain.psql.service.impl.PsqlTableInfoService;
import org.tea.domain.psql.service.impl.PsqlTableStructService;
import org.tea.entity.PropertiesEntity;
import org.tea.entity.SchemaStructure;
import org.tea.entity.TabStructure;
import org.tea.service.impl.FacadeService;
import org.tea.service.GenClassService;
import org.tea.service.TableInfoService;
import org.tea.service.TableStructureService;
import org.tea.service.impl.CommentServiceImpl;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javax.swing.ListSelectionModel.SINGLE_SELECTION;
import static javax.swing.SwingConstants.LEFT;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static org.tea.tool.ConstTools.*;
import static org.tea.tool.ConstTools.packToPath;

public class BaseScreen {
    private final int TEXT_COLS = 50;

    private JTextField nameField, urlField, passField, tabField;

    private JTextField scField; // super class Field

    private JTextField entityPack, mapperPack, xmlPack;

    private JTextField baseDir; // 项目的基本地址

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
        entityPack.setText(prop.getEntityDir());
        xmlPack.setText(prop.getXmlDir());
        mapperPack.setText(prop.getMapperDir());
        baseDir.setText(prop.getBaseDir());
        scField.setText(prop.getSc());
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

                boolean check = DataBaseTools.checkConnect(url, user, pass, da);
                if (!check) {
                    JOptionPane.showMessageDialog(mainFrame, "连接失败");
                    return;
                }

                JOptionPane.showMessageDialog(mainFrame, "连接成功");
                saveProps(); // 保存配置文件内容
            }
        });

        genButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = urlField.getText();
                String da = daList.getSelectedValue();
                String tabName = tabField.getText();
                String entityDirText = entityPack.getText();
                String mapperDirText = mapperPack.getText();
                String baseDirText = baseDir.getText();
                String scFieldText = scField.getText();

                TableInfoService infoService;
                TableStructureService structureService;
                GenClassService genClassService;

                FacadeService facadeService = new FacadeService(new CommentServiceImpl());
                if (da.equalsIgnoreCase("MySQL")) {
                    infoService = new MySQLTableInfoService();
                    structureService = new MySQLTableStructService(facadeService);
                    genClassService = new MySQLGenClassService(facadeService);
                } else if (da.equalsIgnoreCase("PostgresSQL")) {
                    infoService = new PsqlTableInfoService();
                    structureService = new PsqlTableStructService();
                    genClassService = new PsqlGenClassService(facadeService);
                } else {
                    throw new RuntimeException("不能处理的数据库类型");
                }

                String entityPack = pathToPack(entityDirText);
                String mapperPack = pathToPack(mapperDirText);

                String dbName = url.substring(url.lastIndexOf("/") + 1);
                List<SchemaStructure> dbTables = infoService.selectAllTables(dbName);
                Pattern tabPat = Pattern.compile(tabName);
                Class<?> sc;
                try {
                    sc = Class.forName(scFieldText);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                for (SchemaStructure struct : dbTables) {
                    String tab = struct.getTableName();
                    Matcher matcher = tabPat.matcher(tab);
                    if (!matcher.find()) continue;

                    List<TabStructure> structures = structureService.selectByTableName(dbName, tab);
                    structures = structureService.filterColumns(structures, sc); // 过滤父类属性

                    String entity = genClassService.genEntityByStruct(structures,
                            entityPack.substring(0, entityPack.length() - 1) + ";", sc);
                    String xmlMapper = genClassService.genXmlMapperByStruct(
                            structures,
                            pathToPack(entityDirText) + toClassName(tab),
                            pathToPack(mapperDirText) + toMapperName(tab)
                    );
                    String mapper = genClassService.genMapperByStruct(structures,
                            mapperPack.substring(0, mapperPack.length() - 1) + ";");

                    FileTools.writeJavaToFile(entity, packToPath(baseDirText));
                    FileTools.writeXmlMapper(xmlMapper, packToPath(baseDirText));
                    FileTools.writeJavaToFile(mapper, packToPath(baseDirText));

                    JOptionPane.showMessageDialog(mainFrame, "写入成功");

                    saveProps(); // 保存配置文件内容
                }
            }
        });

        return bottom;
    }

    private void saveProps() {
        prop = PropertiesEntity.Builder.builder()
                .withUrl(urlField.getText()).withUserName(nameField.getText())
                .withPassword(passField.getText()).withTableName(tabField.getText())
                .withDaType(daList.getSelectedValue()).withEntityDir(entityPack.getText())
                .withXmlDir(xmlPack.getText()).withMapperDir(mapperPack.getText())
                .withBaseDir(baseDir.getText()).withSc(scField.getText())
                .build();
        FileTools.writeProperties(prop);
    }

    private JPanel getCenterPanel() {
        JPanel urlPanel = getUrlPanel();
        JPanel namePanel = getNamePanel();
        JPanel passPanel = getPassPanel();
        JPanel typePanel = getTypePanel();
        JPanel tabPanel = getTabNamePanel();
        JPanel scPanel = getScFieldPanel();

        JPanel center = new JPanel();
        center.add(urlPanel);
        center.add(namePanel);
        center.add(passPanel);
        center.add(typePanel);
        center.add(tabPanel);
        center.add(scPanel);
        center.add(getEntityPackPanel());
        center.add(getMapperPackPanel());
        center.add(getXmlPackPanel());
        center.add(getBaseDirPanel());

        return center;
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

    private JPanel getScFieldPanel() {
        JPanel jPanel = new JPanel();
        JLabel tabLabel = new JLabel("superClass");
        scField = new JTextField(TEXT_COLS);
        scField.addActionListener(new PasteAction(tabField));

        jPanel.add(tabLabel);
        jPanel.add(scField);

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

    private JPanel getEntityPackPanel() {
        JPanel etPackPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel etPackLabel = new JLabel("EntityPack:", LEFT);
        entityPack = new JTextField(TEXT_COLS);
        entityPack.addActionListener(new PasteAction(entityPack));

        etPackPanel.add(etPackLabel);
        etPackPanel.add(entityPack);
        return etPackPanel;
    }

    private JPanel getMapperPackPanel() {
        JPanel mapPackPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel mapPackLabel = new JLabel("MapperPack:", LEFT);
        mapperPack = new JTextField(TEXT_COLS);
        mapperPack.addActionListener(new PasteAction(mapperPack));

        mapPackPanel.add(mapPackLabel);
        mapPackPanel.add(mapperPack);
        return mapPackPanel;
    }

    private JPanel getXmlPackPanel() {
        JPanel xmlPackPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel xmlPackLabel = new JLabel("xmlPack:", LEFT);
        xmlPack = new JTextField(TEXT_COLS);
        xmlPack.addActionListener(new PasteAction(xmlPack));

        xmlPackPanel.add(xmlPackLabel);
        xmlPackPanel.add(xmlPack);
        return xmlPackPanel;
    }

    private JPanel getBaseDirPanel() {
        JPanel baseDirPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel baseDirLabel = new JLabel("BaseDir:", LEFT);
        baseDir = new JTextField(TEXT_COLS);
        baseDir.addActionListener(new PasteAction(baseDir));

        baseDirPanel.add(baseDirLabel);
        baseDirPanel.add(baseDir);
        return baseDirPanel;
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
