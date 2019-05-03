<template>
  <div>
    <div v-if="novels != undefined && novels.length > 0">
      <NovelListItem v-for="novel in novels" v-bind:novel="novel" v-bind:key="novel.novelId"></NovelListItem>
      <div style="text-align:center">
        <Page :total="100"/>
      </div>
    </div>
    <div v-else style="text-align:center">
      <Card style="width:280px;display:inline-block;margin:10px;">
        <div style>
          <img style="width:248px;height:128px" src="@/assets/logo.png">
          <Alert type="warning">没有更多的内容了~</Alert>
        </div>
      </Card>
    </div>
  </div>
</template>

<script>
import NovelListItem from "./NovelListItem";
export default {
  props: ["novels"],
  data() {
    return {};
  },
  components: {
    NovelListItem: NovelListItem
  }
};
</script>