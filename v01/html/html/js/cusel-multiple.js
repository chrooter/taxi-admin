/* -------------------------------------

	cusel-multiple version 0.9
	смена селект muliple на стильный
	autor: Evgen Ryzhkov
	www.xiper.net
----------------------------------------	
*/
function cuSelMulti(params) {
	
jQuery(document).ready(function(){
							
	jQuery(params.changedEl).each(
	function(num)
	{
	var chEl = jQuery(this),
		chElClass = chEl.attr("class"), // класс селекта
		chElId = chEl.attr("id"), // id
		chElName = chEl.attr("name"), // имя
		disabledSel = chEl.attr("disabled"), // заблокирован ли селект
		scrollArrows = params.scrollArrows,
		chElOnChange = chEl.attr("onchange"),
		chElTab = chEl.attr("tabindex"),
		chElMultiple = chEl.attr("multiple"),
		selectedOpt = chEl.find("option[selected]"),
		firstOpt = chEl.find("option").eq(0);
		
		if(!chElId)	return false; // не стилизируем селект если не задан id
		
		if(!disabledSel)
		{
			classDisCuselText = "", // для отслеживания клика по задизайбленному селекту
			classDisCusel=""; // для оформления задизейбленного селекта
		}
		else
		{
			classDisCuselText = "classDisCuselLabel";
			classDisCusel="classDisCusel";
		}
		
		if(scrollArrows)
		{
			classDisCusel+=" cuselScrollArrows";
		}
		
		selectedOpt.addClass("cuselMultipleActive");
		
		firstOpt.addClass("cuselMultipleCur");
	
	var optionStr = chEl.html(), // список оптионов

		
	/* 
		делаем замену тегов option на span, полностью сохраняя начальную конструкцию
	*/

	spanStr = optionStr.replace(/option/ig,"span");
	
	
	/*
		если нужна проверка z-index, проверяем на наличие уже созданных селектов и задаем z-index новому с учетом имеющихся
	*/
	if(params.checkZIndex)
	{
		num = jQuery(".cusel").length;
	}
	
	/* формируем массив начальных значений селекта */
	var i,
		chElVals = selectedOpt.length,
		chElValsStr = ""; /* результирующая строка html кода для вставки в каркас селекта, содержащая inputы
							по умолчанию значение пустое, т.к. если нет выбранных элементов multiple на сервер ничего не отправляет в отличии от обычного селект	*/
		
	for(i=0;i<chElVals;i++)
	{
		chElValsStr += '<input type="hidden" name="'+chElName+'" value="'+selectedOpt.eq(i).val()+'" />';
	}
	
	
	/* каркас стильного селекта	multiple*/
	var cuselFrame = '<div class="cuselMultiple '+chElClass+' '+classDisCusel+'"'+
		' id=cuselFrame-'+chElId+
		' tabindex="'+chElTab+'"'+
		'>'+
		'<div class="cuselMultipleTop"></div><div class="cuselMultipleContent">'+
		'<div class="cuselMultiple-scroll-wrap"><div class="cuselMultiple-scroll-pane" id="cuselMultiple-scroll-'+chElId+'">'+
		spanStr+
		'</div></div>'+
		'</div><div class="cuselMultipleBottom"></div>'+
		'<div class="cuselMultipleInputsWrap" id="'+chElId+'">'+chElValsStr+'</div>'+
		'</div>';
					
		/*
			устанавливаем начальные значение селекта по selected
		*/
		var arrOptSelected = chEl.find("option[selected]"),
			colOptSelected = arrOptSelected.length;
					
	/* удаляем обычный селект, на его место вставляем стильный */
	chEl.replaceWith(cuselFrame);
	
	/* если был поцеплен onchange - цепляем его полю */
	if(chElOnChange) jQuery("#"+chElId).bind('change',chElOnChange);

	
	/*
		устаналиваем высоту списка основываясь на заданном size и высоте одного оптиона
	*/
	
	if(chEl.attr("size"))
		params.visRows = parseInt(chEl.attr("size")); // число видимых элементов
	
	var newSel = jQuery("#cuselFrame-"+chElId),
		arrSpan = newSel.find("span"),
		defaultHeight;
		
		if(!arrSpan.eq(0).text())
		{
			defaultHeight = arrSpan.eq(1).outerHeight();
			arrSpan.eq(0).css("height", arrSpan.eq(1).height());
		} 
		else
		{
			defaultHeight = arrSpan.eq(0).outerHeight();
		}
		
	if(params.visRows && arrSpan.length>params.visRows)
	{
		newSel.find(".cuselMultiple-scroll-pane").eq(0)
			.css({height: defaultHeight*params.visRows+"px"})
			.jScrollPaneCusel({showArrows:params.scrollArrows});
	}
	
	
	/* вставляем в оптионы дополнительные теги */
	
	var arrAddTags = jQuery("#cusel-scroll-"+chElId).find("span[addTags]"),
		lenAddTags = arrAddTags.length;
		
		for(i=0;i<lenAddTags;i++) arrAddTags.eq(i)
										.append(arrAddTags.eq(i).attr("addTags"))
										.removeAttr("addTags");
	
	});

/* ---------------------------------------
	привязка событий селектам
------------------------------------------
*/

jQuery(".cuselMultiple").unbind("click");

jQuery(".cuselMultiple").click(
	function(e)
	{
				
		var clicked = jQuery(e.target),
			clickedId = clicked.attr("id"),
			clickedClass = clicked.attr("class");
				
		/* если выбрали позицию в списке */
		if(clicked.is("span") && clicked.parents(".cuselMultiple-scroll-wrap").is("div") && !clicked.parents(".cuselMultiple").hasClass("classDisCusel"))
		{
			changeVal(clicked);
			
		}
		
		else if(clicked.parents(".cuselMultiple-scroll-wrap").is("div"))
		{
			return;
		}
		

		
	});

/*
	дубляж псевдоклассов css focus и hover для IE6-7
*/
jQuery(".cuselMultiple").focus(
function()
{
	jQuery(this).addClass("cuselMultipleFocus");
});

jQuery(".cuselMultiple").blur(
function()
{
	jQuery(this)
		.removeClass("cuselMultipleFocus")
		.find(".cuselMultipleOptHover").removeClass("cuselMultipleOptHover");
});

jQuery(".cuselMultiple").hover(
function()
{
	jQuery(this)
		.addClass("cuselMultipleFocus")
		.find(".cuselMultipleOptHover").removeClass("cuselMultipleOptHover");
},
function()
{
	jQuery(this).removeClass("cuselMultipleFocus");
});

jQuery(".cuselMultiple").unbind("keydown"); /* чтобы не было двойного срабатывания события */

jQuery(".cuselMultiple").keydown(
function(event)
{	
	/*
		если селект задизайблин, с него работает только таб
	*/
	var key, keyChar;
		
	if(window.event) key=window.event.keyCode;
	else if (event) key=event.which;
	
	if(key==null || key==0 || key==9 || key==17 || key==16) return true;
	
	if(jQuery(this).attr("class").indexOf("classDisCusel")!=-1) return false;
	/*
		если нажали стрелку вниз
	*/
	if(key==40)
	{
		/*	если по каким либо причинам есть эелмент в спсике подсвеченный как ховер
			тогда ткущий элемент будет им, иначе будет элемент active
			этот учет нужен чтобы была адекватная реакция на зажатый shift приработе с клавой
		*/
		var cuselCur,
			cuselHover = jQuery(this).find(".cuselMultipleOptHover").eq(0),
			cuselCurNext;
			
			if(cuselHover.hasClass("cuselMultipleOptHover")) cuselCur = cuselHover;
			else cuselCur = jQuery(this).find(".cuselMultipleCur").eq(0);
			
			cuselCurNext = cuselCur.next();
			
			
		if(cuselCurNext.is("span"))
		{
			cuselHover.removeClass("cuselMultipleOptHover");
			cuselCurNext.addClass("cuselMultipleOptHover");
			
			/* делаем прокрутку к выбранному элементу */
			var scrollWrap = jQuery(this).find(".cuselMultiple-scroll-pane").eq(0);
			
			if(scrollWrap.parents(".cuselMultiple").find(".jScrollPaneTrack").eq(0).is("div"))
			{
				var hOption = scrollWrap.find("span").eq(0).outerHeight();
					
				scrollWrap[0].scrollBy(hOption);
			}
			
			return false;
		}
		else return false;
	}
	
	/*
		если нажали стрелку вверх
	*/
	if(key==38)
	{
		var cuselCur,
			cuselHover = jQuery(this).find(".cuselMultipleOptHover").eq(0),
			cuselCurPrev;
			
			if(cuselHover.hasClass("cuselMultipleOptHover")) cuselCur = cuselHover;
			else cuselCur = jQuery(this).find(".cuselMultipleCur").eq(0);
			
			cuselCurPrev = cuselCur.prev();
			
		if(cuselCurPrev.is("span"))
		{
			cuselHover.removeClass("cuselMultipleOptHover");
			cuselCurPrev.addClass("cuselMultipleOptHover");
			
			/* делаем прокрутку к выбранному элементу */
			var scrollWrap = jQuery(this).find(".cuselMultiple-scroll-pane").eq(0);
				
			if(scrollWrap.parents(".cuselMultiple").find(".jScrollPaneTrack").eq(0).is("div"))
			{
				var hOption = -parseInt(scrollWrap.find("span").eq(0).outerHeight());
				scrollWrap[0].scrollBy(hOption);
			}
			
			return false;
		}
		else return false;
	}
	
		
	/*
		если нажали enter
	*/
	if(key==13 || key==32)
	{
		var clicked = jQuery(this).find(".cuselMultipleOptHover").eq(0); // текущий элемент, на котором утановлен курсор
		changeVal(clicked);
	
	}
		
	return false; /* специально для опера, чтоб при нажатиии на клавиши не прокручивалось окно браузера */

});

/*
	функция отбора по нажатым символам (от Alexey Choporov)
	отбор идет пока пауза между нажатиями сиволов не будет больше 0.5 сек
	keypress нужен для отлова символа нажатой клавиш
*/
var arr = [];
jQuery(".cuselMultiple").keypress(function(event)
{
	var key, keyChar;
		
	if(window.event) key=window.event.keyCode;
	else if (event) key=event.which;
	
	if(key==null || key==0 || key==9 || key==17 || key==16) return true;
	
	if(jQuery(this).attr("class").indexOf("classDisCusel")!=-1) return false;
	
	var o = this;
	arr.push(event);
	clearTimeout(jQuery.data(this, 'timer'));
	var wait = setTimeout(function() { handlingEvent() }, 300);
	jQuery(this).data('timer', wait);
	function handlingEvent()
	{
		var charKey = [];
		for (var iK in arr)
		{
			if(window.event)charKey[iK]=arr[iK].keyCode;
			else if(arr[iK])charKey[iK]=arr[iK].which;
			charKey[iK]=String.fromCharCode(charKey[iK]).toUpperCase();
		}
		var arrOption=jQuery(o).find("span"),colArrOption=arrOption.length,i,letter;
		for(i=0;i<colArrOption;i++)
		{
			var match = true;
			for (var iter in arr)
			{
				letter=arrOption.eq(i).text().charAt(iter).toUpperCase();
				if (letter!=charKey[iter])
				{
					match=false;
				}
			}
			if(match)
			{
				jQuery(o)
					.find(".cuselMultipleOptHover").removeClass("cuselMultipleOptHover")
					.end().find("span").eq(i).addClass("cuselMultipleOptHover");
					
				var scrollWrap=jQuery(o).find(".cuselMultiple-scroll-pane").eq(0);
				if(scrollWrap.parents(".cuselMultiple").find(".jScrollPaneTrack").eq(0).is("div"))
				{
					var idScrollWrap=scrollWrap.attr("id"),
						hOption=scrollWrap.find("span").eq(0).outerHeight()-0.2;
					scrollWrap[0].scrollTo(hOption*i);
				}
			arr = arr.splice;
			arr = [];
			break;
			return true;
			}
		}
		arr = arr.splice;
		arr = [];
	}
	if(jQuery.browser.opera && window.event.keyCode!=9) return false;
});

jQuery(".cuselMultiple span").mouseover(
function()
{
	jQuery(this).parent().find(".cuselMultipleOptHover").eq(0).removeClass("cuselMultipleOptHover");
});
								



	
								});

	
}

