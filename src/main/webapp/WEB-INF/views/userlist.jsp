<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="container">
    <h1><spring:message code="view.User.List"/></h1>

    <div class="panel panel-primary news-shelf">
        <div class="panel-heading"><spring:message code="view.User.Registered"/></div>
        <div class="panel-body">
            <div class="row">
                <c:forEach items="${userlist}" var="singleUser">
                    <div class="col-sm-2 col-md-2">
                        <div class="thumbnail">
                            <a href="/spring/users/${singleUser.userId}"><img src="/spring/resources/img/theuser.jpg" alt="${singleUser.userName}"></a>
                            <div class="caption">
                                <p><a href="/spring/users/${singleUser.userId}">${singleUser.userName}</a></p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />