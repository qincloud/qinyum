<#include "/include/frame.ftl" />
<@frame title="机构管理">
<div class="card">
	<div class="card-header">
		<div class="card-title">
			<form action="/system/org">
				<div class="form-inline">
					<div class="form-group">
						<label for="exampleInputName2">机构名称</label> <input type="text"
							class="form-control" id="mc" name="mc"
							value="${(model.mc)!}" placeholder="机构名称">
					</div>
					<div class="form-group">
						<label for="exampleInputEmail2">机构编码</label> <input type="text"
							class="form-control" id="bm" name="bm"
							value="${(model.bm)!}" placeholder="机构编码">
					</div>
				</div>
				<div class="form-inline" style="text-align: right;">
					<button type="submit" class="btn btn-info">搜素</button>
					<a href="#" class="btn btn-info">清空</a> <a class="btn btn-info"
						href="/system/org/add">添加机构</a>
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
					<td>机构名称</td>
					<td>机构编码</td>
					<td>父机构</td>
					<td>父机构编码</td>
					<td>创建时间</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<#list pageInfo.list as x>
				<tr>
					<td><input type="checkbox" class="check" id="${(x.id)!}" /></td>
					<td>${x_index+(pageInfo.startRow)!0}</td>
					<td>${(x.mc)!}</td>
					<td>${(x.bm)!}</td>
					<td>${(x.fmc)!}</td>
					<td>${(x.fbm)!}</td>
               <td>${(x.create_time())!}</td>
					<td><button href="#" onclick="edit('${(x.id)!}')"
							class="btn btn-success btn-sm">编辑</button>
						<button href="#" onclick="del('${(x.id)!}')"
							class="btn btn-danger btn-sm">删除</button></td>
				</tr>
				</#list>
			</tbody>
			<tfooter>
			<tr>
				<td>
					<div style="text-align: left;">
						<a href="#" class="btn btn-info">删除全部</a>
					</div>
				</td>
				<td colspan="7">
					<ul class="pagination" url='/system/org'
						style="text-align: right;"></ul>
				</td>
			</tr>
			</tfooter>
		</table>
	</div>
</div>
</@frame>