<template>
  <div>
    <div v-if="novels != undefined && novels.length > 0">
      <NovelListItem v-for="novel in novels" v-bind:novel="novel" v-bind:key="novel.novelId"></NovelListItem>
      <div style="text-align:center">
        <Page :total="pageInfo.total" :current="pageInfo.curent" :page-size="pageInfo.pageSize" @on-change="onPageChange"/>
      </div>
    </div>
    <div v-else style="text-align:center">
      <NoConcent></NoConcent>
    </div>
  </div>
</template>

<script>
import novelApi from "@/api/novel-api";
import NovelListItem from "@/components/novellist/NovelListItem";
import NoConcent from "@/components/nocontent/NoContent";
import { error } from "util";
export default {
  data() {
    return {
      novels: [],
      pageInfo: {
        current: 1,
        pageSize: 10,
        total: 0
      }
    };
  },
  methods: {
    init() {
      novelApi.listNovelHead(this.buildQueryParam()).then(data => {
        this.novels = data.payload.content;
        this.pageInfo.total = data.payload.totalElements;
      });
    },
    onPageChange(current){
      this.pageInfo.current = current;
      this.init();
    },
    buildQueryParam() {
      let param = {};
      param.className = this.$route.query.className;
      param.page = this.pageInfo.current - 1;
      param.size = this.pageInfo.pageSize;
      return param;
    },
    defaultPageInfo(){
      this.pageInfo.current = 1;
      this.pageInfo.pageSize = 10;
      this.total = 0;
    }
  },
  mounted: function() {
    this.init();
  },
  watch: {
    $route: function(to, from) {
      if (to.query.className != from.query.className) {
        this.defaultPageInfo();
        this.init(); //重新加载数据
      }
    }
  },
  components: {
    NovelListItem: NovelListItem,
    NoConcent: NoConcent
  }
};
</script>