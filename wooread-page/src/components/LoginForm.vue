<template>
  <Card class="hv-center" style="width:420px">
    <i-form :model="formItem" :label-width="100">
      <Form-item label="用户名" prop="userName">
        <i-input v-model="formItem.userName" placeholder="请输入"></i-input>
      </Form-item>
      <Form-item label="密码" prop="password">
        <i-input v-model="formItem.password" type="password" placeholder="请输入"></i-input>
      </Form-item>
      <Form-item>
        <i-button @click="login()" type="primary">登录</i-button>
        <i-button @click="goRegister()" style="margin-left: 8px">去注册</i-button>
      </Form-item>
    </i-form>
  </Card>
</template>
<script>
import userApi from "@/api/user-api";
import token from "@/token/token";
export default {
  data() {
    return {
      formItem: {
        userName: '',
        password: ''
      }
    };
  },
  methods: {
    goRegister() {
      console.log("go register");
      this.$router.replace("/RegisterForm");
    },
    login() {
      console.log("login")
      userApi
        .login(this.formItem)
        .then(
          data => {
            token.setToken(data.payload);
            this.$Message.info("登录成功！");
            this.$router.replace("/");
          },
          fail => {
            this.$Message.warning(fail.messageCode);
          }
        )
        .catch(error => {
          this.$Message.error(error);
        });
    }
  }
};
</script>


<style scoped>
.hv-center {
  width: 100%;
  margin: 0 auto; /*水平居中*/
  position: relative;
  top: 50%; /*偏移*/
  transform: translateY(-50%);
}
</style>
