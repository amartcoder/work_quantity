$(function(){

    var sliderObj = $("#slider");
    var sliders = $(sliderObj).find("li");
    $(sliderObj).empty();
    $(sliders).each(function(index){
        $(sliderObj).append(sliders.eq($(sliderObj).length - index));
    });
    var btnTar = $("#slider_btns");
    $(btnTar).createNode("li",$(sliders).length,{
        "class":"floatL"
    });
    $.classOperate($(btnTar).find("li"),0,"cur");
    var sliderIndex = 0;
    var tSlider = null;
    var titleTarget = $("#sslider_text");
    $(sliders).eq(0).find("a").clone(true).appendTo($(titleTarget));
    $(sliders).eq(0).find("p").clone(true).appendTo($(titleTarget));
    tSlider = setInterval(function(){
        sliderIndex = parseInt(sliderIndex + 1) === $(sliders).length ? 0 : parseInt(sliderIndex + 1);
        $.picFade($(sliders),sliderIndex,"slow",function(){
            $.classOperate($(btnTar).find("li"),sliderIndex,"cur");
            $(titleTarget).empty();
            $(sliders).eq(sliderIndex).find("a").clone(true).appendTo($(titleTarget));
            $(sliders).eq(sliderIndex).find("p").clone(true).appendTo($(titleTarget));
        });
    },8000);

    $(btnTar).find("li").each(function(){
        $(this).hover(function(){
            clearInterval(tSlider);
            $.picFade($(sliders),$(this).index(),"slow");
                $.classOperate($(btnTar).find("li"),$(this).index(),"cur");
                $(titleTarget).empty();
                $(sliders).eq($(this).index()).find("a").clone(true).appendTo($(titleTarget));
                $(sliders).eq($(this).index()).find("p").clone(true).appendTo($(titleTarget));
        },function(){
            sliderIndex = $("#slider_btns li.cur").index();
            tSlider = setInterval(function(){
                sliderIndex = parseInt(sliderIndex + 1) === $(sliders).length ? 0 : parseInt(sliderIndex + 1);
                $.picFade($(sliders),sliderIndex,"slow",function(){
                    $.classOperate($(btnTar).find("li"),sliderIndex,"cur");
                    $(titleTarget).empty();
                    $(sliders).eq(sliderIndex).find("a").clone(true).appendTo($(titleTarget));
                    $(sliders).eq(sliderIndex).find("p").clone(true).appendTo($(titleTarget));
                });
            },4000);
        });
    })
});

$.fn.extend({
    createNode:function(nodeName,times,attrs,text){
        var node = null;
        if((/(meta,link,img,br,hr,input,param,embed)/).test(nodeName) == true){
            for(var time = 0;time < times;time++){
                node = $("<"+ nodeName +"/>");
                $(node).attr(attrs);
                for(var item in attrs){
                    if(item == "id"){
                        $(node).attr("id",attrs.id + time);
                    }
                }
                if(text != undefined){
                    $(node).text(text);
                }
                $(this).append(node);
            }
        }else{
            for(var time = 0;time < times;time++){
                node = $("<"+ nodeName +"></" + nodeName + ">");
                $(node).attr(attrs);
                for(var item in attrs){
                    if(item == "id"){
                        $(node).attr("id",attrs.id + time);
                    }
                }
                if(text != undefined){
                    $(node).text(text);
                }
                $(this).append(node);
            }
        }
    }
});

$.fn.extend({
    sFadeIn:function(speed,callback){
        return this.animate({
            opacity:"show"
        },speed,function(){
            if($.isFunction(callback)){
                callback();
            }
        });
    }
});

$.fn.extend({
    sFadeOut:function(speed,callback){
        return this.animate({
            opacity:"hide"
        },speed,function(){
            if($.isFunction(callback)){
                callback();
            }
        })
    }
});

$.fn.extend({
    sFadeTo:function(tagrget,speed,callback){
        return this.animate({
            opacity:tagrget
        },speed,function(){
            if($.isFunction(callback)){
                callback();
            }
        })
    }
});

$.extend({
    classOperate:function(obj,index,className){
        $(obj).eq(index).removeClass(className)
            .addClass(className)
            .siblings()
            .removeClass(className);
    }
});

$.extend({
    picFade:function(obj,index,speed,callback){
        $(obj).eq(index).siblings()
            .sFadeOut(speed);
        $(obj).eq(index).sFadeIn(speed);
        if(callback && $.isFunction(callback)){
            callback();
        }else{}
    }
});

function query() {
	var xm = $("#xm").val();
	var sfzh = $("#sfzh").val();
	if (xm == '') {
		alert("请输入教师姓名！");
		return;
	}
	if (sfzh == '') {
		alert("请输入教师身份证号！");
		return;
	}
	$("form").submit();
}

function preview(){
	bdhtml=window.document.body.innerHTML;  //定义打印内容
	sprnstr="<!--startprint-->";  //打印开始位置符合
	eprnstr="<!--endprint-->";  //打印结束位置符合
	prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);
	prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
	window.document.body.innerHTML=prnhtml;
	window.print();
	window.history.back(); 
}