
var Kid1 = null;
var Kid2 = null;
var Kid3 = null;
var marriedID = null;
var PID = null;

//重置添加和编辑窗口中输入框的内容
function resetFrom() {
    $("#aoe").attr("disabled",true)
    var $vals=$("#addOrEditBook input");
    $vals.each(function(){
        $(this).attr("style","").val("")
    });
}


//重置添加和编辑窗口中输入框的样式
function resetStyle() {
    $("#aoe").attr("disabled",false)
    var $vals=$("#addOrEditBook input");
    $vals.each(function(){
        $(this).attr("style","")
    });
}

//页面加载完成后，给居民模态窗口的输入框绑定失去焦点和获取焦点事件
$(function() {
    var $inputs=$("#addOrEditBook input")
    var eisbn="";
    $inputs.each(function () {
        //给输入框绑定失去焦点事件
        $(this).blur(function () {
            if($(this).val()==''){
                $("#aoe").attr("disabled",true)
                $(this).attr("style","color:red").val("不能为空！")
                // }
                // else if($(this).attr("name")=="isbn"&&eisbn!==$(this).val()){
                //     if($(this).val().length!=13){
                //         $("#aoe").attr("disabled",true)
                //         alert("必须为13位数的标准ISBN，请重新输入！")
                //         $(this).val("")
                //     }
            }else{
                checkval()
            }
        }).focus(function () {
            if($(this).val()=='不能为空！'){
                $(this).attr("style","").val("")
            }else{
                $(this).attr("style","")
            }
            // if($(this).attr("name")=="isbn"){
            //     eisbn=$(this).val();
            // }
        })
    })
});

//查询id对应的子女信息，并将子女信息回显到窗口中
function findKidByIDCard(num) {
    resetStyle()
    if (num == 1){
        var url = getProjectPath()+"/findPersonByIDCard?IDCard=" + Kid1;
        console.log(url)
        $.get(url, function (response) {
            $("#kidName").val(response.data.name);
            $("#kidIDCard").val(response.data.idcard);
            $("#kidSex").val(response.data.sex);
            $("#kidBirth").val(response.data.birth);
            $("#kidAddress").val(response.data.address);
        })
    }else if(num == 2){
        var url = getProjectPath()+"/findPersonByIDCard?IDCard=" + Kid2;
        console.log(url)
        $.get(url, function (response) {
            $("#kidName").val(response.data.name);
            $("#kidIDCard").val(response.data.idcard);
            $("#kidSex").val(response.data.sex);
            $("#kidBirth").val(response.data.birth);
            $("#kidAddress").val(response.data.address);
        })
    }else if(num == 3){
        var url = getProjectPath()+"/findPersonByIDCard?IDCard=" + Kid3;
        console.log(url)
        $.get(url, function (response) {
            $("#kidName").val(response.data.name);
            $("#kidIDCard").val(response.data.idcard);
            $("#kidSex").val(response.data.sex);
            $("#kidBirth").val(response.data.birth);
            $("#kidAddress").val(response.data.address);
        })
    }
}
//添加结婚信息
function add() {
        var url = getProjectPath()+"/addMarried";
        $.post(url, $("#addMarried").serialize(), function (response) {
            alert(response.message)
            if (response.success == true) {
                window.location.href = getProjectPath()+"/searchAllMarried";
            }
        })
}

