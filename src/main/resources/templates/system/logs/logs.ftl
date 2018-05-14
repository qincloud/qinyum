<#include "/include/frame.ftl" /> <@frame title="系统登陆日志">
<@sec.authorize access="hasAnyRole('ROLE_ADMIN')">
<div class="card">
	<div class="card-header">
		<div class="card-title">
			<form action="/system/menu">
				<div class="form-inline">
					<div class="form-group">
						<label for="exampleInputName2">登陆用户</label> <input type="text"
							class="form-control" id="loguser" name="loguser"
							value="${(model.loguser)!}" placeholder="登陆用户">
					</div>
					<div class="form-group">
						<label for="exampleInputEmail2">登陆ip地址</label> <input type="text"
							class="form-control" id="ipaddr" name="ipaddr"
							value="${(model.ipaddr)!}" placeholder="登陆ip地址">
					</div>
				</div>
				<div class="form-inline" style="text-align: right;">
					<button type="submit" class="btn btn-info">搜素</button>
				</div>
			</form>
		</div>
	</div>
	<div class="card-body">
		<table
			class="table table-striped table-bordered table-hover table-condensed"
			width="100%">
			<thead>
				<tr class="active">
					<td style="width: 5%"><input type="checkbox" class="checkall" /></td>
					<td>序号</td>
					<td>登陆用户</td>
					<td>登陆ip地址</td>
					<td>登陆时间</td>
				</tr>
			</thead>
			<tbody>
				<#list pageInfo.list as x>
				<tr>
					<td><input type="checkbox" class="check" id="${(x.id)!}" /></td>
					<td>${x_index+(pageInfo.startRow)!0}</td>
					<td>${(x.loguser)!}</td>
					<td>${(x.ipaddr)!}</td>
               <td>${(x.create_time())!}</td>
				</tr>
				</#list>
			</tbody>
			<tfooter>
			<tr>
				<td>
					<div style="text-align: left;">
				
					</div>
				</td>
				<td colspan="7">
					<ul class="pagination" url='/system/logs'
						style="text-align: right;"></ul>
				</td>
			</tr>
			</tfooter>
		</table>
	</div>
</div>
</@sec.authorize>
</@frame>
