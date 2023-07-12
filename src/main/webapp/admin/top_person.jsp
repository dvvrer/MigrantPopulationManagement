<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>居民代表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pagination.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/pagination.js"></script>
    <script src="${pageContext.request.contextPath}/js/my.js"></script>
</head>
<body class="hold-transition skin-red sidebar-mini">
<!--数据展示头部-->
<div class="box-header with-border">
    <h3 class="box-title">居民代表</h3>
</div>
<!--数据展示头部-->
<!--数据展示内容区-->
<div class="box-body">
    <!-- 数据表格 -->
    <table id="dataList" class="table table-bordered table-striped table-hover dataTable text-center">
        <thead>
        <tr>
            <th class="sorting_asc">身份证号</th>
            <th class="sorting">姓名</th>
            <th class="sorting">性别</th>
            <th class="sorting">出生日期</th>
            <th class="text-center">常用住址</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageResult.rows}" var="person">
            <tr>
                <td> ${person.IDCard}</td>
                <td>${person.name}</td>
                <td>${person.sex}</td>
                <td>${person.birth}</td>
                <td>${person.address}</td>
                <td class="text-center">
                        <button type="button" class="btn bg-olive btn-xs" data-toggle="modal" data-target="#searModal"
                                onclick="findPersonByIDCard('${person.IDCard}','search')"> 查看
                        </button>
                    <c:if test="${book.status ==1 ||book.status ==2}">
                        <button type="button" class="btn bg-olive btn-xs" disabled="true">借阅</button>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- 数据表格 /-->
</div>
<!-- 数据展示内容区/ -->
<%--引入存放模态窗口的页面--%>
<jsp:include page="/admin/person_modal.jsp"></jsp:include>
</body>
</html>
