package p1;
import java.sql.*;

public class ConnectionCls {
	
	private String user,pass,userid,password,username,address,Confirmpassword; // These two are used to get the data from the Servlets
	private String dbuser="",dbpass=""; // These two will store the data which is being retrieved from the database
	Connection con;
	ResultSet rs;
	PreparedStatement ps;
	int i;
	
	public ConnectionCls(String user, String pass) {
		super();
		this.user = user;
		this.pass = pass;
	}
	
	public ConnectionCls(String userid, String password, String username, String address, String Confirmpassword)
	{
		this.userid = userid;
		this.password = password;
		this.username = username;
		this.address = address;
		this.Confirmpassword = Confirmpassword;
	}
	
	private Connection createConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("org.h2.Driver");
		con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/javadb","sa",null);
		return con;
	}
	
	public int checkData() throws ClassNotFoundException, SQLException
	{
		createConnection();
		ps=con.prepareStatement("select userid,password from user where userid=? and password=?");
		ps.setString(1, user);
		ps.setString(2, pass);
		rs = ps.executeQuery();
		while(rs.next()){
			dbuser = rs.getString(1);
			dbpass = rs.getString(2);
		}
		if(user.equals(dbuser)&&pass.equals(dbpass))
			return 1;
		else
			return 0;
	}
	
	public int storeData() throws ClassNotFoundException, SQLException 
	{
		createConnection();
		ps=con.prepareStatement("insert into user values(?,?,?,?)");
		ps.setString(1, userid);
		ps.setString(2, password);
		ps.setString(3, username);
		ps.setString(4, address);
		i = ps.executeUpdate();
		return i;
		
	}
	
}
