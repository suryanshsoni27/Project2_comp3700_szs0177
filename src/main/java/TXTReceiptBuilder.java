public class TXTReceiptBuilder implements IReceiptBuilder {

    StringBuilder sb = new StringBuilder();

    @Override
    public void appendHeader(String header) {
        sb.append(header).append("\n");
    }

    @Override
    public void appendCustomer(CustomerModel customer) {
        sb.append("Customer ID: ").append(customer.mCustomerId).append("\n");
        sb.append("Customer Name: ").append(customer.mNCame).append("\n");
    }

    @Override
    public void appendProduct(ProductModel product) {
        sb.append("Product ID: ").append(product.mProductID).append("\n");
        sb.append("Product name: ").append(product.mName).append("\n");

    }

    @Override
    public void appendPurchase(PurchaseModel purchase) {
        sb.append("Purchase ID: ").append(purchase.mPurchaseID).append("\n");
        sb.append("Customer ID").append(purchase.mCustomerID).append("\n");
        sb.append("Product ID").append(purchase.mProductID).append("\n");
        sb.append("Price").append(purchase.mPrice).append("\n");
        sb.append("Cost").append(purchase.mCost).append("\n");
        sb.append("Tax").append(purchase.mTax).append("\n");
        sb.append("Total").append(purchase.mTotal).append("\n");
    }

    @Override
    public void appendFooter(String footer) {
        sb.append(footer).append("\n");
    }
}

