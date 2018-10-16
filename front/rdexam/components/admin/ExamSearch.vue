<template>
  <el-form :inline="true" label-width="80px" :model="form">
    <el-form-item label="题目名称">
      <el-input v-model="form.text"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">查询</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  export default {
    name: "ExamSearch",
    data() {
      return {
        form: {
          text: ''
        }
      }
    },
    computed: {
      activeTab(){
        return this.$store.state.admin.activeTabName
      }
    },
    methods: {
      onSubmit() {
        if (!this.form.text) {
          return;
        }
        let param = new URLSearchParams();
        param.append("text",this.form.text);
        param.append("collectionName",this.activeTab);
        this.$axios.post('/exam/queryByName',param).then(res => {
          if (res.data) {
            this.$store.commit('exam/getPageData', {data: res.data, tab: this.activeTab})
          }
        });
      }
    }
  }
</script>

<style scoped>

</style>
