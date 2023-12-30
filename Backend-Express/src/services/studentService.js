const db = require('../config/dbconfig')

 //------------------- Get all students -------------------//
 const getAllStudents = async () => {
    try {
      const [rows] = await db.query('SELECT * FROM students');
      return rows;
    } catch (error) {
      console.error('Error fetching students:', error);
      throw new Error('Internal Server Error');
    }
  };




  module.exports = {getAllStudents}