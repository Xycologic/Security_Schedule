package com.mycompany.securityschedule.view;

import com.mycompany.securityschedule.controller.ScheduleController;
import com.mycompany.securityschedule.controller.SecurityController;
import com.mycompany.securityschedule.model.Schedule;
import com.mycompany.securityschedule.model.Security;
import com.mycompany.securityschedule.view.AddScheduleDialog;
import com.mycompany.securityschedule.view.AddSecurityDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private JTable securityTable;
    private JTable scheduleTable;
    private DefaultTableModel securityTableModel;
    private DefaultTableModel scheduleTableModel;
    private ScheduleController scheduleController;
    private SecurityController securityController;

    public MainFrame() {
        scheduleController = new ScheduleController();
        securityController = new SecurityController();
        setTitle("Security Schedule Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();

        setVisible(true);
    }

    private void initComponents() {
        // Initialize security table
        securityTableModel = new DefaultTableModel();
        securityTableModel.addColumn("ID");
        securityTableModel.addColumn("Security Name");

        securityTable = new JTable(securityTableModel);
        JScrollPane securityScrollPane = new JScrollPane(securityTable);

        // Initialize schedule table
        scheduleTableModel = new DefaultTableModel();
        scheduleTableModel.addColumn("ID");
        scheduleTableModel.addColumn("Security ID");
        scheduleTableModel.addColumn("Date");
        scheduleTableModel.addColumn("Shift");

        scheduleTable = new JTable(scheduleTableModel);
        JScrollPane scheduleScrollPane = new JScrollPane(scheduleTable);

        // Populate tables
        refreshSecurityTable();
        refreshScheduleTable();

        // Layout
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, securityScrollPane, scheduleScrollPane);
        splitPane.setResizeWeight(0.5);

        getContentPane().add(splitPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton addScheduleButton = new JButton("Add Schedule");
        addScheduleButton.addActionListener(e -> showAddScheduleDialog());
        buttonPanel.add(addScheduleButton);

        JButton addSecurityButton = new JButton("Add Security");
        addSecurityButton.addActionListener(e -> showAddSecurityDialog());
        buttonPanel.add(addSecurityButton);

        JButton deleteSecurityButton = new JButton("Delete Security");
        deleteSecurityButton.addActionListener(e -> deleteSecurity());
        buttonPanel.add(deleteSecurityButton);

        JButton deleteScheduleButton = new JButton("Delete Schedule");
        deleteScheduleButton.addActionListener(e -> deleteSchedule());
        buttonPanel.add(deleteScheduleButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void refreshSecurityTable() {
        List<Security> securities = securityController.getAllSecurity();
        securityTableModel.setRowCount(0);
        for (Security security : securities) {
            securityTableModel.addRow(new Object[]{
                    security.getId(),
                    security.getName()
            });
        }
    }

    private void refreshScheduleTable() {
        List<Schedule> schedules = scheduleController.getAllSchedules();
        scheduleTableModel.setRowCount(0);
        for (Schedule schedule : schedules) {
            scheduleTableModel.addRow(new Object[]{
                    schedule.getId(),
                    schedule.getSecurityId(),
                    schedule.getDate(),
                    schedule.getShift()
            });
        }
    }

    private void showAddScheduleDialog() {
        AddScheduleDialog dialog = new AddScheduleDialog(this, scheduleController);
        dialog.setVisible(true);
        refreshScheduleTable();
    }

    private void showAddSecurityDialog() {
        AddSecurityDialog dialog = new AddSecurityDialog(this, securityController);
        dialog.setVisible(true);
        refreshSecurityTable();
        refreshScheduleTable(); // If adding a security might affect schedule view
    }

    private void deleteSecurity() {
        int selectedRow = securityTable.getSelectedRow();
        if (selectedRow != -1) {
            int securityId = (int) securityTable.getValueAt(selectedRow, 0);
            securityController.deleteSecurity(securityId);
            refreshSecurityTable();
            refreshScheduleTable(); // Refresh schedule if deleting security affects it
        } else {
            JOptionPane.showMessageDialog(this, "Please select a security to delete.", "Delete Security", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteSchedule() {
        int selectedRow = scheduleTable.getSelectedRow();
        if (selectedRow != -1) {
            int scheduleId = (int) scheduleTable.getValueAt(selectedRow, 0);
            scheduleController.deleteSchedule(scheduleId);
            refreshScheduleTable();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a schedule to delete.", "Delete Schedule", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}