function addCarried(){
    $("#carriedID").val(PID)
}
//添加子女信息
function addKids() {
    //执行添加操作
    var url = "/resident/addResident";
    var url2 = "/resident/fileUp";


    //上传头像
    const fileInput = $("#photo_url")
    const files = fileInput.prop("files")
    if (!files.length) {
        return
    }
    const file = files[0]

    const data = new FormData()
    data.append("photo_url", file)

    //提交头像文件到控制层
    $.ajax({
        type: "POST",
        url: url2,
        data: data,
        contentType: false,
        processData: false,
        success: null
    })

    //居民信息序列化
    var residentData = $("#addKid").serialize();
    console.log(residentData.data())
    $.ajax({
        type: "POST",
        url: url,
        data: residentData + "&photo_url=" + $("#photo_url").val(),
        dataType: "json",
        success: function (response){
                alert(response.message);
                console.log("married=" + marriedID + "&kid1="+$("#IDCard").val())
                $.ajax({
                    type: "POST",
                    url: "/married/updateMarried",
                    data: "marriedID=" + marriedID + "&kid="+$("#IDCard").val(),
                    dataType:"json",
                    success:function () {
                        window.location.href = getProjectPath() + "/searchAllMarried";
                    }
                })
        }
    })
}
//查看结婚详细信息窗口
function findMarriedByID(id,ownID,spouseID,kid1,kid2,kid3) {
    Kid1 = kid1;
    Kid2 = kid2;
    Kid3 = kid3;
    marriedID = id;
    PID = ownID;
    console.log(Kid1)
    if (Kid1 == "") {
        document.getElementById("kid1But").removeAttribute("style")
    }else if(Kid1 != ""){
        document.getElementById("kid1Info").removeAttribute("style")
    }
    if (Kid2 == "") {
        document.getElementById("kid2But").removeAttribute("style")
    }else if(Kid2 != ""){
        document.getElementById("kid2Info").removeAttribute("style")
    }
    if (Kid3 == "") {
        document.getElementById("kid3But").removeAttribute("style")
    }else if(Kid3 != ""){
        document.getElementById("kid3Info").removeAttribute("style")
    }
    resetStyle()
    var url = getProjectPath() + "/findMarriedByID?ID=" + id;
    var spouseUrl = getProjectPath()+"/findPersonByIDCard?IDCard="+spouseID;
    var ownUrl = getProjectPath()+"/findPersonByIDCard?IDCard="+ownID;
    var kid1Url = getProjectPath()+"/findPersonByIDCard?IDCard="+Kid1
    var kid2Url = getProjectPath()+"/findPersonByIDCard?IDCard="+Kid2
    var kid3Url = getProjectPath()+"/findPersonByIDCard?IDCard="+Kid3
    $.get(url, function (response) {
        $("#marriedDate").val(response.data.marriedDate);
    })
    $.get(ownUrl,function (response) {
        $("#ownName").val(response.data.name);
        $("#ownIDCard").val(response.data.idcard);
    })

    $.get(spouseUrl,function (response) {
        $("#spouseName").val(response.data.name);
        $("#spouseIDCard").val(response.data.idcard);
    })
    $.get(kid1Url,function (response) {
        $("#kid1Name").val(response.data.name);
        $("#kid1IDCard").val(response.data.idcard);
        $("#kid1Id").val(response.data.idcard);

    })
    $.get(kid2Url,function (response) {
        $("#kid2Name").val(response.data.name);
        $("#kid2IDCard").val(response.data.idcard);
        $("#kid2Id").val(response.data.idcard);
    })
    $.get(kid3Url,function (response) {
        $("#kid3Name").val(response.data.name);
        $("#kid3IDCard").val(response.data.idcard);
        $("#kid3Id").val(response.data.idcard);
    })
}

//查看预警人员详细信息窗口
function findWarningPersonByIDCard(id,doname) {
    resetStyle()
    console.log(id)
    var url = getProjectPath()+"/findWarningPeopleByID?IDCard=" + id;
    $.get(url, function (response) {
        //如果是查看信息，将获取的个人信息回显到信息的窗口中
        if(doname=='searchWarning'){
            $("#warName").val(response.data.name);
            $("#warID").val(response.data.idcard);
            $("#warSex").val(response.data.sex);
            $("#warBirth").val(response.data.birth);
            $("#warAddress").val(response.data.address);
            $("#warType").val(response.data.warn_type);
        }
        //如果是编辑信息，将获取的个人信息回显到编辑的窗口中
        if(doname=='edit'){
            $("#savemsg").attr("disabled",true)
            $("#uWarName").val(response.data.name);
            $("#uWarID").val(response.data.idcard);
            $("#uWarBirth").val(response.data.birth);
            $("#uWarSex").val(response.data.sex);
            $("#uWarAddress").val(response.data.address);
            $("#uWarType").val(response.data.warn_type);

        }
    })
}

//点击编辑的窗口的确定按钮时，提交预警人员信息
function Edit() {
        var url = getProjectPath()+"/editWarning";
        $.post(url, $("#editWarning").serialize(), function (response) {
            alert(response.message)
            if (response.success == true) {
                window.location.href = getProjectPath()+"/searchWarning";
            }
        })
}
function del(id) {
    var r = confirm("确定删除该预警人员?");
    if (r) {
        var url = getProjectPath()+"/delWarning?IDCard=" + id;
        $.get(url, function (response) {
            alert(response.message)
            //还书成功时，刷新当前借阅的列表数据
            if (response.success == true) {
                window.location.href = getProjectPath()+"/searchWarning";
            }
        })
    }
}

