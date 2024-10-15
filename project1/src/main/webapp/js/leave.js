/**
 * 
 */
// 회원 탈퇴 클릭 시 form submit 중지
// confirm(정말로 탈퇴하시겠습니까?)
document.querySelector("form").addEventListener("submit",(e)=>{
	// submit 중지
	e.preventDefault();
	
	// 확인 : trut 취소 : false
	const result = confirm("정말로 탈퇴하시겠습니까?");
	
	// 확인인 경우 form submit
	if (result){
		e.target.submit();
	}
	
});