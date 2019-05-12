import requestPostProcess from './request-common';
import {API_ADDRESS} from './request-common';
const URL = API_ADDRESS + "/wooread-user";
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