function winRefresh(){
    window.location.href = getProjectPath() + "/searchAllMarried";
}
//查看居民信息
findPersonByIDCard = function(id,doname) {
    resetStyle()
    var url = getProjectPath()+"/findByIDCard?IDCard=" + id;
    console.log(url)
    $.get(url, function (response) {
        //如果是编辑居民信息，将获取的居民信息回显到编辑的窗口中
        if(doname=='edit'){
            $("#seridcard").val(response.data.IDCard);
            $("#sername").val(response.data.name);
            $("#sersex").val(response.data.sex);
            $("#serbirth").val(response.data.birth);
            $("#seraddress").val(response.data.address);
        }
        //如果是查看信息，将获取的个人信息回显到信息的窗口中
        if(doname=='search'){
            $("#savemsg").attr("disabled",true)
            $("#seridcard").val(response.data.idcard);
            console.log(response.data.IDCard)
            $("#sername").val(response.data.name);
            $("#sersex").val(response.data.sex);
            $("#serbirth").val(response.data.birth);
            $("#seraddress").val(response.data.address);
            document.getElementById("portrait").src="../img/"+response.data.photo_url;
        }
    })
}


//
//检测头像可用性
window.onload = () => {
    // console.log(document.getElementById("addP"))
    $("#addP").click(function (){
        var url = "/resident/fileload";
        const fileInput = $("#photo_url")
        const files = fileInput.prop("files")
        if (!files.length) {
            return
        }
        const file = files[0]

        const data = new FormData()
        data.append("photo_url", file)

        const success = (response) => {
            // alert(JSON.stringify(response))
            alert(JSON.stringify(response.message))
        }

        //检测头像可用性
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            contentType: false,
            processData: false,
            success: success
        })
    })
}

//点击添加或编辑的窗口的确定按钮时，提交居民信息
function addOrEdit() {
    //执行添加操作
    var url = "/resident/addResident";
    var url2 = "/resident/fileUp";

    if ($("#photo_url").val()==""){
        alert("头像为空,添加失败！")
    }
    //上传头像
    const fileInput = $("#photo_url")
    const files = fileInput.prop("files")
    if (!files.length) {
        return
    }
    const file = files[0]

    const data = new FormData()
    data.append("photo_url", file)

    // const success = (response) => {
    //     // alert(JSON.stringify(response))
    //     alert(JSON.stringify(response.message))
    // }

    //提交头像文件到控制层
    $.ajax({
        type: "POST",
        url: url2,
        data: data,
        contentType: false,
        processData: false,
        // success: success
    })

    //居民信息序列化
    var residentData = $("#addOrEditBook").serialize();
    console.log(residentData)
    $.ajax({
        type: "POST",
        url: url,
        data: residentData + "&photo_url=" + $("#photo_url").val(),
        dataType: "json",
        success: function (response){
            alert(response.message)
            window.location.href = "/person/resident_manage";
        }
    })

    // // 添加居民信息
    // $.post(url, $("#addOrEditBook,#addPortrait").serialize(), function (response) {
    //     alert(response.message)
    // })
}

//检查图书信息的窗口中，图书信息填写是否完整
function checkval(){
    var $inputs=$("#addTab input")
    var count=0;
    $inputs.each(function () {
        if($(this).val()==''||$(this).val()=="不能为空！"){
            count+=1;
        }
    })
    //如果全部输入框都填写完整，解除确认按钮的禁用状态
    if(count==0){
        $("#aoe").attr("disabled",false)
    }
}

