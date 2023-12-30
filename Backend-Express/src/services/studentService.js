const db = require('../config/dbconfig')

 //------------------- Get all students -------------------//
const getAllStudents = async () => {
    return db.query('SELECT * FROM students')
      .then(([rows]) => rows)
      .catch(error => {
        console.error('Error fetching students:', error);
        throw new Error('Internal Server Error');
      });
  };





  module.exports = {getAllStudents}