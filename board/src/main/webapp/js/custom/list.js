// 제목 클릭시 a 태그 기능 중지
// href 에 있는 번호 가져오기 후 actionForm 안 bno value로 삽임
const actionForm = document.querySelector("#actionForm");

// 제목의 a태그가 여러개 있는 경우
// 개별로 처리
//const allA = document.querySelectorAll(".table a");
//allA.forEach("a", ()=>{
//	a.addEventListner("click",(e)=>{
//		e.preventDefault();
		
		// href 값 가져오기
//	})	
//})
// 부모에게 전달되는 이벤트 버블링
document.querySelector("tbody").addEventListener("click",(e)=>{
	
	e.preventDefault();
	
	console.log(e.target);
	console.log(e.target.href);
	console.log(e.target.getAttribute("href"));
	
	const bno = e.target.getAttribute("href");
	
	actionForm.querySelector("[name='bno']").value = bno;
	
	// 삽입 후 확인
	console.log(actionForm.innerHTML);
	
	// actionForm submit
	// action : /read.do 변경
	actionForm.action= "/read.do";
	actionForm.submit();	
	
});