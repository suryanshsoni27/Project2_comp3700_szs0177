import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI {
    public JFrame view;

    public JButton btnAddProduct = new JButton("Add New Product");
    public JButton btnAddCustomer = new JButton("Add New Customer");
    public JButton btnAddPurchase = new JButton("Add New Purchase");
    public JButton btnUpdateProduct = new JButton("Manage Product");
    public JButton btnUpdateCustomer = new JButton("Manage Customer");
    public JButton btnUpdatePurchase = new JButton("Manage Purchase");


    public MainUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setTitle("Apple Store Management System");
        view.setSize(1000, 600);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Apple Store Management System");

        title.setFont (title.getFont ().deriveFont (24.0f));
        view.getContentPane().add(title);

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnAddProduct);
        panelButtons.add(btnAddCustomer);
        panelButtons.add(btnAddPurchase);
        panelButtons.add(btnUpdateProduct);
        panelButtons.add(btnUpdateCustomer);
        panelButtons.add(btnUpdatePurchase);
        view.getContentPane().add(panelButtons);

        btnAddProduct.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddProductUI ap = new AddProductUI();
                ap.run();
            }
        });

        btnAddCustomer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddCustomerUI ac = new AddCustomerUI();
                ac.run();
            }
        });

        btnAddPurchase.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddPurchaseUI ap = new AddPurchaseUI();
                ap.run();
            }
        });

        btnUpdateProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ManageProductUI ui  = new ManageProductUI();
                ui.run();
            }
        });

        btnUpdateCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ManageCustomerUI ci = new ManageCustomerUI();
                ci.run();
            }
        });

        btnUpdatePurchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ManagePurchaseUI pi = new ManagePurchaseUI();
                pi.run();
            }
        });

    }
}
