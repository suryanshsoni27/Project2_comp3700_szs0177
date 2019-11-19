public class OracleDataAdapter implements IDataAdapter {

    public int connect(String dbfile) {
        //...
        return CONNECTION_OPEN_OK;
    }

    public int disconnect() {
        // ...
        return CONNECTION_CLOSE_OK;

    }

    public ProductModel loadProduct(int id) {
        return null;
    }
    public int saveProduct(ProductModel model) {
        return PRODUCT_SAVED_OK;
    }

    public CustomerModel loadCustomer(int id) { return null;}
    public int saveCustomer(CustomerModel model) { return CUSTOMER_SAVED_OK;}

    public int savePurchase(PurchaseModel model) {return PURCHASE_SAVED_OK;}
    public PurchaseModel loadPurchase(int id) {return null;}

    public int saveUser(UserModel model) {return USER_SAVED_OK;}
    public  UserModel loadUser(String id) {return null;}
    @Override
    public PurchaseHistoryModel loadPurchaseHistory(int customerID) {
        return null;
    }
}
