let currentDate = new Date("2025-03-10");  // 시작 날짜 (예시)

// 주간 날짜를 표시하는 함수
function updateMealDate() {
    const startDate = new Date(currentDate);
    const endDate = new Date(currentDate);

    // 시작일은 현재 날짜를 기준으로 설정
    startDate.setDate(currentDate.getDate() - currentDate.getDay() + 1);  // 월요일
    // 종료일은 시작일로부터 4일 뒤 (금요일)
    endDate.setDate(startDate.getDate() + 4);

    // 날짜 포맷팅
    const startFormatted = startDate.getFullYear() + "." + (startDate.getMonth() + 1).toString().padStart(2, '0') + "." + startDate.getDate().toString().padStart(2, '0');
    const endFormatted = endDate.getFullYear() + "." + (endDate.getMonth() + 1).toString().padStart(2, '0') + "." + endDate.getDate().toString().padStart(2, '0');

    // DOM 요소 업데이트 (날짜 표시)
    document.querySelector(".meal_date h2").innerText = `${startFormatted} ~ ${endFormatted}`;

    // 테이블 날짜 업데이트
    const tableHeaders = document.querySelectorAll(".meal_table th");
    const tableRows = document.querySelectorAll(".meal_table tr");

    // 날짜를 테이블 헤더에 설정
    for (let i = 1; i <= 7; i++) {  // 7일 모두 표시 (토요일 포함)
        const day = new Date(startDate);
        day.setDate(startDate.getDate() + i - 1);  // 월요일부터 토요일까지 날짜 설정
        const formattedDate = day.getFullYear() + "." + (day.getMonth() + 1).toString().padStart(2, '0') + "." + day.getDate().toString().padStart(2, '0');
        tableHeaders[i].innerText = `${formattedDate}(${getDayOfWeek(day)})`;  // 요일 추가
    }
}

// 요일을 반환하는 함수
function getDayOfWeek(date) {
    const daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];
    return daysOfWeek[date.getDay()];
}

// 다음 주로 이동
function nextWeek() {
    currentDate.setDate(currentDate.getDate() + 7); // 7일을 더해 다음 주로
    updateMealDate();
}

// 이전 주로 이동
function prevWeek() {
    currentDate.setDate(currentDate.getDate() - 7); // 7일을 빼서 이전 주로
    updateMealDate();
}

// 페이지 로드 시 초기 날짜 설정
window.onload = function() {
    updateMealDate();
}