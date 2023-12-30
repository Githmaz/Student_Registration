const mysql = require('mysql2/promise');

module.exports = mysql.createPool({
    host:'localhost',
    user:'root',
    password:'Zandaru1',
    database:'studentDB'
});