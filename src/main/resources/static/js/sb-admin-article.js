$("#article-label a").click(function(){
    var selText = $(this).text();
    $(this).parent().siblings("#article-dropdown").html(selText);
});


$("input[id='article-file']").change(function (e) {
    var $this = $(this);
    console.log($(this));
    $this.next().html($this.val().split('\\').pop());
});