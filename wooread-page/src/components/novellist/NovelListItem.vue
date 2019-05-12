<template>
  <Card style="width:280px;display:inline-block;margin:10px;">
    <div style>
      <img style="width:248px;height:128px" v-bind:src="novel.picture ? novel.picture : 'http://img3.imgtn.bdimg.com/it/u=3995237053,3262873629&fm=214&gp=0.jpg'">
      <Row>
        <Col span="12">
          <div class="novel-desc">{{novel.novelName}}</div>
        </Col>
        <Col span="12">
          <div class="novel-desc">{{novel.className}}</div>
        </Col>
      </Row>
      <!-- <div class="novel-desc">{{novel.description}}</div> -->
    </div>
  </Card>
</template>
<script>
export default {
  props: ["novel"],
  data() {
    return {};
  }
};
</script>
<style scoped>
.novel-desc {
  word-wrap: wrap none;
  word-break:keep-all;
  overflow: hidden;
  padding-left: 10px;
  padding-right: 10px;
}
</style>
