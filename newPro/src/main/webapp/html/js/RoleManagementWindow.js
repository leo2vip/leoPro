Ext.define('Leo.UserManagementWindow', {
	extend : 'Ext.grid.Panel',
	requires : [],
	id : null,
	sm :null,
	columns:null,
	tbar:null,
	gridPanel:null,
	init : function() {
	},
	constructor: function(config) {
		config = config || {
		};
		Ext.apply(this, config);
		var me = this;
		this.initCmps(me);
		this.superclass.constructor.call(this, {
			bbar : {
				xtype : 'pagingtoolbar',
				store : me.store,
				displayInfo : true
			},
			viewConfig : {
				enableTextSelection : true
			}
		});
	},
	initCmps : function(me) {
		this.sm = Ext.create('Ext.selection.CheckboxModel',{
			singleSelect : true
			});
		var checkbox = this.sm;
		this.store = Ext.create('Ext.data.JsonStore', {
			//autoLoad : false,
			clearOnPageLoad : true,
			fields : [ {
				name : 'sid',
				type : 'long'
			}],
			pageSize : 20,
			storeId : 'noSendCostSortStore',
			proxy : {
				type : 'ajax',
				api : {
					read : _appctx + '/admin/activity/list.json',
					update : _appctx + '/admin/activity/update.json'
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
					var query = Ext.getCmp("promotionName1").getValue();
					me.store.getProxy().setExtraParam('query', query);
				}
			}
		});
		this.rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
			clicksToEdit : 2,
			autoCancel : true
		});
		this.columns = [{
			dataIndex : 'sid',
//			hidden : true,
//			hideable : false
			text : 'sid',
			flex : 1
		},{
			dataIndex : 'deleteFlag',
			text : '是否有效',
			renderer:function(v){
				if(v =="0"){
					return "有效";
				}else if (v == "1"){
					return "待审核";
				}else if (v == "-1"){
					return "作废";
				}else if (v == "2"){
					return "进行中";
				}else if (v == "3"){
					return "已结束";
				}
			},
			flex : 1
		},{
			text:"操作",
			xtype:'actioncolumn',
			sortable: true,
			width:100,
			items :[{
				text:'生效',
				xtype : 'button',
				tooltip: '生效',
				icon:_appctx+'/images/icons/fam/accept.png',
				handler:function(grid, rowIndex, colIndex){
					//var allsid = checkbox.getSelection();
					var singleStore = grid.getStore().getAt(rowIndex);
					if(singleStore != null){
						Ext.MessageBox.confirm('Confirm', '你确定对'+singleStore.get('sid')+'进行此操作吗？',function (e){
							
							if('yes'==e){
								enforce(singleStore);
							}
							});
					}else{
						Ext.MessageBox.alert("提示","请选中想要修改的一条数据");
					}
					

				}
			},{
				xtype : 'button',
				width:20
			},{
				text:'作废',
				xtype : 'button',
				tooltip: '作废',
				icon:_appctx+'/images/icons/fam/delete.gif',
				handler:function(grid, rowIndex, colIndex){
					//var allsid = checkbox.getSelection();
					var singleStore = grid.getStore().getAt(rowIndex);
					if(singleStore != null){
						Ext.MessageBox.confirm('Confirm','你确定对'+singleStore.get('sid')+'进行此操作吗？',function (e){
							if('yes'==e){
								invalidate(singleStore);
						}
							
						})
					}else{
						Ext.MessageBox.alert("提示","请选中想要修改的一条数据");
					}
					
				}
			},{
				xtype : 'button',
				width:20
			},{
				text:'审核',
				xtype : 'button',
				tooltip: '审核',
				icon:_appctx+'/images/icons/fam/user.png',
				handler:function(grid, rowIndex, colIndex){
					//var allsid = checkbox.getSelection();
					var singleStore = grid.getStore().getAt(rowIndex);
					if(singleStore != null){
						Ext.MessageBox.confirm('Confirm', '你确定对'+singleStore.get('sid')+'进行审核操作吗？',function (e){
							if('yes'==e){
								auditFunction(singleStore);
							}
							});
					}else{
						Ext.MessageBox.alert("提示","请选中想要修改的一条数据");
					}
					

				}
			}]	
		}];
		this.tbar =  [{
				id: "promotionName1",
				xtype: "textfield",
				fieldLabel:"活动名称",
				labelWidth: 60,
				labelAlign: "right",
				width:160
		},{
				id: "operatorName1",
				xtype: "textfield",
				fieldLabel:"操作人",
				labelWidth: 60,
				labelAlign: "right",
				width:160
		},{
			xtype : 'button',
			text : '查询',
			handler : function(button, e) {
				me.store.reload();
			}
		},{
			xtype : 'button',
			text : '置空',
			handler : function(button, e) {
				Ext.getCmp("promotionName1").setValue(null);
				Ext.getCmp("operatorName1").setValue(null);
			}
		}]
		this.gridPanel = Ext.create('Ext.grid.Panel', {
			id : 'webActivityNoSendCostGrid',
			columns : this.columns,
			store : me.store,
			columnLines : true,
			//selModel: this.sm,
			tbar :this.tbar,
			
			//plugins : [ me.rowEditing ],
			sortableColumns : false,
			listeners : {
				edit : function(editor, context, eOpts) {
					me.store.sync({
						success : function(batch, options) {
							me.store.reload();
						},
						failure : function(batch, options) {
							if (options.operations.update) {
								Ext.Msg.alert("操作失败", "更新活动失败");
							}
							me.store.loadPage(1);
						}
					});
				},
				canceledit : function(editor, context, eOpts) {
					me.store.reload();
				},
				itemdblclick:function(view, record,item){
					//alert(record.data.sid);
					Ext.create('ShopinDesktop.WebActivitySelectImportExcelDetailWindow',{
						sid:record.data.sid,
//						activityName:activityName,
//						campaignStartDate : campaignStartDate,
//						campaignEndDate : campaignEndDate,
//						reduceMoney:reduceMoney,
//						goodsMoneyMin :goodsMoneyMin
				}).show();
				}
//				dblclick: {
//     				 element: 'body',
//            		 fn: function(){
//						var allsid = checkbox.getSelection();
//			
//						var sid = allsid[0].get('sid');
//						var activityName = allsid[0].get('name');
//						var campaignStartDate = allsid[0].get('campaignStartDate');
//						var campaignEndDate = allsid[0].get('campaignEndDate');
//						var goodsMoneyMin = allsid[0].get('goodsMoneyMin');
//						Ext.create('ShopinDesktop.WebActivityDetilWindow',{
//								sid:sid,
//								activityName:activityName,
//								campaignStartDate : campaignStartDate,
//								campaignEndDate : campaignEndDate,
//								goodsMoneyMin :goodsMoneyMin
//						}).show();
//
//            		 }
//        		}
			}
		});
		function enforce(singleStore){
				//var allsid = checkbox.getSelection();
						if(singleStore != null){
							var sid = singleStore.get('sid');
							var deleteFlag = singleStore.get('deleteFlag');
							var auditStats = singleStore.get('audit');
							if(deleteFlag==0){
								Ext.Msg.alert("提示", "此活动已生效，不允许再次生效！");
							}else if(auditStats!="2"){
								Ext.Msg.alert("提示", "只有审核状态为审核通过才可以生效！");
							}else{
								Ext.Ajax.request({
									url:_appctx + "/admin/localStore/updatePromotionFlagStatus.json",
									method:"post",
									wait:'Saving Data...',
									params:{
										sid : sid,
										deleteFlag : 0
									},
									success:function(response){
										
										Ext.MessageBox.alert("提示",response.responseText);
										me.store.load();
									},
									failure:function(response){
										Ext.MessageBox.alert("提示",'访问服务器失败');
									}
									});
							}
						}else{
							Ext.MessageBox.alert("提示","请选中想要修改的一条数据");
						}

		};
		function invalidate (singleStore){
					//var allsid = checkbox.getSelection();
						if(singleStore!= null ){
							var sid = singleStore.get('sid');
							var deleteFlag = singleStore.get('deleteFlag');
							if(deleteFlag==-1){
								Ext.Msg.alert("提示", "此活动已作废，不允许再次作废！");
							}else{
								Ext.Ajax.request({
									url:_appctx + "/admin/localStore/updatePromotionFlagStatus.json",
									method:"post",
									wait:'Saving Data...',
									params:{
										sid : sid,
										deleteFlag : -1
									},
									success:function(response){
										
										Ext.MessageBox.alert("提示",response.responseText);
										me.store.load();
									},
									failure:function(response){
										Ext.MessageBox.alert("提示",'访问服务器失败');
									}
									});
							}
							
						}else{
							Ext.MessageBox.alert("提示","请选中想要修改的一条数据");
						}
		};
		function deleteRecord(singleStore){
			//var allsid = checkbox.getSelection();
					if(singleStore!= null){
						var sid = singleStore.get('sid');
						var deleteFlag = singleStore.get('deleteFlag');
						Ext.Ajax.request({
							url:_appctx + "/admin/localStore/updatePromotionFlagStatus.json",
							method:"post",
							wait:'Saving Data...',
							params:{
								sid : sid,
								deleteFlag : -2
							},
							success:function(response){
								
								Ext.MessageBox.alert("提示",response.responseText);
								me.store.load();
							},
							failure:function(response){
								Ext.MessageBox.alert("提示",'访问服务器失败');
							}
							});
					}else{
						Ext.MessageBox.alert("提示","请选中想要删除的一条数据");
					}

	};
	function auditFunction(singleStore){
		var sid = singleStore.get('sid');
		var deleteFlag = singleStore.get('deleteFlag');
		var auditStats = singleStore.get('audit');
		if(auditStats=="2"){
			Ext.MessageBox.alert("提示","审核成功不能再一次审核！");
		}else{
			Ext.Ajax.request({
				url:_appctx + "/admin/localStore/updateDeleteFlagStatus.json",
				method:"post",
				wait:'Saving Data...',
				params:{
					sid : sid,
					deleteFlag : 2
				},
				success:function(response){
					
					Ext.MessageBox.alert("提示",response.responseText);
					me.store.load();
				},
				failure:function(response){
					Ext.MessageBox.alert("提示",'访问服务器失败');
				}
				});
		}
	};
	function importExcel(singleStore){
		//var allsid = checkbox.getSelection();
		var sid = singleStore.get('sid');
		var deleteFlag = singleStore.get('deleteFlag');
		var addWin = new Ext.Window({
			title:'文件导入',
			width:350,  
			height:200,
			layout:'fit',
			closable:false,
			
			items:[{
					xtype:'form',
					fileUpload:true,
					id:'formID',
	                width:250,
	                frame:true,
	                bodyStyle:'padding: 10px 10px 0 10px',
	                defaults: {
	                    anchor: '95%',
	                    allowBlank: false,
	                    msgTarget: 'side'
	                          },
			      items:[
			    	  {  
			    		    xtype: 'filefield',  
				            name: 'newadvertadress', 
				            fieldLabel: '选择文件',  
				            enctype:'multipart/form-data',
				            labelWidth: 80,  
				            msgTarget: 'side',  
				            allowBlank: false,  
				            anchor: '100%',  
				            buttonText: '浏览' 
			        }],
				 buttons:[
					 {
					   text:'保存',
					   handler:function(){
						  Ext.getCmp('formID').getForm().submit({
						      url : _appctx + '/admin/importExcelForWeb/excelImport.json',
						      params: {
						    	  sid: sid
							    },
						      method:"POST", 
						      waitMsg:'file upload...',
						      success:function(response,result){
								     var result = Ext.decode(result.response.responseText);
								     if(result.success=="true"){
								    	 excelStr = result.memo;
								       Ext.Msg.alert('提示',excelStr);
								     //  Ext.getCmp("webActivityNoSendCostGrid").getStore().reload();
								     }else{
								     	Ext.Msg.alert('提示',result.memo);
								     	
								     }
								     addWin.close();
							         
							   },
					          failure:function(){
								     Ext.Msg.alert('','导入失败!');
						       }
		                   });
					  }
					},{
				       text:'取消',
				       handler:function(){
					   addWin.close();
					   }
					}]
			}]
			});
			Ext.getCmp(me.id).add(addWin);
			addWin.show();

};
	this.store.loadPage(1);
	},
	//lllllllllllllll

});
