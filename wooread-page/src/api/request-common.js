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

export default postProcessRequest;