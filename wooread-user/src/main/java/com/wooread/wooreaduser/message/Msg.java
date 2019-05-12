package com.wooread.wooreaduser.message;

public class Msg {

    public enum RoleMsg {
        DUPLICATE_ROLE("duplicate.role"),
        NO_SUCH_ROLE("no.such.role"),
        INVALID_ROLE_ID("invalid.role.ids");
        private String msgCode;

        RoleMsg(String msgCode) {
            this.msgCode = msgCode;
        }

        @Override
        public String toString() {
            return this.msgCode;
        }
    }

    public enum UserMsg {
     DUPLICATE_USER("duplicate.user"),
        NO_SUCH_USER("no.such.user"),
        NO_SUCH_USER_INFO("no.such.user.info");

        private String msgCode;
        UserMsg(String msgCode) {
            this.msgCode = msgCode;
        }
        @Override
        public String toString() {
            return this.msgCode;
        }
    }
    public enum LoginMsg {
        WRONG_PASSWORD("wrong.password");
        private String msgCode;
        LoginMsg(String msgCode) {
            this.msgCode = msgCode;
        }

        @Override
        public String toString(){
            return this.msgCode;
        }
    }
}
