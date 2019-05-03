<template>
  <div>
    <Menu mode="horizontal" theme="dark">
      <Row>
        <Col span="20">
          <MenuItem name="-1">
            <span class="logo">WOO READ</span>
          </MenuItem>
          <MenuItem v-bind:name="novelClass.classId" v-bind:key="novelClass.classId" v-for="novelClass in novelClasses">{{novelClass.className}}</MenuItem>
        </Col>
        <Col span="4">
          <MenuItem>
            <Input suffix="ios-search" placeholder="Enter text" style="width: auto"/>
          </MenuItem>
        </Col>
      </Row>
    </Menu>
  </div>
</template>

<script>
import novelApi from '@/api/novel-api'
export default {
  data() {
    return {
      novelClasses:[]
    };
  },
  mounted:function(){
    novelApi.getNovelClasses({}).then(data=>{
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

