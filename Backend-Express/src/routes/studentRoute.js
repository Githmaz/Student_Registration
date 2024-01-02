const express = require("express");
let studnetController = require('../controller/studentController')
const router = express.Router();

router.get('/list',studnetController.getAllStudents); // Get All Students


//_____ Note: The frontend is not yet connected to these API endpoints. It will be added in the next versions.______//

router.post('/add',studnetController.addStudnet); // Add new Student

router.delete('/delete/:id',studnetController.deleteStudent); // Soft delete Studnet
router.delete('/delete/perma/:id',studnetController.deleteStudentPermanently); // Permanent delete Studnet

router.put('/restore/:id',studnetController.restoreStudent); // Restore Student that soft Deleted

router.patch('/update/:id',studnetController.updateStudent); // Update Student

module.exports = router;
