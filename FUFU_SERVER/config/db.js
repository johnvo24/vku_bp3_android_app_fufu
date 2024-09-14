const mysql = require('mysql');
const config = require('./config.json');

const db = mysql.createConnection(config.development);

module.exports = db;