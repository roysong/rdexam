<template>
  <div>
    <exam-dialog v-bind:form-data="formData" title="编辑题目" v-bind:dialog-form-visible="dialogFormVisible"
                 @success="submitExam" @close-dialog="changeDialog" />

    <el-table
      :data="tableData"
      :cell-class-name="tableCellClassName"
      border
      style="width: 100%">
      <el-table-column
        prop="title"
        label="题面"
        min-width="400">
      </el-table-column>
      <el-table-column
        prop="options[0]"
        label="选项a"
        width="300">
      </el-table-column>
      <el-table-column
        prop="options[1]"
        label="选项b"
        width="300">
      </el-table-column>
      <el-table-column
        prop="options[2]"
        label="选项c"
        width="300">
      </el-table-column>
      <el-table-column
        prop="options[3]"
        label="选项d"
        width="300">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <i class="el-icon-info" @click="editItem(scope.row)"></i>
          <i class="el-icon-error" @click="deleteItem(scope.row)"></i>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import ExamDialog from "./ExamDialog";
  export default {
    data(){
      return {
        formData:{
          title: '',
          options: [],
          tags: [],
          right: '',
          major: ''
        },
        dialogFormVisible:false
      }
    },
    components: {ExamDialog},
    methods: {
      getExamList() {
        let activeTab = this.$store.state.admin.activeTabName;
        let param = new URLSearchParams();
        param.append('activeTabName', activeTab);
        param.append('currentPage', this.$store.state.exam.currentPage);
        param.append('pageSize', this.$store.state.exam.pageSize);
        this.$axios.post(this.$store.state.exam.pageUrl, param).then(res => {
          let data = res.data
          this.$store.commit('exam/getPageData', {data: data, tab: activeTab})
        });
      },
      tableCellClassName({row, column, rowIndex, columnIndex}) {
        if (columnIndex === row.rightIdx) {
          return 'right-option'
        }
        return ''
      },
      editItem(row) {
        this.formData = {...row}
        this.formData.options = [...row.options];
        this.changeDialog()
      },
      deleteItem(row) {
        let params = new URLSearchParams();
        params.append("id", row.id);
        params.append("collectionName", row.major);
        this.$axios.post('/exam/deleteItem', params).then(res => {
          if (res.data && res.data.state) {
            this.$message.success("删除成功");
            this.getExamList()
          } else {
            this.$message.error("删除失败");
          }
        })
      },
      submitExam(){
        this.$axios.post('/exam/updateExam', this.formData).then(res => {
          if (res.data && res.data.state) {
            this.$message.success("修改成功");
          } else {
            this.$message.error("修改失败");
          }
        });
        this.changeDialog();
      },
      changeDialog(){
        this.dialogFormVisible = !this.dialogFormVisible
      },
    },
    beforeCreate() {
      let activeTab = this.$store.state.admin.activeTabName;
      let param = new URLSearchParams();
      param.append('activeTabName', activeTab);
      param.append('currentPage', this.$store.state.exam.currentPage);
      param.append('pageSize', this.$store.state.exam.pageSize);
      this.$axios.post(this.$store.state.exam.pageUrl, param).then(res => {
        let data = res.data
        this.$store.commit('exam/getPageData', {data: data, tab: activeTab})
      });
    },
    computed: {
      tableData: {
        get: function () {
          return this.$store.state.exam.examDatas
            .filter(i => i.tab === this.$store.state.admin.activeTabName)
            .map(i => i.data)[0]
        }
      }
    }
  }
</script>

<style>
  .right-option {
    background: #f0f9eb;
  }
</style>
