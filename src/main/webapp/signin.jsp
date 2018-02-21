<%@ page import="auxiliaryclasses.ConstantsClass" %><%--
  Created by IntelliJ IDEA.
  User: ывв
  Date: 07.02.2018
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<style  type="text/css">

    #content { position: relative;}

    #login {
        position: relative;
        top: 80px;
    }

    .align-right {
        text-align: right;
    }

    table {
        border: 1px solid gray;
        padding: 20px;
        background-color: khaki;
    }

    .login-error {
        font-size: 16px;
        font-weight: bold;
        color: red;
    }
</style>

</head>
<body>

<center>

    <div id="login">

        <h3>Sign In</h3>

        <form method="post" action=<%=ConstantsClass.SERVLET_ADDRESS%>>

            <input type="hidden" name="action" value=<%=ConstantsClass.DO_SIGN_IN%> />

            <table>

                <tr><td class="align-right">Username: </td><td><input type="text" name="email" value="<%= request.getAttribute("email")!=null?request.getAttribute("email"):"" %>"/></td></tr>
                <tr><td class="align-right">Password: </td><td><input type="password" name="password" value="<%= request.getAttribute("password")!=null?request.getAttribute("password"):"" %>"/></td></tr>
                <tr><td class="align-right" colspan="2"><input type="submit" value="Sign in"/></td></tr>

            </table>

            <p class="login-error"><%= request.getAttribute("message")!=null?request.getAttribute("message"):"" %></p>

        </form>

    </div>


</center>


</body>
</html>