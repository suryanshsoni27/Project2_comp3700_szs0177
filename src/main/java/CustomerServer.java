import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class CustomerServer {
    static String dbfile = "C:\\Users\\hsoni\\Desktop\\comp3700\\Project1_data.db";

    public static void main(String[] args) {

        int port = 3000;

        if (args.length > 0) {
            System.out.println( "Running arguments: " );
            for (String arg : args)
                System.out.println( arg );
            port = Integer.parseInt( args[0] );
            dbfile = args[1];
        }

        try {
            ServerSocket server = new ServerSocket( port );

            System.out.println( "Server is listening at port = " + port );

            while (true) {
                Socket pipe = server.accept();
                PrintWriter out = new PrintWriter( pipe.getOutputStream(), true );
                Scanner in = new Scanner( pipe.getInputStream() );

                String command = in.nextLine();
                if (command.equals( "GET" )) {
                    String str = in.nextLine();
                    System.out.println( "GET Customer with id = " + str );
                    int CustomerID = Integer.parseInt( str );

                    Connection conn = null;
                    try {
                        String url = "jdbc:sqlite:" + dbfile;
                        conn = DriverManager.getConnection( url );

                        String sql = "SELECT * FROM Customer WHERE CustomerID = " + CustomerID;
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery( sql );

                        if (rs.next()) {
                            out.println(rs.getString( "Phone" ));
                            out.println( rs.getString( "Address" ) );
                            out.println( rs.getDouble( "Total" ) );

                            String name = rs.getString( "Name" );

                            System.out.println( "Sent Name = "  + name);

                            out.println( name );
                            out.println( rs.getString( "Pin" ) );
                        } else
                            out.println( "null" );

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    conn.close();
                }

                if (command.equals( "PUT" )) {
                    String id = in.nextLine();
                    String Phone = in.nextLine();// read all information from client
                    String Address = in.nextLine();
                    String Total = in.nextLine();
                    String Name = in.nextLine();
                    String Pin = in.nextLine();

                    System.out.println( "PUT command with CustomerID = " + id );

                    Connection conn = null;
                    try {
                        String url = "jdbc:sqlite:" + dbfile;
                        conn = DriverManager.getConnection( url );

                        String sql = "SELECT * FROM Customer WHERE CustomerID = " + id;
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery( sql );

                        if (rs.next()) {
                            rs.close();
                            stmt.execute( "DELETE FROM Customer WHERE CustomerID = " + id );
                        }

                        sql = "INSERT INTO Customer VALUES (" + id + ",\""+ Phone +"\"," + "\"" + Address + "\"," + Total + ",\"" + Name + "\"," + "\"" + Pin + "\"" +")";
                        System.out.println( "SQL for PUT: " + sql );
                        stmt.execute( sql );

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    conn.close();


                } else {
                    out.println( 0 ); // logout unsuccessful!
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


