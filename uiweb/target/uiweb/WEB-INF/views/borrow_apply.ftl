<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>蓝源Eloan-P2P平台</title>
		<#include "common/links-tpl.ftl" />
    <link type="text/css" rel="stylesheet" href="/css/account.css" />
    <style type="text/css">
        #realAuthForm input ,#realAuthForm select{
            width: 260px;
        }
        .idCardItem{
            width: 160px;
            height: 150px;
            float:left;
            margin-right:10px;
            border: 1px solid #e3e3e3;
            text-align: center;
            padding: 5px;
        }
        .uploadImg{
            width: 120px;
            height: 100px;
            margin-top: 5px;
        }
        .swfupload{
            left: 0px;
            cursor: pointer;
        }
        .uploadify{
            height: 20px;
            font-size:13px;
            line-height:20px;
            text-align:center;
            position: relative;
            margin-left:auto;
            margin-right:auto;
            cursor:pointer;
            background-color: #337ab7;
            border-color: #2e6da4;
            color: #fff;
        }
        #viewExample{
            position: relative;
            left: 50px;
            top: 60px;
        }
        .uploadExample{
            width: 200px;
            height: 130px;
            margin-bottom: 20px;
        }
    </style>
    <script type="text/javascript" src="/js/plugins/uploadify/jquery.uploadify.min.js"></script>
    <script type="text/javascript">

        $(function(){
            $("#viewExample").popover({
                html:true,
                content:'正面<img src="/images/upload_source_2.jpg" class="uploadExample"/><br/>反面<img src="/images/upload_source_2_1.jpg" class="uploadExample"/>',
                trigger:"hover",
                placement:"top"
            });

            //处理上传
            $("#uploadBtn1").uploadify({
                height:30,//按钮大小
                width:120,//按钮宽度
                buttonText:"上传正面",//按钮文字
                fileObjName:"file",//上传文件的名字(对应的MultipartFile的名字)
                multi:false,//是否允许多选
                fileTypeExts:'*.gif; *.jpg; *.png',//控制上传文件的后缀
                swf:"/js/plugins/uploadify/uploadify.swf",//falsh文件的地址
                uploader:"/realAuthUpload.do",//后台处理上传文件的地址;
                overrideEvents:['onUploadSuccess','onUploadProgress','onSelect'],
                onUploadSuccess:function(file,data){
                    $("#uploadImg1").attr("src",data);
                    $("#uploadImage1").val(data);
                }
            });

            $("#uploadBtn2").uploadify({
                height:30,//按钮大小
                width:120,//按钮宽度
                buttonText:"上传反面",//按钮文字
                fileObjName:"file",//上传文件的名字(对应的MultipartFile的名字)
                multi:false,//是否允许多选
                fileTypeExts:'*.gif; *.jpg; *.png',//控制上传文件的后缀
                swf:"/js/plugins/uploadify/uploadify.swf",//falsh文件的地址
                uploader:"/realAuthUpload.do",//后台处理上传文件的地址;
                overrideEvents:['onUploadSuccess','onUploadProgress','onSelect'],
                onUploadSuccess:function(file,data){
                    $("#uploadImg2").attr("src",data);
                    $("#uploadImage2").val(data);
                }
            });
        });
    </script>
</head>
<body>
<!-- 网页顶部导航 -->
		<#include "common/head-tpl.ftl"/>
		<#assign currentNav="borrow" />
		<#include "common/navbar-tpl.ftl" />

<div class="container">
    <div class="row">
        <!--导航菜单-->
        <div class="col-sm-3">
					<#assign currentMenu="realAuth"/>
					<#include "common/leftmenu-tpl.ftl" />
        </div>
        <!-- 功能页面 -->
        <div class="col-sm-9">
            <div class="panel panel-default">
                <div class="panel-heading">
                    借款申请
                </div>
                <form class="form-horizontal" id="realAuthForm" method="post" action="/realAuth_save.do" novalidate="novalidate">
                    <fieldset>
                        <div class="form-group">
                            <p class="text-center text-danger">信用标</p>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label ">用户名</label>
                            <div class="col-sm-8">
                                <p class="form-control-static">${logininfo.username }</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="realName">借款金额</label>
                            <div class="col-sm-8">
                                <input id="bidRequestAmount" name="bidRequestAmount" class="form-control" type="text" value="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="realName">借款利息</label>
                            <div class="col-sm-8">
                                <input id="currentRate" name="currentRate" class="form-control" type="text" value="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="realName">借款期限</label>
                            <div class="col-sm-8">
                                <input id="monthes2Return" name="monthes2Return" class="form-control" type="text" value="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4  control-label" for="sex">还款方式</label>
                            <div class="col-sm-8">
                                <select id="sex" class="form-control" name="sex" size="1">
                                    <option value="0">按月分期</option>
                                    <option value="1">按月到期</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4  control-label" for="idnumber">最小投标</label>
                            <div class="col-sm-8">
                                <input id="minBidAmount" class="form-control" name="minBidAmount"  type="text" value="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4  control-label" for="birthdate">招标天数</label>
                            <div class="col-sm-8">
                                <input id="disableDays"  class="form-control" name="disableDays" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4  control-label" for="address">借款标题</label>
                            <div class="col-sm-8">
                                <input id="title" class="form-control" name="title"  type="text" style="max-width: 100%;width:500px;">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4  control-label" for="address">借款描述</label>
                            <div class="col-sm-8">
                                <input id="description" class="form-control" name="description"  type="text" style="max-width: 100%;width:500px;">
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="submit" id="asubmit" class="btn btn-primary col-sm-offset-4" data-loading-text="正在提交"><i class="icon-ok"></i> 提交认证</button>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>
		<#include "common/footer-tpl.ftl" />
</body>
</html>