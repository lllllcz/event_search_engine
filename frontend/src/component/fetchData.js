import {createBrowserHistory} from "history";
const axios = require('axios');
export const fetchData = () => {
    return axios.get("http://124.70.59.249:8080/login/sjtu/123456");
}

export const fetchData1 = () => {
    return axios.get("http://124.70.59.249:8080/login/sjtu1/12345");
}

export const fetchData2 = () => {
    return axios.get("http://124.70.59.249:8080/register/sjtu/12345");
}

export const fetchData3 = () => {
    return axios.get("http://124.70.59.249:8080/register/sjtu2/12345");
}