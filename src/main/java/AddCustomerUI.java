import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCustomerUI {
    public JFrame view;

    public JButton btnAdd = new JButton( "Add" );
    public JButton btnCancel = new JButton( "Cancel" );

    public JTextField txtCustomerID = new JTextField( 20 );
    public JTextField txtNCame = new JTextField( 20 );
    public JTextField txtPhone = new JTextField( 20 );
    public JTextField txtAddress = new JTextField( 20 );
    public JTextField txtPin = new JTextField( 20 );
    public JTextField txtprice = new JTextField( 20  );

    public AddCustomerUI() {
        this.view = new JFrame( );
        view.setTitle( "Add Customer" );
        view.setSize( 600, 400 );
        view.getContentPane().setLayout( new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS ) );

        String[] labels = {"Customerid ", "Phone", "Address ", "Total","Name", "Payment Info"};

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



        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnAdd);
        panelButtons.add(btnCancel);
        view.getContentPane().add(panelButtons);


        btnAdd.addActionListener(new AddButtonListener());

        btnCancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent){
                JOptionPane.showMessageDialog(null, "You click on Cancel button!!!");
            }
        });

    }

    public void run() {view.setVisible(true);
    }

    class AddButtonListener implements ActionListener {


        public void actionPerformed(ActionEvent actionEvent) {
            CustomerModel customer = new CustomerModel();

            String id = txtCustomerID.getText();

            if(id.length()== 0) {
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

            switch (StoreManager.getInstance().getDataAdapter().saveCustomer(customer)) {
                case SQLiteDataAdapter.CUSTOMER_DUPLICATE_ERROR:
                    JOptionPane.showMessageDialog(null, "Customer NOT added successfully! Duplicate customer ID!");
                default:
                    JOptionPane.showMessageDialog(null, "Customer added successfully!" + customer);
            }
        }
    }

}
