import requestPostProcess from '@/api/request-common';
import token from "@/token/token";
import {API_ADDRESS} from './request-common';
console.log("API_ADDRESS:%s",API_ADDRESS);
const URL = API_ADDRESS+"/wooread-novel";
const api = {
    getNovelClasses:function() {
        console.log("url:%s",JSON.stringify(API_ADDRESS))
        return fetch(URL + "/novelClasses", {
            method: "get",
            headers: token.addAuthHeader()
        }).then(resp => {
            return requestPostProcess(resp);
        });
    },
    putNovelClass:function(param){
        return fetch(URL + "/novelClass", {
            method: "put",
            headers: token.addAuthHeader({
                "Content-Type": "application/json"
            }),
            body: JSON.stringify(param)
        }).then(resp => {
            return requestPostProcess(resp);
        });
    },
    deleteNovelClass:function(id) {
        return fetch(URL + '/novelClass/'+id, {
            method: "delete",
            headers: token.addAuthHeader({
                "Content-Type": "application/json"
            }),
            body: JSON.stringify({})
        }).then(resp => {
            return requestPostProcess(resp);
        });
    },
    putNovelHead:function(param) {
        return fetch(URL + '/novelHead/', {
            method: "put",
            headers: token.addAuthHeader({
                "Content-Type": "application/json"
            }),
            body: JSON.stringify(param)
        }).then(resp => {
            return requestPostProcess(resp);
        });
    }
}

export default api;