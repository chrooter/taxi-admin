/* -------------------------------------

	cusel-multiple version 0.9
	смена селект muliple на стильный
	autor: Evgen Ryzhkov
	www.xiper.net
----------------------------------------	
*/
function cuSelMulti(params){jQuery(document).ready(function(){jQuery(params.changedEl).each(function(num)
{var chEl=jQuery(this),chElClass=chEl.attr("class"),chElId=chEl.attr("id"),chElName=chEl.attr("name"),disabledSel=chEl.attr("disabled"),scrollArrows=params.scrollArrows,chElOnChange=chEl.attr("onchange"),chElTab=chEl.attr("tabindex"),chElMultiple=chEl.attr("multiple"),selectedOpt=chEl.find("option[selected]"),firstOpt=chEl.find("option").eq(0);if(!chElId)return false;if(!disabledSel)
{classDisCuselText="",classDisCusel="";}
else
{classDisCuselText="classDisCuselLabel";classDisCusel="classDisCusel";}
if(scrollArrows)
{classDisCusel+=" cuselScrollArrows";}
selectedOpt.addClass("cuselMultipleActive");firstOpt.addClass("cuselMultipleCur");var optionStr=chEl.html(),spanStr=optionStr.replace(/option/ig,"span");if(params.checkZIndex)
{num=jQuery(".cusel").length;}
var i,chElVals=selectedOpt.length,chElValsStr="";for(i=0;i<chElVals;i++)
{chElValsStr+='<input type="hidden" name="'+chElName+'" value="'+selectedOpt.eq(i).val()+'" />';}
var cuselFrame='<div class="cuselMultiple '+chElClass+' '+classDisCusel+'"'+' id=cuselFrame-'+chElId+' tabindex="'+chElTab+'"'+'>'+'<div class="cuselMultipleTop"></div><div class="cuselMultipleContent">'+'<div class="cuselMultiple-scroll-wrap"><div class="cuselMultiple-scroll-pane" id="cuselMultiple-scroll-'+chElId+'">'+
spanStr+'</div></div>'+'</div><div class="cuselMultipleBottom"></div>'+'<div class="cuselMultipleInputsWrap" id="'+chElId+'">'+chElValsStr+'</div>'+'</div>';var arrOptSelected=chEl.find("option[selected]"),colOptSelected=arrOptSelected.length;chEl.replaceWith(cuselFrame);if(chElOnChange)jQuery("#"+chElId).bind('change',chElOnChange);if(chEl.attr("size"))
params.visRows=parseInt(chEl.attr("size"));var newSel=jQuery("#cuselFrame-"+chElId),arrSpan=newSel.find("span"),defaultHeight;if(!arrSpan.eq(0).text())
{defaultHeight=arrSpan.eq(1).outerHeight();arrSpan.eq(0).css("height",arrSpan.eq(1).height());}
else
{defaultHeight=arrSpan.eq(0).outerHeight();}
if(params.visRows&&arrSpan.length>params.visRows)
{newSel.find(".cuselMultiple-scroll-pane").eq(0).css({height:defaultHeight*params.visRows+"px"}).jScrollPaneCusel({showArrows:params.scrollArrows});}
var arrAddTags=jQuery("#cusel-scroll-"+chElId).find("span[addTags]"),lenAddTags=arrAddTags.length;for(i=0;i<lenAddTags;i++)arrAddTags.eq(i).append(arrAddTags.eq(i).attr("addTags")).removeAttr("addTags");});jQuery(".cuselMultiple").unbind("click");jQuery(".cuselMultiple").click(function(e)
{var clicked=jQuery(e.target),clickedId=clicked.attr("id"),clickedClass=clicked.attr("class");if(clicked.is("span")&&clicked.parents(".cuselMultiple-scroll-wrap").is("div")&&!clicked.parents(".cuselMultiple").hasClass("classDisCusel"))
{changeVal(clicked);}
else if(clicked.parents(".cuselMultiple-scroll-wrap").is("div"))
{return;}});jQuery(".cuselMultiple").focus(function()
{jQuery(this).addClass("cuselMultipleFocus");});jQuery(".cuselMultiple").blur(function()
{jQuery(this).removeClass("cuselMultipleFocus").find(".cuselMultipleOptHover").removeClass("cuselMultipleOptHover");});jQuery(".cuselMultiple").hover(function()
{jQuery(this).addClass("cuselMultipleFocus").find(".cuselMultipleOptHover").removeClass("cuselMultipleOptHover");},function()
{jQuery(this).removeClass("cuselMultipleFocus");});jQuery(".cuselMultiple").unbind("keydown");jQuery(".cuselMultiple").keydown(function(event)
{var key,keyChar;if(window.event)key=window.event.keyCode;else if(event)key=event.which;if(key==null||key==0||key==9||key==17||key==16)return true;if(jQuery(this).attr("class").indexOf("classDisCusel")!=-1)return false;if(key==40)
{var cuselCur,cuselHover=jQuery(this).find(".cuselMultipleOptHover").eq(0),cuselCurNext;if(cuselHover.hasClass("cuselMultipleOptHover"))cuselCur=cuselHover;else cuselCur=jQuery(this).find(".cuselMultipleCur").eq(0);cuselCurNext=cuselCur.next();if(cuselCurNext.is("span"))
{cuselHover.removeClass("cuselMultipleOptHover");cuselCurNext.addClass("cuselMultipleOptHover");var scrollWrap=jQuery(this).find(".cuselMultiple-scroll-pane").eq(0);if(scrollWrap.parents(".cuselMultiple").find(".jScrollPaneTrack").eq(0).is("div"))
{var hOption=scrollWrap.find("span").eq(0).outerHeight();scrollWrap[0].scrollBy(hOption);}
return false;}
else return false;}
if(key==38)
{var cuselCur,cuselHover=jQuery(this).find(".cuselMultipleOptHover").eq(0),cuselCurPrev;if(cuselHover.hasClass("cuselMultipleOptHover"))cuselCur=cuselHover;else cuselCur=jQuery(this).find(".cuselMultipleCur").eq(0);cuselCurPrev=cuselCur.prev();if(cuselCurPrev.is("span"))
{cuselHover.removeClass("cuselMultipleOptHover");cuselCurPrev.addClass("cuselMultipleOptHover");var scrollWrap=jQuery(this).find(".cuselMultiple-scroll-pane").eq(0);if(scrollWrap.parents(".cuselMultiple").find(".jScrollPaneTrack").eq(0).is("div"))
{var hOption=-parseInt(scrollWrap.find("span").eq(0).outerHeight());scrollWrap[0].scrollBy(hOption);}
return false;}
else return false;}
if(key==13||key==32)
{var clicked=jQuery(this).find(".cuselMultipleOptHover").eq(0);changeVal(clicked);}
return false;});var arr=[];jQuery(".cuselMultiple").keypress(function(event)
{var key,keyChar;if(window.event)key=window.event.keyCode;else if(event)key=event.which;if(key==null||key==0||key==9||key==17||key==16)return true;if(jQuery(this).attr("class").indexOf("classDisCusel")!=-1)return false;var o=this;arr.push(event);clearTimeout(jQuery.data(this,'timer'));var wait=setTimeout(function(){handlingEvent()},300);jQuery(this).data('timer',wait);function handlingEvent()
{var charKey=[];for(var iK in arr)
{if(window.event)charKey[iK]=arr[iK].keyCode;else if(arr[iK])charKey[iK]=arr[iK].which;charKey[iK]=String.fromCharCode(charKey[iK]).toUpperCase();}
var arrOption=jQuery(o).find("span"),colArrOption=arrOption.length,i,letter;for(i=0;i<colArrOption;i++)
{var match=true;for(var iter in arr)
{letter=arrOption.eq(i).text().charAt(iter).toUpperCase();if(letter!=charKey[iter])
{match=false;}}
if(match)
{jQuery(o).find(".cuselMultipleOptHover").removeClass("cuselMultipleOptHover").end().find("span").eq(i).addClass("cuselMultipleOptHover");var scrollWrap=jQuery(o).find(".cuselMultiple-scroll-pane").eq(0);if(scrollWrap.parents(".cuselMultiple").find(".jScrollPaneTrack").eq(0).is("div"))
{var idScrollWrap=scrollWrap.attr("id"),hOption=scrollWrap.find("span").eq(0).outerHeight()-0.2;scrollWrap[0].scrollTo(hOption*i);}
arr=arr.splice;arr=[];break;return true;}}
arr=arr.splice;arr=[];}
if(jQuery.browser.opera&&window.event.keyCode!=9)return false;});jQuery(".cuselMultiple span").mouseover(function()
{jQuery(this).parent().find(".cuselMultipleOptHover").eq(0).removeClass("cuselMultipleOptHover");});});}
function cuSelMultiRefresh(params)
{var arrRefreshEl=params.refreshEl.split(","),lenArr=arrRefreshEl.length,i;for(i=0;i<lenArr;i++)
{var refreshScroll=jQuery(arrRefreshEl[i]).parents(".cuselMultiple").find(".cuselMultiple-scroll-wrap").eq(0);refreshScroll.jScrollPaneRemoveCusel();var arrSpan=refreshScroll.find("span"),defaultHeight=arrSpan.eq(0).outerHeight();if(params.visRows&&arrSpan.length>params.visRows)
{refreshScroll.css({height:defaultHeight*params.visRows+"px"}).children(".cuselMultiple-scroll-pane").css("height",defaultHeight*params.visRows+"px").jScrollPaneCusel({showArrows:params.scrollArrows});}}}
var keyShift,keyCtrl;document.onkeydown=function checkKeycode(event)
{if(!event)var event=window.event;if(event.keyCode)keycode=event.keyCode;keyShift=event.shiftKey;keyCtrl=event.ctrlKey;}
document.onkeyup=function checkKeycode(event)
{if(!event)var event=window.event;if(event.keyCode)keycode=event.keyCode;if(keycode==16)keyShift=false;if(keycode==17)keyCtrl=false;}
function changeVal(clicked)
{var clickedVal;(clicked.attr("value")==undefined)?clickedVal=clicked.text():clickedVal=clicked.attr("value");if(keyShift)
{clicked.parents(".cuselMultiple-scroll-wrap").find(".cuselMultipleActive").removeClass("cuselMultipleActive");var inputsWrap=clicked.parents(".cuselMultiple").find(".cuselMultipleInputsWrap").eq(0),inputName=inputsWrap.find("input").eq(0).attr("name");inputsWrap.empty();clicked.addClass("cuselMultipleCur");var arrOpt=clicked.parents(".cuselMultiple-scroll-wrap").find("span"),lenArrOpt=arrOpt.length,i,firstCur=false;for(i=0;i<lenArrOpt;i++)
{if(arrOpt.eq(i).hasClass("cuselMultipleCur")&&firstCur==true)
{arrOpt.eq(i).addClass("cuselMultipleActive");inputsWrap.append('<input type="hidden" name="'+inputName+'" value="'+clickedVal+'"');break;}
if(arrOpt.eq(i).hasClass("cuselMultipleCur")&&firstCur==false)firstCur=true;if(firstCur==true)
{arrOpt.eq(i).addClass("cuselMultipleActive");var clickedValTemp;(arrOpt.eq(i).attr("value")==undefined)?clickedValTemp=arrOpt.eq(i).text():clickedValTemp=arrOpt.eq(i).attr("value");inputsWrap.append('<input type="hidden" name="'+inputName+'" value="'+clickedValTemp+'"');}}
clicked.parents(".cuselMultiple-scroll-wrap").find(".cuselMultipleCur").removeClass("cuselMultipleCur");clicked.addClass("cuselMultipleCur");if(document.selection&&document.selection.empty)
{document.selection.empty();}
else if(window.getSelection)
{var sel=window.getSelection();sel.removeAllRanges();}}
else if(keyCtrl)
{clicked.addClass("cuselMultipleActive");var inputsWrap=clicked.parents(".cuselMultiple").find(".cuselMultipleInputsWrap").eq(0),inputName=inputsWrap.find("input").eq(0).attr("name");inputsWrap.append('<input type="hidden" name="'+inputName+'" value="'+clickedVal+'"');clicked.parents(".cuselMultiple-scroll-wrap").find(".cuselCur").removeClass("cuselMultipleCur");clicked.addClass("cuselMultipleCur");}
else
{clicked.parents(".cuselMultiple-scroll-wrap").find(".cuselMultipleActive").removeClass("cuselMultipleActive");clicked.addClass("cuselMultipleActive");var inputsWrap=clicked.parents(".cuselMultiple").find(".cuselMultipleInputsWrap").eq(0),inputName=inputsWrap.find("input").eq(0).attr("name");inputsWrap.empty().html('<input type="hidden" name="'+inputName+'" value="'+clickedVal+'"');clicked.parents(".cuselMultiple-scroll-wrap").find(".cuselMultipleCur").removeClass("cuselMultipleCur");clicked.addClass("cuselMultipleCur");}
clicked.parents(".cuselMultiple").find(".cuselMultipleInputsWrap").eq(0).change();return;}