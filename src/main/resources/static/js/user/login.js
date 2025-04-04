// 로그인 실패 시
document.addEventListener('DOMContentLoaded', function () {
    // URLSearchParms() -> URL에서 쿼리 파라미터 추출
    const urlParams = new URLSearchParams(window.location.search); // ?뒤에 오는 것을 추출
    if (urlParams.get('code') === '100') {  // ?뒤에 code의 값이 100이면
        alert('로그인에 실패했습니다. 아이디 또는 비밀번호를 확인해주세요.');
    }
});
