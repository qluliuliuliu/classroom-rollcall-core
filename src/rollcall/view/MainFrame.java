package rollcall.view;

import rollcall.service.RollCallService;

import javax.swing.*;

public class MainFrame extends JFrame {

    private final RollCallService rollCallService = new RollCallService();

    public MainFrame() {
        setTitle("基于有状态的课堂点名系统 v1.0");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("课堂点名", new RollCallPanel(rollCallService));

        add(tabbedPane);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
