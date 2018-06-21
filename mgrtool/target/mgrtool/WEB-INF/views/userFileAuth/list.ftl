<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>蓝源Eloan-P2P平台(系统管理平台)</title> <#include "../common/header.ftl"/>
<script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
<script type="text/javascript"
	src="/js/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript"
	src="/js/plugins/jquery.twbsPagination.min.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
	$(function(){
		$("#beginDate,#endDate").click(function(){
			WdatePicker();
		});
		
		$.extend($.fn.twbsPagination.defaults, {
			first : "首页",
			prev : "上一页",
			next : "下一页",
			last : "最后一页"
		});

		$('#pagination').twbsPagination({
			totalPages : ${pageResult.totalPage}||1,
			startPage : ${pageResult.currentPage},
			visiblePages : 5,
			onPageClick : function(event, page) {
				$("#currentPage").val(page);
				$("#searchForm").submit();
			}
		});
		
		$("#query").click(function(){
			$("[name=currentPage]").val("1");
			$("#searchForm").submit();
		});
		$(".auditClass").click(function(){
		    $("#myModal").modal("show");
		    var data = $(this).data("json");
		    $("#myModal [name=id]").val(data.id);
		    $("#myModal [name=username]").val(data.username);
		    $("#myModal [name=fileType]").val(data.fileType);
		    $("#myModal [name=image]").attr("src",data.file);
		});
		
		$(".btn_audit").click(function(){
			var form=$("#editForm");
			form.find("[name=state]").val($(this).val());
			$("#myModal").modal("hide");
			form.ajaxSubmit(function(data){
				if(data.success){
					$.messager.confirm("提示","审核成功!",function(){
						window.location.reload();
					});
				}else{
					$.messager.alert("提示",data.msg);
				}
			});
			return false;
		})
		
	});
	</script>
</head>

<body>
	<div class="container">
		<#include "../common/top.ftl"/>
		<div class="row">
			<div class="col-sm-3"><#include "../common/menu.ftl" /></div>
			<div class="col-sm-9">
				<div class="page-header">
					<h3>用户征信文件审核管理</h3>
				</div>
				<div class="row">
					<!-- 提交分页的表单 -->
					<form id="searchForm" class="form-inline" method="post"
						action="/userFileAuth.do">
						<input type="hidden" name="currentPage" value="" />
						<div class="form-group">
							<label>状态</label> <select class="form-control" name="state">
								<option value="-1">全部</option>
								<option value="0">申请中</option>
								<option value="1">审核通过</option>
								<option value="2">审核拒绝</option>
							</select>
							<script type="text/javascript">
						    	$("[name=state] option[value='${(qo.state)!''}']").attr("selected","selected");
						    </script>
						</div>
						<div class="form-group">
							<label>申请时间</label> <input class="form-control"
								style="width: 130px;" type="text" name="beginDate"
								id="beginDate" value="${(qo.beginDate?string('yyyy-MM-dd'))!''}" />到
							<input class="form-control" style="width: 130px;" type="text"
								name="endDate" id="endDate"
								value="${(qo.endDate?string('yyyy-MM-dd'))!''}" />
						</div>
						<div class="form-group">
							<button id="query" class="btn btn-success">
								<i class="icon-search"></i> 查询
							</button>
						</div>
					</form>
				</div>
				<div class="row">
					<table class="table">
						<thead>
							<tr>
								<th>用户名</th>
								<th>状态</th>
								<th>分数</th>
								<th>资料类型</th>
								<th>审核人</th>
							</tr>
						</thead>
						<tbody>
							<#list pageResult.result as vo>
							<tr>
								<td>${vo.applier.username}</td>
								<td>${vo.stateDisplay}</td>
								<td>${(vo.score)!""}</td>
								<td>${(vo.fileType.title)!''}</td>
								<td>${(vo.auditor.username)!""}</td>
								<td>
									<#if (vo.state == 0)>
									    <a href="javascript:void(-1);" id="auditClass" class="auditClass" data-json='${vo.jsonString}'>审核</a>
									</#if>
								</td>
							</tr>
							</#list>
						</tbody>
					</table>

					<div style="text-align: center;">
						<ul id="pagination" class="pagination"></ul>
					</div>
				</div>

				<div id="myModal" class="modal fade" tabindex="-1" role="dialog">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title">材料认证</h4>
							</div>
							<div class="modal-body">
								<form id="editForm" class="form-horizontal" method="post"
									action="/userFile_audit.do">
									<input type="hidden" name="id" value="" />
									<input type="hidden" name="state" value="" />
									<div class="form-group">
										<label class="col-sm-2 control-label">用户</label>
										<div class="col-sm-6">
											<label class="form-control" name="username"></label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="name">资料类型</label>
										<div class="col-sm-6">
                                            <label class="form-control" name="fileType"></label>
										</div>
									</div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label" for="name">资料图片</label>
                                        <div class="col-sm-6">
											<img src="" name="image" alt="" style="width: 200px"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label" for="name">征信分数</label>
                                        <div class="col-sm-6">
                                           <select name="score" class="form-control">
											   <option value="1">1分</option>
											   <option value="2">2分</option>
											   <option value="3">3分</option>
											   <option value="4">4分</option>
											   <option value="5">5分</option>
										   </select>
                                        </div>
                                    </div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="name">审核备注</label>
										<div class="col-sm-6">
											<textarea rows="4" cols="60" name="remark"></textarea>
										</div>
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
								<button type="button" class="btn btn-success btn_audit"
									value="1">审核通过</button>
								<button type="button" class="btn btn-warning btn_audit"
									value="2">审核拒绝</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>