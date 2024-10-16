// 추가 클릭 시 form submit 일어나면
// description 요소를 제외한 모든 요소에 값이 있는지 확인
document.querySelector("body div:nth-child(3) form").addEventListener("submit", (e) => {
	e.preventDefault();
	const form = e.target;

	const code = form.querySelector("#code");
	const title = form.querySelector("#title");
	const writer = form.querySelector("#writer");
	const price = form.querySelector("#price");

	// 1 (1) ~(10)
	// * : 0 ~ 무한대 
	const codeReg = /^[0-9]{4}$/;
	const textReg = /[A-Za-z가-힣0-9]+$/;
	const priceReg = /^[0-9]{1,10}$/;

	if (!codeReg.test(code.value)) {
		alert("도서코드입력 (4자리)");
		code.select(); // focus + 입력값 존재 시 블럭으로 잡아줌
		return;
	} else if (!textReg.test(title.value)) {
		alert("도서명을 입력하세요 (1~10자리 까지)");
		title.select(); // focus + 입력값 존재 시 블럭으로 잡아줌
		return;
	} else if (!textReg.test(writer.value)) {
		alert("저자를 입력하세요 (1~10자리 까지)");
		writer.select(); // focus + 입력값 존재 시 블럭으로 잡아줌
		return;
	} else if (!priceReg.test(price.value)) {
		alert("가격을 입력하세요 (1~10자리 까지)");
		price.select(); // focus + 입력값 존재 시 블럭으로 잡아줌
		return;


		// 이상이 없을 경우 form 전송
	}
	form.submit();
});