<template>
    <div class="layout" style="height:100%">
      <Layout style="height:100%">
        <Header>
           <TopMenu></TopMenu>
        </Header>
        <Content style="height:100%">
          <router-view></router-view>
        </Content>
        <Footer>
          <div style="text-align:center">WOOREAD</div>
        </Footer>
    </Layout>
    </div>
   
</template>
<script>
import TopMenu from './navigation/TopMenu'
export default {
    data(){
        return {
        }
    },
    components:{
        "TopMenu":TopMenu
    }
}
</script>


<style>
html,
body {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}

#app {
  height: 100%;
}
</style>
