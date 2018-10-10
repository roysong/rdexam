<template>
  <el-table
    :data="tableData"
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
        <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
        <el-button type="text" size="small">编辑</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
  export default {
    methods: {
      handleClick(row) {
        console.log(row);
      }
    },

    data() {
      return {
        tableData: [
        {
          question: '2016-05-03',
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
