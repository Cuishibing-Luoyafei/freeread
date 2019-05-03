<template>
  <Card style="width:280px;display:inline-block;margin:10px;">
    <div style>
      <img style="width:248px;height:128px" v-bind:src="novel.picture">
      <Row>
        <Col span="12">
          <div class="novel-desc">西游记</div>
        </Col>
        <Col span="12">
          <div class="novel-desc">罗贯中</div>
        </Col>
      </Row>
      <div class="novel-desc">{{novel.description}}</div>
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
  word-break: normal;
  overflow: hidden;
}
</style>
