package resources;



import com.account.*;
import com.database.Database;
import com.domain.account.Account;
import com.domain.site.LanguageServlet;
import com.randomcodegenerator.CodeGenerator;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddGroupServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");

        String groupname = request.getParameter("groupName");
        String id = request.getParameter("selectProjectID");

        Account account = (Account) request.getSession().getAttribute("account");
        if (account != null)
        {
            if (!groupname.equals("") && !id.equals(""))
            {
                ResultSet rs = Database.getDatabase().query("INSERT INTO pgroup (groupname,logincode) VALUES ( "  + groupname + " , " + CodeGenerator.generateAlphanumericCode(8) + " ) ", Database.QUERY.UPDATE);
                int key = -1;
                if (rs != null)
                {
                    try
                    {
                        while (rs.next())
                        {
                            key = rs.getInt(1);

                        }
                        if (key != -1)
                        {
                            Database.getDatabase().query("INSERT INTO project_pgroup (projectID,pgroupID) VALUES ( " + id + " , " + key + " )", Database.QUERY.UPDATE);
                        }
                    } catch (SQLException ex)
                    {
                        Logger.getLogger(AddGroupServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } finally
                    {
                        Database.getDatabase().closeConnection();
                    }
                }
            }
        }
        response.sendRedirect(request.getContextPath() + "/fotograafpanel.jsp");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
    }

}
