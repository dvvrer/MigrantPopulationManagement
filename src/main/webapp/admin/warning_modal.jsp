<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>图书管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pagination.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/pagination.js"></script>
    <script src="${pageContext.request.contextPath}/js/my.js"></script>
</head>

<body class="hold-transition skin-red sidebar-mini">
<!-- .box-body -->
<div class="box-header with-border">
    <h3 class="box-title">预警人员信息</h3>
</div>
<div class="box-body">
    <!--工具栏 数据搜索 -->
    <div class="box-tools pull-right">
        <div class="has-feedback">
            <form action="${pageContext.request.contextPath}/warning/searchWarning" method="post">
                预警人员姓名：<input name="name" value="${warning.name}">&nbsp&nbsp&nbsp&nbsp
                预警人员身份证号：<input name="IDCard" value="${warning.IDCard}">&nbsp&nbsp&nbsp&nbsp
                预警人员性别：<input name="sex" value="${warning.sex}">&nbsp&nbsp&nbsp&nbsp
                预警人员出生日期：<input name="birth" value="${warning.birth}">&nbsp&nbsp&nbsp&nbsp
                预警人员常用住址：<input name="address" value="${warning.address}">&nbsp&nbsp&nbsp&nbsp
                预警人员类型：<input name="warn_type" value="${warning.warn_type}">&nbsp&nbsp&nbsp&nbsp
                <input class="btn btn-default" type="submit" value="查询">
            </form>
        </div>
    </div>
    <!--工具栏 数据搜索 /-->
    <!-- 数据列表 -->
    <div class="table-box">
        <!-- 数据表格 -->
        <table id="dataList" class="table table-bordered table-striped table-hover dataTable text-center">
            <thead>
            <tr>
                <th class="sorting_asc">预警人员姓名</th>
                <th class="sorting">预警人员身份证号</th>
                <th class="sorting">预警人员性别</th>
                <th class="sorting">预警人员出生日期</th>
                <th class="sorting">预警人员常用住址</th>
                <th class="sorting">预警人员类型</th>
                <th class="text-center">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageResult.rows}" var="warning_person">
                <tr>
                    <td> ${warning_person.name}</td>
                    <td>${warning_person.IDCard}</td>
                    <td>${warning_person.sex}</td>
                    <td>${warning_person.birth}</td>
                    <td>${warning_person.address}</td>
                    <td>${warning_person.warn_type}</td>
                    <td class="text-center">
                        <button type="button" class="btn bg-olive btn-xs" data-toggle="modal"
                                data-target="#editModal" onclick="findWarningPersonByIDCard('${warning_person.IDCard}','edit')"> 编辑
                        </button>
                        <button type="button" class="btn bg-olive btn-xs" data-toggle="modal"
                                data-target="#searchModal" onclick="findWarningPersonByIDCard('${warning_person.IDCard}','searchWarning')"> 查看
                        </button>
                        <button type="button" class="btn bg-olive btn-xs"
                                onclick="del('${warning_person.IDCard}')"> 删除
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <!--数据表格/-->
        <%--分页插件--%>
        <div id="pagination" class="pagination"></div>
    </div>
    <!--数据列表/-->
</div>
<!-- /.box-body -->
<%--引入存放模态窗口的页面--%>
<jsp:include page="/admin/person_modal.jsp"></jsp:include>
<!-- 添加和编辑图书的模态窗口 -->
<div class="modal fade" id="searchModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 id="myModalLabel">预警人员信息</h3>
            </div>
            <div class="modal-body">
                <form id="addOrEditBook">
                    <span><input type="hidden" id="perID" name="id"></span>
                    <table id="addOrEditTab" class="table table-bordered table-striped" width="800px">
                        <tr>
                            <td>姓名</td>
                            <td><input class="form-control" readonly placeholder="姓名" name="name" id="warName"></td>
                            <td>身份证号</td>
                            <td><input class="form-control" readonly placeholder="身份证号" name="IDCard" id="warID"></td>
                        </tr>
                        <tr>
                            <td>性别</td>
                            <td><input class="form-control" readonly placeholder="性别" name="sex" id="warSex"></td>
                            <td>出生日期</td>
                            <td><input class="form-control" readonly placeholder="出生日期" name="birth" id="warBirth"></td>
                        </tr>
                        <tr>
                            <td>常用住址</td>
                            <td><input class="form-control" readonly placeholder="常用住址" name="address" id="warAddress"></td>
                            <td>类型<br/></td>
                            <td><input class="form-control" readonly placeholder="类型" name="warn_type" id="warType"></td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3>预警人员信息</h3>
            </div>
            <div class="modal-body">
                <form id="editWarning">
                    <table id="EditTab" class="table table-bordered table-striped" width="800px">
                        <tr>
                            <td>姓名</td>
                            <td><input class="form-control" readonly placeholder="姓名" name="name" id="uWarName"></td>
                            <td>身份证号</td>
                            <td><input class="form-control" readonly placeholder="身份证号" name="IDCard" id="uWarID"></td>
                        </tr>
                        <tr>
                            <td>性别</td>
                            <td><input class="form-control" placeholder="性别" name="sex" id="uWarSex"></td>
                            <td>出生日期</td>
                            <td><input class="form-control" readonly placeholder="出生日期" name="birth" id="uWarBirth"></td>
                        </tr>
                        <tr>
                            <td>常用住址</td>
                            <td><input class="form-control" placeholder="常用住址" name="address" id="uWarAddress"></td>
                            <td>类型<br/></td>
                            <td><input class="form-control" placeholder="类型" name="warn_type" id="uWarType"></td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" data-dismiss="modal" aria-hidden="true" id="aoe" disabled onclick="Edit()">保存
                </button>
                <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>

</body>
<script>
    /*分页插件展示的总页数*/
    pageargs.total = Math.ceil(${pageResult.total}/pageargs.pagesize);
    /*分页插件当前的页码*/
    pageargs.cur = ${pageNum}
    /*分页插件页码变化时将跳转到的服务器端的路径*/
    pageargs.gourl = "${gourl}"
    /*保存搜索框中的搜索条件，页码变化时携带之前的搜索条件*/
    personVO.name = "${warning_person.name}"
    personVO.IDCard = "${warning_person.IDCard}"
    personVO.warn_type = "${warning_person.warn_type}"
    /*分页效果*/
    pagination(pageargs);
</script>
</html>