function agreeOnTos(checkBox) {
    $('#user_create_submit').attr('disabled', !checkBox.checked);
}

if (typeof _GADGET == 'undefined') {
    var _GADGET = {};
}

_GADGET.selectedGadgetId = 0;

_GADGET.selectedItems = {};
_GADGET.selectItem = function(res) {
    if (_GADGET.selectedItems[res.url]) {
        return;
    }
    var endPoint = '/item/add?format=json';
    var data = {
        "item_url": res.url,
        "gadget_id": _GADGET.selectedGadgetId,
        "one_time_token": $('input[name="one_time_token"]:first').val()
    };

}

_GADGET.SLIDE_VALUE = 95;
$(document).ready(function() {
    $('label').inFieldLabels({"fadeOpacity": 0.1});
    $('#slide-next').click(function() {
        nextleft = parseInt($('#slide-inner').css("margin-left")) - _GADGET.SLIDE_VALUE;
        $('#slide-inner').animate({
            "marginLeft": nextleft + 'px'
        }, 500);
    });
    $('#slide-back').click(function() {
        nextleft = parseInt($('#slide-inner').css("margin-left")) + _GADGET.SLIDE_VALUE;
        $('#slide-inner').animate({
            "marginLeft": nextleft + 'px'
        }, 500);
    });
});
