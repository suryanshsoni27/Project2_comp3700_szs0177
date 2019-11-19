import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ManageCustomerUI {

    public JFrame view;

    public JButton btnLoad = new JButton("Load Customer");
    public JButton btnSave = new JButton("Save Customer");

    public JTextField txtCustomerID = new JTextField( 20 );
    public JTextField txtNCame = new JTextField( 20 );
    public JTextField txtPhone = new JTextField( 20 );
    public JTextField txtAddress = new JTextField( 20 );
    public JTextField txtPin = new JTextField( 20 );
    public JTextField txtprice = new JTextField( 20  );

    public ManageCustomerUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Manage Customer Information");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnLoad);
        panelButtons.add(btnSave);
        view.getContentPane().add(panelButtons);

        JPanel line1 = new JPanel( new FlowLayout() );
        line1.add(new JLabel( "CustomerID" ) );
        line1.add(txtCustomerID );
        view.getContentPane().add( line1 );

        JPanel line2 = new JPanel( new FlowLayout() );
        line2.add(new JLabel( "Phone" ) );
        line2.add(txtPhone);
        view.getContentPane().add( line2 );

        JPanel line3 = new JPanel( new FlowLayout() );
        line3.add( new JLabel("Address" ) );
        line3.add(txtAddress );
        view.getContentPane().add( line3 );

        JPanel line4 = new JPanel( new FlowLayout() );
        line4.add(new JLabel("Total" ) );
        line4.add(txtprice );
        view.getContentPane().add( line4 );

        JPanel line5 = new JPanel( new FlowLayout() );
        line5.add(new JLabel("Name" ) );
        line5.add(txtNCame);
        view.getContentPane().add(line5);

        JPanel line6 = new JPanel( new FlowLayout() );
        line6.add(new JLabel( "Payment Info" ));
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
            Gson gson = new Gson();
            String id = txtCustomerID.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "CustomerID cannot be null!");
                return;
            }

            try {
                int i = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "CustomerID is invalid!");
                return;
            }


            try {

                MessageModel msg = new MessageModel();
                msg.code = MessageModel.GET_CUSTOMER;
                msg.data = id;

//                msg = StoreManager.getInstance().getNetworkAdapter().send(msg, "localhost", 1000);

                if (msg.code == MessageModel.OPERATION_FAILED) {
                    JOptionPane.showMessageDialog(null, "Customer NOT exists!");
                }
                else {
                    CustomerModel customer = gson.fromJson(msg.data, CustomerModel.class);
                    txtNCame.setText(customer.mNCame);
                    txtPhone.setText(customer.mPhone);
                    txtAddress.setText(customer.mAddress);
                    txtPin.setText(customer.mPin);
                    txtprice.setText(Double.toString(customer.mprice));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    class SaveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            CustomerModel customer = new CustomerModel();
            Gson gson = new Gson();
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


            String price = txtprice.getText();

            if (price.length() == 0) {
                JOptionPane.showMessageDialog(null, "price cannot be null!");
                return;
            }

            try {
                customer.mprice = Double.parseDouble(price);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Price is invalid!");
                return;
            }

            String name = txtNCame.getText();
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Customer name cannot be empty!");
                return;
            }

            customer.mNCame = name;

            String address =txtAddress.getText();
            if (address.length() == 0) {
                JOptionPane.showMessageDialog(null, "Address cannot be empty!");
                return;
            }

            customer.mAddress = address;


            String phone = txtPhone.getText();
            if (phone.length() == 0) {
                JOptionPane.showMessageDialog(null, "phone number cannot be empty !");
                return;
            }

            customer.mPhone = phone;


            String pin = txtPin.getText();
            if (pin.length() == 0) {
                JOptionPane.showMessageDialog(null, "Product name cannot be empty!");
                return;
            }

            customer.mPin = pin;
            try {
                MessageModel msg = new MessageModel();
                msg.code = MessageModel.PUT_CUSTOMER;
                msg.data = gson.toJson(customer);

             // msg = StoreManager.getInstance().getDataAdapter().send(msg, "localhost", 1000);

                if (msg.code == MessageModel.OPERATION_FAILED)
                    JOptionPane.showMessageDialog(null, "Customer is NOT saved successfully!");
                else
                    JOptionPane.showMessageDialog(null, "Customer is SAVED successfully!");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

          /*  try {
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
}*/