function cuSelMultiRefresh(params)
{
	/*
		устаналиваем высоту выпадающих списков основываясь на числе видимых позиций и высоты одной позиции
		при чем только тем, у которых число оптионов больше числа заданного числа видимых
	*/

	var arrRefreshEl = params.refreshEl.split(","),
		lenArr = arrRefreshEl.length,
		i;
	
	for(i=0;i<lenArr;i++)
	{
		var refreshScroll = jQuery(arrRefreshEl[i]).parents(".cuselMultiple").find(".cuselMultiple-scroll-wrap").eq(0);
			refreshScroll.jScrollPaneRemoveCusel();
	
		var	arrSpan = refreshScroll.find("span"),
			defaultHeight = arrSpan.eq(0).outerHeight();
		
		
			
		if(params.visRows && arrSpan.length>params.visRows)
		{
			refreshScroll
				.css({height: defaultHeight*params.visRows+"px"})
				.children(".cuselMultiple-scroll-pane")
					.css("height",defaultHeight*params.visRows+"px")
					.jScrollPaneCusel({showArrows:params.scrollArrows});
				
		}
		
	}
	
	
}

/*
	для анализа зажаьа ли shift или ctrl
*/
var keyShift, keyCtrl;
document.onkeydown = function checkKeycode(event)
{
	if(!event) var event = window.event;
	if (event.keyCode) keycode = event.keyCode;
	keyShift = event.shiftKey;
	keyCtrl = event.ctrlKey;
}
document.onkeyup = function checkKeycode(event)
{
	if(!event) var event = window.event;
	if (event.keyCode) keycode = event.keyCode;
	if(keycode == 16) keyShift = false;
	if(keycode == 17) keyCtrl = false; 
}

