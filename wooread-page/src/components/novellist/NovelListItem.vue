<template>
 
  <Card style="width:280px;display:inline-block;margin:10px;">
        <div style="">
            <img style="width:248px;height:128px" v-bind:src="novel.picture">
            <div>NovelName:zhetian Author:Cui shibing</div>
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
  word-break:normal;
  overflow: hidden;
}
</style>
