<template>
    <el-aside class="nav">
        <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">
          <el-radio-button :label="false">展开</el-radio-button>
          <el-radio-button :label="true">收起</el-radio-button>
        </el-radio-group>
        <nuxt />
    </el-aside>
</template>
<style>
.nav{
    width: 200px;
    background-color: rgb(238, 241, 246);
}
</style>
