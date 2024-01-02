const express = require("express");
const cors = require('cors');
const bodyparser = require('body-parser')
const studentRouter = require("../src/routes/studentRoute")

const app = express();
const port = 8080;


app.use(cors());
app.use(bodyparser.json());

app.use("/student",studentRouter); 

app.listen(port,()=>{
    console.log(`Application started on port : ${port} `)
})