<template>
  <Card class="hv-center" style="width:420px">
    <i-form class="login-form" :model="formItem" :label-width="110">
      <Form-item label="用户名">
        <i-input :value.sync="formItem.userName" placeholder="请输入"></i-input>
      </Form-item>
      <Form-item label="密码">
        <i-input :value.sync="formItem.password" type="password" placeholder="请输入"></i-input>
      </Form-item>
      <Form-item label="再次输入密码">
        <i-input :value.sync="formItem.rePassword" type="password" placeholder="请输入"></i-input>
      </Form-item>
      <Form-item label="手机号码">
        <i-input :value.sync="formItem.phone" placeholder="请输入"></i-input>
      </Form-item>
      <Form-item>
        <i-button type="primary">提交</i-button>
      </Form-item>
    </i-form>
  </Card>
</template>
<script>
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
  }
};
</script>


<style>
html,
body {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}

.hv-center {
  width: 100%;
  margin: 0 auto; /*水平居中*/
  position: relative;
  top: 50%; /*偏移*/
  transform: translateY(-50%);
}

#app {
  height: 100%;
}
</style>
