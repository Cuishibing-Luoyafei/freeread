package com.wooread.wooreadnovel.message;

public class Msg {
    public enum NovelHeadMsg{
        DUPLICATE_NOVEL("duplicate.novel"),
        NO_SUCH_CREATOR("no.such.creator"),
        NO_SUCH_NOVEL("no.such.novel");
        private String msgCode;
        NovelHeadMsg(String msgCode) {
            this.msgCode = msgCode;
        }
        @Override
        public String toString() {
            return msgCode;
        }
    }
}
