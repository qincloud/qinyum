<#include "/include/frame.ftl" /> <@frame title="角色添加">
<script>
	
</script>
<div class="card">
	<div class="card-body" style="float: left; width: 61.8%;">
		<form id="roleform" action="/system/role/saveorupdate">
			<div class="form-group">
				<label for="exampleInputEmail1">角色名</label> <input type="text"
					class="form-control" id="name" name="name" value="${(model.name)!}"
					placeholder=""><input type="hidden" id="id" name="id"
					value="${(model.id)!}" />
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">角色英文名</label> <input type="text"
					class="form-control" id="ename" name="ename"
					value="${(model.ename)!}" placeholder=""><input
					type="hidden" id="ids" name="ids" />
			</div>
			<div id="treeview-checkable" class=""></div>
			<div class="form-group">
				<div class="col-lg-9 col-lg-offset-3">
					<button type="submit" class="btn btn-info">保存</button>
					<a href="/system/role" class="btn btn-info">返回</a>
				</div>
			</div>
		</form>
	</div>
</div>
<script>
	$(function() {
		$('#roleform').bootstrapValidator({
			message : '这个值没有验证',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				name : {
					message : '角色名没有验证',
					validators : {
						notEmpty : {
							message : '角色名不能为空'
						}
					}
				},
				ename : {
					message : '角色英文名没有验证',
					validators : {
						notEmpty : {
							message : '角色英文名不能为空'
						}
					}
				}
			}
		})
				.on(
						'success.form.bv',
						function(e) {
							checknodes($('#treeview-checkable').treeview(
									'getChecked')[0].nodes);
							$('#ids').val(result);
							e.preventDefault();
							var $form = $(e.target);
							var bv = $form.data('bootstrapValidator');
							$.post($form.attr('action'), $form.serialize(),
									function(json) {
										if (json.status == '200') {
											alert(json.msg);
										} else {
											alert(json.msg);
										}
										location.href = '/system/role';
									}, 'json');
						});
	});

	var result = "";
	function test() {
		checknodes($('#treeview-checkable').treeview('getChecked')[0].nodes);
	}

	function checknodes(node) {
		if (typeof (node) != "undefined") {
			for (var i = 0; i < node.length; i++) {
				if (typeof (node[i].state.checked) != "undefined"
						|| node[i].state.checked) {
					result += node[i].href + ",";
				}
				checknodes(node[i]);
			}
		}
	}

	//选中父节点时，选中所有子节点
	function getChildNodeIdArr(node) {
		var ts = [];
		if (node.nodes) {
			for (x in node.nodes) {
				ts.push(node.nodes[x].nodeId);
				if (node.nodes[x].nodes) {
					var getNodeDieDai = getChildNodeIdArr(node.nodes[x]);
					for (j in getNodeDieDai) {
						ts.push(getNodeDieDai[j]);
					}
				}
			}
		} else {
			ts.push(node.nodeId);
		}
		return ts;
	}

	// 选中所有子节点时，选中父节点 取消子节点时取消父节点
	function setParentNodeCheck(node) {
		var parentNode = $("#treeview-checkable").treeview("getNode",
				node.parentId);
		if (parentNode.nodes) {
			var checkedCount = 0;
			for (x in parentNode.nodes) {
				if (parentNode.nodes[x].state.checked) {
					checkedCount++;
				} else {
					break;
				}
			}
			if (checkedCount == parentNode.nodes.length) { //如果子节点全部被选 父全选
				$("#treeview-checkable").treeview("checkNode",
						parentNode.nodeId);
				setParentNodeCheck(parentNode);
			} else { //如果子节点未全部被选 父未全选
				$('#treeview-checkable').treeview('uncheckNode',
						parentNode.nodeId);
				setParentNodeCheck(parentNode);
			}
		}
	}

	// 取消父节点时 取消所有子节点
	function setChildNodeUncheck(node) {
		if (node.nodes) {
			var ts = []; //当前节点子集中未被选中的集合 
			for (x in node.nodes) {
				if (!node.nodes[x].state.checked) {
					ts.push(node.nodes[x].nodeId);
				}
				if (node.nodes[x].nodes) {
					var getNodeDieDai = node.nodes[x];
					console.log(getNodeDieDai);
					for (j in getNodeDieDai) {
						if (!getNodeDieDai.nodes[x].state.checked) {
							ts.push(getNodeDieDai[j]);
						}
					}
				}
			}
		}
		return ts;
	}

	var $checkableTree = null;
	$(function() {
		$.get('/system/role/nodes', function(json) {
			var defaultData = [ {
				text : '顶级菜单',
				href : '#parent1',
				selectable : true,
				state : {
					checked : true,
					disabled : false,
					expanded : true,
					selected : false
				},
				nodes : json.data
			} ]

			$checkableTree = $('#treeview-checkable').treeview(
					{
						data : defaultData,
						showIcon : false,
						showCheckbox : true,
						onNodeChecked : function(event, node) { //选中节点 
							var selectNodes = getChildNodeIdArr(node); //获取所有子节点      
							if (selectNodes) { //子节点不为空，则选中所有子节点       
								$('#treeview-checkable').treeview('checkNode',
										[ selectNodes, {
											silent : true
										} ]);
							}
							var parentNode = $("#treeview-checkable").treeview(
									"getNode", node.parentId);
							//setParentNodeCheck(node);
						},
						onNodeUnchecked : function(event, node) { //取消选中节点  
							// 取消父节点 子节点取消
							var selectNodes = setChildNodeUncheck(node); //获取未被选中的子节点 
							var childNodes = getChildNodeIdArr(node); //获取所有子节点 
							if (selectNodes && selectNodes.length == 0) { //有子节点且未被选中的子节点数目为0，则取消选中所有子节点   
								$('#treeview-checkable').treeview(
										'uncheckNode', [ childNodes, {
											silent : true
										} ]);
							}
							// 取消节点 父节点取消
							//var parentNode = $("#treeview-checkable").treeview(
							//		"getNode", node.parentId);
							//获取父节点
							//var selectNodes = getChildNodeIdArr(node);
							//setParentNodeCheck(node);
						}
					});
		});
	})
</script>
</@frame>
