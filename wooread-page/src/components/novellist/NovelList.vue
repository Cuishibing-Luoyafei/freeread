<template>
  <div>
    <div v-if="novels != undefined && novels.length > 0">
      <NovelListItem v-for="novel in novels" v-bind:novel="novel" v-bind:key="novel.novelId"></NovelListItem>
      <div style="text-align:center">
        <Page :total="100"/>
      </div>
    </div>
    <div v-else style="text-align:center">
      <NoConcent></NoConcent>
    </div>
  </div>
</template>

<script>
import NovelListItem from "@/components/novellist/NovelListItem";
import NoConcent from "@/components/nocontent/NoContent";
export default {
  props: ["novels"],
  data() {
    return {};
  },
  components: {
    NovelListItem: NovelListItem,
    NoConcent: NoConcent
  }
};
</script>