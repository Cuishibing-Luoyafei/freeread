<template>
  <Card class="hv-center" style="width:420px">
    <i-form :model="formItem" :label-width="100">
      <Form-item label="小说名" prop="novelName">
        <i-input v-model="formItem.novelName" placeholder="请输入"></i-input>
      </Form-item>
      <Form-item label="描述" prop="description">
        <i-input v-model="formItem.description" type="password" placeholder="请输入"></i-input>
      </Form-item>
     
      <Form-item>
        <i-button @click="submit()" type="primary">添加</i-button>
        <i-button @click="cancel()" style="margin-left: 8px">取消</i-button>
      </Form-item>
    </i-form>
  </Card>
</template>
<script>
import novelApi from "@/api/novel-api";
export default {
  data() {
    return {
      formItem: {
        novelName: "",
        description: ""
      }
    };
  },
  methods: {
    submit() {
      novelApi.putNovelHead(this.formItem).then(
        data => {
          this.$Message.info("添加成功！");
           this.$router.replace("/NovelList");
        },
        fail => {
          this.$Message.warning(fail.message);
        }
      );
    },
    cancel() {
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
