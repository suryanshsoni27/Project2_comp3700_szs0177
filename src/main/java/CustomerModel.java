public class CustomerModel {
    public int mCustomerId;
    public String mNCame, mAddress, mPin,mPhone;
    public double mprice;

    public String toString() {
        StringBuilder ss = new StringBuilder( "(" );
        ss.append( mCustomerId ).append( "," );
        ss.append( "\"" ).append( mPhone ).append( "\"" ).append( "," );
        ss.append( "\"" ).append( mAddress).append( "\"" ).append( "," );
        ss.append( "\"" ).append( mprice).append( "\"" ).append( "," );
        ss.append( "\"" ).append( mNCame).append( "\"" ).append( "," );
        ss.append( "\"" ).append( mPin).append( "\"" ).append( ")" );

        return ss.toString();
    }
}
