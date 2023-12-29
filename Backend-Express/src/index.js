const express = require("express");
const cors = require('cors');
const bodyparser = require('body-parser')
const app = express();
const port = 4000;


app.use(cors());
app.use(bodyparser.json());


app.listen(port,()=>{
    console.log(`Application started on port : ${port} `)
})