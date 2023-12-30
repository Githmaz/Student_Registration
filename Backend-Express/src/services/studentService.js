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
            return result.insertId ;
        } catch (error) {
            console.error('Error:', error);
            throw new Error('Internal Server Error');
        }

  }


  module.exports = {getAllStudents,addStudnet}