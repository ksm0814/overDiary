// (function poll(){
//     $.ajax({ url: "api/articles/updateAlarm", success: function(data){
//             //console.log(data);
//             var selText = data.title;
//             console.log(data.title);
//            // $("#realtime-alarm-text").html(data.title.text());
//
//         }, dataType: "json", complete: poll, timeout: 1000});
// })();

(function poll() {
    setTimeout(function () {
        $.ajax({
            url: "api/articles/updateAlarm",
            dataType: "json",
            success: function (data) {
                    console.log(data[0]);
                    $(".alarm-div").fadeOut('fast').remove();
                    var alarmTemplate = $("#alarmTemplate").html();

                    var template = alarmTemplate.format(data[0].writer.userId, data[0].title, data[1].writer.userId, data[1].title, data[2].writer.userId, data[2].title, data[3].writer.userId, data[3].title, data[4].writer.userId, data[4].title);
                    $("#realtime-text").append(template).fadeIn(3000);
            },
            error: function(){

            },
            complete: poll
        });
    }, 5000);
})();

String.prototype.format = function () {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function (match, number) {
        return typeof args[number] != 'undefined' ? args[number] : match;
    });
};


alert("배포 자동화 완료");
alert("홀");






