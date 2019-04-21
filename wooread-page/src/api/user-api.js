const URL = "http://localhost:8092";
const api = {
    login: function (param) {
        return fetch(URL+"/wooread-user/generateJwtToken", {
            method: "post",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(param)
        }).then(resp => {
            return new Promise((resolve, reject) => {
                if (resp.ok) {
                    resolve(resp.json())
                } else {
                    reject('request_error')
                }
            });
        }).then(data => {
            const code = data.code;
            return new Promise((resolve,reject)=>{
                if(code == 2) {
                    resolve(data);
                }else if(code == 4) {
                    reject(data);
                }else {
                    throw new Error(data.message);
                }
            });
        });
    }
};

export default api;