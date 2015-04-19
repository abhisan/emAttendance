var express = require('express');
var app = express();
var bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
var port = process.env.PORT || 8080; 
var router = express.Router();
router.get('/students', function(req, res) {
	console.log("get");
    res.json([
		{"studentName":"Abhishek Sharma","studentId":"1001","type":"1"},
		{"studentName":"Santosh Kumar","studentId":"1002","type":"2"},
		{"studentName":"Anish Bapana","studentId":"1003","type":"3"},
		{"studentName":"Pardeep Garg","studentId":"1004","type":"1"}
	]);
});
router.post('/students', function (req, res) {
    var attendence = req.body;
	console.log("post" + JSON.stringify(attendence));
	res.json({"statusCode":"OK", "errorCode":"101"});
    //res.sendStatus(200);
});
app.use('/rest', router);
app.listen(port);