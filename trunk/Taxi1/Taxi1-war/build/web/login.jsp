<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<title>Вход в систему</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
		<link rel="stylesheet" href="<%=request.getContextPath() + "/css/style.css"%>" type="text/css" />
		<!--[if IE]><link rel="stylesheet" type="text/css" href="<%=request.getContextPath() + "/css/ie.css"%>"/><![endif]-->
		<script type="text/javascript" src="<%=request.getContextPath() + "/js/jquery.min.js"%>"></script>
		<!--[if IE]><script type="text/javascript" src="<%=request.getContextPath() + "/js/ie.js"%>"></script><![endif]-->
	</head>
	<body>
		<div class="wrapper">
			<div class="head"><div class="head_inner">
				<a href="<%=request.getContextPath()%>/" class="logo"></a>
				<a href="#" class="o_code"></a>
			</div></div>
			<div class="a_form"><form method="POST" action="<%=response.encodeURL("j_security_check")%>"><fieldset>
				<div class="a_form_inputs">
					<div class="box a_form_div"><label class="fl" for="login">Ваш логин</label><div class="box"><input id="login" type="text" name="j_username"/></div></div>
					<div class="box a_form_div"><label class="fl" for="password">Ваш пароль</label><div class="box"><input id="password" type="password" name="j_password"/></div></div>
				</div>
				<div class="a_form_enter"><input type="submit" value="" class="a_form_submit"/></div>
			</fieldset></form></div>
		</div>
		<div class="footer"></div>
	</body>
</html>