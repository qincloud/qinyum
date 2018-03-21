<#include "/include/frame.ftl" /> <@frame title="用户添加">
<script type="text/javascript">
	$(function() {
		$('#userform').bootstrapValidator({
			message : '这个值没有验证',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				username : {
					message : '姓名不能为空',
					validators : {
						notEmpty : {
							message : '姓名不能为空'
						}
					}
				},
				logname : {
					message : '登录名不能为空',
					validators : {
						notEmpty : {
							message : '登录名不能为空'
						}
					}
				},
				password : {
					message : '密码不能为空',
					validators : {
						notEmpty : {
							message : '密码不能为空'
						}
					}
				},
				phone : {
					message : '电话号码不能为空',
					validators : {
						notEmpty : {
							message : '电话号码不能为空'
						}
					}
				},
				email : {
					message : '邮件不能为空',
					validators : {
						notEmpty : {
							message : '邮件不能为空'
						}
					}
				}
			}
		}).on('success.form.bv', function(e) {
			e.preventDefault();
			var $form = $(e.target);
			var bv = $form.data('bootstrapValidator');
			$.post($form.attr('action'), $form.serialize(), function(json) {
				if (json.status == '200') {
					alert(json.msg);
				} else {
					alert(json.msg);
				}
				location.href = '/system/user';
			}, 'json');
		});
	});
</script>
<div class="card">
	<div class="card-body" style="width: 38.2%;">
		<form id="userform" action="/system/user/saveorupdate">
			<div class="form-group">
				<label for="exampleInputEmail1">姓名</label> <input type="text"
					class="form-control" id="username" name="username"
					value="${(model.username)!}" placeholder=""><input
					type="hidden" id="id" name="id" value="${(model.id)!}" />
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">用户名</label> <input type="text"
					class="form-control" id="logname" name="logname"
					value="${(model.logname)!}" placeholder="">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">密码</label> <input type="password"
					class="form-control" id="pass" name="pass" value="${(model.pass)!}"
					placeholder="">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">电话</label> <input type="text"
					class="form-control" id="phone" name="phone"
					value="${(model.phone)!}" placeholder="">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">邮件</label> <input type="email"
					class="form-control" id="email" name="email"
					value="${(model.email)!}" placeholder="">
			</div>
			<div>
				<#if roles??> <#list roles as role>
				<div
					class="checkbox3 checkbox-success checkbox-inline checkbox-check checkbox-round  checkbox-light">
					<input type="checkbox" id="${(role.id)!}"> <label
						for="${(role.id)!}">${(role.name)!}</label>
				</div>
				</#list> </#if>
			</div>
			<div class="form-group">
				<div class="col-lg-9 col-lg-offset-3">
					<button type="submit" class="btn btn-info">保存</button>
					<a href="/system/user" class="btn btn-info">返回</a>
				</div>
			</div>
		</form>
	</div>
</div>
</@frame>