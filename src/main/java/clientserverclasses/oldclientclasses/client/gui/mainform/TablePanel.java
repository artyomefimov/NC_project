package clientserverclasses.oldclientclasses.client.gui.mainform;

import clientserverclasses.MessageBox;
import clientserverclasses.oldclientclasses.client.commandprocessor.ClientCommandSender;
import server.model.Task;
import clientserverclasses.oldclientclasses.client.network.ClientNetworkFacade;
import clientserverclasses.oldserverclasses.exceptions.UnsuccessfulCommandActionException;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TablePanel extends JPanel {
    private JTable table;
    private JournalTableModel tableModel;
    private JPopupMenu popupMenu;
    private TableListener listener;
    private List<Task> taskList;
    private MainForm form;
    private TaskSender taskSender;
    private static TablePanel instance;
    private ClientCommandSender commandSender = ClientCommandSender.getInstance();

    public TablePanel(MainForm form) {
        this.form = form;
        instance = this;
        tableModel = new JournalTableModel();
        table = new JTable(tableModel);
        taskSender = TaskSender.getInstance();
        table.setRowHeight(20);
        popupMenu = new JPopupMenu();
        table.setRowSelectionAllowed(true);
        for (int i = 1; i < 8; i++) {
            table.setDefaultRenderer(table.getColumnClass(i), new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    super.setHorizontalAlignment(SwingConstants.CENTER);
                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    return this;
                }
            });
        }


        JMenuItem cancelItem = new JMenuItem("Cancel task");
        popupMenu.add(cancelItem);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                //System.out.println(row + " " + col);
                table.getSelectionModel().setSelectionInterval(row, row);
                Task task = taskList.get(row);

                if (e.getButton() == MouseEvent.BUTTON3) { // правой вызывем popup menu
                    popupMenu.show(table, e.getX(), e.getY());
                    taskSender.setTask(task);
                } else if (e.getButton() == MouseEvent.BUTTON1) { // левой выделяем для редактирования
                    if (col == 0) {
                        Boolean b = (Boolean) table.getValueAt(row, col);
                        table.setValueAt(!b, row, col);
                    }
                    taskSender.setTask(task);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        cancelItem.addActionListener((ActionEvent ev) -> {
            TaskSender sender = TablePanel.getInstance().getTaskSender();
            Task task = sender.getTask();
            try {
                commandSender.sendCancelCommand(task, ClientNetworkFacade.getInstance().getDataOutputStream());
            } catch (UnsuccessfulCommandActionException e) {
                MessageBox.getInstance().showAskForRestartMessage();
            }
            refresh();
        });

        setLayout(new BorderLayout());
        add(table, BorderLayout.CENTER);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public static TablePanel getInstance() {
        return instance;
    }

    public TaskSender getTaskSender() {
        return taskSender;
    }

    /**
     * Set tasks to be representing at table
     *
     * @param taskList list with tasks
     */
    public void setData(List<Task> taskList) {
        tableModel.setData(taskList);
        this.taskList = taskList;
    }

    /**
     * Set listener to the <code>TablePanel</code> which listens commands for tasks representing at the table
     *
     * @param listener
     */

    public void setTableListener(TableListener listener) {
        this.listener = listener;
    }

    /**
     * Get <code>JTable</code> of this <code>TablePanel</code>
     *
     * @return
     */

    public JTable getTable() {
        return table;
    }

    /**
     * Refresh values of the table
     */

    public void refresh() {
        tableModel.fireTableDataChanged();
    }
}
