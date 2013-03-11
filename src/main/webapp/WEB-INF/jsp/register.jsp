<%-- 
    Document   : register
    Created on : 2013-3-10, 19:25:48
    Author     : jerryran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form name="registerForm" action="register" method="post">
            <label for="username">Username</label>:
            <input name="username" type="text" /> <br/>
            <label for="j_password">Password</label>:
            <input name="password" type="password" /><br/>
            <input type="submit" value="提交" />
        </form>
    </body>
</html>
