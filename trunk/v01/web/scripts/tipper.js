/*
 * jQuery 1.2.1 - New Wave Javascript
 *
 * Copyright (c) 2007 John Resig (jquery.com)
 * Dual licensed under the MIT (MIT-LICENSE.txt)
 * and GPL (GPL-LICENSE.txt) licenses.
 *
 * $Date: 2007-09-16 23:42:06 -0400 (Sun, 16 Sep 2007) $
 * $Rev: 3353 $
 */
jQuery.showTip = function(tip, x, y) {
if (tip != null) {
var t = $('#divTip');
var ox = 16;
var oy = 16;
var twp = parseInt(t.css('padding-left')) + parseInt(t.css('padding-right'));
var twb = parseInt(t.css('border-left-width')) + parseInt(t.css('border-right-width'));
var thp = parseInt(t.css('padding-top')) + parseInt(t.css('padding-bottom'));
var thb = parseInt(t.css('border-top-width')) + parseInt(t.css('border-bottom-width'));

if ($.browser.msie) {
t.html(tip).css({display: 'block', width: ''});
var tw = t.width();
var twm = parseInt(t.css('max-width'));
if (tw > twm) {
t.css('width', twm);
tw = twm + twp + twb;
} else {
tw = tw + twp + twb;
}
} else {
t.html(tip).css({display: 'block'});
var tw = t.width() + twp + twb;
}

var th = t.height() + thp + thb;

x = (x + tw + ox > $(document).width()) ? x = x - tw - (ox/2) + 'px' : x = x + ox + 'px';
y = (y + th + oy > $(document).height()) ? y = y - th - (oy/2) + 'px' : y = y + oy + 'px';

t.css({left: x, top: y});
}
}

jQuery.hideTip = function() {
 $('#divTip').css({display: 'none'});
 }

jQuery.styleTip = function(style) {
 $('#divTip').css(style);
 }

jQuery.initTips = function() {
 $('*[@tip]').hover(
 function(e) {
 $.showTip($(this).attr('tip'), e.pageX, e.pageY);
 },
 function() {
 $.hideTip();
 }
 );
 }

jQuery.fn.tip = function(tip, e) {
 if (tip == null) {
 $(this).removeAttr('tip');
 $.hideTip();
 } else {
 $(this).attr('tip', tip).hover(
 function(e) {
 $.showTip(tip, e.pageX, e.pageY);
 },
 function() {
 $.hideTip();
 }
 );
 if (e != null) {
 $.showTip(tip, e.pageX, e.pageY);
 }
 }
 return this;
 }

$(document).ready(function() {
 $('<div id="divTip"></div>').css({
 position:'absolute',
 display: 'none',
 border:'1px solid #565656',
 background:'#E8EFF2',
 font:'11px Tahoma',
 color:'#000000',
 padding:'5px',
 maxWidth:'400px',
 opacity: '1'
 }).appendTo('body');
 $.initTips();
 });