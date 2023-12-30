const db = require('../config/dbconfig')

 //------------------- Get all students -------------------//

    const getAllStudents = async () => {
        try {
            const [rows] = await db.query('SELECT id, first_name, last_name, age, email, phone_number FROM student WHERE deleted_at IS NULL');
            return rows;
        } catch (error) {
            console.error('Error :', error);
            throw new Error('Internal Server Error');
        }
    };

 //------------------ Add a new student ------------------//

    const addStudnet = async({first_name,last_name,age,email,phone_number})=>{
        try {
            const [result] = await db.query('INSERT INTO student (first_name, last_name, age, email, phone_number) VALUES (?, ?, ?, ?, ?)', [first_name, last_name, age, email, phone_number]);
            return {
                Message : "Student Added succesufuly",
                studentId : result.insertId,
            };
        } catch (error) {
            console.error('Error:', error);
            throw new Error('Internal Server Error');
        }

    }

   //------------------ Soft Delete ------------------//

    const deleteStudent = async(studentId)=>{
        try {
            const [result] = await db.query('UPDATE student SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?',[studentId]);
            if (result.affectedRows === 0) {
                return { message: `Student with ID:${studentId.replace(/\n/g, '')} not found`, success: false };
            }
            return { message: `Student with ID:${studentId.replace(/\n/g, '')} deleted successfully`, success: true };
        } catch (error) {
            console.error('Error:', error);
            throw new Error('Internal Server Error');
        } 
    }

  //------------------ Permanent Delete ------------------//

    const deleteStudentPermanently = async (studentId) =>{
        try {
            const [result] = await db.query('DELETE FROM student WHERE id = ?', [studentId]);
            if (result.affectedRows === 0) {
                return { message: `Student with ID:${studentId.replace(/\n/g, '')} not found`, success: false };
            }
            return { message: `Student with ID:${studentId.replace(/\n/g, '')} Permanently Deleted Successfully`, success: true };
        } catch (error) {
            console.error('Error:', error);
            throw new Error('Internal Server Error'); 
        }
    } 

 //------------------ Restore Student ------------------//

    const restoreStudent = async (studentId) => {
        try {
        const [result] = await db.query('UPDATE student SET deleted_at = NULL WHERE id = ?', [studentId]);
    
        if (result.affectedRows === 0) {
            return { message: `Student with ID:${studentId.replace(/\n/g, '')} not found`, success: false };
        }
    
        return { message: `Student with ID:${studentId.replace(/\n/g, '')} restored successfully`, success: true };
        } catch (error) {
        console.error('Error:', error);
        throw new Error('Internal Server Error');
        }
    };

 //------------------ Update Student ------------------//

    const updateStudent = async (studentId, updatedFields) => {
        try {
        const [result] = await db.query('UPDATE student SET ? WHERE id = ? AND deleted_at IS NULL', [updatedFields, studentId]);
    
        if (result.affectedRows === 0) {
            return { message: `Student with ID:${studentId.replace(/\n/g, '')} not found`, success: false };
        }
    
        return { message: `Student with ID:${studentId.replace(/\n/g, '')} updated successfully`, success: true };
        } catch (error) {
        console.error('Error:', error);
        throw new Error('Internal Server Error');
        }
    };
  
  module.exports = {getAllStudents,addStudnet,deleteStudent,deleteStudentPermanently,restoreStudent,updateStudent}