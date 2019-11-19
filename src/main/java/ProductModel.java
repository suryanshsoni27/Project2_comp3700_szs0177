public class ProductModel {
    public int mProductID;
    public String mName, mVendor;
    public double mPrice;
    public double mQuantity;

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(mProductID).append(",");
        sb.append("\"").append(mVendor).append("\"").append(",");
        sb.append("\"").append(mName).append("\"").append(",");
        sb.append(mQuantity).append(",");
        sb.append(mPrice).append(")");

        return sb.toString();
    }
}
