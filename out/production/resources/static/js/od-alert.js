$("#alarm-button").click(function(e){
    e.preventDefault();
    $.ajax({
        type: "get",
        url: "/api/alarms/send",
        success: function (data) {
            console.log("SUCCESS : ", data);

            alert("파일 업로드를 성공적으로 마쳤습니다! : ");
        },
        error: function (e) {
            console.log("ERROR : ", e);

        }
    });
});