/* 
	смена значения селекта
	вынесена в одельную фукцию чтобы не дублировать код для работы с мышью и клавиатуры
	clicked - элемент, котрый выбрал пользователь
*/
function changeVal(clicked)
{
	var clickedVal;
			(clicked.attr("value") == undefined) ? clickedVal=clicked.text() : clickedVal=clicked.attr("value");
			
			// если зажат shift
			if(keyShift)
			{
				/* убираем все выделения активных пунктов */
				clicked.parents(".cuselMultiple-scroll-wrap").find(".cuselMultipleActive").removeClass("cuselMultipleActive");
				
				/* удаляем масив полей со значениями */
				var inputsWrap = clicked.parents(".cuselMultiple").find(".cuselMultipleInputsWrap").eq(0),
					inputName = inputsWrap.find("input").eq(0).attr("name");
				inputsWrap.empty();
				
				/* ставим временную метку */
				clicked.addClass("cuselMultipleCur");
				
				var arrOpt = clicked.parents(".cuselMultiple-scroll-wrap").find("span"),
					lenArrOpt = arrOpt.length,
					i,
					firstCur = false; // флаг, что в массиве встретился первый cur - элемент. повтрнная встреча закончит цикл

				for(i=0;i<lenArrOpt;i++)
				{
					if(arrOpt.eq(i).hasClass("cuselMultipleCur") && firstCur == true) // записываем последнее значение и заканчиваем цикл
					{
						arrOpt.eq(i).addClass("cuselMultipleActive");
						inputsWrap.append('<input type="hidden" name="'+inputName+'" value="'+clickedVal+'"');
						break;
					}
					if(arrOpt.eq(i).hasClass("cuselMultipleCur") && firstCur == false) firstCur = true;  // с этого момента начинаются нужные нам оптионы
					if(firstCur == true) // просто очерденой нужный нам оптион
					{
						arrOpt.eq(i).addClass("cuselMultipleActive");
						var clickedValTemp;
						(arrOpt.eq(i).attr("value") == undefined) ? clickedValTemp=arrOpt.eq(i).text() : clickedValTemp=arrOpt.eq(i).attr("value");
						inputsWrap.append('<input type="hidden" name="'+inputName+'" value="'+clickedValTemp+'"');
												
					}
										
				}
				
					
				clicked.parents(".cuselMultiple-scroll-wrap").find(".cuselMultipleCur").removeClass("cuselMultipleCur");
				clicked.addClass("cuselMultipleCur");
				
				/* удаляем возможно появившиеся выделение текста */
				if(document.selection && document.selection.empty)
				{
					document.selection.empty();
				}
				else if(window.getSelection)
				{
					var sel = window.getSelection();
					sel.removeAllRanges();
				}
			}
			// если зажат ctrl
			else if(keyCtrl)
			{
				clicked.addClass("cuselMultipleActive");
				var inputsWrap = clicked.parents(".cuselMultiple").find(".cuselMultipleInputsWrap").eq(0),
					inputName = inputsWrap.find("input").eq(0).attr("name");
				inputsWrap.append('<input type="hidden" name="'+inputName+'" value="'+clickedVal+'"');
				
				clicked.parents(".cuselMultiple-scroll-wrap").find(".cuselCur").removeClass("cuselMultipleCur");
				clicked.addClass("cuselMultipleCur");
				
			}
			// если ничего не зажато
			else
			{
				clicked.parents(".cuselMultiple-scroll-wrap").find(".cuselMultipleActive").removeClass("cuselMultipleActive");
				clicked.addClass("cuselMultipleActive");
				var inputsWrap = clicked.parents(".cuselMultiple").find(".cuselMultipleInputsWrap").eq(0),
					inputName = inputsWrap.find("input").eq(0).attr("name");
				inputsWrap
					.empty()
					.html('<input type="hidden" name="'+inputName+'" value="'+clickedVal+'"');
					
				clicked.parents(".cuselMultiple-scroll-wrap").find(".cuselMultipleCur").removeClass("cuselMultipleCur");
				clicked.addClass("cuselMultipleCur");
				
			}
			clicked.parents(".cuselMultiple").find(".cuselMultipleInputsWrap").eq(0).change(); // чтобы срабатывал onchange
	return;
}