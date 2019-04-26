<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Online Shopping - ${title}</title>
<script>
	window.menu = '${title}';

	window.contextRoot = '${contextRoot}'
</script>
<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<%-- <link href="${css}/bootstrap-theme.css" rel="stylesheet"> --%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" ></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" ></script>
</head>

<body>
	<div class="wrapper">
		<!-- Navigation -->
		<%@include file="./shared/navbar.jsp"%>
		<!-- Page Content -->
		<div class="content">
			<!-- Loading the home content -->
			<c:if test="${userClickHome == true }">
				<%@include file="home.jsp"%>
			</c:if>
			<!-- Load only when user clicks about -->
			<c:if test="${userClickAbout == true }">
				<%@include file="about.jsp"%>
			</c:if>

			<!-- Load only when user clicks contact -->
			<c:if test="${userClickContact == true }">
				<%@include file="contact.jsp"%>
			</c:if>
			<!-- Load only when user clicks view products -->
			<c:if
				test="${userClickAllProducts == true or userClickCategoryProducts == true }">
				<%@include file="listProducts.jsp"%>
			</c:if>

			<!-- Load only when user clicks show product -->
			<c:if test="${userClickShowProduct == true}">
				<%@include file="singleProduct.jsp"%>
			</c:if>

		</div>
		<!-- Footer comes here -->
		<%@include file="./shared/footer.jsp"%>

		<!-- Bootstrap core JavaScript -->
		<script src="${js}/jquery.js"></script>
<%-- 		<script src="${js}/jquery.min.js"></script> --%>
<%-- 		<script src="${js}/bootstrap.bundle.min.js"></script> --%>
<%-- 		<script src="${js}/bootstrap.min.js"></script> --%>

		<!-- PLUGIN DATATABLE -->
		<script src="${js}/jquery.dataTables.js"></script>


<%--  		<script src="${js}/dataTables.bootstrap.js"></script>  --%>

		<!-- DataTable Bootstrap Script -->
<%-- 		<script src="${js}/bootbox.min.js"></script> --%>

		<!-- Self coded javascript -->
		<script src="${js}/myapp.js"></script>

		

	</div>
</body>

</html>
