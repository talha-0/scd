import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import abstractclasses.Item;
import items.Book;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LibraryManagementSystemGUI extends JFrame {
    private Library library;
    private JTable item_table;
    private DefaultTableModel item_table_model;
    private JButton edit_button;
    private JButton remove_item_button;

    private class selected_item {
        int item_id;
        public selected_item(int item) {
            item_id = item;
        }
    }

    public LibraryManagementSystemGUI(String file_name) {
        super("Library Management System");
        this.library = new Library();
        library.loadItemsFromFile(file_name);
        item_table_model = new DefaultTableModel();
        item_table = new JTable(item_table_model);
        edit_button = new JButton("Edit");
        remove_item_button = new JButton("Remove Item");

        JScrollPane scrollPane = new JScrollPane(item_table);
        JButton add_item_button = new JButton("Add Item");
        JButton hot_picks_button = new JButton("Hot Picks");
        item_table_model.addColumn("");
        item_table_model.addColumn("Title");
        item_table_model.addColumn("Author");
        item_table_model.addColumn("Year");
        item_table_model.addColumn("Read Item");

        update_table();

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        JPanel button_panel = new JPanel();
        button_panel.add(add_item_button);
        button_panel.add(edit_button);
        button_panel.add(remove_item_button);
        button_panel.add(hot_picks_button);
        add(button_panel, BorderLayout.SOUTH);

        // Add action listeners
        add_item_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add_item_button_action_performed();
            }
        });
        edit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit_button_action_performed();
            }
        });
        remove_item_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove_item_button_action_performed();
            }
        });
        hot_picks_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hot_picks_button_action_performed();
            }
        });

        ListSelectionModel selectionModel = item_table.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                edit_button.setEnabled(!selectionModel.isSelectionEmpty());
                remove_item_button.setEnabled(!selectionModel.isSelectionEmpty());
            }
        });

        TableColumnModel columnModel = item_table.getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        remove_item_button.setEnabled(false);
        edit_button.setEnabled(false);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
    }

    private void update_table() {
        item_table_model.setRowCount(0);
        for (Item item : library.get_items()) {
            item_table_model.addRow(new Object[]{"",item.get_title(), item.get_author(), item.get_year(), "Read Item"});
            int row = item_table_model.getRowCount() - 1;
            int item_id = item.get_id();
            selected_item display_item = new selected_item(item_id);
            item_table_model.setValueAt(display_item, row, 0);
        }
    }

    private void add_item_button_action_performed() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField yearField = new JTextField();
        
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Author:"));
        panel.add(authorField);
        panel.add(new JLabel("Year:"));
        panel.add(yearField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add Item",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            String author = authorField.getText();
            String yearStr = yearField.getText();

            if (title.isEmpty() || author.isEmpty() || yearStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invalid Input");
            } else {
                try {
                    int year = Integer.parseInt(yearStr);
                    Book book = new Book(title, author, year, 0, 0);
                    library.addItem(book);
                    update_table();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid Input");
                }
            }
        }
    }

    private void edit_button_action_performed() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField yearField = new JTextField();
        
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Author:"));
        panel.add(authorField);
        panel.add(new JLabel("Year:"));
        panel.add(yearField);

        int result = JOptionPane.showConfirmDialog(
            this,
            panel, 
            "Edit Item",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            String author = authorField.getText();
            String yearStr = yearField.getText();

            if (title.isEmpty() || author.isEmpty() || yearStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invalid Input");
            } else {
                try {
                    int year = Integer.parseInt(yearStr);
                    Book book = new Book(title, author, year, 0, 0);
                    library.addItem(book);
                    library.deleteItem(((selected_item)item_table_model.getValueAt(item_table.getSelectedRow(), 0)).item_id);
                    update_table();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid Input");
                }
            }
        }
    }

    private void remove_item_button_action_performed() {
        library.deleteItem(((selected_item)item_table_model.getValueAt(item_table.getSelectedRow(), 0)).item_id);
        update_table();
    }

    private void hot_picks_button_action_performed() {
        library.displayHotPicks();
        ArrayList<Item> items = library.get_items();
        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int bar_width = getWidth() / items.size();
                int max_height = getHeight()-30;
                int i=0;
                for(Item item:items){
                    int popularity = item.getPopularityCount();
                    int bar_height = (int) (((double)popularity / items.get(0).getPopularityCount()) * max_height);
                    g.setColor(Color.RED);
                    g.fillRect(i*bar_width, max_height-bar_height, bar_width, bar_height);
                    g.setColor(Color.BLACK);
                    g.drawString(item.get_title()+"("+popularity+")", i*bar_width, max_height+20);
                    i++;
                }
            }
        };
        JFrame frame = new JFrame("Hot Picks");
        frame.setContentPane(panel);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        LibraryManagementSystemGUI library_management_system_gui = new LibraryManagementSystemGUI("items.txt");
        library_management_system_gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        library_management_system_gui.setSize(500, 500);
    }
}
