$("#alerts-Dropdown").dropdown();

$("#alerts-Dropdown").click(function(e){
    var isDown =  $("#alerts-Dropdown").attr('aria-expanded');
    console.log("out : ",isDown)
    e.preventDefault();
    if(isDown == 'true') {
        console.log("in")

        $.ajax({
            type: "get",
            url: "/api/alarms/send",
            success: function (data) {

                var lists = [];
                lists = data.alarmList;
                var alarmtText = $("#realtime-alarm-template").html();
                $(".alarm-dropdown-div").fadeOut('fast').remove();
                $.each(lists, function (index, value) {
                    var template = alarmtText.format(value.message, value.articleId, value.alarmKey);
                    $("#alarm-dropdown").append(template).fadeIn(3000);
                });
            },
            error: function (e) {
                console.log("ERROR : ", e);

            }
        });
    }
});
String.prototype.format = function () {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function (match, number) {
        return typeof args[number] != 'undefined' ? args[number] : match;
    });
};