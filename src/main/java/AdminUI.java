import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminUI {
    public JFrame view;
    public JButton btnSystemConfiguration = new JButton("Update Purchase");
    public JButton btnAddUser = new JButton("Update Product");
    public JButton btnDeleteUser = new JButton("Update Customer ");

    public AdminUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setTitle("Store Management System - Admin View");
        view.setSize(1000, 600);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("ADMINS WORKING");

        title.setFont (title.getFont ().deriveFont (24.0f));
        view.getContentPane().add(title);

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnSystemConfiguration);
        panelButtons.add(btnAddUser);
        panelButtons.add(btnDeleteUser);

        view.getContentPane().add(panelButtons);

        btnSystemConfiguration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ManagePurchaseUI ui = new ManagePurchaseUI();
                ui.run();
            }
        });


        btnAddUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ManageProductUI ui = new ManageProductUI();
                ui.run();
            }
        });

        btnDeleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ManageCustomerUI ui = new ManageCustomerUI();
                ui.run();
            }
        });

    }
}
