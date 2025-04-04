// 유효성 검사에 사용할 정규표현식
const rePass  = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[$`~!@%*#^?&()\-_=+]).{8,16}$/;

document.addEventListener('DOMContentLoaded', function () {
    // 폼 요소를 명시적으로 참조
    const formUpdatePass = document.forms['formUpdatePass'];

    // 유효성 검사에 사용할 상태 변수
    let isPassOk = false;

    //  비밀번호 유효성 검사
    const passResult = document.getElementsByClassName('passResult')[0];

    formUpdatePass.password2.addEventListener('focusout', function() {
        const value1 = formUpdatePass.password.value;
        const value2 = formUpdatePass.password2.value;

        if (!value1.match(rePass)) {
            passResult.innerText = '비밀번호는 숫자, 소문자, 대문자, 특수문자 조합 8자리 이상이어야 합니다.';
            passResult.style.color = 'red';
            isPassOk = false;
            return;
        }

        if (value1 === value2) {
            passResult.innerText = '사용 가능한 비밀번호 입니다.';
            passResult.style.color = 'green';
            isPassOk = true;
        } else {
            passResult.innerText = '비밀번호가 일치하지 않습니다.';
            passResult.style.color = 'red';
            isPassOk = false;
        }
    });

    // 최종 폼 전송 이벤트
    formUpdatePass.onsubmit = function(e) {
        // 비밀번호 유효성 검사 결과
        if (!isPassOk) {
            return false;
        } else {
            return true;
        }
    };
});