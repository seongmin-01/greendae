
const departmentData = {
    humanities: ["국어국문학과", "영어영문학과", "일어일문학과", "중어중문학과","역사학과","경제학과","경영학과","법학과","철학과","정치외교학과","행정학과","사회복지학과","유아교육학과"],
    naturalscience: ["수학과","물리학과","화학과","천문학과","지구과학과","생명과학과","미생물학과","해양학과"],
    engineering: ["기계공학과", "전자공학과", "전기공학과", "컴퓨터공학과","건축공학과","재료공학과","화학공학과"],
    teacher: ["국어교육과","영어교육과","수학교육과","윤리학과","교육학과","사회교육과","역사교육과","체육교육과","특수교육과"],
    graduateSchool: ["경영대학관원","경제대학원","행정대학원","교육대학원","산업대학원"]
};

function updateDepartments() {
    const collegeSelect = document.getElementById("collegeSelect");
    const departmentSelect = document.getElementById("departmentSelect");
    
    // 선택한 단과대 가져오기
    const selectedCollege = collegeSelect.value;

    // 학과 목록 초기화
    departmentSelect.innerHTML = "<option value=''>학과를 선택하세요</option>";

    // 학과 목록 갱신
    if (selectedCollege && departmentData[selectedCollege]) {
        departmentData[selectedCollege].forEach(dept => {
            const option = document.createElement("option");
            option.value = dept;
            option.textContent = dept;
            departmentSelect.appendChild(option);
        });
    }
}