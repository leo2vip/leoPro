/*!
 * Ext JS Library 4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */
Ext.define('MyDesktop.App', {
    extend: 'Ext.ux.desktop.App',

    requires: [
        'MyDesktop.Notepad',
        //'MyDesktop.SystemStatus',
        //'MyDesktop.VideoWindow',
        //'MyDesktop.GridWindow',
        //'MyDesktop.TabWindow',
        //'MyDesktop.AccordionWindow',
        //'MyDesktop.BogusMenuModule',
        //'MyDesktop.BogusModule'
        
    ],

    init: function() {
        this.callParent();
    },
    //开始菜单左边选框
    getModules : function(){
    	return this.modules;
        //return [
        //    new MyDesktop.Notepad(),
            //new MyDesktop.VideoWindow(),
            //new MyDesktop.SystemStatus(),
            //new MyDesktop.GridWindow(),
            //new MyDesktop.TabWindow(),
            //new MyDesktop.AccordionWindow(),
            //new MyDesktop.BogusMenuModule(),
            //new MyDesktop.BogusModule()
        //];
    },

    getDesktopConfig: function () {
        var me = this, ret = me.callParent();
        //spring security动态获取
        var shotcutIcons = [];
        for (i in this.getModules()) {
			var module = this.getModules()[i];
			shotcutIcons.push({
				name : module.launcher.text,
				iconCls : module.iconCls,
				module : module.id
			});
		}
        
        return Ext.apply(ret, {
            contextMenuItems: [
                { text: '设置', handler: me.onSettings, scope: me }
            ],

            shortcuts: Ext.create('Ext.data.Store', {
                model: 'Ext.ux.desktop.ShortcutModel',
                data: shotcutIcons
                //data: [
                    //{ name: 'Grid Window', iconCls: 'grid-shortcut', module: 'grid-win' },
                    //{ name: 'Accordion Window', iconCls: 'accordion-shortcut', module: 'acc-win' },
                    //{ name: 'Notepad', iconCls: 'notepad-shortcut', module: 'notepad' },
                    //{ name: 'System Status', iconCls: 'cpu-shortcut', module: 'systemstatus'}
                //]
            }),

            wallpaper: path+'/html/mydesktop/wallpapers/Wood-Sencha.jpg',
            wallpaperStretch: false
        });
    },

    // 页面开始菜单
    getStartConfig : function() {
        var me = this, ret = me.callParent();

        return Ext.apply(ret, {
            title: username,
            iconCls: 'user',
            height: 300,
            toolConfig: {
                width: 100,
                items: [
                    {
                        text:'设置',
                        iconCls:'settings',
                        handler: me.onSettings,
                        scope: me
                    },
                    '-',
                    {
                        text:'退出',
                        iconCls:'logout',
                        handler: me.onLogout,
                        scope: me
                    }
                ]
            }
        });
    },
    //任务栏快速启动
    getTaskbarConfig: function () {
        var ret = this.callParent();

        return Ext.apply(ret, {
            quickStart: [
                { name: 'Accordion Window', iconCls: 'accordion', module: 'acc-win' },
                { name: 'Grid Window', iconCls: 'icon-grid', module: 'grid-win' }
            ],
            trayItems: [
                { xtype: 'trayclock', flex: 1 }
            ]
        });
    },

    onLogout: function () {
        Ext.Msg.confirm('退出', '确定要退出本系统?', function(buttonId) {
			if (buttonId == 'ok' || buttonId == 'yes') {
				window.location = path + "/logout.html";
			}
		});
    },

    onSettings: function () {
        var dlg = new MyDesktop.Settings({
            desktop: this.desktop
        });
        dlg.show();
    }
});
