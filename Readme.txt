//POST
http://192.168.1.5:8080/rest/ticket
input:{"userId":"abhishek", "password":"abhishek"}
output: {responseCode:"200", data:{"st":"4AAQSkZJRgABAQEAYABgAAD/4QBaRXhpZgAATU0AKgAAAAg"}}
output: {responseCode:"400", data:{}}

http://192.168.1.5:8080/rest/classes/1001/sections/2001/attendance?userid=abhishek&st=4AAQSkZJRgABAQEAYABgAAD/4QBaRXhpZgAATU0AKgAAAAg	
input:{"studentId":10001, "subjectId":1001, "type":1}

//GET
http://192.168.1.5:8080/rest/classes?userid=abhishek&st=4AAQSkZJRgABAQEAYABgAAD/4QBaRXhpZgAATU0AKgAAAAg	
output:{responseCode:"200", data:[{"className":"XI", "classId":"1001"},{"className":"XII", "classId":"1002"}]}
output:{responseCode:"401", data:{}}

http://192.168.1.5:8080/rest/classes/1001/sections?userid=abhishek&st=4AAQSkZJRgABAQEAYABgAAD/4QBaRXhpZgAATU0AKgAAAAg	
output:{responseCode:"200", data:[{"sectionName":"A","sectionId": "2001"},{"sectionName":"B","sectionId": "2002"}]}
output:{responseCode:"401", data:{}}

http://192.168.1.5:8080/rest/classes/1001/sections/2001/subjects?userid=abhishek&st=4AAQSkZJRgABAQEAYABgAAD/4QBaRXhpZgAATU0AKgAAAAg	
output:{responseCode:"200", data:[{"subjectId":"1001", "subjectName":"Math"},{"subjectId":"1002", "subjectName":"Science"}]}
output:{responseCode:"401", data:{}}

http://192.168.1.5:8080/rest/classes/1001/sections/2001/students?userid=abhishek&st=4AAQSkZJRgABAQEAYABgAAD/4QBaRXhpZgAATU0AKgAAAAg	
output:{responseCode:"200", data:[{"studentName":"Abhishek Sharma","studentId":"10001","type":"0"},{"studentName":"Santosh Kumar","studentId":"10002","type":"0"}]}
output:{responseCode:"401", data:{}}

http://192.168.1.5:8080/rest/images/10001?userid=abhishek&st=4AAQSkZJRgABAQEAYABgAAD/4QBaRXhpZgAATU0AKgAAAAg	

//responseCode 
200 OK
400 authentication failed
401 invalid resource
