$(document).ready(function(){
    $('.ottBackSlide').slick({
        slidesToShow: 1,
        slidesToScroll: 1,
        arrows: false,
        fade: true,
        asNavFor: '.ottList' // 가지고올 기준 클래스 동기화
    });
    $('.ottList').slick({
        centerMode: true, // 센터모드 활성화
        centerPadding: '140px', // 양옆에 보여질 padding 값 ex 요소너비가 마진,패딩,너비 합의 반을 넣어주면 된다.
        slidesToShow: 5, // 양옆 짤리는 요소 빼고 보여지는 요소 수
        asNavFor: '.ottBackSlide', // 선택 클래스에 동기화
        focusOnSelect: true, // 중앙 포커스 효과 활성화
        infinite: true, // 무한모드 활성화
        responsive: [ // 반응형 제이쿼리
            {
                breakpoint: 768,
                settings: {
                    arrows: true,
                    centerMode: true,
                    centerPadding: '140px',
                    slidesToShow: 3
                }
            },
            {
                breakpoint: 480,
                settings: {
                    arrows: true,
                    centerMode: true,
                    centerPadding: '140px',
                    slidesToShow: 1
                }
            }
        ]
    }).on('beforeChange', function(event, slick, currentSlide, nextSlide) {
        if (currentSlide !== nextSlide) {
            $('.slick-center + .slick-cloned').each(function(index, node) {
                let $node = $(node);

                setTimeout(function() {
                    $node.addClass('slick-current');
                    $node.addClass('slick-center');
                });
            });
        }
    });
    //slick infinite 가 맨끝에서 다시 처음으로 돌아가거나 할때도 트랜지션이 적용되기 위한 코드
});