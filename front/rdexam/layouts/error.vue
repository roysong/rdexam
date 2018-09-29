<template>
  <div class="container">
    <h1 v-if="error.statusCode === 404">页面不存在</h1>
    <h1 v-else>应用发生错误异常</h1>
    <nuxt-link to="/">首 页</nuxt-link>
  </div>
</template>

<script>
export default {
  props: ['error']
}
</script>
<style>
.container{
    height: 500px;
    display: flex;
    flex-flow: column;
    justify-content: space-around;
    align-items: center;
}
</style>
