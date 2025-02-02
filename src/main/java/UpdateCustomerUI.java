import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class UpdateCustomerUI {

    public JFrame view;

    public JButton btnLoad = new JButton("Load Customer");
    public JButton btnSave = new JButton("Save Customer");

    public JTextField txtCustomerID = new JTextField(20);
    public JTextField txtPhone = new JTextField(20);
    public JTextField txtAddress = new JTextField( 20 );
    public JTextField txtTotal = new JTextField(20);
    public JTextField txtName = new JTextField(20);
    public JTextField txtPin = new JTextField(20);




    public UpdateCustomerUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Update Customer Information");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnLoad);
        panelButtons.add(btnSave);
        view.getContentPane().add(panelButtons);

        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("CustomerID "));
        line1.add(txtCustomerID);
        view.getContentPane().add(line1);

        JPanel line2 = new JPanel(new FlowLayout());
        line2.add(new JLabel("Phone"));
        line2.add(txtPhone);
        view.getContentPane().add(line2);

        JPanel line3 = new JPanel(new FlowLayout());
        line3.add(new JLabel("Address"));
        line3.add(txtAddress);
        view.getContentPane().add(line3);

        JPanel line4 = new JPanel(new FlowLayout());
        line4.add(new JLabel("Total"));
        line4.add(txtTotal);
        view.getContentPane().add(line4);

        JPanel line5 = new JPanel(new FlowLayout());
        line5.add(new JLabel("Name"));
        line5.add(txtName);
        view.getContentPane().add(line5);

        JPanel line6 = new JPanel(new FlowLayout());
        line6.add(new JLabel("Payment information "));
        line6.add(txtPin);
        view.getContentPane().add(line6);



        btnLoad.addActionListener(new LoadButtonListerner());

        btnSave.addActionListener(new SaveButtonListener());

    }

    public void run() {
        view.setVisible(true);
    }

    class LoadButtonListerner implements ActionListener {

        public void actionPerformed(ActionEvent actionEvent) {
            CustomerModel customer = new CustomerModel();
            String id = txtCustomerID.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "CustomerID cannot be null!");
                return;
            }

            try {
                customer.mCustomerId = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "CustomerID is invalid!");
                return;
            }

            // do client/server

            try {
                Socket link = new Socket("localhost", 1000);
                Scanner input = new Scanner(link.getInputStream());
                PrintWriter output = new PrintWriter(link.getOutputStream(), true);

                output.println("GET");
                output.println(customer.mCustomerId);




                customer.mPhone = input.nextLine();
                if (customer.mPhone.equals("null")) {
                    JOptionPane.showMessageDialog(null, "Customer NOT exists!");
                    return;
                }
                txtPhone.setText(customer.mPhone);




                customer.mAddress = input.nextLine();
                if (customer.mAddress.equals("null")) {
                    JOptionPane.showMessageDialog(null, "Customer NOT exists!");
                    return;
                }
                txtAddress.setText(customer.mAddress);

                String st = input.nextLine();
                customer.mprice = Double.parseDouble( st );
                txtTotal.setText(Double.toString(customer.mprice));


                customer.mNCame = input.nextLine();
                if (customer.mNCame.equals("null")) {
                    JOptionPane.showMessageDialog(null, "Customer NOT exists!");
                    return;
                }
                txtName.setText(customer.mNCame);

                customer.mPin = input.nextLine();
                if (customer.mPin.equals("null")) {
                    JOptionPane.showMessageDialog(null, "Customer NOT exists!");
                    return;
                }
                txtPin.setText(customer.mPin);





            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    class SaveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            CustomerModel customer = new CustomerModel();
            String id = txtCustomerID.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "CustomerID cannot be null!");
                return;
            }

            try {
                customer.mCustomerId = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Customer is invalid!");
                return;
            }

            String phone = txtPhone.getText();
            if (phone.length() == 0) {
                JOptionPane.showMessageDialog(null, "Product name cannot be empty!");
                return;
            }

            customer.mPhone = phone;


            String Address = txtAddress.getText();
            if (Address.length() == 0) {
                JOptionPane.showMessageDialog(null, "Address cannot be empty");
                return;
            }

            customer.mAddress = Address;

            String total = txtTotal.getText();
            try {
                customer.mprice = Double.parseDouble(total);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Price is invalid!");
                return;
            }

            String Name = txtName.getText();
            if (Name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Name cannot be empty");
                return;
            }

            customer.mNCame = Name;


            String Pin = txtPin.getText();
            if (Pin.length() == 0) {
                JOptionPane.showMessageDialog(null, "Payemnt info cannot be null");
                return;
            }

            customer.mPin = Pin;


            // all product infor is ready! Send to Server!

            try {
                Socket link = new Socket("localhost", 1000);
                Scanner input = new Scanner(link.getInputStream());
                PrintWriter output = new PrintWriter(link.getOutputStream(), true);

                output.println("PUT");
                output.println(customer.mCustomerId);
                output.println(customer.mPhone);
                output.println(customer.mAddress);
                output.println(customer.mprice);
                output.println(customer.mNCame);
                output.println(customer.mPin);
                JOptionPane.showMessageDialog( null, "saved with new information - confirmed" );

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
