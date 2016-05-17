Ext.define('Leo.UserManagement', {
	extend : 'Ext.ux.desktop.Module',
	requires: [
		'Ext.data.ArrayStore',
		'Ext.util.Format',
		'Ext.grid.Panel',
		'Ext.TabPanel',
		'Ext.grid.RowNumberer'//,
	],
	id: 'userManagement',
	init : function(){
		this.launcher = {
			text: '权限管理',
			iconCls:'icon-grid'
		};
	},
	createWindow : function(){
		var me = this;
		var desktop = this.app.getDesktop();
		var win = desktop.getWindow(me.id);
		if(!win){
			win = desktop.createWindow({
				id: me.id,
				title: me.title,
//				width : 1000,
//				height : 580,
				maximized  : true,
				iconCls: me.launcher.iconCls,
				animCollapse:false,
				constrainHeader:true,
				layout: 'border',
				items: [Ext.create("Ext.tree.TreePanel",{
					title : '导航',
			        region:'west',
			        split:true,
			        width: "10%",
			        collapsible:true,
			        rootVisible : true,
			       root:{
			    	   cls : 'feeds-node',
						text : '权限管理',
						expanded : true,
						children:[{
							  text: "用户管理", 
							  iconCls:'button_tree',
							  id:"userManagementID",
							  leaf: true,
						},{
							text: "角色管理", 
							  iconCls:'button_tree',
							  id:"roleManagementID",
							  leaf: true,
						}]
			       },listeners:{
						  itemclick:function(view, record,item){
							  var newId = record.data.id;
							  var oldId =Ext.getCmp(newId+1);
							  if (oldId == undefined) {
								  if(newId=="userManagementID"){
									  Ext.getCmp('userManagementView').add(
											  Ext.create("Leo.UserManagementWindow", {
													id: "userManagementID1",
													title:"用户管理",
													baseUrl: path,
													itemsPerPage: 20,
													width: "90%",
													region: "center",
													closable:true
												})
									  );
									  Ext.getCmp('userManagementView').setActiveTab(Ext.getCmp("userManagementID1"));
								  }else if(newId=="roleManagementID"){
									  Ext.getCmp('userManagementView').add(
												 Ext.create("Leo.RoleManagementWindow", {
													id: "roleManagementID1",
													title:"角色管理",
													baseUrl: path,
													itemsPerPage: 20,
													width: "80%",
													region: "center",
													closable:true
											})
										  );
										  Ext.getCmp('userManagementView').setActiveTab(Ext.getCmp("roleManagementID1"));
								}
							 }else{
								 Ext.getCmp('userManagementView').setActiveTab(oldId);
							 }
						  }
					  }
			       }),Ext.create("Ext.TabPanel",{
						id:"userManagementView",
						width: "80%",
						region:'center',//中间面板
						activeTab: 0,
						enableTabScroll : true,
						autoScroll : true,						
			 			frame:true, 
			 			items:[{}]
			       })]
			});
		}
		return win;
	}
	
});
