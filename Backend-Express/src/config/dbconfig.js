const mysql = require('mysql2');

module.exports = mysql.createConnection({
    host:'localhost',
    user:'root',
    password:'Zandaru1',
    database:'icet'
});