<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- 图书借阅信息的模态窗口，默认是隐藏的 -->
<div class="modal fade" id="searModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 id="myModalLabel" style="font-size: 35px; float: left; margin-top: 50px;margin-left: 150px">居民信息</h3>
                <img src="../img/user.jpg" id="portrait" width="100px" height="130px" style="float: right; margin-right: 50px">
            </div>
            <div class="modal-body">
                <form id="editResident">
                    <table class="table table-bordered table-striped" width="800px">
                        <%--图书的id，不展示在页面--%>
                        <%--                        <span><input type="hidden" id="seridcard" name="seridcard"></span>--%>

                        <tr>
                            <td>姓名</td>
                            <td><input class="form-control" readonly name="name" id="name"></td>
                            <td>身份证</td>
                            <td><input class="form-control" readonly name="IDCard" id="IDCard"></td>
                        </tr>
                        <tr>
                            <td>性别</td>
                            <td><input class="form-control" readonly name="sex" id="sex"></td>
                            <td>生日</td>
                            <td><input class="form-control" readonly name="birth" id="birth"></td>
                        </tr>
                        <tr>
                            <td>电话号码</td>
                            <td><input class="form-control" readonly name="tel" id="tel"></td>
                            <td>民族</td>
                            <td><input class="form-control" readonly name="nation" id="nation"></td>
                        </tr>
                        <tr>
                            <td>地址</td>
                            <td><input class="form-control" readonly name="address" id="address"></td>
<%--                            <td>携带人</td>--%>
<%--                            <td><input class="form-control" readonly name="carriedID" id="carriedID"></td>--%>
                            <td>宗教信仰</td>
                            <td><input class="form-control" readonly name="religion" id="religion"></td>
                        </tr>
                        <tr>
                            <td>邮箱</td>
                            <td><input class="form-control" readonly name="email" id="email"></td>
                            <td>学历</td>
                            <td><input class="form-control" readonly name="education" id="education"></td>
                        </tr>
                        <tr>
                            <td>政治面貌</td>
                            <td><input class="form-control" readonly name="political" id="political"></td>
                            <td>婚姻状况</td>
                            <td><input class="form-control" readonly name="isMarry" id="isMarry"></td>
                        </tr>
                        <tr>
                            <td>年龄</td>
                            <td><input class="form-control" readonly name="age" id="age"></td>
                            <td>户籍地址</td>
                            <td><input class="form-control" readonly name="domicileplace" id="domicileplace"></td>
                        </tr>

                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <%--点击保存按钮时，隐藏模态窗口并调用js文件中的borrow()方法--%>
                <button class="btn btn-success" data-dismiss="modal" aria-hidden="true"
                        disabled id="editRes" onclick="saveRes()">保存
                </button>

                <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>
