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