<%@page import="ru.dreamjteam.entity.CarVO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="isAdmin" value="true"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<title>Импорт автомобилей</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css" />
		<!--[if IE]><link rel="stylesheet" type="text/css" href="<%=request.getContextPath() + "/css/ie.css"%>"/><![endif]-->
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
	    <script type="text/javascript" src="<%=request.getContextPath() + "/js/my.js"%>"></script>
	    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.qtip.js"></script>
		<!--[if IE]><script type="text/javascript" src="<%=request.getContextPath() + "/js/ie.js"%>"></script><![endif]-->
	    <!--[if IE]><script type="text/javascript" src="<%=request.getContextPath() + "/js/pie.js"%>"></script><![endif]-->
	</head>
	<body>
	<div id="loadpage" style="position: absolute; height: 120px; width: 400px; top: 50%; margin-top:-60px;
		left: 50%; margin-left: -200px; background-color: #ebf0f5; vertical-align: middle; text-align: right; z-index:1001">
		<p style=" height:100%; width:100%; vertical-align:middle; text-align:center; font-size: large;">
			<br><B>Загрузка ... ... Пожалуйста, подождите</B><br><br>
			<img src="<%=request.getContextPath()%>/i/loading.gif" alt="Загрузка"/>
		</p>
	</div>
		<div class="wrapper">
			<div class="head"><div class="head_inner">
				<a href="<%=request.getContextPath()%>/" class="logo"></a>
				<a href="#" class="o_code"></a>
			</div></div>

			<div class="content box">
				<div class="top pr"><div class="top2"><div class="top3">
					<div class="top_t">Парк автомобилей</div>
					<div class="fr top_inf">
						<%--<span class="top_inf_span"><strong><img src="<%=request.getContextPath()%>/i/m2.png" alt=""/>Пользователь:</strong> <a href="#"><c:out value="${requestScope.User}"/></a>  </span>--%>
						<%--<span class="top_inf_span"><strong>Статус:</strong> <a href="#"><c:out value="${requestScope.UserRole}"/></a></span>--%>
						<%--<span class="top_inf_span"><a href="#"><img src="<%=request.getContextPath()%>/i/settings.png" alt=""/><span>Настройки</span></a></span>--%>
						<%--<html:link action="/Logout"><img src="<%=request.getContextPath()%>/i/m.png" alt=""/><span>Выход</span></html:link>--%>
					</div>
				</div></div></div>
				<div class="text_content box">
					<div class="box txt_wrap">
						<div class="txt pr zoom after">
							<div class="zoom after pr menu1">
								<div class="menu1_notactive fl"><div class="menu1_notactive_wrap"><div class="menu1_notactive_inner"><div class="menu1_notactive_div"><a href="<%=request.getContextPath()%>/ViewOrderList">Заказы</a></div></div></div></div>
								<div class="menu1_notactive fl"><div class="menu1_notactive_wrap"><div class="menu1_notactive_inner"><div class="menu1_notactive_div"><a href="<%=request.getContextPath()%>/ViewCarTypeList">Автопарк</a></div></div></div></div>
								<div class="menu1_notactive fl"><div class="menu1_notactive_wrap"><div class="menu1_notactive_inner"><div class="menu1_notactive_div"><a href="<%=request.getContextPath()%>/ViewCarTypeList">Типы автомобилей</a></div></div></div></div>
							</div>
                                                        
							<div class="txt_content">
                                                            <h2>Импорт автомобилей</h2>
                                                                <form action="<%=request.getContextPath()%>/ImportCar" method="POST" accept-charset="UTF-8" enctype="multipart/form-data">
                                                                    <span class="file-wrapper">
                                                                         <input type="file" name="file1" id="photo" />
                                                                         <span class="button">Выберите файл</span>
                                                                    </span><br>
                                                                    <input type="Submit" value="Загрузить" class="dialog_btn"><br>
                                                                </form>
                                                                
								<div class="xml_output">
								<c:if test="${!empty requestScope.output}">
                                                                <%= (String) request.getAttribute("output")%>
                                                                </c:if>
                                                                </div>
                                                                    
                                                                <%  String xmlString = (String) request.getAttribute("xmlString");
                                                                            request.getSession().setAttribute("xmlString", xmlString); %>
										
								<p class="create_test">
                                                                    <form>
                                                                        <button formaction="<%=request.getContextPath()%>/CreateOrUpdateCar" class="btn">Импортировать</button>
                                                                        <button formaction="<%=request.getContextPath()%>/ViewCarList" class="btn">Отменить</button>
                                                                    </form>
								</p>
								<div class="start_wrap box"><a class="pr fl start" href="#"><span class="db">В начало</span></a></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer"></div>
		<script type="text/javascript">
			$(function() {
				UpdateNewOrderList('<%=request.getContextPath()%>/newOrders.jsp');
			});
			$(function() {
				$('.close_b_div_link').live('click', function() {
					var buttons = $('.close_b_div_link', $(this).parent().parent());
					$('.box', $(this).parent().parent()).removeClass('close_b_div');
					buttons.removeClass('close_b_div_link');
					buttons.addClass('open_b_div_link');
				});
				$('.open_b_div_link').live('click', function() {
					var buttons = $('.open_b_div_link', $(this).parent().parent());
					$('.box', $(this).parent().parent()).addClass('close_b_div');
					buttons.removeClass('open_b_div_link');
					buttons.addClass('close_b_div_link');
				});
			});
                        <c:if test="${requestScope.error == 'wrongXML'}">
                           if (confirm('xml не соответсвует схеме! Загрузить новый?')) { parent.location='<%=request.getContextPath()%>/importCar.jsp'; }
                            else { parent.location='<%=request.getContextPath()%>/ViewCarList'; }
                        </c:if>
                        <c:if test="${requestScope.error == 'Car01'}">
                            if (confirm('Машина с таким гос. номером существует! Импортировать с заменой?')) { parent.location='<%=request.getContextPath()%>/CreateOrUpdateCar?update=true'; }
                            else { parent.location='<%=request.getContextPath()%>/CreateOrUpdateCar?update=false'; }  
                        </c:if>
                            
                        var SITE = SITE || {};
                        SITE.fileInputs = function() {
                          var $this = $(this),
                              $val = $this.val(),
                              valArray = $val.split('\\'),
                              newVal = valArray[valArray.length-1],
                              $button = $this.siblings('.button'),
                              $fakeFile = $this.siblings('.file-holder');
                          if(newVal !== '') {
                            if($fakeFile.length === 0) {
                              $button.after('<span class="file-holder">' + newVal + '</span>');
                            } else {
                              $fakeFile.text(newVal);
                            }
                          }
                        };

                        $(document).ready(function() {
                          $('.file-wrapper input[type=file]').bind('change focus click', SITE.fileInputs);
                        });
		</script>
	</body>
</html>