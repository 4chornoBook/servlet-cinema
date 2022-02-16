<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<%--<head>--%>
<%--	<title>Cinema</title>--%>
<%--</head>--%>
<jsp:include page="/WEB-INF/head.jsp"/>
<jsp:include page="header.jsp"/>
<%--<nav class="navbar navbar-expand-lg navbar-dark bg-primary">--%>
<%--	<div class="container-fluid">--%>
<%--		<a class="navbar-brand" href="#">Cinema</a>--%>
<%--		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"--%>
<%--				aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">--%>
<%--			<span class="navbar-toggler-icon"></span>--%>
<%--		</button>--%>
<%--&lt;%&ndash;		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">&ndash;%&gt;--%>
<%--&lt;%&ndash;			<div class="navbar-nav">&ndash;%&gt;--%>
<%--&lt;%&ndash;&lt;%&ndash;				<a class="nav-link active" aria-current="page" href="#">Home</a>&ndash;%&gt;&ndash;%&gt;--%>
<%--&lt;%&ndash;				<a class="nav-link active" href="login.jsp">Login</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;			</div>&ndash;%&gt;--%>

<%--&lt;%&ndash;		</div>&ndash;%&gt;--%>
<%--		<div class="collapse navbar-collapse justify-content-end" id="3navbarNavAltMarkup">--%>
<%--			<div class="navbar-nav">--%>
<%--				<a class="nav-link active" href="registration.jsp">Register</a>--%>
<%--				<a class="nav-link active" href="login.jsp">Login</a>--%>
<%--			</div>--%>

<%--		</div>--%>
<%--	</div>--%>
<%--</nav>--%>
<jsp:include page="/WEB-INF/jsp/availableMovies.jsp"/>
<jsp:include page="/footer.jsp"/>
</body>

</html>
