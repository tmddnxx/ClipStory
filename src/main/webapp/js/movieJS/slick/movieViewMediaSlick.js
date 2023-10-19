$(document).ready(function(){
    $.noConflict();
    $('.media').slick({
        infinite: true,
        slidesToShow: 5,
        slidesToScroll: 5,
        arrows:true,
        dots: true
    });

});