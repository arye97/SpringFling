import axios from 'axios';

require('dotenv').config();

//make our server object
const SERVER_URL = "http://localhost:8080";

const server = axios.create({
    baseURL: SERVER_URL,
    timeout: 10000
});

function getTokenHeader() {
    let token = sessionStorage.getItem("token");
    let header = {
        headers: {"Token": token, "Access-Control-Allow-Origin": "*", "Content-Type": "application/json"},
        withCredentials: true
    };
    return header;
}




// eslint-disable-next-line import/no-anonymous-default-export
export default {
    login: (loginData) => server.post('/login', loginData, getTokenHeader()),
    register: (registerData) => server.post('/register', registerData),
    logout: () => server.post('/logout', null, getTokenHeader())
}

