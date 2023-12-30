const express = require("express");
let studnetController = require('../controllers/student.controller')
const router = express.Router();



router.get('/list',studnetController.getAllStudents); // Get All Students


module.exports = router;
