$(function() {
	$("#mask").hide();
	$("#loadpage").hide();	
});

function ShowLoadingSplash() {
	$("#mask").show();
	$("#loadpage").show();
}

function ShowAdd2ScheduleForm() {
	var box = $('#add2schedule_box');
	$('.add_auto', box).hide();
	$('#add2schedule_form', box).show();
}


function HideAdd2ScheduleForm() {
	var box = $('#add2schedule_box');
	$('#add2schedule_form', box).hide();
	$('.add_auto', box).show();
}

function isLoginPage(data) {
	return RegExp('.*(Вход в систему).*').test(data);
}

function UpdateNewOrderList(url) {
	$.ajax({
		type: 'GET',
		url: url,
		dataType: 'html',
		success: function(data) {
			if (isLoginPage(data)) location.reload();
			$('#neworders_list').html(data)
			setTimeout(function() {
				UpdateNewOrderList(url);
			}, 15000);
		},
		statusCode: {
			403: function() {
				location.reload();
			}
		}
	});
}

function CheckStatus(autotest, action) {
	var id = RegExp('at(.*)').exec(autotest.id)[1];
	$.ajax({
		type: 'GET',
		url: action + id,
		dataType: 'text',
		success: function(data) {
			if (isLoginPage(data)) window.location.refresh();
			var running = $('#at' + id + '_running', autotest);
			var in_queue = $('#at' + id + '_in_queue', autotest);
			var idle = $('#at' + id + '_idle', autotest);
			running.hide();
			in_queue.hide();
			idle.hide();
			if (data == 'RUNNING')
				running.show();
			if (data == 'IN_QUEUE')
				in_queue.show();
			if (data == 'IDLE')
				idle.show();
			setTimeout(function() {
				CheckStatus(autotest, action)
			}, 10000);
		},
		statusCode: {
			403: function() {
				location.reload();
			}
		}
	});
}

function PeriodOnChange() {
	$('#cronString').val($('#period').val());
}

function addNewParamInput(where, parameter) {
	var keyValue = parameter.split("=");
	var input = '';
	if (keyValue.length == 2 && keyValue[0].length > 0)
		input = '<div class="box edit_box_div"><div class="fl edit_param"><label class="param_key">' + keyValue[0] +
				'</label></div><div class="box"><input class="db edit_txt param_value" type="text" value="' + keyValue[1] + '"/></div></div>';
	where.append(input);
}

function addNewUserGroup(where) {
}

function joinParams() {
	var keys = $('.param_key');
	var values = $('.param_value');
	var result = [];
	for (var i = 0; i < keys.length; i++) {
		var key = $(keys[i]).text();
		var value = values[i].value;
		if (key == 'undefined' || key == '')
			continue;
		result[i] = key + '=' + value;
	}
	return result.join("&");
}

function autotestOnSubmit() {
	var s = $('#paramsField');
	var ss = joinParams();
	s.val(ss);
	return true;
}

function ShowFilterPart2(filterBy) {
	$('.filter_part2').hide();
	$('#' + filterBy).show();
}

function CancelBuild(url) {
	$.ajax({
		type: 'GET',
		url: url,
		dataType: 'text',
		success: function() {
			location.reload();
		}
	});
}

function AddUser(url) {
	var add_button = $('#add_user');
	add_button.hide();
	var userBox = $('#usersBox');
	var newuser = $('#newUser');
	if (newuser && newuser.length > 0) {
		var hiddenUserId = '<input class="users_field" type="hidden" value="' + newuser.val() + '" name="users"' + (newuser[0].disabled ? 'disabled=""' : "") + '/>';
		var inputUser = '<input class="edit_txt" type="text" readonly="readonly" disabled="disabled" value="' + $('#newUser :selected').text() + '" name="users">';
		var newUserParent = newuser.parent();
		newuser.remove();
		newUserParent.prepend(inputUser);
		newUserParent.prepend(hiddenUserId);
	}

	$.ajax({
		type: 'GET',
		url: url,
		dataType: 'html',
		data: {
			users: getUsers(),
			cmd: 'getAddUserBlock'
		},
		success: function(data) {
			if (isLoginPage(data)) location.reload();
			add_button.show();
			if (data.length == 0)
				return;
			userBox.append(data);
			UsersOnChange(url);
		},
		statusCode: {
			403: function() {
				location.reload();
			}
		}
	});
}

function RemoveUser(_this, url) {
	var parent = _this.parents('.edit_box_div');
	var names = $('.users_field', parent);
	names.get(0).disabled = true;
	parent.hide();
	UsersOnChange(url);
}

function getUsers() {
	var users = $('.users_field');
	if (!users || users.length == 0)
		return "";
	var users_str = '';
	for (var i = 0; i < users.length; i++) {
		if (users[i].disabled) continue;
		var userId = users[i].value;
		users_str += userId + ',';
	}
	return users_str.substr(0, users_str.length - 1);
}

function UsersOnChange(url) {
	var emailsBox = $('#emailsBox');
	emailsBox.html('');
	var users = getUsers();
	if (!users || users.length == 0)
		return;
	$.ajax({
		type: 'GET',
		url: url,
		dataType: 'text',
		data: {cmd: 'getEmails',
			users: users},
		success: function(data) {
			if (data.length == 0)
				return;
			if (isLoginPage(data)) location.reload();
			var params = data.split(';');
			for (var i = 0; i < params.length; i++) {
				emailsBox.append('<div>' + params[i] + '</div>');
			}
		},
		statusCode: {
			403: function() {
				location.reload();
			}
		}
	});
}

function BuildTypeOnChange(url, buildTypeId, autotestId) {
	$('#params').html('');

	var paramsBox = $('#paramsBox');
	paramsBox.hide();
	$.ajax({
		type: 'GET',
		url: url,
		dataType: 'text',
		data: {
			cmd: 'getParams',
			buildTypeId: buildTypeId,
			autotestId: autotestId
		},
		success: function(data) {
			if (data.length == 0)
				return;
			if (isLoginPage(data)) location.reload();
			var params = data.split(';');
			paramsBox.show();
			for (var i = 0; i < params.length; i++)
				addNewParamInput($('#params'), params[i]);
		},
		statusCode: {
			403: function() {
				location.reload();
			}
		}
	});
}