const reUid   = /^[a-z]+[a-z0-9]{4,19}$/g;
const reEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

document.addEventListener('DOMContentLoaded', function () {

    let isIdOk = false;
    let isEmailOk = false;
    let preventDoubleClick = false;

    // 요소 선택
    const formfindPass = document.forms['formfindPass'];
    const nameInput = document.querySelector('input[name="id"]');
    const nameResult = document.querySelector('.uidResult');
    const emailInput = document.querySelector('input[name="email"]');
    const emailResult = document.querySelector('.emailResult');
    const authGroup = document.querySelector('.auth-group'); // 인증코드 입력 영역
    const btnSendEmail = document.getElementById('btnSendEmail');
    const btnAuthEmail = document.getElementById('btnAuthEmail');

    // 아이디 유효성 검사
    const uidResult = document.getElementsByClassName('uidResult')[0];

    formfindPass.id.addEventListener('focusout', function() {
        const value = this.value;

        if (!value.match(reUid)) {
            uidResult.innerText = '아이디가 올바르지 않습니다.';
            uidResult.style.color = 'red';
            isIdOk = false;
        } else {
            uidResult.innerText = '';
            isIdOk = true;
        }
    });

    // 이메일 인증번호 받기
    btnSendEmail.addEventListener('click', async function(){
        const emailValue = emailInput.value;
        console.log("인증번호 받기 버튼 클릭:", emailValue);

        // 이중 클릭 방지
        if (preventDoubleClick) {
            console.log("중복 클릭 방지");
            return;
        }

        // 이메일 정규식 검증
        if (!emailValue.match(reEmail)) {
            emailResult.innerText = '이메일이 유효하지 않습니다.';
            emailResult.style.color = 'red';
            isEmailOk = false;
            return;
        }

        // 중복 클릭 방지 플래그 설정
        preventDoubleClick = true;

        try {
            const response = await fetch(`/find/email/${emailValue}`);
            const data = await response.json();
            console.log("이메일 발송 응답:", data);
            // 이메일 발송 성공 시 인증번호 입력 영역 보이기 (display를 'flex'로 지정)
            if (authGroup) {
                authGroup.style.display = 'flex';  // 'block' 대신 'flex' 사용
                console.log("인증번호 입력 영역 표시됨");
            } else {
                console.warn("인증번호 입력 영역(auth-group) 요소가 존재하지 않습니다.");
            }
        } catch (error) {
            console.error("이메일 인증번호 요청 중 오류 발생:", error);
            preventDoubleClick = false;
        }
    });

    // 인증 코드 확인
    btnAuthEmail.addEventListener('click', async function(){
        const authValue = document.querySelector('input[name="auth"]').value;
        console.log("인증코드 확인 버튼 클릭:", authValue);

        const jsonData = { "authCode": authValue };

        try {
            const response = await fetch('/find/email/auth', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(jsonData)
            });
            const data = await response.json();
            console.log("인증 코드 응답:", data);

            if (data) {
                emailResult.innerText = '이메일이 인증 되었습니다.';
                emailResult.style.color = 'green';
                isEmailOk = true;
            } else {
                emailResult.innerText = '유효한 인증코드가 아닙니다.';
                isEmailOk = false;
            }
        } catch (error) {
            console.error("인증코드 확인 중 오류 발생:", error);
        }
    });

    formfindPass.onsubmit = function () {

        if(!isIdOk) {
            return false;
        }
        if(!isEmailOk) {
            return false;
        }

        return true;
    }

});
