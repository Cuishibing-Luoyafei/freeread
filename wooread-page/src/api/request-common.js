import router from '@/router/index';

const API_ADDRESS = 'http://localhost:8092';

const postProcessRequest = function (resp) {
    return new Promise((resolve, reject) => {
        if (resp.ok) {
            let data = resp.json();
            data.then(d => {
                console.info("resp:%s", JSON.stringify(d))
                if (d.code == 2) {
                    console.info("success")
                    resolve(d);
                } else if (d.code == 4) {
                    console.info("fail")
                    if (d.messageCode == 'no-token' || d.messageCode=='expire-token' || d.messageCode == 'invalid-token') {
                        console.info("no-token")
                        router.push("/LoginForm");
                    }
                    reject(d);
                } else {
                    console.info("exception")
                    throw new Error(d.message);
                }
            });
        } else {
            console.info("request error")
            throw new Error("request_error");
        }
    });
}
export { API_ADDRESS };
export default postProcessRequest;