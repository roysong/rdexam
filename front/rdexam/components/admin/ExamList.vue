<template>
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
      deleteItem(row) {
        console.log(row);
      }
    },
    beforeCreate(){
      let activeTab = this.$store.state.admin.activeTabName;
      let param = new URLSearchParams();
      param.append('activeTabName',activeTab);
      param.append('currentPage',this.$store.state.exam.currentPage);
      param.append('pageSize',this.$store.state.exam.pageSize);
      this.$axios.post(this.$store.state.exam.pageUrl,param).then(res=>{
        let data = res.data
        this.$store.commit('exam/getPageData',{data:data,tab:activeTab})
      });
    },
    computed: {
      tableData: {
        get: function(){
          return this.$store.state.exam.examDatas
            .filter(i=>i.tab===this.$store.state.admin.activeTabName)
            .map(i=>i.data)[0]
        }
      }
    }
  }
</script>

<style>
.right-option{
  background: #f0f9eb;
}
</style>
