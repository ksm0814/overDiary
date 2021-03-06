$("#article-label a").click(function(){
    var selText = $(this).text();
    $(this).parent().siblings("#article-dropdown").html(selText);
});


$("input[id='file-label']").change(function (e) {
    var $this = $(this);
    $this.next().html($this.val().split('\\').pop());
});

$("#file-upload-button").click(addFile);

function addFile(e){
    console.log("you clicked");
    e.preventDefault();
    var file = $("#file-upload-form")[0];
    console.log(file);
    var data = new FormData(file);
    console.log(data);

    $.ajax({
        type: "post",
        enctype: 'multipart/form-data',
        url: "/api/attachments",
        data: data,
        contentType: false,
        processData: false,
        success: function (data) {
            console.log("SUCCESS : ", data.attachmentKey);
            $("#filePath-hidden").val(data.attachmentKey);

            alert("파일 업로드를 성공적으로 마쳤습니다! : ");
        },
        error: function (e) {
            alert("파일 업로드를 실패했습니다. 다시 시도해주세요");
            console.log("ERROR : ", e);

        }
    });
}