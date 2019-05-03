const TOKEN_NAME = "token";
const TOKEN_HEADER_NAME = "Authorization";
export default {
    setToken: function (token) {
        localStorage.setItem(TOKEN_NAME, token);
    },
    getToken: function () {
        return localStorage.getItem(TOKEN_NAME);
    },
    addAuthHeader: function (headers) {
        if (headers == null || headers == undefined) {
            headers = {};
        }
        headers[TOKEN_HEADER_NAME] = this.getToken();
        return headers;
    }
}