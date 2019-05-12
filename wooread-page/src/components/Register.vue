<template>
  <Card class="hv-center" style="width:420px">
    <i-form :model="formItem" :label-width="100">
      <Form-item label="用户名" prop="userName">
        <i-input v-model="formItem.userName" placeholder="请输入"></i-input>
      </Form-item>
      <Form-item label="密码" prop="password">
        <i-input v-model="formItem.password" type="password" placeholder="请输入"></i-input>
      </Form-item>
      <Form-item label="再次输入密码" prop="password">
        <i-input v-model="formItem.rePassword" type="password" placeholder="请输入"></i-input>
      </Form-item>
      <Form-item label="电话" prop="phone">
        <i-input v-model="formItem.phone" type="phone" placeholder="请输入"></i-input>
      </Form-item>
      <Form-item>
        <i-button @click="submit()" type="primary">注册</i-button>
        <i-button @click="reset()" style="margin-left: 8px">重置</i-button>
      </Form-item>
    </i-form>
  </Card>
</template>
<script>
import userApi from "../api/user-api";
export default {
  data() {
    return {
      formItem: {
        userName: "",
        password: "",
        rePassword: "",
        phone: ""
      }
    };
  },
  methods: {
    submit() {
      userApi.register(this.formItem).then(
        data => {
          this.$Message.info("注册成功！");
           this.$router.replace("/LoginForm");
        },
        fail => {
          this.$Message.warning(fail.messageCode);
        }
      );
    },
    reset() {
      this.formItem = {};
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
