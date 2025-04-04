document.addEventListener('DOMContentLoaded', function (){
    const btnNext = document.querySelector('.btnNext');

    btnNext.addEventListener('click', function (e){
        const termsChecked = document.querySelector('input.ckbx').checked;
        if (!termsChecked){
            event.preventDefault();//링크 이동 방지코드
            alert('약관 및 개인정보 처리방침에 모두 동의해야 다음으로 이동할 수 있습니다.');
        }
    });
});