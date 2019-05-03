import requestPostProcess from './request-common';
const URL = "http://192.168.1.9:8092/wooread-user";
const api = {
    login: function (param) {
        return fetch(URL + "/generateJwtToken", {
            method: "post",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(param)
        }).then(resp => {
            return requestPostProcess(resp);
        });
    },
    register: function (param) {
        return fetch(URL + "/createUser", {
            method: "post",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(param)
        }).then(resp => {
            return requestPostProcess(resp);
        });
    }
};

export default api;