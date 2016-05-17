Ext.define('Leo.UserManagementWindow', {
	extend : 'Ext.grid.Panel',
	requires: [
		'Ext.data.ArrayStore',
		'Ext.util.Format',
		'Ext.grid.Panel',
		'Ext.TabPanel',
		'Ext.grid.RowNumberer'
	],
	id: 'userManagementWindow',
	init : function(){},
	
	constructor: function(config) {
		config = config || {
		};
		Ext.apply(this, config);
		var me = this;
		this.initCmps(me);
		this.superclass.constructor.call(this, {
//			bbar : {
//				xtype : 'pagingtoolbar',
//				store : me.store,
//				displayInfo : true
//			},
//			viewConfig : {
//				enableTextSelection : true
//			}
		});
	},
	
	initCmps : function(me) {
		
		this.userStore = Ext.create('Ext.data.JsonStore',{
			clearOnPageLoad:true,
			fields:[{
				name:'sid',
				type:'int'
			},{
				name:'username',
				type:'string'
			},{
				name:'password',
				type:'string'
			}],
			pageSize : 20,
			storeId : 'userStore',
			proxy : {
				type : 'ajax',
				api : {
					read : path + '/userManagement/getUser.html',
				},
				idParam : 'sid',
				reader : {
					type : 'json',
					root : 'list'
				},
				writer : {
					type : 'json',
					encode : true,
					root : 'data',
					expandData : true
					
				}
			},
			listeners : {
				beforeload : function(store, operation, eOpts) {
//					var sid = Ext.getCmp("sid").getValue();
					var username =Ext.getCmp("username").getValue();
//					var password = Ext.getCmp("password").getValue();
//					me.userStore.getProxy().setExtraParam('sid', sid);
					me.userStore.getProxy().setExtraParam('username', username);
//					me.userStore.getProxy().setExtraParam('password', password);
				}
			}
		});
		
		this.columns = [{
			dataIndex : 'sid',
			hidden : true,
			text : 'sid',
			flex : 1
		},{
			dataIndex : 'username',
			text : '用户名',
			flex : 1
		},{
			dataIndex : 'password',
			hidden : true,
			text : '密码',
			flex : 1
		}];
		
		this.gridPanel = Ext.create('Ext.grid.Panel', {
			id : 'user',
			columns : this.columns,
			store : me.userStore,
			columnLines : true,
			tbar :this.tbar,
			sortableColumns : false
		});
		
		this.tbar =  [{
			id: "username",
			xtype: "textfield",
			fieldLabel:"用户名",
			labelWidth: 60,
			labelAlign: "right",
			width:160
		},{
			xtype : 'button',
			text : '查询',
			handler : function(button, e) {
				me.userStore.reload();
			}
		}];
		
		
		
		
	},
	
	
});
