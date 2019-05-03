<template>
    <div>
        <NovelListItem v-for="novel in novels" v-bind:novel="novel" v-bind:key="novel.novelId"></NovelListItem>
    </div>
</template>

<script>
import NovelListItem from './NovelListItem'
export default {
    props:['novels'],
    data(){
        return {

        }
    },
    components:{
        "NovelListItem":NovelListItem
    }
}
</script>