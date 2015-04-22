var express = require('express');
var app = express();
var bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
var port = process.env.PORT || 8080; 
var router = express.Router();
var students = [];
var classes = [];
var sections = [];
var subjects = [];
router.get('/classes', function(req, res) {
	console.log("get classes");
    res.json(classes);
});
router.get('/sections', function(req, res) {
	console.log(sections);
    res.json(sections);
});
router.get('/subjects', function(req, res) {
	console.log(subjects);
    res.json(subjects);
});
router.get('/students', function(req, res) {
	console.log(students);
    res.json(students);
});
router.post('/students', function (req, res) {
    var attendence = req.body;
	console.log("post" + JSON.stringify(attendence));
	res.json({"statusCode":"OK", "errorCode":"101"});
    //res.sendStatus(200);
});

app.use('/rest', router);
app.listen(port);
students = [
		{"studentName":"Abhishek Sharma","studentId":"1001","type":"1"},
		{"studentName":"Santosh Kumar","studentId":"1002","type":"2"},
		{"studentName":"Anish Bapana","studentId":"1003","type":"3"},
		{"studentName":"Pardeep Garg","studentId":"1004","type":"1"}
	]
classes = [
	{
		"className":"XI", 
		"classId":"1001"
	},
	{
		"className":"XII", 
		"classId":"1002", 
		
	}
];
sections = [{
				"sectionName":"A",
				"sectionId": "10011",
			},
			{
				"sectionName":"B",
				"sectionId": "10012",
			}
		];
subjects = [
			{"subjectId":"1001", "subjectName":"Math"},
			{"subjectId":"1002", "subjectName":"Science"} 
		]		