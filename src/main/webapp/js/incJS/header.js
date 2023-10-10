window.addEventListener("scroll", function() {
    const header = document.querySelector('.header');
    const scrollPosition = window.scrollY;

    if (scrollPosition > 0) {
        // 스크롤이 아래로 내려갈 때
        header.style.opacity = "0.7";
    } else {
        // 스크롤이 맨 위로 올라올 때
        header.style.opacity = "1";
    }
});