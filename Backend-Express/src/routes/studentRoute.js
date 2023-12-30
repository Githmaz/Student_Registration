const express = require("express");
let studnetController = require('../controller/studentController')
const router = express.Router();

router.get('/list',studnetController.getAllStudents); // Get All Students

router.post('/add',studnetController.addStudnet); // add new Student

router.delete('/delete/:id',studnetController.deleteStudent); // soft delete Studnet
router.delete('/delete/perma/:id',studnetController.deleteStudentPermanently); // Permanent delete Studnet

router.put('/restore/:id',studnetController.restoreStudent); // Restore Student that soft Deleted


module.exports = router;
