const studentService = require('../services/studentService')
const { studentResponse } = require('../utils/studentResponseUtil')


//------------------- Get all students -------------------//

const getAllStudents =async (req, res)=>{
    try {
        studentResponse.sendAllStudents(res,await studentService.getAllStudents())
    } catch (error) {
        studentResponse.sendServerError(res);
    }
}

//------------------ Add a new student ------------------//

const addStudnet = async(req,res) =>{
    try {
        studentResponse.sendCreated(res,await studentService.addStudnet(req.body))
    } catch (error) {
        studentResponse.sendServerError(res);
    }
}

//------------------ Soft Delete ------------------//

const deleteStudent = async (req, res)=>{
    try {
        const result =  await studentService.deleteStudent(req.params.id);
        if (!result.success) {
            studentResponse.sendNotFound(res,result.message);
          } else {
            studentResponse.sendSuccess(res,result.message);
          } 
    } catch (error) {
        studentResponse.sendServerError(res);
    }
}

//------------------ Permanet Delete ------------------//

const deleteStudentPermanently = async (req, res)=>{
    try {
       const result = await studentService.deleteStudentPermanently(req.params.id);
        if (!result.success) {
            studentResponse.sendNotFound(res,result.message);
          } else {
            studentResponse.sendSuccess(res,result.message);
          }
    } catch (error) {
        studentResponse.sendServerError(res);
    }
}

//------------------ Resotore Delete ------------------//

const restoreStudent = async (req, res)=>{
    try {
        const result =  await studentService.restoreStudent(req.params.id);
        if (!result.success) {
            studentResponse.sendNotFound(res,result.message);
          } else {
            studentResponse.sendSuccess(res,result.message);
          }
    } catch (error) {
        studentResponse.sendServerError(res);
    }
}

//------------------ Update Student ------------------//

const updateStudent = async (req, res) => {
    try {
        const result = await studentService.updateStudent(req.params.id, req.body);
        if (!result.success) {
            studentResponse.sendNotFound(res,result.message);
        } else {
            studentResponse.sendSuccess(res,result.message);
        }
    } catch (error) {
        studentResponse.sendServerError(res);
    }
}

module.exports = { getAllStudents , addStudnet ,deleteStudent,deleteStudentPermanently,restoreStudent,updateStudent};