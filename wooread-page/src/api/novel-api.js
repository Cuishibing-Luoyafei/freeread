import requestPostProcess from './request-common';
const URL = "http://192.168.1.9:8092/wooread-novel";
import token from "@/token/token";
const api = {
    getNovelClasses:function(param) {
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
    }
}

export default api;