Handlebars.registerHelper("loop-time", function(n, block){
    var temp = '';
    for(var i = 0; i < n; ++i)
        temp += block.fn(i);
    return temp;
});

Handlebars.registerHelper('for', function(from, to, incr, block) {
    var accum = '';
    for(var i = from; i < to; i += incr)
        accum += block.fn(i);
    return accum;
});

var t = Handlebars.compile($('#t').html());
$('body').append(t());


$('#btn-chart').submit(function(e){
    e.preventDefault();
    var showRankUrl = this.closest("#form-chart").attr("action");
    console.log(showRankUrl);
    $.ajax({
        type: 'post',
        url: showRankUrl,
        dataType: 'json',
        error: function (xhr, status) {
            console.log("실패");

        },
        success: function (data, status) {
            console.log("성공");
            console.log("data : " + data);
        }
});

function myChart(e) {
    e.preventDefault();
    var showRankUrl = this.closest("#form-chart").attr("action");
    console.log(showRankUrl);
            $.ajax({
        type: 'post',
        url: showRankUrl,
        dataType: 'json',
        error: function (xhr, status) {
            console.log("실패");

        },
        success: function (data, status) {
            console.log("성공");
            console.log("data : " + data);
            var ctxP = document.getElementById("rank").getContext('2d');
            // var myPieChart = new Chart(ctxP, {
            //     type: 'pie',
            //     data: {
            //         labels: ["Red", "Green", "Yellow", "Grey", "Dark Grey"],
            //         datasets: [
            //             {
            //                 data: [300, 50, 100, 40, 120],
            //                 backgroundColor: ["#F7464A", "#46BFBD", "#FDB45C", "#949FB1", "#4D5360"],
            //                 hoverBackgroundColor: ["#FF5A5E", "#5AD3D1", "#FFC870", "#A8B3C5", "#616774"]
            //             }
            //         ]
            //     },
            //     options: {
            //         responsive: true
            //     }
            // });

        }
    })
}