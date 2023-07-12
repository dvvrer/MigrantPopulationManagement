<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8">
    <title>居民信息管理</title>
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
    <h3 class="box-title">居民信息管理</h3>

</div>
<!--数据展示头部-->
<!--数据展示内容区-->
<div class="box-body">
    <div class="pull-left">
        <div class="form-group form-inline">
            <div class="btn-group">
                <button type="button" class="btn btn-default" title="新建" data-toggle="modal"
                        data-target="#addOrEditModal" onclick="resetFrom()"> 新增居民信息
                </button>
            </div>
        </div>
    </div>
    <!--工具栏 数据搜索 -->
    <div class="box-tools pull-right">
        <div class="has-feedback">
            <form action="${pageContext.request.contextPath}/resident/searchResident" method="post">
                姓名：<input name="name" value="${search.name}">&nbsp&nbsp&nbsp&nbsp
                身份证号：<input name="IDCard" value="${search.IDCard}">&nbsp&nbsp&nbsp&nbsp
                <input class="btn btn-default" type="submit" value="查询">
            </form>
        </div>
    </div>
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
                <td>${person.IDCard}</td>
                <td>${person.name}</td>
                <td>${person.sex}</td>
                <td>${person.birth}</td>
                <td>${person.address}</td>
                <td class="text-center">
                        <%-- 查看居民信息 --%>
                    <button type="button" class="btn bg-olive btn-xs" data-toggle="modal" data-target="#searModal"
                            onclick="findPersonByIDCard2('${person.IDCard}','search')"> 查看
                    </button>

                        <%-- 编辑居民信息 --%>
                    <button type="button" class="btn bg-olive btn-xs" data-toggle="modal" data-target="#searModal"
                            onclick="findPersonByIDCard2('${person.IDCard}','edit')"> 编辑
                    </button>

                        <%-- 删除居民信息 --%>
                    <button type="button" class="btn bg-olive btn-xs"
                            onclick="deleteResident('${person.IDCard}')"> 删除
                    </button>
                        <%--                    <c:if test="${book.status ==1 ||book.status ==2}">--%>
                        <%--                        <button type="button" class="btn bg-olive btn-xs" disabled="true">借阅</button>--%>
                        <%--                    </c:if>--%>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div id = "pagination" class="pagination"></div>
    <!-- 数据表格 /-->
</div>
<!-- 数据展示内容区/ -->
<%--引入存放模态窗口的页面--%>
<jsp:include page="/admin/person_modal.jsp"></jsp:include>
</body>
<script>
    /*分页插件展示的总页数*/
    pageargs.total = Math.ceil(${pageResult.total}/pageargs.pagesize);
    /*分页插件当前的页码*/
    pageargs.cur = ${pageNum}
        /*分页插件页码变化时将跳转到的服务器端的路径*/
        pageargs.gourl = "${gourl}"
    /*保存搜索框中的搜索条件，页码变化时携带之前的搜索条件*/
    personVO.name = "${person.name}"
    /*分页效果*/
    pagination(pageargs);
</script>
<!-- 添加和编辑居民信息的模态窗口 -->
<div class="modal fade" id="addOrEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 id="myModalLabel">居民信息</h3>
            </div>
            <div class="modal-body">
                <form id="addOrEditBook">
                    <span><input type="hidden" id="ebid" name="id"></span>
                    <table id="addTab" class="table table-bordered table-striped" width="800px">
                        <%--居民的身份证号码,不展示在页面--%>
                        <tr>
                            <td>姓名</td>
                            <td><input class="form-control" placeholder="姓名" name="name" id="name"></td>
                            <td>身份证号</td>
                            <td><input class="form-control" placeholder="身份证号" name="IDCard" id="IDCard"></td>
                        </tr>
                        <tr>
                            <td>性别</td>
                            <td><input class="form-control" placeholder="性别" name="sex" id="sex"></td>
                            <td>生日</td>
                            <td><input class="form-control" placeholder="生日" name="birth" id="birth"></td>
                        </tr>
                        <tr>
                            <td>电话号码</td>
                            <td><input class="form-control" placeholder="电话号码" name="tel" id="tel"></td>
                            <td>民族</td>
                            <td><input class="form-control" placeholder="民族" name="nation" id="nation"></td>
                        </tr>
                        <tr>
                            <td>地址</td>
                            <td><input class="form-control" placeholder="地址" name="address" id="address"></td>
<%--                            <td>携带人</td>--%>
<%--                            <td><input class="form-control" placeholder="携带人身份证号" name="carriedID" id="carriedID"></td>--%>
                            <td>宗教信仰</td>
                            <td><input class="form-control" placeholder="宗教信仰" name="religion" id="religion"></td>
                        </tr>
                        <tr>
                            <td>邮箱</td>
                            <td><input class="form-control" placeholder="邮箱" name="email" id="email"></td>
                            <td>学历</td>
                            <td><input class="form-control" placeholder="学历" name="education" id="education"></td>
                        </tr>
                        <tr>
                            <td>政治面貌</td>
                            <td><input class="form-control" placeholder="政治面貌" name="political" id="political"></td>
                            <td>婚姻状况</td>
                            <td><input class="form-control" placeholder="婚姻状况" name="isMarry" id="isMarry"></td>
                        </tr>
                        <tr>
                            <td>年龄</td>
                            <td><input class="form-control" placeholder="年龄" name="age" id="age"></td>
                            <td>户籍地址</td>
                            <td><input class="form-control" placeholder="户籍地址" name="domicileplace" id="domicileplace"></td>
                        </tr>

                    </table>
                </form>
            </div>
            <div class="portrait-sty" style="padding-left: 25px">

                <%--上传头像，通过人脸识别判定是不是预警人员--%>
                <form target="form" id="addPortrait" method="post" enctype="multipart/form-data">
                    <span style="float: left;font-size: 15px;" >头像:&nbsp;&nbsp;&nbsp;</span>
                    <input style="float: left" type="file" name="photo_url" id="photo_url">
                    <input class="btn btn-success" type="reset" value="清空">
                    <%--                    <input type="button" value="检测可用性" id="sub-portrait" name="sub-portrait" onclick="addP()">--%>
                    <button class="btn btn-success" type="button" id="addP" style="margin-left: 37px;">检测头像可用性</button>
                </form>
                <iframe id="form" name="form" style="display: none"></iframe>

            </div>
            <div class="modal-footer">
                <button class="btn btn-success" data-dismiss="modal" aria-hidden="true" id="aoe" disabled onclick="addOrEdit()">保存
                </button>
                <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>


</html>

