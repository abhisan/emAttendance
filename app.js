var express = require('express');
var app = express();
var bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
var port = process.env.PORT || 8080; 
var router = express.Router();
router.get('/students', function(req, res) {
    res.json([{"studentName":"Abhishek Sharma","studentId":"1001","type":"1"}]);
});
router.post('/students', function (req, res) {
    var attendence = req.body;
    console.log(attendence);
	res.json([{"status":"OK"}]);
    //res.sendStatus(200);
});
app.use('/rest', router);
app.listen(port);