<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Zarejestruj się - ReadStack</title>
    <%@ include file="../segments/stylesheets.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/add-discovery-form.css">
</head>
<body>
<div class="container">
    <%@ include file="../segments/header.jspf" %>

    <form action="${pageContext.request.contextPath}/discovery/add" method="post" class="discovery-form">
        <h2 class="discovery-form-title">Dodaj nowe znalezisko</h2>
        <input name="title" placeholder="Tytuł" required>
        <input name="url" placeholder="URL" type="url" required>
        <select name="categoryId">
            <c:forEach var="category" items="${requestScope.categories}">
                <option value="${category.id}">${category.name}</option>
            </c:forEach>
        </select>
        <textarea name="description" placeholder="Opis"></textarea>
        <button class="discovery-form-button">Dodaj</button>
    </form>

    <%@ include file="../segments/footer.jspf" %>
</div>
</body>
</html>