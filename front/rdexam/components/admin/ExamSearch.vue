<template>
  <div>
    <el-form :inline="true" label-width="80px" :model="form">
      <el-form-item label="题目名称">
        <el-input v-model="form.text"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
        <el-button icon="el-icon-circle-plus" @click="changeDialog" type="success" style="float: right;">
          新增
        </el-button>
      </el-form-item>
    </el-form>
    <exam-dialog v-bind:form-data="addForm" title="新增题目" v-bind:dialog-form-visible="dialogFormVisible"
                 @success="addItem" @close-dialog="changeDialog"/>
  </div>
</template>

<script>
  import ExamDialog from "./ExamDialog";
  export default {
    name: "ExamSearch",
    components: {ExamDialog},
    data() {
      return {
        form: {
          text: ''
        },
        addForm: {
          title: '',
          options: [],
          tags: [],
          right: '',
          major: ''
        },
        dialogFormVisible: false
      }
    },
    computed: {
      activeTab() {
        return this.$store.state.admin.activeTabName
      }
    },
    methods: {
      onSubmit() {
        if (!this.form.text) {
          return;
        }
        let param = new URLSearchParams();
        param.append("text", this.form.text);
        param.append("collectionName", this.activeTab);
        this.$axios.post('/exam/queryByName', param).then(res => {
          if (res.data) {
            this.$store.commit('exam/getPageData', {data: res.data, tab: this.activeTab})
          }
        });
      },
      addItem() {
        this.addForm.major = this.activeTab;
        this.dialogFormVisible = false;

        this.$axios.post('/exam/addExam', this.addForm).then(res => {
          if (res.data && res.data.state) {
            this.$message.success("添加成功");
            this._restAddForm();
          } else {
            this.$message.error("添加失败");
          }
        })
      },
      changeDialog(){
        this.dialogFormVisible = !this.dialogFormVisible
      },
      _restAddForm() {
        this.addForm.title = '';
        this.addForm.options = [];
        this.addForm.tags = [];
        this.addForm.right = '';
      }
    }
  }
</script>

<style scoped>

</style>
