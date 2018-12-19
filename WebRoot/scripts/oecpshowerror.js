/*
 * 基于 jQuery validation plug-in 1.6 校验框架的自定义显示错误
 *
 * slx 2010-5-19
 * 注意:
 * 	使用时需要修改 使用的img 图片路径 （搜索一下img根据自己存放的位置改一下吧）
 */



function showErrors(){
		var t = this;
		for ( var i = 0; this.errorList[i]; i++ ) {
			var error = this.errorList[i];
			this.settings.highlight && this.settings.highlight.call( this, error.element, this.settings.errorClass, this.settings.validClass );
			
			var elename = this.idOrName(error.element);
			// 错误信息div
			var errdiv = $('div[htmlfor='+ elename + ']');
			var errimg = $('img[htmlfor='+ elename + ']');
			if(errdiv.length == 0){ // 没有div则创建
				
				// 纯css不用图片的圆角div，存在在IE6下显示过长的问题
//				errdiv = $('<div>' 
//				+	'<b class="rtop"><b class="r1"></b><b class="r2"></b><b class="r3"></b><b class="r4"></b></b>'
//				+	'<span class="errmsg"> </span>'
//				+	'<b class="rbottom"><b class="r4"></b><b class="r3"></b><b class="r2"></b><b class="r1"></b></b> '
//				+	'</div> ');
				
				// 带图片的圆角div在所有浏览器兼容
				errdiv = $("<div><img src='"+path+"/images/left_icon.gif' width='6' height='24' align='absmiddle' class='fl' /><div class='errmsgdiv fl errmsg'>" +
				"</div><img src='"+path+"/images/right_icon.gif' width='6' height='24' align='absmiddle' class='fl' /></div>");
				
				
				errdiv.attr({"for":  this.idOrName(error.element), generated: true})
				.addClass(this.settings.errorClass);
//				errdiv.css({left : $.getLeft(error.element) + 'px',top : $.getTop(error.element) + 'px'}); // 显示在控件的下面
				errdiv.appendTo($('body'));
			}
			if(errimg.length == 0){ // 没有img则创建
				errimg = $("<img alt='错误' src='"+path+"/images/unchecked.gif' width='14' height='14'  >")
				errimg.attr({"for":  this.idOrName(error.element), generated: true});
				if ($(error.element).attr("name") == "pm.country"|| $(error.element).attr("name") == "pm.state" || $(error.element).attr("name") == "pm.city"){
					errimg.insertAfter("#city");
				}else{
					errimg.insertAfter(error.element);
				}
				
				//errimg.insertAfter(error.element);
			}
			errimg.show();
			errdiv.find(".errmsg").html(error.message || "");
			
			// 鼠标放到图片显示错误
			$(errimg).hover(function(e){
				$('div[htmlfor="'+ $(this).attr('htmlfor') + '"]').css({left : (e.pageX+10) + 'px',top : (e.pageY+10) + 'px'}); // 显示在鼠标位置偏移10的位置
				$('div[htmlfor="'+ $(this).attr('htmlfor') + '"]').fadeIn(200);
			},
			function(){
				$('div[htmlfor="'+ $(this).attr('htmlfor') + '"]').fadeOut(200);
			});
			// 鼠标放到控件上显示错误
			$(error.element).hover(function(e){
				$('div[htmlfor="'+ t.idOrName(this) + '"]').css({left : (e.pageX+10) + 'px',top : (e.pageY+10) + 'px'}); // 显示在鼠标位置偏移20的位置
				$('div[htmlfor="'+ t.idOrName(this) + '"]').fadeIn(200);
			},
			function(){
				$('div[htmlfor="'+ t.idOrName(this) + '"]').fadeOut(200);
			});
		}
		
		// 校验成功的去掉错误提示
		for ( var i = 0; this.successList[i]; i++ ) {
				$('div[htmlfor="'+ this.idOrName(this.successList[i]) + '"]').remove();
				$('img[htmlfor='+ this.idOrName(this.successList[i]) + ']').hide();
		}
		
		// 自定义高亮
		if (this.settings.unhighlight) {
			for ( var i = 0, elements = this.validElements(); elements[i]; i++ ) {
				this.settings.unhighlight.call( this, elements[i], this.settings.errorClass, this.settings.validClass );
			}
		}
	}
