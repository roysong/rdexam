<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>题目：</span>
      <el-button style="float: right;" type="info" size="small">再来一套</el-button>
    </div>
    <div v-for="o in 4" :key="o" class="text item">
      {{'列表内容 ' + o }}
    </div>
  </el-card>
</template>

<script>
    export default {
        name: "ExamRegion"
    }
</script>

<style scoped>
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    margin: 10px;
  }
</style>
