const express = require("express");
let studnetController = require('../controllers/student.controller')
const router = express.Router();

router.get('/list',studnetController.getAllStudents); // Get All Students
router.post('/add',studnetController.addStudnet); // add new Student
// router.delete('/delete',studnetController.deleteStudent); // delete Studnet
module.exports = router;
