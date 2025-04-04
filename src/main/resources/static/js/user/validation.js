// 유효성 검사에 사용할 정규표현식
const reUid   = /^[a-z]+[a-z0-9]{4,19}$/g;
const rePass  = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[$`~!@%*#^?&()\-_=+]).{8,16}$/;
const reName  = /^[가-힣]{2,10}$/;
const reEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
const reHp    = /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;

document.addEventListener('DOMContentLoaded', function () {
    // 폼 요소를 명시적으로 참조
    const formRegister = document.forms['formRegister'];

    // 유효성 검사에 사용할 상태 변수
    let isIdOk = false;
    let isPassOk = false;
    let isNameOk = false;
    let isEmailOk = false;
    let isHpOk = false;

    // 1. 아이디 유효성 검사(중복체크 포함)
    const btnCheckUid = document.getElementById('btnCheckUid');
    const uidResult = document.getElementsByClassName('uidResult')[0];

    btnCheckUid.onclick = function(){
        const value = formRegister.id.value;

        // 아이디 유효성 검사
        if (!value.match(reUid)) {
            uidResult.innerText = '아이디 형식에 맞지 않습니다.';
            uidResult.style.color = 'red';
            isIdOk = false;
            return;
        }

        // 아이디 중복 체크
        fetch(`/user/id/${value}`)
            .then(response => response.json())
            .then((data) => {
                if (data.count > 0) {
                    // 이미 사용중인 아이디
                    uidResult.innerText = '이미 사용중인 아이디 입니다.';
                    uidResult.style.color = 'red';
                    isIdOk = false;
                } else {
                    // 사용 가능한 아이디
                    uidResult.innerText = '사용 가능한 아이디 입니다.';
                    uidResult.style.color = 'green';
                    isIdOk = true;
                }
            })
            .catch((err) => {
                console.log(err);
            });
    };

    // 2. 비밀번호 유효성 검사
    const passResult = document.getElementsByClassName('passResult')[0];

    formRegister.password2.addEventListener('focusout', function() {
        const value1 = formRegister.password.value;
        const value2 = formRegister.password2.value;

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

    // 3. 이름 유효성 검사
    const nameResult = document.getElementsByClassName('nameResult')[0];

    formRegister.name.addEventListener('focusout', function() {
        const value = this.value;

        if (!value.match(reName)) {
            nameResult.innerText = '이름이 유효하지 않습니다.';
            nameResult.style.color = 'red';
            isNameOk = false;
        } else {
            nameResult.innerText = '';
            isNameOk = true;
        }
    });

    // 6. 휴대폰 유효성 검사(중복체크 포함)
    const hpResult = document.getElementsByClassName('hpResult')[0];

    formRegister.phone.addEventListener('focusout', async function() {
        const value = this.value;

        if (!value.match(reHp)) {
            hpResult.innerText = '휴대폰번호가 유효하지 않습니다. (- 포함)';
            hpResult.style.color = 'red';
            isHpOk = false;
            return;
        }

        // 휴대폰 중복체크
        const response = await fetch(`/user/phone/${value}`);
        const data = await response.json();

        if (data.count > 0) {
            hpResult.innerText = '이미 사용중인 휴대폰번호 입니다.';
            hpResult.style.color = 'red';
            isHpOk = false;
        } else {
            hpResult.innerText = '사용 가능한 휴대폰번호 입니다.';
            hpResult.style.color = 'green';
            isHpOk = true;
        }
    });

    // 5. 이메일 유효성 검사(중복/인증처리 포함)
    const btnSendEmail = document.getElementById('btnSendEmail');
    const emailResult = document.querySelector('.emailResult');
    const auth = document.querySelector('.auth');
    let preventDoubleClick = false;

    btnSendEmail.onclick = async function() {
        const value = formRegister.email.value;

        // 이중 클릭 방지
        if (preventDoubleClick) {
            return;
        }

        if (!value.match(reEmail)) {
            emailResult.innerText = '이메일이 유효하지 않습니다.';
            emailResult.style.color = 'red';
            isEmailOk = false;
            return;
        }

        preventDoubleClick = true;
        const response = await fetch(`/user/email/${value}`);
        const data = await response.json();

        if (data.count > 0) {
            emailResult.innerText = '이미 사용중인 이메일 입니다.';
            emailResult.style.color = 'red';
            isEmailOk = false;
        } else {
            // 인증번호 입력 필드 출력 (이메일 입력란 아래쪽에 표시)
            auth.style.display = 'flex';
        }
    };

    // 인증 코드 비교
    const btnAuthEmail = document.getElementById('btnAuthEmail');

    btnAuthEmail.onclick = async function() {
        const value = formRegister.auth.value;

        // JSON 데이터 생성
        const jsonData = {
            authCode: value
        };

        // 서버 전송
        const response = await fetch('/user/email/auth', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(jsonData)
        });

        const data = await response.json();

        if (data) {
            emailResult.innerText = '이메일이 인증 되었습니다.';
            emailResult.style.color = 'green';
            isEmailOk = true;
        } else {
            emailResult.innerText = '유효한 인증코드가 아닙니다.';
            emailResult.style.color = 'red';
            isEmailOk = false;
        }
    };

    // 최종 폼 전송 이벤트
    formRegister.onsubmit = function(e) {
        // 1) 아이디 유효성 검사 결과
        if (!isIdOk) {
            return false; // 폼 전송 취소
        }

        // 2) 비밀번호 유효성 검사 결과
        if (!isPassOk) {
            return false;
        }

        // 3) 이름 유효성 검사 결과
        if (!isNameOk) {
            return false;
        }

        // 5) 이메일 유효성 검사 결과
        if (!isEmailOk) {
            return false;
        }

        // 6) 휴대폰 유효성 검사 결과
        if (!isHpOk) {
            return false;
        }

        return true; // 폼 전송 시작
    };
});
