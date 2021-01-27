/*
    Function:  me
    Author:    WuJoel
    BuildDate: 2021-01-14
    Version:   Alpha
*/

$('.menu.toggle').click(function () {
    $('.m-item').toggleClass('m-mobile-hide');
});

$('#payButton').popup({
    popup:$('#payQR'),
    on:'click',
    position:'bottom center'
});

$('.wechat').popup({
    popup: $('.wechatQR'),
    position: 'bottom center'
});

$('.qq').popup();

$('.ui.dropdown').dropdown({
    on: 'hover'
});

