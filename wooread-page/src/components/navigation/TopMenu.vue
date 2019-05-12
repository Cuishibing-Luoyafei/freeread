<template>
  <div>
    <Menu mode="horizontal" theme="dark" @on-select="onMenuSelect">
      <Row>
        <Col span="16">
          <MenuItem name="-1">
            <span class="logo">WOO READ</span>
          </MenuItem>
          <MenuItem
            v-bind:name="novelClass.className"
            v-bind:key="novelClass.classId"
            v-for="novelClass in novelClasses"
          >{{novelClass.className}}</MenuItem>
        </Col>
        <Col span="8">
          <Submenu name="user-center">
            <template slot="title">
              <Icon type="ios-analytics"/>个人中心
            </template>
            <MenuItem name="AddNovel">添加小说</MenuItem>
          </Submenu>
          <MenuItem>
            <Input suffix="ios-search" placeholder="Enter text" style="width: auto"/>
          </MenuItem>
        </Col>
      </Row>
    </Menu>
  </div>
</template>

<script>
import novelApi from "@/api/novel-api";
export default {
  data() {
    return {
      novelClasses: []
    };
  },
  methods: {
    onMenuSelect: function(name) {
      if (name == undefined) {
        return;
      }
      if (name == "-1") {
        console.info("go home");
        this.$router.replace("/NovelList");
      } else if (name == "AddNovel") {
        this.$router.push('/NewNovelHead');
      } else {
        this.$router.replace({ path: "/NovelList", query: { className: name } });
      }
    }
  },
  mounted: function() {
    novelApi.getNovelClasses({}).then(data => {
      this.novelClasses = data.payload;
    });
  }
};
</script>

<style>
.logo {
  width: 30px;
  margin: 10px;
  font-size: 25px;
  font-family: fantasy;
}
</style>

