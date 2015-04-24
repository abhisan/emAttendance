# emAttendance
Attendace module to capture student's attendance.

//GET
<SERVER_URL>/rest/classes?userId=abhishek&token=HOI5VcH7McqFoAP-tIHQDg
<SERVER_URL>/rest/classes/1?userId=abhishek&token=HOI5VcH7McqFoAP-tIHQDg
<SERVER_URL>/rest/classes/1/sections?userId=abhishek&token=HOI5VcH7McqFoAP-tIHQDg
<SERVER_URL>/rest/classes/1/sections/1?userId=abhishek&token=HOI5VcH7McqFoAP-tIHQDg
<SERVER_URL>/rest/classes/1/sections/subjects?userId=abhishek&token=HOI5VcH7McqFoAP-tIHQDg
<SERVER_URL>/rest/classes/1/sections/subjects/1?userId=abhishek&token=HOI5VcH7McqFoAP-tIHQDg
<SERVER_URL>/rest/classes/1/sections/1/students/?userId=abhishek&token=HOI5VcH7McqFoAP-tIHQDg
<SERVER_URL>/rest/classes/1/sections/1/students/1?userId=abhishek&token=HOI5VcH7McqFoAP-tIHQDg
<SERVER_URL>/rest/classes/1/sections/1/students/1/images?userId=abhishek&token=HOI5VcH7McqFoAP-tIHQDg

//POST
<SERVER_URL>/rest/token?userId=abhishek&token=HOI5VcH7McqFoAP-tIHQDg
<SERVER_URL>/rest/classes/1/sections/1/subjects/1/attendances?userId=abhishek&token=HOI5VcH7McqFoAP-tIHQDg

ResponseEntity{
	responseCode:"",
	data:""
}
e.g.
//login failure
ResponseEntity{
	responseCode:"100",
	data:""
}
//login successful
ResponseEntity{
	responseCode:"200",
	data:"{token:"HOI5VcH7McqFoAP-tIHQDg"}"
}
//invalid token
ResponseEntity{
	responseCode:"101",
	data:""
}
//invalid url
ResponseEntity{
	responseCode:"102",
	data:""
}
