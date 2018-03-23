<#include "/include/frame.ftl" /> <@frame title="字典管理">
<div class="card">
	<div class="card-header">
		<div class="card-title">
			<form action="/system/dict">
				<div class="form-inline">
					<div class="form-group">
						<label for="exampleInputEmail2">字典名称</label> <input type="text"
							class="form-control" id="mc" name="mc"
							value="${(model.mc)!}" placeholder="字典名称">
					</div>
					<div class="form-group">
						<label for="exampleInputEmail2">字典编码</label> <input type="text"
							class="form-control" id="bm" name="bm"
							value="${(model.bm)!}" placeholder="字典编码">
					</div>
					<div class="form-group">
						<label for="exampleInputEmail2">字典类别</label> <input type="text"
							class="form-control" id="zdlb" name="zdlb"
							value="${(model.zdlb)!}" placeholder="字典类别">
					</div>
				</div>
				<div class="form-inline" style="text-align: right;">
					<button type="submit" class="btn btn-info">搜素</button>
					<a href="#" class="btn btn-info">清空</a> <a class="btn btn-info"
						href="/system/dict/add">添加字典</a>
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
					<td>字典名称</td>
					<td>字典编码</td>
					<td>字典类别</td>
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
					<td>${(x.zdlb)!}</td>
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
					<ul class="pagination" url='/system/dict' style="text-align: right;"></ul>
				</td>
			</tr>
			</tfooter>
		</table>
	</div>
</div>
</@frame>
