package homeinventorysystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class landingpage extends JFrame {

    private DefaultTableModel inventoryTableModel;

    landingpage() {
        this.setTitle("Home Inventory System");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 500);
        this.getContentPane().setBackground(new Color(0x404040));
        ImageIcon I1 = new ImageIcon("D:\\Other Stuff\\OneDrive\\Documents\\University\\Semester 4\\AOOP\\Code\\Home Inventory System- GUI\\homeinventorysystem\\def.jpg");
        this.setIconImage(I1.getImage());
        createmenubar();
        addWelcomeMessage();
        addCoreButtons();
        initInventoryTableModel();

        this.setVisible(true);
    }

    // Create and assign particular icons for each menu item
    ImageIcon newIcon = new ImageIcon("\new.png");
    ImageIcon allItemsIcon = new ImageIcon("path_to_icon\\all_items.png");
    ImageIcon printIcon = new ImageIcon("path_to_icon\\print.png");
    ImageIcon exitIcon = new ImageIcon("path_to_icon\\exit.png");

    ImageIcon addIcon = new ImageIcon("path_to_icon\\add.png");
    ImageIcon editIcon = new ImageIcon("path_to_icon\\edit.png");
    ImageIcon saveIcon = new ImageIcon("path_to_icon\\save.png");
    ImageIcon deleteIcon = new ImageIcon("path_to_icon\\delete.png");

    ImageIcon aboutIcon = new ImageIcon("path_to_icon\\about.png");
    ImageIcon guideIcon = new ImageIcon("path_to_icon\\guide.png");
    ImageIcon contactIcon = new ImageIcon("path_to_icon\\contact.png");
    ImageIcon feedbackIcon = new ImageIcon("path_to_icon\\feedback.png");

    void createmenubar() {
        JMenuBar menubar = new JMenuBar();

        menubar.setBackground(Color.lightGray);
        menubar.setBorder(new LineBorder(Color.BLACK, 2));

        // File menu
        JMenu filemenu = new JMenu("File");

        JMenuItem newitem = new JMenuItem("New", newIcon);
        newitem.setIconTextGap(1);
        newitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openNewItemFrame();
            }
        });

        JMenuItem allitem = new JMenuItem("All Items", allItemsIcon);
        allitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewInventory();
            }
        });

        JMenuItem printinventory = new JMenuItem("Print Inventory", printIcon);
        JMenuItem exititem = new JMenuItem("Exit", exitIcon);

        filemenu.add(newitem);
        filemenu.add(allitem);
        filemenu.add(printinventory);
        filemenu.add(exititem);

        // Edit menu
        JMenu editmenu = new JMenu("Edit");

        JMenuItem additem = new JMenuItem("Add", addIcon);
        additem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openNewItemFrame();
            }
        });

        JMenuItem edititem = new JMenuItem("Edit", editIcon);
        JMenuItem saveitem = new JMenuItem("Save", saveIcon);
        JMenuItem deleteitem = new JMenuItem("Delete", deleteIcon);
        deleteitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedItem();
            }
        });

        editmenu.add(additem);
        editmenu.add(edititem);
        editmenu.add(saveitem);
        editmenu.add(deleteitem);

        // Help menu
        JMenu helpmenu = new JMenu("Help");

        JMenuItem aboutitem = new JMenuItem("About", aboutIcon);
        JMenuItem userguideitem = new JMenuItem("User Guide", guideIcon);
        JMenuItem contactitem = new JMenuItem("Contact", contactIcon);
        JMenuItem feedbackitem = new JMenuItem("Feedback", feedbackIcon);

        helpmenu.add(aboutitem);
        helpmenu.add(userguideitem);
        helpmenu.add(contactitem);
        helpmenu.add(feedbackitem);

        // Add menus to menubar
        menubar.add(filemenu);
        menubar.add(editmenu);
        menubar.add(helpmenu);

        this.setJMenuBar(menubar);
    }

    void addWelcomeMessage() {
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(new Color(0x020d1f));
        welcomePanel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("<html><h1 style='color:white;'>Welcome to the Home Management System</h1>"
                + "<p style='color:white;'>This application helps you keep track of your valuable belongings. "
                + "Easily manage your inventory and print it for insurance purposes.</p></html>", SwingConstants.CENTER);

        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);

        this.add(welcomePanel, BorderLayout.NORTH);
    }

    void addCoreButtons() {
        JPanel coreButtonPanel = new JPanel();
        coreButtonPanel.setBackground(new Color(0x404040));
        coreButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));

        JButton addItemButton = createStyledButton("Add New Item");
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openNewItemFrame();
            }
        });

        JButton viewItemsButton = createStyledButton("View Inventory");
        viewItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewInventory();
            }
        });

        JButton deleteItemButton = createStyledButton("Delete Item");
        deleteItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedItem();
            }
        });

        coreButtonPanel.add(addItemButton);
        coreButtonPanel.add(viewItemsButton);
        coreButtonPanel.add(deleteItemButton);

        this.add(coreButtonPanel, BorderLayout.SOUTH);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(150, 50));
        return button;
    }

    private void openNewItemFrame() {
        JFrame newItemFrame = new JFrame("Add New Item");
        newItemFrame.setSize(400, 500);
        newItemFrame.setLayout(new GridLayout(8, 2));

        // Create labels and input fields
        JLabel nameLabel = new JLabel("Item Name:");
        JTextField nameField = new JTextField();

        JLabel locationLabel = new JLabel("Location:");
        JTextField locationField = new JTextField();

        JLabel purchaseDateLabel = new JLabel("Purchase Date:");
        JTextField purchaseDateField = new JTextField();

        JLabel purchaseStoreLabel = new JLabel("Purchase Store:");
        JTextField purchaseStoreField = new JTextField();

        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField();

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();

        JLabel photoLabel = new JLabel("Photo:");
        JButton attachPhotoButton = new JButton("Attach Photo");

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] rowData = {nameField.getText(), locationField.getText(), purchaseDateField.getText(),
                        purchaseStoreField.getText(), priceField.getText(), descriptionField.getText()};
                inventoryTableModel.addRow(rowData);
                newItemFrame.dispose();
            }
        });

        // Add components to frame
        newItemFrame.add(nameLabel);
        newItemFrame.add(nameField);
        newItemFrame.add(locationLabel);
        newItemFrame.add(locationField);
        newItemFrame.add(purchaseDateLabel);
        newItemFrame.add(purchaseDateField);
        newItemFrame.add(purchaseStoreLabel);
        newItemFrame.add(purchaseStoreField);
        newItemFrame.add(descriptionLabel);
        newItemFrame.add(descriptionField);
        newItemFrame.add(priceLabel);
        newItemFrame.add(priceField);
        newItemFrame.add(photoLabel);
        newItemFrame.add(attachPhotoButton);
        newItemFrame.add(new JLabel()); // empty space
        newItemFrame.add(saveButton);

        newItemFrame.setVisible(true);
    }

    private void initInventoryTableModel() {
        inventoryTableModel = new DefaultTableModel();
        inventoryTableModel.addColumn("Item Name");
        inventoryTableModel.addColumn("Location");
        inventoryTableModel.addColumn("Purchase Date");
        inventoryTableModel.addColumn("Purchase Store");
        inventoryTableModel.addColumn("Price");
        inventoryTableModel.addColumn("Description");
    }

    private void viewInventory() {
        JFrame viewInventoryFrame = new JFrame("Inventory");
        viewInventoryFrame.setSize(600, 400);

        JTable inventoryTable = new JTable(inventoryTableModel);
        JScrollPane scrollPane = new JScrollPane(inventoryTable);

        viewInventoryFrame.add(scrollPane);
        viewInventoryFrame.setVisible(true);
    }

    private void deleteSelectedItem() {
        JFrame deleteFrame = new JFrame("Delete Item");
        deleteFrame.setSize(400, 200);
        deleteFrame.setLayout(new GridLayout(2, 1));

        JTable inventoryTable = new JTable(inventoryTableModel);
        JScrollPane scrollPane = new JScrollPane(inventoryTable);

        JButton deleteButton = new JButton("Delete Selected");s
        deleteButton.setSize(100, 100);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = inventoryTable.getSelectedRow();
                if (selectedRow != -1) {
                    inventoryTableModel.removeRow(selectedRow);
                    deleteFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an item to delete.");
                }
            }
        });

        deleteFrame.add(scrollPane);
        deleteFrame.add(deleteButton);
        deleteFrame.setVisible(true);
    }
}
