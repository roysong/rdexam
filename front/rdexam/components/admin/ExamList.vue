<template>
  <el-table
    :data="tableData"
    :cell-class-name="tableCellClassName"
    border
    style="width: 100%">
    <el-table-column
      prop="question"
      label="题面"
      min-width="400">
    </el-table-column>
    <el-table-column
      prop="optiona"
      label="选项a"
      width="300">
    </el-table-column>
    <el-table-column
      prop="optionb"
      label="选项b"
      width="300">
    </el-table-column>
    <el-table-column
      prop="optionc"
      label="选项c"
      width="300">
    </el-table-column>
    <el-table-column
      prop="optiond"
      label="选项d"
      width="300">
    </el-table-column>
    <el-table-column
      fixed="right"
      label="操作"
      width="100">
      <template slot-scope="scope">
        <i class="el-icon-circle-plus" @click="addItem"></i>
        <i class="el-icon-info" @click="editItem(scope.row)"></i>
        <i class="el-icon-error" @click="deleteItem(scope.row)"></i>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
  export default {
    methods: {
      tableCellClassName({row, column, rowIndex, columnIndex}){
        if(columnIndex === row.rightIdx){
          return 'right-option'
        }
        return ''
      },
      editItem(row) {
        console.log(row);
      },
      addItem() {
      },
      deleteItem(row) {
        console.log(row);
      }
    },
    fetch(){
      let activeTab = this.$store.state.admin.activeTabName
      let param = new URLSearchParams();
      param.append('activeTabName',activeTab);
      param.append('currentPage',this.$store.state.exam.currentPage)
      param.append('pageSize',pageSize)
      let _self = this;
      this.$axios.post(this.$store.state.exam.pageUrl,param).then(res ->{
        let data = res.data
        _self.$store.commit('exam/getPageData',data)
      });
    },
    data() {
      return {
        tableData: [
        {
          question: '2016-05-03',
          rightIdx: 3,
          optiona: '王小虎',
          optionb: '上海',
          optionc: '普陀区',
          optiond: '上海市普陀区金沙江路 1518 弄'
        }
        ]
      }
    }
  }
</script>

<style>
.right-option{
  background: #f0f9eb;
}
</style>