//失去焦点之后的ajax
$(function() {
    var $inputs=$("#addMarried input")
    var personID="";
    $inputs.each(function () {
        //给输入框绑定失去焦点事件
        $(this).blur(function () {
            if($(this).attr("name")=="PID" &&personID!==$(this).val()){
                if($(this).val().length!==18){
                    $("#aoe").attr("disabled",true)
                    alert("必须为18位数的标准身份证号，请重新输入！")
                    $(this).val("")
                    return;
                }
            }else if($(this).attr("name")=="spouse" &&personID!==$(this).val()){
                if($(this).val().length!==18){
                    $("#aoe").attr("disabled",true)
                    alert("必须为18位数的标准身份证号，请重新输入！")
                    $(this).val("")
                    return;
                }
            }
            var ownIDCard = $("#PID").val();
            var spouseIDCard = $("#spouse").val();
            $.ajax({
                url: getProjectPath()+"/isMarried?IDCard="+ownIDCard,
                type: "post",
                data: JSON,
                contentType: "application/json;charset=UTF-8",
                dataType: "json",
                success: function (response) {
                    if (!response.data.flag){
                        $.ajax({
                            url: getProjectPath()+"/findPersonByIDCard?IDCard="+ownIDCard,
                            type: "post",
                            data: JSON,
                            contentType: "application/json;charset=UTF-8",
                            dataType: "json",
                            success: function (response) {
                                $("#addOwnName").val(response.data.name);
                                $("#addOwnAdd").val(response.data.address);
                                $("#addOwnBir").val(response.data.birth);
                            }
                        })
                    }else {
                        alert("已有结婚信息！重婚违法！")
                        $("#PID").val("")
                        $("#addOwnName").val("");
                        $("#addOwnAdd").val("");
                        $("#addOwnBir").val("");
                        return;
                    }
                }
            })
            $.ajax({
                url: getProjectPath()+"/isMarried?IDCard="+spouseIDCard,
                type: "post",
                data: JSON,
                contentType: "application/json;charset=UTF-8",
                dataType: "json",
                success: function (response) {
                    if (!response.data.flag){
                        $.ajax({
                            url: getProjectPath()+"/findPersonByIDCard?IDCard="+spouseIDCard,
                            type: "post",
                            data: JSON,
                            contentType: "application/json;charset=UTF-8",
                            dataType: "json",
                            success: function (response) {
                                $("#addSpouseName").val(response.data.name);
                                $("#addSpouseAdd").val(response.data.address);
                                $("#addSpouseBir").val(response.data.birth);
                            }
                        })
                    }else {
                        alert("已有结婚信息！重婚违法！")
                        $("#spouse").val("")
                        $("#addSpouseName").val("");
                        $("#addSpouseAdd").val("");
                        $("#addSpouseBir").val("");
                        return
                    }
                }
            })
        })
    })
});

function findPersonByIDCard2(id,doname) {
    resetStyle()
    var url = getProjectPath()+"/findByIDCard?IDCard=" + id;
    // console.log(url)
    $.get(url, function (response) {
        //如果是编辑信息，将获取的居民信息回显到编辑的窗口中
        if(doname=='edit'){
            $("#editRes").attr("disabled",false)

            //去除只读状态
            // $("#IDCard").removeAttr("readonly");
            $("#name").removeAttr("readonly");
            // $("#sex").removeAttr("readonly");
            // $("#birth").removeAttr("readonly");
            $("#address").removeAttr("readonly");
            $("#tel").removeAttr("readonly");
            $("#email").removeAttr("readonly");
            $("#education").removeAttr("readonly");
            $("#political").removeAttr("readonly");
            $("#isMarry").removeAttr("readonly");
            $("#age").removeAttr("readonly");

            $("#IDCard").val(response.data.idcard);
            // console.log(response.data.idcard);
            $("#name").val(response.data.name);
            $("#sex").val(response.data.sex);
            $("#birth").val(response.data.birth);

            $("#tel").val(response.data.tel);
            $("#nation").val(response.data.nation);
            $("#email").val(response.data.email);
            $("#political").val(response.data.political);
            $("#education").val(response.data.education);
            $("#isMarry").val(response.data.isMarry);
            $("#age").val(response.data.age);
            $("#domicileplace").val(response.data.domicileplace);
            $("#religion").val(response.data.religion);




            $("#address").val(response.data.address);
            document.getElementById("portrait").src ="../img/"+ response.data.photo_url;
        }
        //如果是查看信息，将获取的居民信息回显到信息的窗口中
        if(doname=='search'){
            // console.log(response.data)
            $("#editRes").attr("disabled",true)
            $("#IDCard").val(response.data.idcard);
            // console.log(response.data.idcard);
            $("#name").val(response.data.name);
            $("#sex").val(response.data.sex);
            $("#birth").val(response.data.birth);
            $("#address").val(response.data.address);
            $("#tel").val(response.data.tel);
            $("#nation").val(response.data.nation);
            $("#email").val(response.data.email);
            $("#political").val(response.data.political);
            $("#education").val(response.data.education);
            $("#isMarry").val(response.data.isMarry);
            $("#age").val(response.data.age);
            $("#domicileplace").val(response.data.domicileplace);
            $("#religion").val(response.data.religion);
            document.getElementById("portrait").src ="../img/"+ response.data.photo_url;
        }
    })
}


