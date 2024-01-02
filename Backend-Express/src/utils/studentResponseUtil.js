const studentResponse = {
      
    sendAllStudents:(res,students)=>{
        res.status(200).json({studentList:students });
    },

    sendSuccess: (res, message) => {
      res.status(200).json({ message, success: true });
    },

    sendCreated: (res, Body) => {
        res.status(201).json({ success: true, Body });
    },

    sendNotFound: (res, message) => {
      res.status(404).json({ message, success: false });
    },
  
    sendServerError: (res) => {
      res.status(500).json({ error: 'Internal Server Error' });
    },
    
  };

  module.exports = { studentResponse };