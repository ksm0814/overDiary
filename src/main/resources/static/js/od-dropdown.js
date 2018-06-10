var dropdown = $("#alerts-Dropdown");

dropdown.click(function (e) {
    e.preventDefault();
    console.log("clicked");
})

dropdown.dropdown(function (e) {
    e.preventDefault();
    console.log("clicked2");
});


(function alarmPoll() {
    setTimeout(function () {
            $.ajax({
                type: "get",
                url: "/api/alarms/send",
                dataType: "json",
                success: function (data) {
                    var lists = data.alarmList;
                    if(lists.size != 0) {
                        console.log(lists)
                        var alarmtText = $("#realtime-alarm-template").html();
                        $(".alarm-dropdown-div").fadeOut('fast').remove();
                        $.each(lists, function (index, value) {
                            var template = alarmtText.format(value.message, value.articleId, value.alarmKey);
                            $("#alarm-dropdown").append(template).fadeIn(3000);
                        });
                    }
                },
                complete: alarmPoll()
            });
    }, 5000);

})();
String.prototype.format = function () {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function (match, number) {
        return typeof args[number] != 'undefined' ? args[number] : match;
    });
};