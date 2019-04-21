const TOKEN_NAME = "token";
export default {
    setToken:function(token){
        localStorage.setItem(TOKEN_NAME,token);
    },
    getToken:function(){
        return localStorage.getItem(TOKEN_NAME);
    }
}