//获取当前项目的名称
function getProjectPath() {
    //获取主机地址之后的目录，如： cloudlibrary/admin/people.jsp
    var pathName = window.document.location.pathname;
    //获取带"/"的项目名，如：/cloudlibrary
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return  projectName;
}


/**
 * 数据展示页面分页插件的参数
 * cur 当前页
 * total 总页数
 * len   显示多少页数
 * pagesize 1页显示多少条数据
 * gourl 页码变化时 跳转的路径
 * targetId 分页插件作用的id
 */
var pageargs = {
    cur: 1,
    total: 0,
    len: 5,
    pagesize:10,
    gourl:"",
    targetId: 'pagination',
    callback: function (total) {
        var oPages = document.getElementsByClassName('page-index');
        for (var i = 0; i < oPages.length; i++) {
            oPages[i].onclick = function () {
                changePage(this.getAttribute('data-index'), pageargs.pagesize);
            }
        }
        var goPage = document.getElementById('go-search');
        goPage.onclick = function () {
            var index = document.getElementById('yeshu').value;
            if (!index || (+index > total) || (+index < 1)) {
                return;
            }
            changePage(index, pageargs.pagesize);
        }
    }
}

var personVO = {
    idcard:'',
    name:'',
    sex:'',
    birth:'',
    address:'',
    warn_type:'',
    photo_url:'',
    tel:'',
    nation:'',
    email:'',
    education:'',
    age:'',
    domicileplace:'',
    religion:'',
    isMarry:'',
    political:'',
    carriedID:''
}
var marriedVO = {
    ID:'',
    PID:'',
    ownName:'',
    marriedDate:'',
    spouse:'',
    kid1:'',
    kid2:'',
    kid3:''
}

var marriedFlag = {
    flag:''
}

//数据展示页面分页插件的页码发送变化时执行
function changePage(pageNo,pageSize) {
    pageargs.cur=pageNo;
    pageargs.pagesize=pageSize;
    document.write("<form action="+pageargs.gourl +" method=post name=form1 style='display:none'>");
    document.write("<input type=hidden name='pageNum' value="+pageargs.cur+" >");
    document.write("<input type=hidden name='pageSize' value="+pageargs.pagesize+" >");
    //如果跳转的是图书信息查询的相关链接，提交图书查询栏中的参数
    if(pageargs.gourl.indexOf("warning_person")>=0){
        document.write("<input type=hidden name='name' value="+personVO.name+" >");
        document.write("<input type=hidden name='IDCard' value="+personVO.IDCard+" >");
        document.write("<input type=hidden name='birth' value="+personVO.birth+" >");
        document.write("<input type=hidden name='warn_type' value="+personVO.warn_type+" >");
        document.write("<input type=hidden name='sex' value="+personVO.sex+" >");
        document.write("<input type=hidden name='address' value="+personVO.address+" >");
    }

    document.write("</form>");
    document.form1.submit();
    pagination(pageargs);
}


//点击保存修改居民信息时执行
function saveRes() {
    var url = "/resident/editResident";
    var formdata = $("#editResident").serialize();
    console.log(formdata)
    $.post(url, formdata, function (response) {
        alert(response.message)
        window.location.href = "/resident/searchResident";
    })
}

//删除居民信息
function deleteResident(IDCard){

    var url = "/resident/deleteResident?IDCard=" + IDCard;
    console.log(url)
    // $.ajax({
    //     type: "POST",
    //     url: url,
    //     data: data,
    //     contentType: false,
    //     processData: false,
    //     success: null
    // })

    // var url2 = "/married/updateKid?kid=" + IDCard
    //
    // $.post(url2)
    //
    $.post(url,function (response){
        alert(response.message)
        window.location.href = "/person/resident_manage";
    })
}
