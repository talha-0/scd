import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryManagementSystemGUI extends JFrame {
    private Library library;
    private JTable item_table;
    private DefaultTableModel item_table_model;
    public LibraryManagementSystemGUI(String file_name) {
        super("Library Management System");
        this.library = new Library();
        library.loadItemsFromFile(file_name);
        item_table_model = new DefaultTableModel();
        item_table = new JTable(item_table_model);
        JScrollPane scrollPane = new JScrollPane(item_table);
        JButton add_item_button = new JButton("Add Item");
        JButton edit_button = new JButton("Edit");
        JButton remove_item_button = new JButton("Remove Item");
        JButton hot_picks_button = new JButton("Hot Picks");
        item_table_model.addColumn("Title");
        item_table_model.addColumn("Author");
        item_table_model.addColumn("Year");
        item_table_model.addColumn("Read Item");

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
        setVisible(true);  
    }
    private void add_item_button_action_performed() {
    }
    private void edit_button_action_performed() {
    }
    private void remove_item_button_action_performed() {
    }
    private void hot_picks_button_action_performed() {
    }
    public static void main(String[] args) {
        LibraryManagementSystemGUI library_management_system_gui = new LibraryManagementSystemGUI("items.txt");
        library_management_system_gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        library_management_system_gui.setSize(500, 500);
    }
}
