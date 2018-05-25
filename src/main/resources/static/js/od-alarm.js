(function poll(){
    $.ajax({ url: "api/articles/updateAlarm", success: function(data){
            console.log(data);
            $("#realtime-alarm-text").html(selText);

        }, dataType: "json", complete: poll, timeout: 600000 });
})();