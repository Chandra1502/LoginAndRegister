package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());*/
		PrintWriter pw = response.getWriter();
		String str1 = request.getParameter("userid");
		String str2 = request.getParameter("password");
		String str3 = request.getParameter("name");
		String str4 = request.getParameter("address");
		String str5 = request.getParameter("confirmpassword");
		
		ConnectionCls c2 = new ConnectionCls(str1,str2,str3,str4,str5); //Instead of passing the values in the constructor, you can pass the values through storeData() as well 
		
		if(str2.equals(str5))
		{
			try {
				int b = c2.storeData();
				if(b>0){
					System.out.println("Successfully Registered"); //The output will be shown only on the console and that's why you need to go with PrintWriter which will display the output on browser.
					pw.println("Successfully Registered");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.html");
					dispatcher.include(request, response);
				}
				else
				{
					System.out.println("Not registered successfully");
					pw.println("Not registered successfully");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Register.html");
					dispatcher.forward(request, response);
				}
			} 
			catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			pw.println("Passwords do not match");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
