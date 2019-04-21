<template>
    <Slider :value.sync="value" range></Slider>
</template>
<script>
    export default {
       
        data () {
            return {
                value: [20, 50]
            }
        }
    }
</script>