$.extend({
		getLeft : function(object) {
			var go = object;
			var oParent, oLeft = go.offsetLeft;
			while (go.offsetParent != null) {
				oParent = go.offsetParent;
				oLeft += oParent.offsetLeft;
				go = oParent;
			}
			return oLeft;
		},
		getTop : function(object) {
			var go = object;
			var goHeight = go.height;
			var oParent, oTop = go.offsetTop;
			while (go.offsetParent != null) {
				oParent = go.offsetParent;
				oTop += oParent.offsetTop;
				go = oParent;
			}
			return oTop + 22;// 之所以加22不加控件高度,为了兼容ie6.
		}
});


function showError(){
		var t = this;
		for ( var i = 0; this.errorList[i]; i++ ) {
			var error = this.errorList[i];
			this.settings.highlight && this.settings.highlight.call( this, error.element, this.settings.errorClass, this.settings.validClass );
			
			var elename = this.idOrName(error.element);
			// 错误信息div
			var errdiv = $('div[htmlfor='+ elename + ']');
			var errimg = $('img[htmlfor='+ elename + ']');
			if(errdiv.length == 0){ // 没有div则创建
				
				// 纯css不用图片的圆角div，存在在IE6下显示过长的问题
//				errdiv = $('<div>' 
//				+	'<b class="rtop"><b class="r1"></b><b class="r2"></b><b class="r3"></b><b class="r4"></b></b>'
//				+	'<span class="errmsg"> </span>'
//				+	'<b class="rbottom"><b class="r4"></b><b class="r3"></b><b class="r2"></b><b class="r1"></b></b> '
//				+	'</div> ');
				
				// 带图片的圆角div在所有浏览器兼容
				errdiv = $("<div><img src='"+path+"/images/left_icon.gif' width='6' height='24' align='absmiddle' class='fl' /><div class='errmsgdiv fl errmsg'>" +
				"</div><img src='"+path+"/images/right_icon.gif' width='6' height='24'  /></div>");
				
				
				errdiv.attr({"for":  this.idOrName(error.element), generated: true})
				.addClass(this.settings.errorClass);
//				errdiv.css({left : $.getLeft(error.element) + 'px',top : $.getTop(error.element) + 'px'}); // 显示在控件的下面
			}
			if(errimg.length == 0){ // 没有img则创建
				errimg = $("<img alt='错误' src='"+path+"/images/unchecked.gif' width='14' height='14'  >")
				errimg.attr({"for":  this.idOrName(error.element), generated: true});
				errimg.insertAfter(error.element);
			}
			errimg.show();
			errdiv.find(".errmsg").html(error.message || "");
			
			// 鼠标放到图片显示错误
			$(errimg).hover(function(e){
				$('div[htmlfor="'+ $(this).attr('htmlfor') + '"]').css({left : (e.pageX+10) + 'px',top : (e.pageY+10) + 'px'}); // 显示在鼠标位置偏移10的位置
				$('div[htmlfor="'+ $(this).attr('htmlfor') + '"]').fadeIn(100);
			},
			function(){
				$('div[htmlfor="'+ $(this).attr('htmlfor') + '"]').fadeOut(100);
			});
			// 鼠标放到控件上显示错误
			$(error.element).hover(function(e){
				$('div[htmlfor="'+ t.idOrName(this) + '"]').css({left : (e.pageX+10) + 'px',top : (e.pageY+10) + 'px'}); // 显示在鼠标位置偏移20的位置
				$('div[htmlfor="'+ t.idOrName(this) + '"]').fadeIn(100);
			},
			function(){
				$('div[htmlfor="'+ t.idOrName(this) + '"]').fadeOut(100);
			});
		}
		
		// 校验成功的去掉错误提示
		for ( var i = 0; this.successList[i]; i++ ) {
				$('div[htmlfor="'+ this.idOrName(this.successList[i]) + '"]').remove();
				$('img[htmlfor='+ this.idOrName(this.successList[i]) + ']').hide();
		}
		
		// 自定义高亮
		if (this.settings.unhighlight) {
			for ( var i = 0, elements = this.validElements(); elements[i]; i++ ) {
				this.settings.unhighlight.call( this, elements[i], this.settings.errorClass, this.settings.validClass );
			}
		}
	}