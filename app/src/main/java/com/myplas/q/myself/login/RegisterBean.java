package com.myplas.q.myself.login;

/**
 * Created by 黄双 on 2017/11/9 0009.
 */

public class RegisterBean {

    /**
     * err : 0
     * msg : 注册成功
     * token : 347e7bff4c00d483b97fa2515a540dac
     * user_id : 53806
     * redDot : {"is_socket_connected":0,"unread_supply_and_demand":0,"unread_customer":1,"unread_myorder":0,"unread_who_saw_me":0,"unread_recommend_update":0,"unread_reply_user_msg":0,"unread_reply_purchase_msg":0,"unread_plastic_msg":0,"unread_purchase_msg":0}
     * register_ranking : 460
     */

    private int err;
    private String msg;
    private String token;
    private String user_id;
    private String register_ranking;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


    public String getRegister_ranking() {
        return register_ranking;
    }

    public void setRegister_ranking(String register_ranking) {
        this.register_ranking = register_ranking;
    }
}
